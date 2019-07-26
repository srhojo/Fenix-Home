package io.github.srhojo.fenix.ms.warehouse.services.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseFoodDao;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.FoodEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.mappers.FoodMapper;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.FoodRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseFoodService;
import io.github.srhojo.utils.cdm.ContainerList;
import io.github.srhojo.utils.cdm.OffsetPagination;
import io.github.srhojo.utils.commons.ql.QueryLanguajeComponentImpl;
import io.github.srhojo.utils.commons.ql.domain.OffsetPaginationRequest;

/**
 * @author hojo
 */
@Service
class WarehouseFoodServiceImpl implements WarehouseFoodService {

    private final WarehouseFoodDao warehouseDao;
    private final QueryLanguajeComponentImpl<FoodEntity> qlFood;
    private final FoodMapper foodMapper;

    public WarehouseFoodServiceImpl(final WarehouseFoodDao warehouseFoodDao, final FoodMapper foodMapper,
            final QueryLanguajeComponentImpl<FoodEntity> qlFood) {
        this.warehouseDao = warehouseFoodDao;
        this.foodMapper = foodMapper;
        this.qlFood = qlFood;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(final FoodRequest food) {
        // Check manually if categories and subcategories exist?
        return warehouseDao.createFood(foodMapper.mapToInner(food));
    }

    @Override
    public FoodEntity updateFood(final FoodRequest food) {
        // Check manually if categories and subcategories exist?
        return warehouseDao.updateFood(foodMapper.mapToInner(food));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<FoodEntity> searchFoods(final String filter, final Integer limit, final Long offset) {
        if ((offset != null) && (limit != null)) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<FoodEntity> foodEntityPage = warehouseDao.searchFoods(qlFood.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset,
                    foodEntityPage.getTotalElements());
            return new ContainerList<>(foodEntityPage.get().collect(Collectors.toList()), offsetPagination);

        } else {
            return new ContainerList<>(warehouseDao.searchFoods(qlFood.parse(filter)));
        }

    }

}
