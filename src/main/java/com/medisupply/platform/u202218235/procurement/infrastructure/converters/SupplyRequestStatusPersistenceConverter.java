package com.medisupply.platform.u202218235.procurement.infrastructure.converters;

import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.SupplyRequestStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class SupplyRequestStatusPersistenceConverter implements AttributeConverter<SupplyRequestStatus, String> {

    @Override
    public String convertToDatabaseColumn(SupplyRequestStatus attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public SupplyRequestStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : SupplyRequestStatus.valueOf(dbData);
    }
}