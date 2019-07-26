package io.github.srhojo.fenix.ms.warehouse.controllers;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListItemsRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseShoppingListService;
import io.github.srhojo.utils.cdm.ContainerList;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseShoppingListControllerTest {

    @InjectMocks
    private WarehouseShoppingListController warehouseShoppingListController;

    @Mock
    private WarehouseShoppingListService warehouseShoppingListService;

    @Test
    public void getShoppingListById() {
        // Given
        final Long id = 1l;
        given(warehouseShoppingListService.getShoppingListById(id)).willReturn(new ShoppingListEntity());

        // When
        final ShoppingListEntity result = warehouseShoppingListController.getShoppingListById(id);

        // Then
        assertNotNull(result);
        verify(warehouseShoppingListService, times(1)).getShoppingListById(id);
    }

    @Test
    public void getShoppingLists() {
        // Given
        final String filter = "filter";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseShoppingListService.getShoppingLists(filter, limit, offset)).willReturn(new ContainerList<>());

        // When
        final ContainerList result = warehouseShoppingListController.getShoppingLists(filter, limit, offset);

        // Then
        assertNotNull(result);
        verify(warehouseShoppingListService, times(1)).getShoppingLists(filter, limit, offset);
    }

    @Test
    public void createShoppingList() {
        // Given
        final ShoppingListRequest request = new ShoppingListRequest();
        given(warehouseShoppingListService.saveShoppingList(request)).willReturn(new ShoppingListEntity());

        // When
        final ShoppingListEntity result = warehouseShoppingListController.createShoppingList(request);

        // Then
        assertNotNull(result);
        verify(warehouseShoppingListService, times(1)).saveShoppingList(request);
    }

    @Test
    public void editShoppingList() {
        // Given
        final Long id = 1l;
        final ShoppingListRequest request = new ShoppingListRequest();
        given(warehouseShoppingListService.saveShoppingList(request)).willReturn(new ShoppingListEntity());

        // When
        final ShoppingListEntity result = warehouseShoppingListController.editShoppingList(id, request);

        // Then
        assertNotNull(result);
        assertEquals(id, request.getId());
        verify(warehouseShoppingListService, times(1)).saveShoppingList(request);
    }

    @Test
    public void deleteShoppingList() {
        // Given
        final Long id = 1l;
        doNothing().when(warehouseShoppingListService).deleteShoppingList(id);

        // When
        warehouseShoppingListController.deleteShoppingList(id);

        // Then
        verify(warehouseShoppingListService, times(1)).deleteShoppingList(id);
    }

    @Test
    public void editShoppingListItems() {
        // Given
        final Long id = 1l;
        final ShoppingListItemsRequest request = new ShoppingListItemsRequest();
        given(warehouseShoppingListService.editShoppingListItems(request)).willReturn(new ShoppingListEntity());

        // When
        final ShoppingListEntity result = warehouseShoppingListController.editShoppingListItems(id, request);

        // Then
        assertNotNull(result);
        assertEquals(id, request.getShoppingListId());
        verify(warehouseShoppingListService, times(1)).editShoppingListItems(request);
    }
}