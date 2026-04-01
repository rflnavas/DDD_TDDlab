package com.rnr.error;


public class SubstractionNotAllowed extends RuntimeException {

    private final double quantity;

    public SubstractionNotAllowed(String message, Number quantity) {
        super(message);
        this.quantity = quantity.doubleValue();
    }

    public double getQuantity() {
        return quantity;
    }
}
