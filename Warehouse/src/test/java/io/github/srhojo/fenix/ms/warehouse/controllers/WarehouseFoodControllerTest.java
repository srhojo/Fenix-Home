package io.github.srhojo.fenix.ms.warehouse.controllers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.FoodEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.FoodRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseFoodService;
import io.github.srhojo.utils.cdm.ContainerList;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseFoodControllerTest {

    @InjectMocks
    private WarehouseFoodController warehouseFoodController;

    @Mock
    private WarehouseFoodService warehouseFoodService;

    @Test
    public void searchFood() {
        // Given
        final String filter = "filter";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseFoodService.searchFoods(filter, limit, offset)).willReturn(new ContainerList<>());

        // When
        final ContainerList result = warehouseFoodController.searchFood(filter, limit, offset);

        // Then
        assertNotNull(result);
        verify(warehouseFoodService, times(1)).searchFoods(filter, limit, offset);
    }

    @Test
    public void createFood() {
        // Given
        final FoodRequest request = new FoodRequest();
        given(warehouseFoodService.createFood(request)).willReturn(new FoodEntity());

        // When
        final FoodEntity result = warehouseFoodController.createFood(request);

        // Then
        assertNotNull(result);
        verify(warehouseFoodService, times(1)).createFood(request);
    }

    @Test
    public void updateFood() {
        // Given
        final String name = "food";
        final FoodRequest request = new FoodRequest();
        given(warehouseFoodService.updateFood(request)).willReturn(new FoodEntity());

        // When
        final FoodEntity result = warehouseFoodController.updateFood(name, request);

        // Then
        assertNotNull(result);
        verify(warehouseFoodService, times(1)).updateFood(request);
    }
}