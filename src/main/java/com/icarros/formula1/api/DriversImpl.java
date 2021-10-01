package com.icarros.formula1.api;


import com.icarros.formula1.model.Driver;
import com.icarros.formula1.model.Race;
import com.icarros.formula1.model.RaceResults;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Api(tags = {"drivers",})
class DriversImpl implements DriversApi {

    @Autowired
    private RacesApiImpl racesApi;

    @Override
    public ResponseEntity<List<Driver>> getAllDrivers(@Valid String nationality, @Valid String name, @Valid String code) {
        ResponseEntity<List<Race>> data = racesApi.getAllRaces();
        List<Driver> result = Objects.requireNonNull(data.getBody()).get(0).
                getResults().stream().map(RaceResults::getDriver).collect(Collectors.toList());

        //  Filtra por nacionalidade
        if (nationality != null && !nationality.isEmpty()) {
            result = result.stream().filter(driver -> driver.getNationality().contains(nationality)).collect(Collectors.toList());
        }

        // Filtra por nome, podendo ser tanto nome quanto sobrenome
        if (name != null && !name.isEmpty()){
            result = result.stream().filter(driver -> driver.getGivenName().contains(name)
                    | driver.getFamilyName().contains(name)).collect(Collectors.toList());
        }

        return new ResponseEntity<>(result, data.getStatusCode());
    }

    @Override
    public ResponseEntity<Driver> getDriverById(Integer driverId) {

        ResponseEntity<List<Race>> data = racesApi.getAllRaces();

        return new ResponseEntity<>((Driver) Objects.requireNonNull(
                data.getBody()).get(0).getResults().stream().filter(
                result -> Integer.valueOf(result.getDriver().getDriverId()).equals(driverId)),
                data.getStatusCode());
    }


}
