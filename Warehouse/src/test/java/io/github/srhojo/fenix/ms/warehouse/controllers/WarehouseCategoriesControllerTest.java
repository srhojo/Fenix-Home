package io.github.srhojo.fenix.ms.warehouse.controllers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductSubCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.CategoryRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.SubCategoryRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseCategoriesServices;
import io.github.srhojo.utils.cdm.ContainerList;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseCategoriesControllerTest {

    @InjectMocks
    private WarehouseCategoriesController warehouseCategoriesController;

    @Mock
    private WarehouseCategoriesServices warehouseCategoriesServices;

    @Test
    public void createCategory() {
        // Given
        final CategoryRequest request = new CategoryRequest();
        given(warehouseCategoriesServices.createCategory(request)).willReturn(new ProductCategoryEntity());

        // When
        final ProductCategoryEntity result = warehouseCategoriesController.createCategory(request);

        // Then
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).createCategory(request);
    }

    @Test
    public void updateCategory() {
        // Given
        final CategoryRequest request = new CategoryRequest();
        given(warehouseCategoriesServices.updateCategory(request)).willReturn(new ProductCategoryEntity());

        // When
        final ProductCategoryEntity result = warehouseCategoriesController.updateCategory(request);

        // THen
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).updateCategory(request);
    }

    @Test
    public void updateSubcategoriesToCategory() {
        // Given
        final CategoryUpdateSubCategoriesRequest request = new CategoryUpdateSubCategoriesRequest();
        given((warehouseCategoriesServices.updateSubCategoriesToCategory(request)))
                .willReturn(new ProductCategoryEntity());

        // When
        final ProductCategoryEntity result = warehouseCategoriesController.updateSubcategoriesToCategory(request);

        // Then
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).updateSubCategoriesToCategory(request);
    }

    @Test
    public void getCategories() {
        // Given
        final String filter = "";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseCategoriesServices.getCategories(filter, limit, offset)).willReturn(new ContainerList<>());

        // When
        final ContainerList result = warehouseCategoriesController.getCategories(filter, limit, offset);

        // Then
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).getCategories(filter, limit, offset);
    }

    @Test
    public void deleteCategory() {
        // Given
        final String name = "test";
        doNothing().when(warehouseCategoriesServices).deleteCategory(name);

        // When
        warehouseCategoriesController.deleteCategory(name);

        // Then
        verify(warehouseCategoriesServices, times(1)).deleteCategory(name);
    }

    @Test
    public void createSubCategoryEntity() {
        // Given
        final SubCategoryRequest request = new SubCategoryRequest();
        given(warehouseCategoriesServices.createSubCategory(request)).willReturn(new ProductSubCategoryEntity());

        // When
        final ProductSubCategoryEntity result = warehouseCategoriesController.createSubCategoryEntity(request);

        // THen
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).createSubCategory(request);
    }

    @Test
    public void updateSubCategory() {
        // Given
        final SubCategoryRequest request = new SubCategoryRequest();
        given(warehouseCategoriesServices.updateSubCategory(request)).willReturn(new ProductSubCategoryEntity());

        // When
        final ProductSubCategoryEntity result = warehouseCategoriesController.updateSubCategory(request);

        // THen
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).updateSubCategory((request));
    }

    @Test
    public void getSubCategories() {
        // Given
        final String filter = "";
        final Integer limit = 2;
        final Long offset = 0l;
        given(warehouseCategoriesServices.searchSubCategories(filter, limit, offset)).willReturn(new ContainerList<>());

        // When
        final ContainerList result = warehouseCategoriesController.getSubCategories(filter, limit, offset);

        // THen
        assertNotNull(result);
        verify(warehouseCategoriesServices, times(1)).searchSubCategories(filter, limit, offset);
    }
}