# Prompt for AWS Access Key ID
$awsAccessKeyId = Read-Host "Enter AWS Access Key ID"

# Prompt for AWS Secret Access Key
$awsSecretAccessKey = Read-Host "Enter AWS Secret Access Key"

# Prompt for AWS Default Region
$awsDefaultRegion = Read-Host "Enter AWS Default Region (e.g., us-east-1)"

# Set AWS environment variables
$env:AWS_ACCESS_KEY_ID = $awsAccessKeyId
$env:AWS_SECRET_ACCESS_KEY = $awsSecretAccessKey
$env:AWS_DEFAULT_REGION = $awsDefaultRegion

# Display a confirmation message
Write-Host "AWS credentials set successfully."

