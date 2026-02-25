package org.ionescu.david.projweb.cart;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;
    private CartRepository cartRepository;

    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}")
    CartItem findById(@PathVariable Integer id) {
        Optional<CartItem> e = cartRepository.findById(id);
        if (e.isEmpty()) {
            throw new CartItemNotFoundException();
        }
        return e.get();
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    CartItem create(@Valid @RequestBody CartItem body) {
        return cartRepository.save(body);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    CartItem update(@Valid @PathVariable Integer id, @RequestBody CartItem body) {
        Optional<CartItem> e = cartRepository.findById(id);
        return cartRepository.save(body);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        cartRepository.delete(cartRepository.findById(id).orElseThrow(CartItemNotFoundException::new));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("user/{userId}")
    List<CartFullDTO> findByUserId(@PathVariable Integer userId) {
        return cartRepository.findCartItemsForUsers(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    List<CartFullDTO> getLoggedUserCart() {
        return cartService.getLoggedUserCart();
    }
}
