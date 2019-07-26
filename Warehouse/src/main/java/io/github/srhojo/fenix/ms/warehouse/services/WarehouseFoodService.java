package io.github.srhojo.fenix.ms.warehouse.services;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.FoodEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.FoodRequest;
import io.github.srhojo.utils.cdm.ContainerList;

/**
 * @author hojo
 */
public interface WarehouseFoodService {

    FoodEntity createFood(FoodRequest food);

    FoodEntity updateFood(FoodRequest food);

    ContainerList<FoodEntity> searchFoods(String filter, Integer limit, Long offset);

}
