# Inventory Management System

A Java console application for managing product inventory with configurable storage options.

## Features
- Complete CRUD operations for products
- Support for perishable and regular product types
- Configurable storage (CSV file or in-memory)
- Sorted inventory display by product ID
- Comprehensive input validation and error handling
- Spring Boot dependency injection and configuration

## Package Structure

### config
| Class | Description |
|-------|-------------|
| `InventoryConfig` | Spring configuration class that creates appropriate repository based on application properties |

### model
| Class | Description |
|-------|-------------|
| `Product` | Abstract base class for all products with common properties |
| `PerishableProduct` | Products requiring storage temperature (extends Product) |
| `RegularProduct` | Standard products with unit of measure (extends Product) |
| `ProductType` | Enum defining PERISHABLE and REGULAR product types |

### repository
| Class | Description |
|-------|-------------|
| `InventoryRepository` | Interface defining CRUD operations for product storage |
| `CsvInventoryRepository` | File-based storage implementation using CSV format |
| `SampleInventoryRepository` | In-memory storage with sample data for testing |

### service
| Class | Description |
|-------|-------------|
| `InventoryService` | Business logic layer handling product operations and validation |

### view
| Class | Description |
|-------|-------------|
| `Inventory` | Main controller managing user interaction and menu navigation |
| `InventoryIO` | Handles console input/output operations and display formatting |

### standalone
| Class | Description |
|-------|-------------|
| `InventoryManagerApplication` | Spring Boot main class and application entry point |

## How It Works
1. Spring Boot configures repository type based on application.properties
2. User interacts through numbered menu system
3. Controller delegates to service layer for business logic
4. Service layer uses repository for data persistence
5. All changes automatically persist to configured storage

## Technology Stack
- Java 17+
- Spring Boot 3.x
- Maven (build management)
- JUnit 5 (testing)

## Configuration
Application properties:
```properties
# Use CSV storage (persistent)
inventory.repository.type=csv
inventory.csv.filepath=data/inventory.csv

# OR use memory storage (temporary)
inventory.repository.type=memory
```


## Testing

### CsvInventoryRepositoryTests
- File loading and saving operations
- CRUD operations with CSV persistence
- Error handling for invalid data

### SampleInventoryRepositoryTests
- In-memory CRUD operations
- Sample data initialization
- HashMap storage validation

### InventoryServiceTests
- Business logic validation
- Add/update product workflows
- Null input handling