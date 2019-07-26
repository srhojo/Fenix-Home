package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.CategoryRequest;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class ProductCategoryMapper implements InnerMapper<ProductCategoryEntity, CategoryRequest> {

    @Override
    public ProductCategoryEntity mapToInner(final CategoryRequest outer) {
        final ProductCategoryEntity inner = new ProductCategoryEntity();
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setImage(outer.getImage());
        return inner;
    }
}
