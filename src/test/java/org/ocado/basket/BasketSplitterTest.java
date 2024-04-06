package org.ocado.basket;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketSplitterTest {

    @Test
    public void testSplitWithMultipleProductsAndDeliveryWays() {
        // Arrange
        BasketSplitter basketSplitter = new BasketSplitter("src/test/java/org/ocado/basket/jsonFilesToTest/splitTest.json");
        List<String> items = Arrays.asList("Cocoa Butter", "English Muffin", "Oatmeal Cookies", "Orange Juice", "Granola Bars", "Chocolate Chips");

        // Act
        Map<String, List<String>> deliveryWayAndProductsDeliveredByIt = basketSplitter.split(items);

        // Assert
        assertEquals(2, deliveryWayAndProductsDeliveredByIt.size());
        assertTrue(deliveryWayAndProductsDeliveredByIt.containsKey("Parcel locker"));
        assertTrue(deliveryWayAndProductsDeliveredByIt.containsKey("Mailbox delivery"));

        List<String> productsForParcelLocker = deliveryWayAndProductsDeliveredByIt.get("Parcel locker");
        List<String> productsForMailboxDelivery = deliveryWayAndProductsDeliveredByIt.get("Mailbox delivery");

        assertEquals(4, productsForParcelLocker.size());
        assertEquals(2, productsForMailboxDelivery.size());

        assertTrue(productsForParcelLocker.contains("Cocoa Butter"));
        assertTrue(productsForParcelLocker.contains("English Muffin"));
        assertTrue(productsForParcelLocker.contains("Orange Juice"));
        assertTrue(productsForParcelLocker.contains("Chocolate Chips"));

        assertTrue(productsForMailboxDelivery.contains("Oatmeal Cookies"));
        assertTrue(productsForMailboxDelivery.contains("Granola Bars"));
    }
    @Test
    void testSplitWithEmptyList() {
        // Arrange
        BasketSplitter splitter = new BasketSplitter("src/test/java/org/ocado/basket/jsonFilesToTest/splitTest.json");
        List<String> emptyList = new ArrayList<>();
        // Act
        Map<String, List<String>> result = splitter.split(emptyList);
        // Assert
        assertTrue(result.isEmpty());
    }
}
