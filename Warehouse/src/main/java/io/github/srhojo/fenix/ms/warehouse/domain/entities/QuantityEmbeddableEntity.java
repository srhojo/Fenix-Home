package io.github.srhojo.fenix.ms.warehouse.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.github.srhojo.utils.cdm.enums.UnitiesEnum;

@Embeddable
public class QuantityEmbeddableEntity {

    @Column(name = "quantity")
    private Float value;

    @Column(name = "unities")
    private UnitiesEnum Unities;

    public Float getValue() {
        return value;
    }

    public void setValue(final Float value) {
        this.value = value;
    }

    public UnitiesEnum getUnities() {
        return Unities;
    }

    public void setUnities(final UnitiesEnum unities) {
        Unities = unities;
    }

    @Override
    public String toString() {
        return "QuantityEmbeddableEntity{" + "value=" + value + ", Unities=" + Unities + '}';
    }
}
