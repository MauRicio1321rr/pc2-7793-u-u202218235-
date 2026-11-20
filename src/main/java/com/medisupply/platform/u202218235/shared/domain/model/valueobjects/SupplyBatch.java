package com.medisupply.platform.u202218235.shared.domain.model.valueobjects;
import com.medisupply.platform.u202218235.shared.domain.exceptions.ValidationException;



public record SupplyBatch(Integer quantity, String unit) {
    public SupplyBatch {
        if (quantity == null || quantity <= 0) {
            throw new ValidationException("error.supply.batch.quantity.invalid", "Supply batch quantity must be greater than zero");
        }
        if (unit == null || unit.isBlank()) {
            throw new ValidationException("error.supply.batch.unit.invalid", "Supply batch unit cannot be null or blank");
        }
    }

    public SupplyBatch addUnits(Integer additionalQuantity) {
        if (additionalQuantity == null || additionalQuantity <= 0) {
            throw new ValidationException("error.supply.batch.quantity.additional.invalid", "Additional quantity must be greater than zero");
        }
        if ((long) this.quantity + additionalQuantity > Integer.MAX_VALUE) {
            throw new ValidationException("error.supply.batch.quantity.overflow", "Total supply batch quantity cannot exceed maximum integer value");
        }
        return new SupplyBatch(this.quantity + additionalQuantity, this.unit);
    }
}