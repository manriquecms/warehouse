# Assignment: Warehouse

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

## First functionality to develop
Considering an API Rest solution
- Get Product: GET - list all products available, in other words, buildable according the current Inventory
- Sell Product: 
  - POST Updating Inventory/ 
  - ~~DELETE~~(Not removing product from catalog) 

## Considerations for production environment
### Persistence abstraction
- In memory (MVP)
- Allow future implementations

### Presentation
- API Rest
- Allow other software interactions (SOAP, etc.)

### Monitoring
- Consider monitoring metrics: Actuator

### Logging
- Stdout
- File
- Message queues

### Container
- MVP



