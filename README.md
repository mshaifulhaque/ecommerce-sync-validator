# E-Commerce to ERP Data Mapping & Integration Validator

A lightweight Java-based integration testing framework designed to validate data transformation, payload mapping, and data integrity between e-commerce checkout channels (e.g., Shopify) and downstream ERP systems / inventory databases.

## 🎯 Project Objective
In modern retail and direct-to-consumer (DTC) operations, system synchronization failures can lead to unfulfilled orders, mismatched SKUs, and inventory discrepancies. This project automates the validation of incoming webhook payloads to ensure data structures map correctly *before* ingestion into internal databases.

## 🛠️ Technical Stack
* **Language:** Java 17 (Eclipse Temurin)
* **Build Tool:** Apache Maven
* **Test Orchestration:** TestNG
* **JSON Processing:** Jackson (`jackson-databind`)
* **IDE:** IntelliJ IDEA

## 📂 Project Structure
ecommerce-sync-validator/
├── src/
│   └── test/
│       └── java/
│           └── com/qa/integration/
│               └── OrderDataMappingTest.java  # Core integration validation test
├── pom.xml                                    # Project dependencies & configuration
└── README.md

## 🔍 Key Test Scenarios Covered (`OrderDataMappingTest`)
1. **Payload Parsing:** Simulates incoming JSON webhooks from e-commerce checkout events.
2. **Field Mapping & Transformation:** Extracts and validates core identifiers (`orderId`, `itemSku`, `quantity`, `totalAmount`) to ensure correct alignment between e-commerce keys and ERP data models.
3. **Data Integrity Assertions:**
    * Verifies non-null/matching order IDs using TestNG assertions.
    * Validates positive pricing constraints (`totalAmount > 0`) to prevent financial discrepancies.
    * Ensures item quantity and SKU integrity prior to warehouse database routing.

## 🚀 How to Run the Tests
1. Clone the repository or open the project folder in **IntelliJ IDEA**.
2. Ensure Maven has resolved dependencies (or trigger a reload via the Maven tool window).
3. Open `OrderDataMappingTest.java`.
4. Click the green play icon (`▶`) next to the test method and select **Run 'OrderDataMappingTest'**.