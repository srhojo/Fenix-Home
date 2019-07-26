package io.github.srhojo.fenix.ms.warehouse.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants;
import io.github.srhojo.fenix.microservices.exceptions.FenixException;
import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseVehicleDao;
import io.github.srhojo.fenix.ms.warehouse.dao.repositories.VehicleRepository;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleEntity;

@Repository
@Transactional
public class WarehouseVehicleDaoImpl implements WarehouseVehicleDao {

    private final VehicleRepository vehicleRepository;

    public WarehouseVehicleDaoImpl(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleEntity create(final VehicleEntity vehicleEntity) {
        if (vehicleEntity.getId() != null) {
            throw new FenixException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE,
                    ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return vehicleRepository.save(vehicleEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleEntity update(final VehicleEntity vehicleEntity) {
        this.get(vehicleEntity.getId());
        return vehicleRepository.save(vehicleEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleEntity get(final Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new FenixException(HttpStatus.NOT_FOUND,
                        ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                        String.format(ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Long id) {
        this.get(id);
        vehicleRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleEntity> search(final Specification<VehicleEntity> spec) {
        return vehicleRepository.findAll(spec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<VehicleEntity> search(final Specification<VehicleEntity> spec, final Pageable pageable) {
        return vehicleRepository.findAll(spec, pageable);
    }
}
