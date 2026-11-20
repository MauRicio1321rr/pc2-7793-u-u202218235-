package com.medisupply.platform.u202218235.procurement.infrastructure.converters;

import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.FacilityId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = false)
public class FacilityIdPersistenceConverter implements AttributeConverter<FacilityId, String> {

    @Override
    public String convertToDatabaseColumn(FacilityId attribute) {
        return attribute == null ? null : attribute.value().toString();
    }

    @Override
    public FacilityId convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new FacilityId(UUID.fromString(dbData));
    }
}