package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListItemsEmbeddableIdEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListItemsEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListItemRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListItemsRequest;
import io.github.srhojo.utils.commons.mappers.InnerMapper;

@Component
public class ShoppingListItemMapper
        implements InnerMapper<Map<String, ShoppingListItemsEntity>, ShoppingListItemsRequest> {

    private final QuantityMapper quantityMapper;

    public ShoppingListItemMapper(final QuantityMapper quantityMapper) {
        this.quantityMapper = quantityMapper;
    }

    @Override
    public Map<String, ShoppingListItemsEntity> mapToInner(final ShoppingListItemsRequest outer) {
        return outer.getItems().stream().map(item -> mapShoppingListItem(item, outer.getShoppingListId()))
                .collect(Collectors.toMap(o -> o.getId().getProductId(), o -> o));
    }

    private ShoppingListItemsEntity mapShoppingListItem(final ShoppingListItemRequest item, final Long idShoppingList) {
        final ShoppingListItemsEntity itemEntity = new ShoppingListItemsEntity();
        final ShoppingListItemsEmbeddableIdEntity shoppingListItemsEmbeddableIdEntity = new ShoppingListItemsEmbeddableIdEntity();
        shoppingListItemsEmbeddableIdEntity.setProductId(item.getProductId());
        shoppingListItemsEmbeddableIdEntity.setShoppingListId(idShoppingList);
        itemEntity.setId(shoppingListItemsEmbeddableIdEntity);
        quantityMapper.toInner(item.getQuantity()).ifPresent(itemEntity::setQuantity);
        return itemEntity;
    }

}
