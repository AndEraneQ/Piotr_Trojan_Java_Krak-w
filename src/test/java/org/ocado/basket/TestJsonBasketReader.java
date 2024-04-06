package org.ocado.basket;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class TestJsonBasketReader {
    @Test
    public void testReadListOfProduct() {
        // Arrange
        JsonBasketReader jsonBasketReader = new JsonBasketReader();
        String fileName = "src/test/java/org/ocado/basket/jsonFilesToTest/readListOfProductTest.json";
        // Act
        List<String> productsList = jsonBasketReader.readListOfProduct(fileName);
        // Assert
        assertEquals(6, productsList.size());
        assertEquals("Cocoa Butter", productsList.get(0));
        assertEquals("Cookies - Englishbay Wht", productsList.get(5));
    }
    @Test
    public void testReadDeliveryWaysForProducts() {
        // Preparation
        JsonBasketReader jsonBasketReader = new JsonBasketReader();
        List<String> productsList = Arrays.asList("Cocoa Butter", "Cookies - Englishbay Wht");
        String absolutePathToConfigFile = "src/test/java/org/ocado/basket/jsonFilesToTest/readDeliveryWaysForProductsTest.json";
        // Execution
        Map<String, Set<String>> productsAndDeliveryWays = jsonBasketReader.readDeliveryWaysForProducts(productsList, absolutePathToConfigFile);
        // Verification
        assertEquals(2, productsAndDeliveryWays.size());
        assertTrue(productsAndDeliveryWays.containsKey("Cocoa Butter"));
        assertTrue(productsAndDeliveryWays.containsKey("Cookies - Englishbay Wht"));
        Set<String> deliveryWaysForCocoaButter = productsAndDeliveryWays.get("Cocoa Butter");
        assertEquals(3, deliveryWaysForCocoaButter.size());
        assertTrue(deliveryWaysForCocoaButter.contains("Courier"));
        assertTrue(deliveryWaysForCocoaButter.contains("Parcel locker"));
        assertTrue(deliveryWaysForCocoaButter.contains("Same day delivery"));
        assertFalse(deliveryWaysForCocoaButter.contains("Next day shipping"));
    }
}
