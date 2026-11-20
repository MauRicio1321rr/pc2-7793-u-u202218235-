package com.medisupply.platform.u202218235.procurement.infrastructure.adapters;

import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.procurement.domain.model.valueobjects.RequestCode;
import com.medisupply.platform.u202218235.procurement.domain.repositories.ISupplyRequestRepository;
import com.medisupply.platform.u202218235.procurement.infrastructure.assemblers.SupplyRequestPersistenceAssembler;
import com.medisupply.platform.u202218235.procurement.infrastructure.repositories.ISupplyRequestJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SupplyRequestPersistenceAdapter implements ISupplyRequestRepository {
    private final ISupplyRequestJpaRepository supplyRequestJpaRepository;

    public SupplyRequestPersistenceAdapter(ISupplyRequestJpaRepository supplyRequestJpaRepository) {
        this.supplyRequestJpaRepository = supplyRequestJpaRepository;
    }

    @Override
    public SupplyRequest save(SupplyRequest entity) {
        var supplyRequestEntity = SupplyRequestPersistenceAssembler.toEntity(entity);
        var supplyRequestSaved = supplyRequestJpaRepository.save(supplyRequestEntity);
        return SupplyRequestPersistenceAssembler.toDomain(supplyRequestSaved);
    }

    @Override
    public SupplyRequest update(SupplyRequest entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
    }

    @Override
    public SupplyRequest findById(Long aLong) {
        return null;
    }

    @Override
    public boolean existsByRequestCode(RequestCode requestCode) {
        return supplyRequestJpaRepository.existsByRequestCode(requestCode);
    }
}