package com.shoppingcartapp.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcartapp.dao.CartRepository;
import com.shoppingcartapp.exception.ResourceNotFoundException;
import com.shoppingcartapp.model.Cart;
import com.shoppingcartapp.model.CartItem;

@Service
public class CartService {
	 @Autowired

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Create a new cart
    public Cart createCart(Cart cart) {
        cart.setTotalPrice(0.0); // Ensure the total price is initialized
        return cartRepository.save(cart);
    }

    // Get a cart by ID
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));
    }

    // Add an item to a cart
    public Cart addItemToCart(Long cartId, CartItem cartItem) {
        Cart cart = getCartById(cartId); // Fetch the cart by ID

        // Associate the item with the cart
        cartItem.setCart(cart);

        // Add the item to the cart's items list
        cart.getItems().add(cartItem);

        // Update the cart's total price
        updateCartTotalPrice(cart);

        // Save and return the updated cart
        return cartRepository.save(cart);
    }


    // Remove an item from a cart
    public Cart removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = getCartById(cartId); // Fetch the cart by ID

        // Use removeIf directly on the cart's items list
        boolean removed = cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        
        // If the item was not found, throw an exception
        if (!removed) {
            throw new ResourceNotFoundException("CartItem not found with id: " + cartItemId);
        }

        // Update the total price of the cart after removing the item
        updateCartTotalPrice(cart);

        // Save and return the updated cart
        return cartRepository.save(cart);
    }

    // Helper method to update the total price of the cart
    private void updateCartTotalPrice(Cart cart) {
        // Ensure items are not null to avoid NullPointerException
        if (cart.getItems() != null) {
            double totalPrice = cart.getItems().stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();
            cart.setTotalPrice(totalPrice);
        } else {
            cart.setTotalPrice(0.0); // Set total price to 0 if no items are present
        }
    }

	public Map<String, Object> fetchAllCarts(int page, int size, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cart calculateTotalPrice(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteCartById(Long id) {
		// TODO Auto-generated method stub
		
	}

	public Double getCartTotal(Long cartId) {
		// TODO Auto-generated method stub
		return null;
	}

    }