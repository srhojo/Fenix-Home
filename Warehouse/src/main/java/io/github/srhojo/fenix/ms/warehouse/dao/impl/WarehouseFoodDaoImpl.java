package io.github.srhojo.fenix.ms.warehouse.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants;
import io.github.srhojo.fenix.microservices.exceptions.FenixException;
import io.github.srhojo.fenix.ms.warehouse.dao.WarehouseFoodDao;
import io.github.srhojo.fenix.ms.warehouse.dao.repositories.FoodRepository;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.FoodEntity;

@Repository
@Transactional
class WarehouseFoodDaoImpl implements WarehouseFoodDao {

    private final FoodRepository foodRepository;

    public WarehouseFoodDaoImpl(final FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity createFood(final FoodEntity foodEntity) {
        if (foodRepository.existsById(foodEntity.getName())) {
            throw new FenixException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE,
                    ExceptionConstants.ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return save(foodEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity updateFood(final FoodEntity foodEntity) {
        this.getFood(foodEntity.getName());
        return save(foodEntity);
    }

    private FoodEntity save(final FoodEntity foodEntity) {
        try {
            return foodRepository.save(foodEntity);
        } catch (final Exception ex) {
            throw new FenixException(HttpStatus.METHOD_NOT_ALLOWED, ExceptionConstants.ERRORS_DAO_SAVE_FOOD_CODE, String
                    .format(ExceptionConstants.ERRORS_DAO_SAVE_FOOD_MESSAGE, foodEntity.toString(), ex.getMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodEntity getFood(final String name) {
        return foodRepository.findById(name)
                .orElseThrow(() -> new FenixException(HttpStatus.NOT_FOUND,
                        ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                        String.format(ExceptionConstants.ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, name)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodEntity> searchFoods(final Specification<FoodEntity> specification) {
        return foodRepository.findAll(specification);
    }

    @Override
    public Page<FoodEntity> searchFoods(final Specification<FoodEntity> specification, final Pageable pageable) {
        return foodRepository.findAll(specification, pageable);
    }
}
