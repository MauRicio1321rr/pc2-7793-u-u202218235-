package com.medisupply.platform.u202218235.shared.domain.repositories;

import com.medisupply.platform.u202218235.shared.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.shared.domain.model.valueobjects.RequestCode;

public interface ISupplyRequestRepository extends ICrudService<SupplyRequest, Long> {
    boolean existsByRequestCode(RequestCode requestCode);
}
