package io.github.srhojo.fenix.ms.warehouse.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;

import java.util.List;
import java.util.Optional;

public interface VehicleInspectionRepository extends CrudRepository<VehicleInspection,Long> {


    Optional<VehicleInspection> findByIdAndVehicleId(Long id, long vehicleId);

    List<VehicleInspection> findAllByVehicleId(Long vehicleId);
}
