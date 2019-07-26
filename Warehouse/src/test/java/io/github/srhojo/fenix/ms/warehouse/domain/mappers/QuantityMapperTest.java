package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.QuantityEmbeddableEntity;
import io.github.srhojo.utils.cdm.Quantity;
import io.github.srhojo.utils.cdm.enums.UnitiesEnum;

@RunWith(MockitoJUnitRunner.class)
public class QuantityMapperTest {

    @InjectMocks
    private QuantityMapper quantityMapper;

    @Test
    public void mapToInner() {
        // Given
        final Quantity outer = new Quantity();
        outer.setUnities(UnitiesEnum.KILOGRAMS);
        outer.setValue(234f);
        // When
        final QuantityEmbeddableEntity inner = quantityMapper.mapToInner(outer);

        // Then
        Assert.assertEquals(outer.getUnities(), inner.getUnities());
        assertEquals(outer.getValue(), inner.getValue());
    }
}