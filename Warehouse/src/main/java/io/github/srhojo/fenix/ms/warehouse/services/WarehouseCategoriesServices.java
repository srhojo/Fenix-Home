package io.github.srhojo.fenix.ms.warehouse.services;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ProductSubCategoryEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.CategoryRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.SubCategoryRequest;
import io.github.srhojo.utils.cdm.ContainerList;

public interface WarehouseCategoriesServices {

    ProductCategoryEntity createCategory(CategoryRequest categoryEntities);

    ProductCategoryEntity updateCategory(CategoryRequest categoryEntities);

    ProductCategoryEntity updateSubCategoriesToCategory(CategoryUpdateSubCategoriesRequest request);

    ContainerList<ProductCategoryEntity> getCategories(String filter, Integer limit, Long offset);

    void deleteCategory(String name);

    ProductSubCategoryEntity createSubCategory(SubCategoryRequest subCategoryRequest);

    ProductSubCategoryEntity updateSubCategory(SubCategoryRequest subCategoryRequest);

    ContainerList<ProductSubCategoryEntity> searchSubCategories(String filter, Integer limit, Long offset);
}
