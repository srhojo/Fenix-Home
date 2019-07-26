package io.github.srhojo.fenix.ms.warehouse.dao;

import java.util.List;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;
import io.github.srhojo.utils.commons.dao.CreateDao;

public interface WarehouseVehicleInspectionDao extends CreateDao<VehicleInspection, Long> {

    VehicleInspection getDetail(Long vehicleId, Long inspectionId);

    List<VehicleInspection> getAll(Long vechicleId);

}
