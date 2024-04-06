package org.ocado.basket;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestSplitterActions {

    @Test
    void testTakeSetOfProductPossibleToDeliverByOneWay() {
        // Arrange
        SplitterActions splitterActions = new SplitterActions();
        Map<String, Set<String>> namesOfProductsAndDeliveryWays = new HashMap<>();
        Set<String> deliveryWays1 = new HashSet<>(Arrays.asList("Courier", "Parcel locker"));
        Set<String> deliveryWays2 = new HashSet<>(Arrays.asList("Mailbox delivery", "Courier"));
        Set<String> deliveryWays3 = new HashSet<>(Arrays.asList("Mailbox delivery", "Parcel locker"));
        namesOfProductsAndDeliveryWays.put("Product1", deliveryWays1);
        namesOfProductsAndDeliveryWays.put("Product2", deliveryWays2);
        namesOfProductsAndDeliveryWays.put("Product3", deliveryWays3);
        String deliveryWay = "Courier";
        // Act
        Set<String> result = splitterActions.takeSetOfProductPossibleToDeliverByOneWay(namesOfProductsAndDeliveryWays, deliveryWay);
        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains("Product1"));
        assertTrue(result.contains("Product2"));
        assertFalse(result.contains("Product3"));
    }

    @Test
    void testTakeQuantityOfDeliveryWays() {
        // Arrange
        SplitterActions splitterActions = new SplitterActions();
        Map<String, Set<String>> namesOfProductsAndDeliveryWays = new HashMap<>();
        namesOfProductsAndDeliveryWays.put("Product1", Set.of("Courier", "Parcel locker"));
        namesOfProductsAndDeliveryWays.put("Product2", Set.of("Mailbox delivery", "Courier", "Parcel locker"));
        namesOfProductsAndDeliveryWays.put("Product3", Set.of("Mailbox delivery", "Express Collection"));

        // Act
        Map<String, Integer> result = splitterActions.takeQuantityOfDeliveryWays(namesOfProductsAndDeliveryWays);

        // Assert
        assertEquals(4, result.size());
        assertEquals(2, result.get("Courier"));
        assertEquals(2, result.get("Parcel locker"));
        assertEquals(2, result.get("Mailbox delivery"));
        assertEquals(1, result.get("Express Collection"));
    }

    @Test
    void testTakeMostRepeatedDeliveryWay() {
        // Arrange
        SplitterActions splitterActions = new SplitterActions();
        Map<String, Integer> quantityOfDeliveryWays = new HashMap<>();
        quantityOfDeliveryWays.put("Courier", 5);
        quantityOfDeliveryWays.put("Parcel locker", 3);
        quantityOfDeliveryWays.put("Mailbox delivery", 2);

        // Act
        String result = splitterActions.takeMostRepeatedDeliveryWay(quantityOfDeliveryWays);

        // Assert
        assertEquals("Courier", result);
    }
    @Test
    void testRemoveProductsBySet() {
        // Arrange
        SplitterActions splitterActions = new SplitterActions();
        Map<String, Set<String>> namesOfProductsAndDeliveryWays = new HashMap<>();
        Set<String> deliveryWays1 = Set.of("Courier", "Parcel locker");
        Set<String> deliveryWays2 = Set.of("Mailbox delivery", "Courier");
        Set<String> deliveryWays3 = Set.of("Mailbox delivery", "Parcel locker");
        namesOfProductsAndDeliveryWays.put("Product1", deliveryWays1);
        namesOfProductsAndDeliveryWays.put("Product2", deliveryWays2);
        namesOfProductsAndDeliveryWays.put("Product3", deliveryWays3);
        Set<String> productsToRemove = Set.of("Product1", "Product2");

        // Act
        Map<String, Set<String>> result = splitterActions.removeProductsBySet(namesOfProductsAndDeliveryWays, productsToRemove);

        // Assert
        assertEquals(1, result.size());
        assertFalse(result.containsKey("Product1"));
        assertFalse(result.containsKey("Product2"));
        assertTrue(result.containsKey("Product3"));
    }
}
