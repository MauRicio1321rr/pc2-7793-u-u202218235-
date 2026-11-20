package com.medisupply.platform.u202218235.procurement.infrastructure.entities;

import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.FacilityId;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.RequestCode;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.SupplyRequestStatus;
import com.medisupply.platform.u202218235.procurement.infrastructure.converters.FacilityIdPersistenceConverter;
import com.medisupply.platform.u202218235.procurement.infrastructure.converters.RequestCodePersistenceConverter;
import com.medisupply.platform.u202218235.procurement.infrastructure.converters.SupplyRequestStatusPersistenceConverter;
import com.medisupply.platform.u202218235.procurement.infrastructure.embeddables.FulfillmentPeriodPersistenceEmbeddable;
import com.medisupply.platform.u202218235.procurement.infrastructure.embeddables.SupplyBatchPersistenceEmbeddable;
import com.medisupply.platform.u202218235.shared.infrastructure.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supply_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplyRequestPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = RequestCodePersistenceConverter.class)
    @Column(name = "request_code", nullable = false, unique = true)
    private RequestCode requestCode;

    @Convert(converter = FacilityIdPersistenceConverter.class)
    @Column(name = "facility_id", nullable = false, length = 36)
    private FacilityId facilityId;

    @Embedded
    private FulfillmentPeriodPersistenceEmbeddable fulfillmentPeriod;

    @Embedded
    private SupplyBatchPersistenceEmbeddable supplyBatch;

    @Column(name = "urgency_level", nullable = false)
    private Integer urgencyLevel;

    @Convert(converter = SupplyRequestStatusPersistenceConverter.class)
    @Column(name = "status", nullable = false)
    private SupplyRequestStatus status;

    @Column(name = "request_notes")
    private String requestNotes;
}