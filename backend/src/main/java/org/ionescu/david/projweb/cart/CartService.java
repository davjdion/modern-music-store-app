package org.ionescu.david.projweb.cart;

import org.ionescu.david.projweb.user.User;
import org.ionescu.david.projweb.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public List<CartFullDTO> getLoggedUserCart() {
        // Retrieve the logged-in user's email
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        // Find the user by email to get their ID
        User user = userRepository.findByEmail(email).get(0);
        Integer userId = user.id();

        // Fetch the cart items for the logged-in user
        return cartRepository.findCartItemsForUsers(userId);
    }
}
