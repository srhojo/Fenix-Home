package io.github.srhojo.fenix.ms.warehouse.services.impl;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.srhojo.fenix.microservices.exceptions.FenixException;
import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseVehicleDao;
import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseVehicleInspectionDao;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;
import io.github.srhojo.fenix.ms.warehouse.domain.mappers.VehicleInspectionMapper;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.VehicleInspectionRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseVehicleService;
import io.github.srhojo.utils.cdm.ContainerList;
import io.github.srhojo.utils.cdm.OffsetPagination;
import io.github.srhojo.utils.commons.ql.QueryLanguajeComponentImpl;
import io.github.srhojo.utils.commons.ql.domain.OffsetPaginationRequest;

@Service
public class WarehouseVehicleServiceImpl implements WarehouseVehicleService {

    private final WarehouseVehicleDao warehouseVehicleDao;
    private final WarehouseVehicleInspectionDao warehouseVehicleInspectionDao;
    private final VehicleInspectionMapper vehicleInspectionMapper;
    private final QueryLanguajeComponentImpl<VehicleEntity> qlVehicle;

    public WarehouseVehicleServiceImpl(final WarehouseVehicleDao warehouseVehicleDao,
            final WarehouseVehicleInspectionDao warehouseVehicleInspectionDao,
            final VehicleInspectionMapper vehicleInspectionMapper,
            final QueryLanguajeComponentImpl<VehicleEntity> qlVehicle) {
        this.warehouseVehicleDao = warehouseVehicleDao;
        this.warehouseVehicleInspectionDao = warehouseVehicleInspectionDao;
        this.vehicleInspectionMapper = vehicleInspectionMapper;
        this.qlVehicle = qlVehicle;
    }

    @Override
    public VehicleEntity save(@NotNull final VehicleEntity vehicleEntity) {
        return vehicleEntity.getId() != null ? warehouseVehicleDao.update(vehicleEntity)
                : warehouseVehicleDao.create(vehicleEntity);
    }

    @Override
    public VehicleEntity get(final Long id) {
        return warehouseVehicleDao.get(id);
    }

    @Override
    public VehicleEntity updateVehicleImage(final Long id, final MultipartFile file) {
        final VehicleEntity vehicle = warehouseVehicleDao.get(id);
        try {
            vehicle.setImage(file.getBytes());
        } catch (final IOException e) {
            throw new FenixException(e.getMessage());
        }
        return warehouseVehicleDao.update(vehicle);
    }

    @Override
    public void deleteVehicle(final Long id) {
        warehouseVehicleDao.delete(id);
    }

    @Override
    public ContainerList<VehicleEntity> search(final String filter, final Integer limit, final Long offset) {
        if ((offset != null) && (limit != null)) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<VehicleEntity> vehicleEntityPage = warehouseVehicleDao.search(qlVehicle.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset,
                    vehicleEntityPage.getTotalElements());
            return new ContainerList<>(vehicleEntityPage.get().collect(Collectors.toList()), offsetPagination);
        }
        return new ContainerList<>(warehouseVehicleDao.search(qlVehicle.parse(filter)));
    }

    @Override
    public VehicleEntity saveInspection(final VehicleInspectionRequest request) {
        try {
            final VehicleEntity vehicleEntity = warehouseVehicleDao.get(request.getVehicleId());
            warehouseVehicleInspectionDao.create(vehicleInspectionMapper.mapToInner(request));
            // if (vehicleEntity.getInspections() == null)
            // vehicleEntity.setInspections(Collections.emptyList());
            // vehicleEntity.getInspections().add(inspectionSaved);
            return vehicleEntity;
        } catch (final FenixException FenixException) {
            throw new FenixException(FenixException.getStatus(), "101", FenixException);
        }
    }

    @Override
    public VehicleEntity updateInspection(final VehicleInspectionRequest request) {
        try {
            final VehicleEntity vehicleEntity = warehouseVehicleDao.get(request.getVehicleId());

            // Me va a dejar actualizar la información dentro del array??
            vehicleEntity.getInspections().stream().filter(i -> i.getId().equals(request.getId())).forEach(i -> {
                vehicleInspectionMapper.updateToInner(i, request);
                warehouseVehicleInspectionDao.update(i);
            });

            return vehicleEntity;
        } catch (final FenixException FenixException) {
            throw new FenixException(FenixException.getStatus(), "101", FenixException);
        }
    }

    @Override
    public VehicleInspection getInspectionDetail(final Long vehicleId, final Long inspectionId) {
        return warehouseVehicleInspectionDao.getDetail(vehicleId, inspectionId);
        // return warehouseVehicleInspectionDao.get(inspectionId);
    }

    @Override
    public void deleteVehicleInspection(final Long vehicleId, final Long inspectionId) {
        final VehicleEntity vehicle = warehouseVehicleDao.get(vehicleId);
        final VehicleInspection vehicleInspection = vehicle.getInspections().stream()
                .filter(i -> i.getId().equals(inspectionId)).findAny().orElseThrow(() -> new FenixException(
                        String.format("Inspection %s not found in vehicle: %s", inspectionId, vehicleId)));
        warehouseVehicleInspectionDao.delete(vehicleInspection.getId());
    }
}
