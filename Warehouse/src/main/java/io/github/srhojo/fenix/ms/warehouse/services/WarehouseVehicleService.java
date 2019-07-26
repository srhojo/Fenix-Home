package io.github.srhojo.fenix.ms.warehouse.services;

import org.springframework.web.multipart.MultipartFile;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.VehicleInspectionRequest;
import io.github.srhojo.utils.cdm.ContainerList;

public interface WarehouseVehicleService {

    VehicleEntity save(VehicleEntity vehicleEntity);

    VehicleEntity get(Long id);

    VehicleEntity updateVehicleImage(Long id, MultipartFile file);

    void deleteVehicle(Long id);

    ContainerList<VehicleEntity> search(String filter, Integer limit, Long offset);

    VehicleEntity saveInspection(VehicleInspectionRequest request);

    VehicleEntity updateInspection(VehicleInspectionRequest request);

    VehicleInspection getInspectionDetail(Long vehicleId, Long inspectionId);

    void deleteVehicleInspection(Long vehicleId, Long inspectionId);

}
