package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.QuantityEmbeddableEntity;
import io.github.srhojo.utils.cdm.Quantity;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class QuantityMapper implements InnerMapper<QuantityEmbeddableEntity, Quantity> {

    @Override
    public QuantityEmbeddableEntity mapToInner(final Quantity outer) {
        final QuantityEmbeddableEntity inner = new QuantityEmbeddableEntity();
        inner.setUnities(outer.getUnities());
        inner.setValue(outer.getValue());
        return inner;
    }
}
