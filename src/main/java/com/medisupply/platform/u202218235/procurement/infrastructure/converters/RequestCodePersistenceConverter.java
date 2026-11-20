package com.medisupply.platform.u202218235.procurement.infrastructure.converters;

import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.RequestCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class RequestCodePersistenceConverter implements AttributeConverter<RequestCode, String> {

    @Override
    public String convertToDatabaseColumn(RequestCode attribute) {
        return attribute == null ? null : attribute.code();
    }

    @Override
    public RequestCode convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new RequestCode(dbData);
    }
}