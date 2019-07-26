package io.github.srhojo.fenix.ms.warehouse.controllers;

import java.util.List;

import io.github.srhojo.fenix.microservices.FenixController;
import io.github.srhojo.fenix.microservices.logger.FenixLogger;
import io.github.srhojo.fenix.ms.warehouse.api.WarehouseUtils;
import io.github.srhojo.fenix.ms.warehouse.clients.VectaliaClient;
import io.github.srhojo.fenix.ms.warehouse.clients.entities.VectaliaLines;

@FenixController
@FenixLogger
public class WarehouseUtilsController implements WarehouseUtils {

    private final VectaliaClient vectaliaClient;

    // TODO: Call service not client directly...
    public WarehouseUtilsController(final VectaliaClient vectaliaClient) {
        this.vectaliaClient = vectaliaClient;
    }

    @Override
    public VectaliaLines getBusStopInfo(final Integer id) {

        return vectaliaClient.getBusStopInfo(id);
    }

    @Override
    public List<VectaliaLines> getBusStopInfo(final List<Integer> id) {
        return vectaliaClient.getBusStopInfoParalell(id);
    }
}
