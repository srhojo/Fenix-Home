package io.github.srhojo.fenix.ms.warehouse.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import io.github.srhojo.fenix.microservices.exceptions.ExceptionConstants;
import io.github.srhojo.fenix.microservices.exceptions.FenixException;
import io.github.srhojo.fenix.ms.warehouse.dao.repositories.ShoppingListRepository;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.ShoppingListEntity;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseShoppingListDaoImplTest {

    @InjectMocks
    @Spy
    private WarehouseShoppingListDaoImpl warehouseShoppingListDao;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Test
    public void getById() {
        // Given
        final Long id = 1L;
        given(shoppingListRepository.findById(id)).willReturn(Optional.of(new ShoppingListEntity()));

        // When
        final ShoppingListEntity result = warehouseShoppingListDao.getById(id);

        // Then
        assertNotNull(result);
    }

    @Test(expected = FenixException.class)
    public void getById_notFound() {
        // Given
        final Long id = 1L;
        given(shoppingListRepository.findById(id)).willReturn(Optional.empty());

        // When
        warehouseShoppingListDao.getById(id);

        // Then
        // Exception
    }

    @Test
    public void search() {
        // Given
        final Specification<ShoppingListEntity> specification = Mockito.mock(Specification.class);
        given(shoppingListRepository.findAll(specification)).willReturn(Collections.EMPTY_LIST);

        // When
        final List<ShoppingListEntity> results = warehouseShoppingListDao.search(specification);

        // Then
        assertNotNull(results);
    }

    @Test
    public void search_pagination() {
        // Given
        final Specification<ShoppingListEntity> specification = Mockito.mock(Specification.class);
        final Pageable pageable = Mockito.mock(Pageable.class);
        given(shoppingListRepository.findAll(specification, pageable)).willReturn(Mockito.mock(Page.class));

        // When
        final Page<ShoppingListEntity> results = warehouseShoppingListDao.search(specification, pageable);

        // Then
        assertNotNull(results);
    }

    @Test
    public void create() {
        // Given
        final ShoppingListEntity entity = new ShoppingListEntity();
        given(shoppingListRepository.save(entity)).willReturn(new ShoppingListEntity());

        // When
        final ShoppingListEntity result = warehouseShoppingListDao.create(entity);

        // Then
        assertNotNull(result);
    }

    @Test
    public void create_updateException() {
        // Given
        final ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(1L);

        // When
        try {

            warehouseShoppingListDao.create(entity);
        } catch (final FenixException wex) {
            // Then
            assertEquals(HttpStatus.METHOD_NOT_ALLOWED, wex.getStatus());
            Assert.assertEquals(ExceptionConstants.ERRORS_DAO_NOT_UPDATE_CODE, wex.getCode());
        }
    }

    @Test
    public void create_saveException() {
        // Given
        final ShoppingListEntity entity = new ShoppingListEntity();
        given(shoppingListRepository.save(entity)).willThrow(new RuntimeException());

        // When
        try {
            warehouseShoppingListDao.create(entity);
        } catch (final FenixException wex) {
            // Then
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, wex.getStatus());
            Assert.assertEquals(ExceptionConstants.ERRORS_DAO_SAVE_SHOPPING_LIST_CODE, wex.getCode());
        }
    }

    @Test
    public void update() {
        // Given
        final ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(1L);
        given(shoppingListRepository.findById(entity.getId())).willReturn(Optional.of(new ShoppingListEntity()));
        given(shoppingListRepository.save(entity)).willReturn(new ShoppingListEntity());

        // When
        final ShoppingListEntity response = warehouseShoppingListDao.update(entity);

        // Then
        assertNotNull(response);
        verify(warehouseShoppingListDao, times(1)).getById(entity.getId());
        verify(shoppingListRepository, times(1)).save(entity);
    }

    @Test
    public void delete() {
        // Given
        final Long id = 1L;
        given(shoppingListRepository.findById(id)).willReturn(Optional.of(new ShoppingListEntity()));
        final ArgumentCaptor<ShoppingListEntity> shoppingListEntityArgumentCaptor = ArgumentCaptor
                .forClass(ShoppingListEntity.class);
        doNothing().when(shoppingListRepository).delete(shoppingListEntityArgumentCaptor.capture());

        // When
        warehouseShoppingListDao.delete(id);

        // Then
        verify(shoppingListRepository, times(1)).delete(shoppingListEntityArgumentCaptor.getValue());
    }
}