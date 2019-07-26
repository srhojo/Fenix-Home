package io.github.srhojo.fenix.ms.warehouse.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.srhojo.fenix.microservices.FenixController;
import io.github.srhojo.fenix.microservices.exceptions.FenixExceptionResponse;
import io.github.srhojo.fenix.microservices.logger.FenixLogger;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.FoodEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.FoodRequest;
import io.github.srhojo.fenix.ms.warehouse.services.WarehouseFoodService;
import io.github.srhojo.utils.cdm.ContainerList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@FenixController
@FenixLogger
@RequestMapping("/foods")
@Api(consumes = "application/json", produces = "application/json")
public class WarehouseFoodController {

    private final WarehouseFoodService warehouseFoodService;

    public WarehouseFoodController(final WarehouseFoodService warehouseFoodService) {
        this.warehouseFoodService = warehouseFoodService;
    }

    @GetMapping()
    @ApiOperation(value = "Food search method", response = ContainerList.class, nickname = "searchFood", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve foods successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    public ContainerList<FoodEntity> searchFood(@RequestParam(value = "filter", required = false) final String filter,
            @ApiParam(name = "limit", example = "2") @Valid @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
            @ApiParam(name = "offset", example = "0") @Valid @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseFoodService.searchFoods(filter, limit, offset);
    }

    @PostMapping()
    @ApiOperation(value = "Create new food", response = FoodEntity.class, nickname = "createFood", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create foods successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    public FoodEntity createFood(@RequestBody @Valid final FoodRequest food) {
        return warehouseFoodService.createFood(food);
    }

    @PutMapping("/{name}")
    @ApiOperation(value = "Update food", response = FoodEntity.class, nickname = "updateFood", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create foods successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    public FoodEntity updateFood(@PathVariable("name") final String name, @RequestBody @Valid final FoodRequest food) {
        food.setName(name);
        return warehouseFoodService.updateFood(food);
    }

}
