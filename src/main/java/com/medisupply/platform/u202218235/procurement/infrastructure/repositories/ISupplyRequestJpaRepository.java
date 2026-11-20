package com.medisupply.platform.u202218235.procurement.infrastructure.repositories;

import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.RequestCode;
import com.medisupply.platform.u202218235.procurement.infrastructure.entities.SupplyRequestPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplyRequestJpaRepository extends JpaRepository<SupplyRequestPersistenceEntity, Long> {
    boolean existsByRequestCode(RequestCode requestCode);
}