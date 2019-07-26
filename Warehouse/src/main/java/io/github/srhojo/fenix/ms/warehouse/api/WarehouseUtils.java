package io.github.srhojo.fenix.ms.warehouse.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.srhojo.fenix.ms.warehouse.clients.entities.VectaliaLines;

import java.util.List;

public interface WarehouseUtils {

    @GetMapping("/utils/busstop/{id}")
    VectaliaLines getBusStopInfo(@PathVariable Integer id);


    @GetMapping("/utils/busstop")
    List<VectaliaLines> getBusStopInfo(@RequestParam("busId") List<Integer> id);
}
