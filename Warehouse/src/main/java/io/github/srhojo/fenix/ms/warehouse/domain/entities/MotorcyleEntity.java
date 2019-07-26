package io.github.srhojo.fenix.ms.warehouse.domain.entities;

import javax.persistence.Entity;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.enums.VehicleTypeEnum;

@Entity(name = VehicleTypeEnum.Names.MOTORCYCLE)
public class MotorcyleEntity extends VehicleEntity {
}
