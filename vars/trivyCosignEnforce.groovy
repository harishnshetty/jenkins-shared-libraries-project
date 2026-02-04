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
def call() {
    withCredentials([
        file(credentialsId: 'COSIGN_PRIVATE_KEY', variable: 'COSIGN_KEY_FILE'),
        string(credentialsId: 'COSIGN_PASSWORD', variable: 'COSIGN_PASSWORD')
    ]) {
        sh """
            set -e
            export COSIGN_EXPERIMENTAL=1

            if [ -z "${env.IMAGE_DIGEST}" ]; then
                echo "❌ IMAGE_DIGEST not found. Did dockerPush.groovy run?"
                exit 1
            fi

            echo "🔐 Using image digest: ${env.IMAGE_DIGEST}"

            echo "🔐 Setting up cosign..."
            cp "\$COSIGN_KEY_FILE" cosign.key
            chmod 600 cosign.key

            if head -1 cosign.key | grep -q "ENCRYPTED"; then
                echo "🔐 Key is encrypted"
                NEED_PASSWORD=true
            else
                echo "🔓 Key is not encrypted"
                NEED_PASSWORD=false
            fi

            echo "📦 Generating SBOM (CycloneDX)..."
            trivy image \
              --format cyclonedx \
              --output sbom.cdx.json \
              ${env.IMAGE_DIGEST}

            echo "🛡️ Running vulnerability scan..."
            trivy image \
              --scanners vuln \
              --severity HIGH,CRITICAL \
              --ignore-unfixed \
              --format json \
              --output vuln-report.json \
              ${env.IMAGE_DIGEST}

            echo "🧾 Attesting SBOM..."
            if [ "\$NEED_PASSWORD" = "true" ]; then
                echo "\$COSIGN_PASSWORD" | cosign attest \
                    --key cosign.key \
                    --predicate sbom.cdx.json \
                    --type cyclonedx \
                    ${env.IMAGE_DIGEST}
            else
                cosign attest \
                    --key cosign.key \
                    --predicate sbom.cdx.json \
                    --type cyclonedx \
                    ${env.IMAGE_DIGEST}
            fi

            echo "✍️ Signing image..."
            if [ "\$NEED_PASSWORD" = "true" ]; then
                echo "\$COSIGN_PASSWORD" | cosign sign \
                    --key cosign.key \
                    ${env.IMAGE_DIGEST}
            else
                cosign sign \
                    --key cosign.key \
                    ${env.IMAGE_DIGEST}
            fi

            rm -f cosign.key sbom.cdx.json vuln-report.json
            echo "✅ Image signed & SBOM attested using digest"
        """
    }
}
