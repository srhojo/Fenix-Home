package io.github.srhojo.fenix.ms.warehouse.dao.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity,Long>, JpaSpecificationExecutor<ShoppingListEntity> {
}
