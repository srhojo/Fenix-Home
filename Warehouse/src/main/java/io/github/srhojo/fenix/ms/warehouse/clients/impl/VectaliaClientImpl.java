package io.github.srhojo.fenix.ms.warehouse.clients.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import io.github.srhojo.fenix.ms.warehouse.clients.VectaliaClient;
import io.github.srhojo.fenix.ms.warehouse.clients.entities.VectaliaLines;
import io.github.srhojo.utils.restclient.client.RestClient;
import io.github.srhojo.utils.restclient.entities.NameValuePair;
import io.github.srhojo.utils.restclient.entities.RestRequest;
import io.github.srhojo.utils.restclient.impl.RestClientBuilderExecutor;
import io.github.srhojo.utils.restclient.impl.RestClientParallelExecutor;

@Component
public class VectaliaClientImpl implements VectaliaClient {

    private final RestClient restClient;

    @Value("${vectalia.alicante.api.url}")
    private String url;

    @Value("${vectalia.alicante.api.path.estimateStop}")
    private String estimatePathStop;

    public VectaliaClientImpl(final RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public VectaliaLines getBusStopInfo(final Integer busStopId) {
        final String fullUrl = url + estimatePathStop;
        final List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(NameValuePair.of("lang", "es"));
        queryParams.add(NameValuePair.of("__internal__", "1"));
        queryParams.add(NameValuePair.of("code", String.valueOf(busStopId)));

        return RestClientBuilderExecutor.of(restClient).method(HttpMethod.GET).url(fullUrl).withQueryParams(queryParams)
                .withResponseType(VectaliaLines.class).execute();

    }

    @Override
    public List<VectaliaLines> getBusStopInfoParalell(final List<Integer> busStopId) {
        final String fullUrl = url + estimatePathStop;
        final RestClientParallelExecutor parallelExecutor = RestClientParallelExecutor.of(restClient);

        busStopId.forEach(id -> {
            final List<NameValuePair> queryParams = new ArrayList<>();
            queryParams.add(NameValuePair.of("lang", "es"));
            queryParams.add(NameValuePair.of("__internal__", "1"));
            queryParams.add(NameValuePair.of("code", String.valueOf(id)));

            final RestRequest restRequest = new RestRequest(fullUrl, HttpMethod.GET);
            restRequest.getQueryParams().addAll(queryParams);
            restRequest.setResponseType(VectaliaLines.class);

            parallelExecutor.addRequest(restRequest);
        });

        return parallelExecutor.execute();
    }
}
