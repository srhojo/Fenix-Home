package io.github.srhojo.fenix.ms.warehouse.domain.mappers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.QuantityEmbeddableEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListItemsEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListItemRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListItemsRequest;
import io.github.srhojo.utils.cdm.Quantity;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListItemMapperTest {

    @InjectMocks
    private ShoppingListItemMapper shoppingListItemMapper;

    @Mock
    private QuantityMapper quantityMapper;

    @Test
    public void mapToInner() {
        // Given
        final ShoppingListItemsRequest outer = new ShoppingListItemsRequest();
        outer.setShoppingListId(123l);

        final ShoppingListItemRequest itemRequest1 = new ShoppingListItemRequest();
        itemRequest1.setProductId("product1");
        itemRequest1.setQuantity(new Quantity());

        final ShoppingListItemRequest itemRequest2 = new ShoppingListItemRequest();
        itemRequest2.setProductId("product2");
        itemRequest2.setQuantity(new Quantity());

        outer.setItems(Arrays.asList(itemRequest1, itemRequest2));

        given(quantityMapper.toInner(ArgumentMatchers.any(Quantity.class)))
                .willReturn(Optional.of(new QuantityEmbeddableEntity()));

        // When
        final HashMap<String, ShoppingListItemsEntity> result = (HashMap<String, ShoppingListItemsEntity>) shoppingListItemMapper
                .mapToInner(outer);

        // Then
        assertEquals(outer.getItems().size(), result.size());
    }
}