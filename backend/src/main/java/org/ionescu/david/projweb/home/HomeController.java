package org.ionescu.david.projweb.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/users", "/products"})
    public String home() {
        return "forward:/index.html";
    }
}
