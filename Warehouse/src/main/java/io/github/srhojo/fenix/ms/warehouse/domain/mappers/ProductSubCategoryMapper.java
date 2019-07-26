package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductSubCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.SubCategoryRequest;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class ProductSubCategoryMapper implements InnerMapper<ProductSubCategoryEntity, SubCategoryRequest> {

    @Override
    public ProductSubCategoryEntity mapToInner(final SubCategoryRequest outer) {
        final ProductSubCategoryEntity inner = new ProductSubCategoryEntity();
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setImage(outer.getImage());
        return inner;
    }
}
