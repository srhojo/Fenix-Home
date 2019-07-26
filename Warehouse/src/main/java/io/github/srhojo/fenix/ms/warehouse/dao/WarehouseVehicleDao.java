package io.github.srhojo.fenix.ms.warehouse.dao;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleEntity;
import io.github.srhojo.utils.commons.dao.CreateDao;
import io.github.srhojo.utils.commons.dao.SearchDao;

public interface WarehouseVehicleDao extends CreateDao<VehicleEntity, Long>, SearchDao<VehicleEntity> {

}
