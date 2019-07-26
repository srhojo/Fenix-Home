package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.FoodEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductSubCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.FoodRequest;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class FoodMapper implements InnerMapper<FoodEntity, FoodRequest> {

    private final QuantityMapper quantityMapper;

    public FoodMapper(final QuantityMapper quantityMapper) {
        this.quantityMapper = quantityMapper;
    }

    @Override
    public FoodEntity mapToInner(final FoodRequest outer) {
        final FoodEntity inner = new FoodEntity();
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setCategory(new ProductCategoryEntity());
        inner.getCategory().setName(outer.getCategoryName());
        if (outer.getSubCategoryName() != null) {
            inner.setSubCategory(new ProductSubCategoryEntity());
            inner.getSubCategory().setName(outer.getSubCategoryName());
        }
        quantityMapper.toInner(outer.getQuantity()).ifPresent(inner::setQuantity);
        inner.setLastUpdatedDate(LocalDateTime.now());
        inner.setExpirationDate(outer.getExpirationDate());
        return inner;

    }
}
