package io.github.srhojo.fenix.ms.warehouse.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.github.srhojo.fenix.microservices.FenixController;
import io.github.srhojo.fenix.microservices.logger.FenixLogger;
import io.github.srhojo.fenix.ms.warehouse.api.WarehouseGarage;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.VehicleInspectionRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseVehicleService;
import io.github.srhojo.utils.cdm.ContainerList;
import io.swagger.annotations.ApiParam;

@FenixController
@FenixLogger
public class WarehouseGarageController implements WarehouseGarage {

    private final WarehouseVehicleService warehouseVehicleService;

    public WarehouseGarageController(final WarehouseVehicleService warehouseVehicleService) {
        this.warehouseVehicleService = warehouseVehicleService;
    }

    @Override
    public ContainerList<VehicleEntity> searchVehicles(
            @RequestParam(value = "filter", required = false) final String filter,
            @ApiParam(name = "limit", example = "2") @Valid @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
            @ApiParam(name = "offset", example = "0") @Valid @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseVehicleService.search(filter, limit, offset);
    }

    @Override
    public VehicleEntity getVehicleById(final Long id) {
        return warehouseVehicleService.get(id);
    }

    @Override
    public VehicleEntity addVehicle(final VehicleEntity request) {
        return warehouseVehicleService.save(request);
    }

    @Override
    public VehicleEntity updateVehicle(final Long id, final @Valid VehicleEntity request) {
        request.setId(id);
        return warehouseVehicleService.save(request);
    }

    @Override
    public VehicleEntity updateVehicleImage(final Long id, @RequestPart(name = "image") final MultipartFile[] files) {
        return warehouseVehicleService.updateVehicleImage(id, files[0]);
    }

    @Override
    public void deleteVehicle(final Long id) {
        warehouseVehicleService.deleteVehicle(id);
    }

    @Override
    public VehicleInspection getVehicleInspections(final Long vehicleId, final Long inspectionId) {
        return warehouseVehicleService.getInspectionDetail(vehicleId, inspectionId);

    }

    @Override
    public VehicleEntity addVehicleInspection(final Long vehicleId, final @Valid VehicleInspectionRequest request) {
        request.setVehicleId(vehicleId);
        return warehouseVehicleService.saveInspection(request);
    }

    @Override
    public VehicleEntity updateVehicleInspection(final Long vehicleId, final Long inspectionId,
            final @Valid VehicleInspectionRequest request) {
        request.setId(inspectionId);
        request.setVehicleId(vehicleId);
        return warehouseVehicleService.updateInspection(request);
    }

    @Override
    public void removeVehicleInspection(final Long vehicleId, final Long inspectionId) {
        warehouseVehicleService.deleteVehicleInspection(vehicleId, inspectionId);
    }

}
