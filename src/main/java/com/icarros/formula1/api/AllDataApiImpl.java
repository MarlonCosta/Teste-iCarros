package com.icarros.formula1.api;

import com.icarros.formula1.model.F1Resource;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Api(tags = {"races",})
public class AllDataApiImpl implements AllDataApi {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<F1Resource> getAllData() {
        return restTemplate.getForEntity("https://ergast.com/api/f1/2017/last/results.json", F1Resource.class);
    }
}
