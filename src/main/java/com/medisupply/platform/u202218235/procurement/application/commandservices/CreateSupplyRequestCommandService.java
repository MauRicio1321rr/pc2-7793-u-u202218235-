package com.medisupply.platform.u202218235.procurement.application.commandservices;

import com.medisupply.platform.u202218235.procurement.application.commands.CreateSupplyRequestCommand;
import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.shared.domain.model.results.Result;

public interface CreateSupplyRequestCommandService {

    Result<SupplyRequest> handle(CreateSupplyRequestCommand command);

}