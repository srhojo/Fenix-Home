package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.VehicleInspectionRequest;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class VehicleInspectionMapper implements InnerMapper<VehicleInspection, VehicleInspectionRequest> {

    @Override
    public VehicleInspection mapToInner(final VehicleInspectionRequest outer) {
        final VehicleInspection inner = new VehicleInspection();
        inner.setDate(outer.getDate());
        inner.setDescription(outer.getDescription());
        inner.setKilometers(outer.getKilometers());
        inner.setPickupDate(outer.getPickupDate());
        inner.setPrice(outer.getPrice());
        inner.setShortDescription(outer.getShortDescription());
        inner.setVehicleId(outer.getVehicleId());
        return inner;
    }

    public void updateToInner(@NotNull final VehicleInspection inner, @NotNull final VehicleInspectionRequest outer) {
        inner.setDescription(outer.getDescription());
        inner.setKilometers(outer.getKilometers());
        inner.setPickupDate(outer.getPickupDate());
        inner.setPrice(outer.getPrice());
        inner.setShortDescription(outer.getShortDescription());
        inner.setDate(outer.getDate());
    }
}
