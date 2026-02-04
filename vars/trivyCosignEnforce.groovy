// def call(Map config) {
//   withCredentials([
//     string(credentialsId: 'COSIGN_KEY_B64', variable: 'COSIGN_KEY_B64')
//   ]) {
//     sh """
//       set -e
//       export COSIGN_EXPERIMENTAL=1

//       echo "🔐 Reconstructing cosign private key"
//       echo "\$COSIGN_KEY_B64" | base64 -d > cosign.key
//       chmod 600 cosign.key

//       echo "🔍 Generating SBOM using Trivy"
//       trivy image \
//         --format cyclonedx \
//         --output sbom.cdx.json \
//         ${config.image}

//       echo "🧾 Attesting SBOM"
//       cosign attest \
//         --key cosign.key \
//         --predicate sbom.cdx.json \
//         --type cyclonedx \
//         ${config.image}

//       echo "✍️ Signing image"
//       cosign sign \
//         --key cosign.key \
//         ${config.image}
        
//       echo "🧹 Cleanup"
//       rm -f cosign.key sbom.cdx.json
//     """
//   }
// }
def call(Map config) {
    withCredentials([
        file(credentialsId: 'COSIGN_PRIVATE_KEY', variable: 'COSIGN_KEY_FILE')
    ]) {
        sh """
            set -e
            export COSIGN_EXPERIMENTAL=1

            echo "🔐 Using cosign private key"
            cp "\$COSIGN_KEY_FILE" cosign.key
            chmod 600 cosign.key
            
            echo "🔍 Getting image digest..."
            DIGEST=\$(docker inspect ${config.image} --format='{{index .RepoDigests 0}}' | cut -d'@' -f2)
            IMAGE_WITH_DIGEST="${config.image%@*}@\$DIGEST"
            
            echo "🔍 Generating SBOM using Trivy for image: \$IMAGE_WITH_DIGEST"
            trivy image \\
                --format cyclonedx \\
                --output sbom.cdx.json \\
                \$IMAGE_WITH_DIGEST

            echo "🧾 Attesting SBOM"
            echo "" | cosign attest \\
                --key cosign.key \\
                --predicate sbom.cdx.json \\
                --type cyclonedx \\
                \$IMAGE_WITH_DIGEST

            echo "✍️ Signing image"
            echo "" | cosign sign \\
                --key cosign.key \\
                \$IMAGE_WITH_DIGEST
                
            echo "🧹 Cleanup"
            rm -f cosign.key sbom.cdx.json
        """
    }
}