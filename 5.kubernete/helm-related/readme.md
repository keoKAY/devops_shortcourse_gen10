## NOTE related to helm 

```bash
helm create nginx-chart 
helm lint nginx-chart 
# to preview the  full configuratino based on your values 
helm template nginx-chart --values=nginx-chart/stag-values.yaml 
helm install nginx-release nginx-chart 

# edit the values.yaml and upgrade it 
helm upgrade nginx-release nginx-chart 
# if the release doens't exist, it will install the release 
helm upgrade nginx-release nginx-chart --install 
helm uninstall nginx-release

helm status nginx-release 
helm rollback nginx-release <revision-number>


```
- ### Working with Nexus Helm Repository on Nexus  
```bash
helm package nginx-chart 
helm install nginx-release nginx-chart-0.1.0.tgz  

# upload a zip , compressed file into helm repository 
# to upload your chart to nexus using the nexus api 
curl -u admin:<your-password> https://nexus-oss.anajak-khmer.site/repository/helm-hosted/ \
    --upload-file nginx-chart-0.1.0.tgz

helm repo add nexus-selfhosted https://nexus-oss.anajak-khmer.site/repository/helm-hosted --username admin --password <your-password>

helm repo list 
# to install a new release from your repository 
helm install nexus-selfhosted nexus-selfhosted/nginx-chart

```