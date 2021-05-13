package com.gaja.country.endpoint;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.gaja.country.stubs.Country;
import com.gaja.country.stubs.GetCountryRequest;
import com.gaja.country.stubs.GetCountryResponse;
import com.gaja.model.CountryMO;
import com.gaja.service.CountryServiceImpl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://gaja.com/country/stubs";

    private CountryServiceImpl service;

    @Autowired
    public CountryEndpoint(CountryServiceImpl service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        CountryMO findCountry = this.service.findCountry(request.getName());
        Country outputObj = new Country();
        BeanUtils.copyProperties(findCountry, outputObj);
        response.setCountry(outputObj);
        return response;
    }
}