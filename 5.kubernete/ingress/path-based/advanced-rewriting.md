## Advanced rewriting 

```yaml 
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: my-app-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /api/v1/item/$1
spec:
  rules:
  - host: myapp.com
    http:
      paths:
      - path: /products(/.*)? # Matches /products and /products/
        pathType: ImplementationSpecific
        backend:
          service:
            name: product-service
            port:
              number: 80

```
/(group1)/group2/group3
- (/.*)? -> first group that we will capture 
- When you sent to the request to /products/123 it will be /api/v1/item/15