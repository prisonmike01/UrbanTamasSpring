package com.UrbanTamas.demo.session;

import static org.junit.jupiter.api.Assertions.*;

import com.UrbanTamas.demo.data.dto.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

@DisplayName("CartSession Tests")
class CartSessionTest {

    private CartSession cartSession;

    @BeforeEach
    void setUp() {
        cartSession = new CartSession();
    }

    @Test
    @DisplayName("Should initialize with empty items list")
    void testInitialState() {
        List<CartItem> items = cartSession.getItems();

        assertNotNull(items, "Items list should not be null");
        assertTrue(items.isEmpty(), "Items list should be empty initially");
    }

    @Test
    @DisplayName("Should add item to cart")
    void testAddItem() {
        CartItem item = new CartItem(); // Adjust based on your CartItem constructor

        cartSession.addItem(item);

        assertEquals(1, cartSession.getItems().size(), "Cart should contain 1 item");
        assertTrue(cartSession.getItems().contains(item), "Cart should contain the added item");
    }

    @Test
    @DisplayName("Should add multiple items to cart")
    void testAddMultipleItems() {
        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();
        CartItem item3 = new CartItem();

        cartSession.addItem(item1);
        cartSession.addItem(item2);
        cartSession.addItem(item3);

        assertEquals(3, cartSession.getItems().size(), "Cart should contain 3 items");
    }

    @Test
    @DisplayName("Should clear all items from cart")
    void testClear() {
        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();

        cartSession.addItem(item1);
        cartSession.addItem(item2);

        assertEquals(2, cartSession.getItems().size(), "Cart should contain 2 items before clear");

        cartSession.clear();

        assertTrue(cartSession.getItems().isEmpty(), "Cart should be empty after clear");
    }

    @Test
    @DisplayName("Should clear empty cart without errors")
    void testClearEmptyCart() {
        assertDoesNotThrow(() -> cartSession.clear(), "Clearing empty cart should not throw exception");
        assertTrue(cartSession.getItems().isEmpty(), "Cart should remain empty");
    }

    @Test
    @DisplayName("Should return same list instance")
    void testGetItemsReturnsSameInstance() {
        List<CartItem> items1 = cartSession.getItems();
        List<CartItem> items2 = cartSession.getItems();

        assertSame(items1, items2, "getItems() should return the same list instance");
    }

    @Test
    @DisplayName("Should handle null item addition")
    void testAddNullItem() {
        // This tests current behavior - adjust based on your requirements
        assertDoesNotThrow(() -> cartSession.addItem(null), "Adding null should not throw exception");
        assertEquals(1, cartSession.getItems().size(), "Cart should contain 1 item (null)");
    }
}