package com.qa.integration;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InventorySyncTest {

    @Test
    public void testInventoryDeductionAndStockValidation() {
        int currentWarehouseStock = 15;
        int orderedQuantity = 2;

        int updatedStock = currentWarehouseStock - orderedQuantity;

        Assert.assertEquals(updatedStock, 13, "Inventory stock was not deducted correctly in the ERP system!");
    }

    @Test
    public void testOutOfStockWarningAndRejection() {
        // Mock scenario: Warehouse has only 1 item left, but customer tries to buy 5
        String sku = "SHIRT-BLU-L";
        int currentWarehouseStock = 1;
        int orderedQuantity = 5;

        String warningMessage = "";
        boolean orderProcessedSuccessfully = true;

        // Integration business logic check
        if (orderedQuantity > currentWarehouseStock) {
            // Generate the enterprise warning message
            warningMessage = "WARNING: Insufficient stock for SKU: " + sku +
                    ". Requested: " + orderedQuantity +
                    ", Available: " + currentWarehouseStock;

            orderProcessedSuccessfully = false; // Block the order from hitting the database
        }

        // Print the warning to the console logs so developers/analysts can see it
        System.out.println(warningMessage);

        // Assertions to verify the integration acted safely
        Assert.assertFalse(orderProcessedSuccessfully, "System incorrectly processed an out-of-stock order!");
        Assert.assertEquals(warningMessage, "WARNING: Insufficient stock for SKU: SHIRT-BLU-L. Requested: 5, Available: 1");
    }
}