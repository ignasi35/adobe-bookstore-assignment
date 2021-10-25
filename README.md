# Online BookStore

# Pre-requisites

1. This documentation assumes you have [httpie](https://httpie.io/docs#examples) installed


# Getting orders

1. It is possible to retrieve all the existing orders using the `GET /orders` endpoint. Using a different `Accept:` header on the request will 
   return the data in either JSON (using `application/json`) or CSV (using `text/csv`):
   
```
#  with "mvn spring-boot:run" running on a separate terminal 
http localhost:8080/orders # defaults to returning content in JSON
http localhost:8080/orders Accept:application/json
http localhost:8080/orders Accept:text/csv
```



## Contact Details

This was implemented by @ignasi35 as a part of the [Online BookStore Test](https://github.com/magento-mcom/springboot-interview-test).
