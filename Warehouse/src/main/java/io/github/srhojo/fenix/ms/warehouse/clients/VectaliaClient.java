package io.github.srhojo.fenix.ms.warehouse.clients;

import java.util.List;

import io.github.srhojo.fenix.ms.warehouse.clients.entities.VectaliaLines;

public interface VectaliaClient {

    VectaliaLines getBusStopInfo(Integer busStopId);


    List<VectaliaLines> getBusStopInfoParalell(List<Integer> busStopId);
}
