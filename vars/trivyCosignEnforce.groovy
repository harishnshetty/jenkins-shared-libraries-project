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
        string(credentialsId: 'COSIGN_KEY_B64', variable: 'COSIGN_KEY_B64')
    ]) {
        sh """
            set -e
            export COSIGN_EXPERIMENTAL=1

            echo "🔐 Debug: Checking COSIGN_KEY_B64"
            echo "Length of base64 string: \${#COSIGN_KEY_B64}"
            
            # Test base64 decoding first
            echo "\$COSIGN_KEY_B64" | base64 -d > /dev/null && echo "✅ Base64 is valid" || echo "❌ Base64 is invalid"
            
            echo "🔐 Reconstructing cosign private key"
            echo "\$COSIGN_KEY_B64" | base64 -d > cosign.key
            chmod 600 cosign.key
            
            # Test if the key file looks valid
            head -1 cosign.key
            
            echo "🔍 Generating SBOM using Trivy for image: ${config.image}"
            trivy image \
                --format cyclonedx \
                --output sbom.cdx.json \
                ${config.image}

            echo "🧾 Attesting SBOM"
            cosign attest \
                --key cosign.key \
                --predicate sbom.cdx.json \
                --type cyclonedx \
                ${config.image}

            echo "✍️ Signing image"
            cosign sign \
                --key cosign.key \
                ${config.image}
                
            echo "🧹 Cleanup"
            rm -f cosign.key sbom.cdx.json
        """
    }
}