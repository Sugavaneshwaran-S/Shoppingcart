package com.shoppingcartapp.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcartapp.model.Cart;
import com.shoppingcartapp.model.CartItem;
import com.shoppingcartapp.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart.getCustomerName());
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(OK)
    public Cart getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @PostMapping("/{cartId}/items")
    public void addItemToCart(@PathVariable Long cartId, @RequestBody CartItem item) {
        cartService.addItemToCart(cartId, item);
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    @ResponseStatus(NO_CONTENT)
    public void removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        cartService.removeItemFromCart(cartId, itemId);
    }

    @GetMapping("/{cartId}/total")
    @ResponseStatus(OK)
    public Double getCartTotal(@PathVariable Long cartId) {
        return cartService.getCartTotal(cartId);
    }

    // Additional methods for listing all carts, modifying cart details, etc. can be added here.

}