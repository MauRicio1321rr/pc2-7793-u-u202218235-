package com.medisupply.platform.u202218235.shared.domain.model.aggregates;


import com.medisupply.platform.u202218235.shared.domain.model.valueobjects.*;
import com.medisupply.platform.u202218235.shared.domain.exceptions.ValidationException;

import java.time.Instant;

public class SupplyRequest {
    private Long id;
    private RequestCode requestCode;
    private FacilityId facilityId;
    private FulfillmentPeriod fulfillmentPeriod;
    private SupplyBatch supplyBatch;
    private Integer urgencyLevel;
    private SupplyRequestStatus status;
    private String requestNotes;
    private Instant createdAt;
    private Instant updatedAt;

    public SupplyRequest(RequestCode requestCode,
                         FacilityId facilityId,
                         FulfillmentPeriod fulfillmentPeriod,
                         SupplyBatch supplyBatch,
                         Integer urgencyLevel,
                         SupplyRequestStatus status,
                         String requestNotes) {
        if (requestCode == null) {
            throw new ValidationException("error.request.code.invalid", "Request code cannot be null or blank");
        }
        if (facilityId == null) {
            throw new ValidationException("error.facility.id.null", "Facility ID cannot be null");
        }
        if (fulfillmentPeriod == null) {
            throw new ValidationException("error.fulfillment.period.null", "Fulfillment period cannot be null");
        }
        if (supplyBatch == null) {
            throw new ValidationException("error.supply.batch.null", "Supply batch cannot be null");
        }
        if (urgencyLevel == null || urgencyLevel < 1 || urgencyLevel > 5) {
            throw new ValidationException("error.urgency.level.invalid", "Urgency level must be an integer between 1 and 5");
        }
        if (status == null) {
            throw new ValidationException("error.status.null", "Supply request status cannot be null");
        }
        Instant now = Instant.now();
        this.requestCode = requestCode;
        this.facilityId = facilityId;
        this.fulfillmentPeriod = fulfillmentPeriod;
        this.supplyBatch = supplyBatch;
        this.urgencyLevel = urgencyLevel;
        this.status = status;
        this.requestNotes = requestNotes;
        this.createdAt = now;
        this.updatedAt = now;
    }

    public SupplyRequest(Long id,
                         RequestCode requestCode,
                         FacilityId facilityId,
                         FulfillmentPeriod fulfillmentPeriod,
                         SupplyBatch supplyBatch,
                         Integer urgencyLevel,
                         SupplyRequestStatus status,
                         String requestNotes,
                         Instant createdAt,
                         Instant updatedAt) {
        this.id = id;
        this.requestCode = requestCode;
        this.facilityId = facilityId;
        this.fulfillmentPeriod = fulfillmentPeriod;
        this.supplyBatch = supplyBatch;
        this.urgencyLevel = urgencyLevel;
        this.status = status;
        this.requestNotes = requestNotes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public RequestCode getRequestCode() {
        return requestCode;
    }

    public FacilityId getFacilityId() {
        return facilityId;
    }

    public FulfillmentPeriod getFulfillmentPeriod() {
        return fulfillmentPeriod;
    }

    public SupplyBatch getSupplyBatch() {
        return supplyBatch;
    }

    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }

    public SupplyRequestStatus getStatus() {
        return status;
    }

    public String getRequestNotes() {
        return requestNotes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}