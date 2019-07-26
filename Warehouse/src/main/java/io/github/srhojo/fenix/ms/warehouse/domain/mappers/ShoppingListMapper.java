package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListRequest;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class ShoppingListMapper implements InnerMapper<ShoppingListEntity, ShoppingListRequest> {

    @Override
    public ShoppingListEntity mapToInner(final ShoppingListRequest outer) {
        final ShoppingListEntity inner = new ShoppingListEntity();
        inner.setId(outer.getId());
        inner.setName(outer.getName());
        inner.setDescription(outer.getDescription());
        inner.setLastUpdatedDate(LocalDateTime.now());
        return inner;
    }
}
