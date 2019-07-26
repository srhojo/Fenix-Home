package io.github.srhojo.fenix.ms.warehouse.dao.impl;

import static io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE;
import static io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE;
import static io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE;
import static io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import io.github.srhojo.fenix.microservices.exceptions.FenixException;
import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseVehicleInspectionDao;
import io.github.srhojo.fenix.ms.warehouse.dao.repositories.VehicleInspectionRepository;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;

@Repository
@Transactional
public class WarehouseVehicleInspectionDaoImpl implements WarehouseVehicleInspectionDao {

    private final VehicleInspectionRepository vehicleInspectionRepository;

    public WarehouseVehicleInspectionDaoImpl(final VehicleInspectionRepository vehicleInspectionRepository) {
        this.vehicleInspectionRepository = vehicleInspectionRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection create(final VehicleInspection vehicleInspection) {
        if (vehicleInspection.getId() != null) {
            throw new FenixException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE,
                    ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return vehicleInspectionRepository.save(vehicleInspection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection update(final VehicleInspection vehicleInspection) {
        this.get(vehicleInspection.getId());
        return vehicleInspectionRepository.save(vehicleInspection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection get(final Long id) {
        return vehicleInspectionRepository.findById(id).orElseThrow(() -> new FenixException(HttpStatus.NOT_FOUND,
                ERRORS_DAO_ENTITY_NOT_FOUND_CODE, String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Long id) {
        this.get(id);
        vehicleInspectionRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection getDetail(final Long vehicleId, final Long inspectionId) {
        return vehicleInspectionRepository.findByIdAndVehicleId(inspectionId, vehicleId)
                .orElseThrow(() -> new FenixException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                        String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, inspectionId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleInspection> getAll(final Long vehicleId) {
        return vehicleInspectionRepository.findAllByVehicleId(vehicleId);
    }
}
