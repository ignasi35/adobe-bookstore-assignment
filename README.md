# Online BookStore

# Pre-requisites

1. this documentation uses [httpie](https://httpie.io/docs#examples) as an HTTP client

# Start the service

Open a terminal and change directories into the project. Run the commands:

```shell
mvn spring-boot:run
```

# Getting orders

1. Retrieve all the existing orders using the `GET /orders` endpoint. Using a different `Accept:` header on the request will 
   return the data in either JSON (using `application/json`) or CSV (using `text/csv`):
   
```shell
http localhost:8080/orders # defaults to returning content in JSON
http localhost:8080/orders Accept:application/json
http localhost:8080/orders Accept:text/csv
```

If you've followed the documentation from top to bottom, the commands above will have returned empty lists.


## Contact Details

This was implemented by @ignasi35 as a part of the [Online BookStore Test](https://github.com/magento-mcom/springboot-interview-test).
