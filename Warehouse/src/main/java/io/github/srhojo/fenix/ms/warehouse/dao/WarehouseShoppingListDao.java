package io.github.srhojo.fenix.ms.warehouse.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;

import java.util.List;

public interface WarehouseShoppingListDao {

    ShoppingListEntity getById(Long id);

    List<ShoppingListEntity> search(Specification<ShoppingListEntity> specification);

    Page<ShoppingListEntity> search(Specification<ShoppingListEntity> specification, Pageable pageable);

    ShoppingListEntity create(ShoppingListEntity shoppingListEntity);

    ShoppingListEntity update(ShoppingListEntity shoppingListEntity);

    void delete(Long id);
}
