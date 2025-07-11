Write-Host "Creando port-forward del servicio Kubernetes..." -ForegroundColor Green
Write-Host "Puerto local: 9090" -ForegroundColor Yellow
Write-Host "Puerto del servicio: 8087" -ForegroundColor Yellow
Write-Host "Namespace: cristopher" -ForegroundColor Yellow
Write-Host "Endpoint: http://localhost:9090/v1/api/student" -ForegroundColor Cyan
Write-Host ""
Write-Host "Para detener el port-forward, presiona Ctrl+C" -ForegroundColor Red
Write-Host ""

# Iniciar el port-forward
kubectl port-forward svc/cristopher-service 9090:8087 -n cristopher