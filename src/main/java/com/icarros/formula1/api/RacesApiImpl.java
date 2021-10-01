package com.icarros.formula1.api;


import com.icarros.formula1.model.F1Resource;
import com.icarros.formula1.model.Race;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = {"races",})
class RacesApiImpl implements RacesApi {

    @Autowired
    private AllDataApiImpl allDataApi;

    @Override
    public ResponseEntity<List<Race>> getAllRaces() {
        ResponseEntity<F1Resource> data = allDataApi.getAllData();

        return new ResponseEntity<>(Objects.requireNonNull(data.getBody()).getMrData().getRaceTable().getRaces(),
                data.getStatusCode());
    }
}
