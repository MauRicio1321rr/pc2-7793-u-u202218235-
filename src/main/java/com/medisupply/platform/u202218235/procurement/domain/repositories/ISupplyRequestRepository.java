package com.medisupply.platform.u202218235.procurement.domain.repositories;

import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.RequestCode;

public interface ISupplyRequestRepository extends ICrudService<SupplyRequest, Long> {
    boolean existsByRequestCode(RequestCode requestCode);
}
