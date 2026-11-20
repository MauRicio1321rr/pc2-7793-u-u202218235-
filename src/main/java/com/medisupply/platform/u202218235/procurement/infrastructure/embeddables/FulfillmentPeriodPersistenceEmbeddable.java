package com.medisupply.platform.u202218235.procurement.infrastructure.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FulfillmentPeriodPersistenceEmbeddable {
    @Column(name = "fulfillment_period_start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "fulfillment_period_end_date", nullable = false)
    private LocalDate endDate;
}