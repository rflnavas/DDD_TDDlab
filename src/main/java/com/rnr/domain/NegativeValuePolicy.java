package com.rnr.domain;

import com.rnr.error.SubstractionNotAllowed;

import java.math.BigDecimal;

public enum NegativeValuePolicy {
    ALLOW_NEGATIVE {
        @Override
        public void validateSubstraction(BigDecimal quantity) {
            // No validation needed, negative values are allowed
        }
    },
    FORBID_NEGATIVE {
        @Override
        public void validateSubstraction(BigDecimal quantity) {
            if (quantity.compareTo(BigDecimal.ZERO) < 0) {
                throw new SubstractionNotAllowed("Resulting amount cannot be negative", quantity);
            }
        }
    };

    public abstract void validateSubstraction(BigDecimal resultMoney);
}
