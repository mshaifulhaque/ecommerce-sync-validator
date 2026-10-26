package com.qa.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderDataMappingTest {

    @Test
    public void testOrderPayloadDataMapping() throws Exception {
        // Mock JSON payload coming from a DTC Checkout event (e.g., Shopify / NewStore webhook payload)
        String incomingJsonPayload = "{\n" +
                "  \"orderId\": \"ORD-998822\",\n" +
                "  \"customerEmail\": \"shopper@example.com\",\n" +
                "  \"totalAmount\": 149.99,\n" +
                "  \"itemSku\": \"SHIRT-BLU-L\",\n" +
                "  \"quantity\": 2,\n" +
                "  \"fulfillmentStatus\": \"UNFULFILLED\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(incomingJsonPayload);

        // 1. Validate Data Transformation & Field Mapping Rules
        String orderId = jsonNode.get("orderId").asText();
        double totalAmount = jsonNode.get("totalAmount").asDouble();
        int quantity = jsonNode.get("quantity").asInt();
        String sku = jsonNode.get("itemSku").asText();

        // 2. Assertions to ensure data integrity before ERP ingestion
        Assert.assertEquals(orderId, "ORD-998822", "Order ID mapping mismatch!");
        Assert.assertTrue(totalAmount > 0, "Total amount must be greater than zero!");
        Assert.assertEquals(quantity, 2, "Item quantity mapping is incorrect!");
        Assert.assertEquals(sku, "SHIRT-BLU-L", "SKU translation failed between DTC and ERP mapping!");

        System.out.println("Integration Data Mapping Validation Passed Successfully for Order: " + orderId);
    }
}