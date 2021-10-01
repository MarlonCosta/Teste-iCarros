package com.icarros.formula1.api;


import com.icarros.formula1.model.Driver;
import com.icarros.formula1.model.Race;
import com.icarros.formula1.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = {"drivers",})
public class DriversImpl implements DriversApi {

    @Autowired
    private RacesApiImpl racesApi;

    @Override
    public ResponseEntity<List<Driver>> getAllDrivers(@Valid String nationality, @Valid String name, @Valid String constructor) {
        log.info("Getting all drivers");

        ResponseEntity<List<Race>> data = racesApi.getAllRaces();

        @Valid List<Result> resultList = Objects.requireNonNull(data.getBody()).get(0).
                getResults();

        List<Driver> result = resultList.stream().map(Result::getDriver).collect(Collectors.toList());

        //  Filtra por nacionalidade
        if (nationality != null && !nationality.isEmpty()) {

            log.info("Filtering by nationality: {}", nationality);
            result = result.stream().filter(driver -> driver.getNationality().contains(nationality))
                    .collect(Collectors.toList());
        }

        // Filtra por nome, podendo ser tanto nome, quanto sobrenome
        if (name != null && !name.isEmpty()) {
            log.info("Filtering by name:  {}", name);
            result = result.stream().filter(driver -> driver.getGivenName().contains(name)
                    | driver.getFamilyName().contains(name)).collect(Collectors.toList());
        }

        //Filtra pela construtora
        if (constructor != null) {
            log.info("Filtering by constructor: {}", constructor);
            result = resultList.stream().filter(res -> res.getConstructor().getName().equalsIgnoreCase(constructor))
                    .map(Result::getDriver).collect(Collectors.toList());
        }

        return new ResponseEntity<>(result, data.getStatusCode());
    }

    @Override
    public ResponseEntity<Driver> getDriverById(String driverId) {
        log.info("Getting driver by ID");
        ResponseEntity<List<Driver>> data = this.getAllDrivers(null, null, null);

        Driver result = (Driver) Objects.requireNonNull(data.getBody()).stream().filter(
                driver -> driver.getDriverId().equals(driverId));

        return new ResponseEntity<>(result, data.getStatusCode());
    }

}
