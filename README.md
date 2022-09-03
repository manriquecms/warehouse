# Assignment: Warehouse

## Quick guide
Compile project
- `mvn clean compile`

Run tests (Unit & Integration)
- `mvn test`

Build artifact (Jar & Docker image)
- `mvn package`

Run locally
- Docker `docker run -d --rm -p8080:8080 --name warehouse warehouse:0.0.1-SNAPSHOT` 
- JVM `java -jar target/warehouse-0.0.1-SNAPSHOT.jar`

Test API (Swagger)
- Docker [only César MAC...](http://192.168.99.100:8080/swagger-ui/)
  - docker-machine env default --> Read DOCKER_HOST
  - i.e: http://192.168.99.100:8080/swagger-ui/
- JVM [Swagger](http://localhost:8080/swagger-ui/)

Debug manually container
- Docker `docker run -it --name warehouse --entrypoint bash warehouse:0.0.1-SNAPSHOT`

## Concepts
We're managing a Warehouse composed of:
- Set of 'Articles' (called "Inventory")
  - id
  - name
  - stock
- List of 'Products' "built" with different 'Articles' (We'll call it: "Catalog")
  - name
  - price
  - articles<id, quantity>

## Model
### MVP
Article (id*, name, stock)

Product (name*, price)

ProductArticle (product_name*, article_id*, amount)

### Orders functionality
Order (@id*, @create_date, fee)

OrderProduct (order_id*, product_id*, amount)

## First functionality to develop
Considering an API Rest solution
- Get Product: GET - list all products available, in other words, buildable according the current Inventory
- Sell Product: 
  - POST Updating Inventory/ 
  - ~~DELETE~~(Not removing product from catalog) 

## Considerations for production environment
### Architecture
- CQRS + Event Driven? Interesting for Inventory & Catalog management (TODO)

### Persistence abstraction
- In memory (MVP)
- Allow future implementations (Distr. cache, DDBB, etc)

### Presentation
- API Rest
- Allow other software interactions (SOAP, etc.) By architecture (New Port over infrastructure package)

### Monitoring
- Consider monitoring metrics: Actuator

### Logging
- Stdout
- Other options: (TODO)
  - File
  - Message queues

### Security
- TLS: Certificate to expose on 8443 (TODO)

### Container
- Docker image built (integrate with package)



