# Shopping Cart System

A Java terminal app for managing a shopping cart.

## Features
- Add/remove items from cart
- View cart contents with formatted display
- Checkout with confirmation


## Package Structure

### Standalone
| Class | Description |
|-------|-------------|
| `App` | Main entry point that handles menu navigation and command execution |

### model
| Class            | Description                                            |
|------------------|--------------------------------------------------------|
| `Product`        | Represents products with ID, name and price (in cents) |
| `ProductCatalog` | Contains the default list of products                  |

### service
| Class                 | Description                                                  |
|-----------------------|--------------------------------------------------------------|
| `ShoppingCartService` | Manages cart operations (add/remove items, calculate totals) |
| `ProductCatalogService` | Manages catalog operations (search products by ID/name)      |

### io
| Class | Description |
|-------|-------------|
| `ConsoleIO` | Handles all console input/output operations and display formatting |

### command
| Class | Description |
|-------|-------------|
| `Command` | Interface for all command implementations |
| `AddItemCommand` | Adds new products to the cart |
| `RemoveItemCommand` | Removes products/quantities from cart |
| `DisplayCartCommand` | Shows current cart contents |
| `CheckoutCommand` | Processes checkout with confirmation |


## How It Works
1. User (cashier) interacts through a numbered menu system
2. Each menu option executes a corresponding command
3. Commands modify the shopping cart state

## Testing

### ProductCatalogService Tests
- `findProductById()` - Valid/invalid ID searches
- `findProductByName()` - Case-insensitive name searches
- `getAllProducts()` - Catalog completeness verification
- `getPriceById()` - Price lookup validation

### ShoppingCartService Tests
- `addItem()` - Adding single/multiple items
- `removeItem()` - Removing items/quantities
- `getCart()` - Defensive copy verification
- `getQuantity()` - Quantity lookup
- `findProductByName()` - Product search
- `clearCart()` - Cart clearance
- `getSubTotal()` - Price calculations
- `isEmpty()` - Empty state check



