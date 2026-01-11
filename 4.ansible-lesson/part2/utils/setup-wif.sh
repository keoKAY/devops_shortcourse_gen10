#!/usr/bin/env bash
set -euo pipefail

########################################
# CONFIG — CHANGE THESE VALUES
########################################
PROJECT_ID="project-2196034a-008b-495b-b87"
PROJECT_NUMBER="$(gcloud projects describe "$PROJECT_ID" --format='value(projectNumber)')"

SERVICE_ACCOUNT_NAME="ansible-sa"
SERVICE_ACCOUNT_EMAIL="${SERVICE_ACCOUNT_NAME}@${PROJECT_ID}.iam.gserviceaccount.com"

POOL_NAME="ansible-pool"
PROVIDER_NAME="ansible-provider"

WIF_CONFIG_FILE="$PWD/ansible-wif.json"

########################################
# 1. Set active project
########################################
gcloud config set project "$PROJECT_ID"

########################################
# 2. Create Service Account (NO KEY)
########################################
echo "Creating service account..."
gcloud iam service-accounts create "$SERVICE_ACCOUNT_NAME" \
  --display-name="Ansible Automation Service Account" || true

########################################
# 3. Grant permissions to Service Account
########################################
echo "Granting Compute permissions..."
gcloud projects add-iam-policy-binding "$PROJECT_ID" \
  --member="serviceAccount:${SERVICE_ACCOUNT_EMAIL}" \
  --role="roles/compute.instanceAdmin.v1"

gcloud projects add-iam-policy-binding "$PROJECT_ID" \
  --member="serviceAccount:${SERVICE_ACCOUNT_EMAIL}" \
  --role="roles/iam.serviceAccountUser"

########################################
# 4. Create Workload Identity Pool
########################################
echo "Creating Workload Identity Pool..."
gcloud iam workload-identity-pools create "$POOL_NAME" \
  --location="global" \
  --display-name="Ansible Local Pool" || true

########################################
# 5. Create OIDC Provider
########################################
echo "Creating OIDC Provider..."
gcloud iam workload-identity-pools providers create-oidc "$PROVIDER_NAME" \
  --location="global" \
  --workload-identity-pool="$POOL_NAME" \
  --issuer-uri="https://accounts.google.com" || true

########################################
# 6. Allow Pool to Impersonate Service Account
########################################
echo "Binding Workload Identity User role..."
gcloud iam service-accounts add-iam-policy-binding \
  "$SERVICE_ACCOUNT_EMAIL" \
  --role="roles/iam.workloadIdentityUser" \
  --member="principalSet://iam.googleapis.com/projects/${PROJECT_NUMBER}/locations/global/workloadIdentityPools/${POOL_NAME}/*"

########################################
# 7. Create Credential Config (WIF JSON)
########################################
echo "Generating WIF credential config..."
gcloud iam workload-identity-pools create-cred-config \
  "projects/${PROJECT_NUMBER}/locations/global/workloadIdentityPools/${POOL_NAME}/providers/${PROVIDER_NAME}" \
  --service-account="${SERVICE_ACCOUNT_EMAIL}" \
  --output-file="${WIF_CONFIG_FILE}" \
  --executable-command="gcloud auth print-access-token"

########################################
# 8. Export Environment Variable
########################################
echo "Exporting GOOGLE_APPLICATION_CREDENTIALS..."
export GOOGLE_APPLICATION_CREDENTIALS="${WIF_CONFIG_FILE}"

########################################
# DONE
########################################
echo "✅ Setup complete"
echo "WIF config file: ${WIF_CONFIG_FILE}"
echo "Run: export GOOGLE_APPLICATION_CREDENTIALS=${WIF_CONFIG_FILE}"
