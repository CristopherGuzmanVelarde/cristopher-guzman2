[# cristopher-guzman2]
https://github.com/CristopherGuzmanVelarde/cristopher-guzman2.git
ls
cd cristopher-guzman2
cd cristopher
cd manifest
kubectl apply -f cristopher-namespace.yml
kubectl apply -f cristopher-deployment.yml
kubectl apply -f cristopher-service.yml
kubectl get svc -n cristopher-namespace
trafic ports
puerto 30088

--kubectl get deployment cristopher-deployment -n cristopher-namespace
kubectl get pods -n cristopher-namespace
kubectl logs cristopher-deployment-68fb787f7d-24c2w -n cristopher-namespace
kubectl port-forward service/cristopher-service 9090:8088 -n cristopher-namespace

nueva pestaña -http://localhost:9090/v1/api/student)
