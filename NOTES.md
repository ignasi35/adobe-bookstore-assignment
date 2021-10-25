# NOTES to Self

## TODO

1. `OrderController`
    1. getOrders: return JSON or CSV 
    1. placeOrder: validate order with Inventory and only place it if stock is available
1. `OrderRepo`
1. `InventoryRepo` // mocks a remote service


The inventory Repo in the Order service will probably have to keep a stale copy of the actual inventory, so that when the downstream service (inventory) is offline, we can still have a rough idea of the actual inventory by checking the local (stale) copy of the inventory.

### Retrieve Orders

1. Controller with `GET /api/v1/orders` to list existing orders
1. `GET /api/v1/orders` has a param to provide data in either JSON or CSV format. Hmmm, CSV format could use a line per book on each order or maybe put all books or the order on a single field.

### Create a new Order

1. Controller with `POST /api/v1/orders`
1. Order format: `{ "items": [{ "book-id": "",  "quantity": "" }, ...] }`
1. `OrderREpository` VS `InventoryRepository` --> only place an order if there's enough stock to fulfill the complete order
1. (NFR) Pre-load the inventory from the [provided JSON file](https://github.com/magento-mcom/springboot-interview-test/blob/main/stock.json)
1. When placing an order, return a `201` status code with the `Unique Order Identifier`
1. when processing the order, update stock accordingly
    1. updating stock in the inventory should not be a blocker for the user (must happen async)
    1. There's ambiguity on the statement `If the process of updating stock fails, should not cause an error in order processing.`. It sounds like inventory is a separate service (so it's not possible to update the inventory transactionally?)



## NFR

resilience

circuit breaking

exponential back-off

concurrent access

ledgers of changes

projections(?)

local copies of data in case the downstream service is down.

etc...


