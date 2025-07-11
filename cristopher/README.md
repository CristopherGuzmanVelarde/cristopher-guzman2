minikube start
kubectl apply -f cristopher-namespace.yml
kubectl apply -f cristopher-deployment.yml
kubectl apply -f cristopher-service.yml
kubectl get pods -n cristopher-namespace
kubectl port-forward service/cristopher-service 9090:8088 -n cristopher-namespace

kubectl delete namespace cristopher-namespace