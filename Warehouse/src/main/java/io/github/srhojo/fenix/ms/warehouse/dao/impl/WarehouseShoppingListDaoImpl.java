package io.github.srhojo.fenix.ms.warehouse.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants;
import io.github.srhojo.fenix.microservices.exceptions.FenixException;
import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseShoppingListDao;
import io.github.srhojo.fenix.ms.warehouse.dao.repositories.ShoppingListRepository;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;

@Repository
@Transactional
class WarehouseShoppingListDaoImpl implements WarehouseShoppingListDao {

    private final ShoppingListRepository shoppingListRepository;

    public WarehouseShoppingListDaoImpl(final ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity getById(final Long id) {
        return shoppingListRepository.findById(id)
                .orElseThrow(() -> new FenixException(HttpStatus.NOT_FOUND,
                        ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                        String.format(ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShoppingListEntity> search(final Specification<ShoppingListEntity> specification) {
        return shoppingListRepository.findAll(specification);
    }

    @Override
    public Page<ShoppingListEntity> search(final Specification<ShoppingListEntity> specification,
            final Pageable pageable) {
        return shoppingListRepository.findAll(specification, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity create(final ShoppingListEntity shoppingListEntity) {
        if (!StringUtils.isEmpty(shoppingListEntity.getId())) {
            throw new FenixException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE,
                    ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return save(shoppingListEntity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingListEntity update(final ShoppingListEntity shoppingListEntity) {
        this.getById(shoppingListEntity.getId());
        return save(shoppingListEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Long id) {
        final ShoppingListEntity entity = this.getById(id);
        shoppingListRepository.delete(entity);
    }

    private ShoppingListEntity save(final ShoppingListEntity shoppingListEntity) {
        try {
            return shoppingListRepository.save(shoppingListEntity);
        } catch (final Exception ex) {
            throw new FenixException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ExceptionConstants.ERRORS_DAO_SAVE_SHOPPING_LIST_CODE,
                    String.format(ExceptionConstants.ERRORS_DAO_SAVE_SHOPPING_LIST_MESSAGE, shoppingListEntity,
                            ex.getMessage()));
        }
    }
}
