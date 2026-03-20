Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

Write-Host "Configuring this repo to use .githooks/ as Git hooks path..."
git config core.hooksPath ".githooks"

Write-Host "Done."
Write-Host "Tip: If commits still bypass hooks, ensure you are committing from this repo and not a different folder."

