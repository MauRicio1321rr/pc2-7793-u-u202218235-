package com.medisupply.platform.u202218235.procurement.interfaces.rest.controllers;

import com.medisupply.platform.u202218235.procurement.application.commandservices.CreateSupplyRequestCommandService;
import com.medisupply.platform.u202218235.procurement.domain.model.aggregates.SupplyRequest;
import com.medisupply.platform.u202218235.procurement.interfaces.rest.assemblers.CreateSupplyRequestCommandFromResourceAssembler;
import com.medisupply.platform.u202218235.procurement.interfaces.rest.assemblers.SupplyRequestResourceFromEntityAssembler;
import com.medisupply.platform.u202218235.procurement.interfaces.rest.resources.CreateSupplyRequestResource;
import com.medisupply.platform.u202218235.procurement.interfaces.rest.resources.SupplyRequestResource;
import com.medisupply.platform.u202218235.shared.domain.model.results.Result;
import com.medisupply.platform.u202218235.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/supply-requests", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Supply Requests", description = "Medical Supply Request Management Endpoints")
public class SupplyRequestsController {
    private final CreateSupplyRequestCommandService createSupplyRequestCommandService;
    private final MessageSource messageSource;

    public SupplyRequestsController(CreateSupplyRequestCommandService createSupplyRequestCommandService,
                                    MessageSource messageSource) {
        this.createSupplyRequestCommandService = createSupplyRequestCommandService;
        this.messageSource = messageSource;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new supply request", description = "Registers a new medical supply request for a healthcare facility")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supply request created successfully",
                    content = @Content(schema = @Schema(implementation = SupplyRequestResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data or business rule violation",
                    content = @Content(schema = @Schema(implementation = MessageResource.class))),
            @ApiResponse(responseCode = "409", description = "A supply request with the specified request code already exists",
                    content = @Content(schema = @Schema(implementation = MessageResource.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected internal server error",
                    content = @Content(schema = @Schema(implementation = MessageResource.class)))
    })
    public ResponseEntity<?> createSupplyRequest(@RequestBody CreateSupplyRequestResource resource) {
        var command = CreateSupplyRequestCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = createSupplyRequestCommandService.handle(command);
        return switch (result) {
            case Result.Success<SupplyRequest> success -> {
                var supplyRequestResource = SupplyRequestResourceFromEntityAssembler.toResourceFromEntity(success.value());
                yield new ResponseEntity<>(supplyRequestResource, HttpStatus.CREATED);
            }
            case Result.Failure<SupplyRequest> failure -> {
                var localizedMessage = messageSource.getMessage(
                        failure.errorCode(),
                        null,
                        failure.message(),
                        LocaleContextHolder.getLocale()
                );
                var status = switch (failure.errorType()) {
                    case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
                    case CONFLICT -> HttpStatus.CONFLICT;
                    case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
                };
                yield new ResponseEntity<>(new MessageResource(localizedMessage), status);
            }
        };
    }
}