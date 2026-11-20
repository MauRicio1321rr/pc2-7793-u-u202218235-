package com.medisupply.platform.u202218235.procurement.infrastructure.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyBatchPersistenceEmbeddable {
    @Column(name = "supply_batch_quantity", nullable = false)
    private Integer quantity;
    @Column(name = "supply_batch_unit", nullable = false)
    private String unit;
}