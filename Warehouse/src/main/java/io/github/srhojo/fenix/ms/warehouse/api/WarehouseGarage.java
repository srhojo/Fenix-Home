package io.github.srhojo.fenix.ms.warehouse.api;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import io.github.srhojo.fenix.microservices.exceptions.FenixExceptionResponse;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleEntity;
import io.github.srhojo.fenix.ms.warehouse.domain.entities.VehicleInspection;
import io.github.srhojo.fenix.ms.warehouse.domain.requests.VehicleInspectionRequest;
import io.github.srhojo.utils.cdm.ContainerList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/garage")
@Api(consumes = "application/json", produces = "application/jsons")
public interface WarehouseGarage {

    @GetMapping("/vehicles")
    @ApiOperation(value = "Method to retrieve vehicles", response = ContainerList.class, nickname = "getVehicles", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve vehicles successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    ContainerList<VehicleEntity> searchVehicles(@RequestParam(value = "filter", required = false) String filter,
            @ApiParam(name = "limit", example = "2") @Valid @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
            @ApiParam(name = "offset", example = "0") @Valid @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset);

    @GetMapping("/vehicles/{id}")
    @ApiOperation(value = "Method to retrieve vehicle by its id", response = VehicleEntity.class, nickname = "getVehicleById", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve vehicle successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    VehicleEntity getVehicleById(@PathVariable Long id);

    @PostMapping("/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Method to create a vehicle", response = VehicleEntity.class, nickname = "addVehicle", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle created successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    VehicleEntity addVehicle(@RequestBody VehicleEntity request);

    @PutMapping("/vehicles/{id}")
    @ApiOperation(value = "Method to update a vehicle", response = VehicleEntity.class, nickname = "updateVehicle", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle updated successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json") })
    VehicleEntity updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleEntity request);

    @PatchMapping(value = "/vehicles/{id}/set-image", consumes = { "multipart/form-data" })
    @ApiOperation(value = "Method set a vehicle image", response = VehicleEntity.class, nickname = "updateVehicleImage", httpMethod = "PATCH", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Set vehicle image successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "multipart/form-data") })
    VehicleEntity updateVehicleImage(@PathVariable Long id, @RequestPart(name = "image") MultipartFile[] files);

    @DeleteMapping(value = "/vehicles/{id}")
    @ApiOperation(value = "Delete vehicle successfully", nickname = "deleteVehicle", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete vehicle successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = FenixExceptionResponse.class) })
    void deleteVehicle(@PathVariable Long id);

    @GetMapping("/vehicles/{vehicleId}/inspections/{inspectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    VehicleInspection getVehicleInspections(@PathVariable Long vehicleId, @PathVariable Long inspectionId);

    @PostMapping("/vehicles/{vehicleId}/inspections")
    @ResponseStatus(HttpStatus.CREATED)
    VehicleEntity addVehicleInspection(@PathVariable Long vehicleId,
            @RequestBody @Valid VehicleInspectionRequest request);

    @PutMapping("/vehicles/{vehicleId}/inspections/{inspectionId}")
    VehicleEntity updateVehicleInspection(@PathVariable Long vehicleId, @PathVariable Long inspectionId,
            @RequestBody @Valid VehicleInspectionRequest request);

    @DeleteMapping("/vehicles/{vehicleId}/inspections/{inspectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeVehicleInspection(@PathVariable Long vehicleId, @PathVariable Long inspectionId);

}
