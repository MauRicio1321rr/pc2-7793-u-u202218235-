package com.medisupply.platform.u202218235.procurement.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateSupplyRequestResource {
    private String requestCode;
    private String facilityId;
    private String fulfillmentPeriodStartDate;
    private String fulfillmentPeriodEndDate;
    private Integer supplyBatchQuantity;
    private String supplyBatchUnit;
    private Integer urgencyLevel;
    private String status;
    @JsonIgnore
    private boolean statusPresent;
    private String requestNotes;

    @JsonSetter("status")
    public void setStatus(String status) {
        this.status = status;
        this.statusPresent = true;
    }
}