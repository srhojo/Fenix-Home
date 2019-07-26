package io.github.srhojo.fenix.ms.warehouse.services.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseShoppingListDao;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.mappers.ShoppingListItemMapper;
import io.github.srhojo.fenix.ms.warehouse.domain.mappers.ShoppingListMapper;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListItemsRequest;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.ShoppingListRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseShoppingListService;
import io.github.srhojo.utils.cdm.ContainerList;
import io.github.srhojo.utils.cdm.OffsetPagination;
import io.github.srhojo.utils.commons.ql.QueryLanguajeComponentImpl;
import io.github.srhojo.utils.commons.ql.domain.OffsetPaginationRequest;

@Service
class WarehouseShoppingListServiceImpl implements WarehouseShoppingListService {

    private final WarehouseShoppingListDao warehouseShoppingListDao;
    private final ShoppingListMapper shoppingListMapper;
    private final ShoppingListItemMapper shoppingListItemMapper;
    private final QueryLanguajeComponentImpl<ShoppingListEntity> qlShoppingList;

    public WarehouseShoppingListServiceImpl(final WarehouseShoppingListDao warehouseShoppingListDao,
            final ShoppingListMapper shoppingListMapper, final ShoppingListItemMapper shoppingListItemMapper,
            final QueryLanguajeComponentImpl<ShoppingListEntity> qlShoppingList) {
        this.warehouseShoppingListDao = warehouseShoppingListDao;
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListItemMapper = shoppingListItemMapper;
        this.qlShoppingList = qlShoppingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity getShoppingListById(final Long id) {
        return warehouseShoppingListDao.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerList<ShoppingListEntity> getShoppingLists(final String filter, final Integer limit,
            final Long offset) {
        if ((offset != null) && (limit != null)) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<ShoppingListEntity> shoppingListEntityPage = warehouseShoppingListDao
                    .search(qlShoppingList.parse(filter), pageable);
            final OffsetPagination offsetPagination = new OffsetPagination(limit, offset,
                    shoppingListEntityPage.getTotalElements());
            return new ContainerList<>(shoppingListEntityPage.get().collect(Collectors.toList()), offsetPagination);

        } else {
            return new ContainerList<>(warehouseShoppingListDao.search(qlShoppingList.parse(filter)));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity saveShoppingList(final ShoppingListRequest shoppingListRequest) {
        final ShoppingListEntity shoppingListEntity = shoppingListMapper.mapToInner(shoppingListRequest);
        return StringUtils.isEmpty(shoppingListEntity.getId()) ? warehouseShoppingListDao.create(shoppingListEntity)
                : warehouseShoppingListDao.update(shoppingListEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity editShoppingListItems(final ShoppingListItemsRequest shoppingListItems) {
        final ShoppingListEntity entity = warehouseShoppingListDao.getById(shoppingListItems.getShoppingListId());
        entity.setItems(shoppingListItemMapper.mapToInner(shoppingListItems));
        return warehouseShoppingListDao.update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteShoppingList(final Long id) {
        warehouseShoppingListDao.delete(id);
    }
}
