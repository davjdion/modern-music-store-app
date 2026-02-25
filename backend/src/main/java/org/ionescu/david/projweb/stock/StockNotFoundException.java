package org.ionescu.david.projweb.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException() {
        super("Stock not found");
    }
}
