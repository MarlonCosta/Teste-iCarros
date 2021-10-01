package com.icarros.formula1.api;

import com.icarros.formula1.model.Constructor;
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
@Api(tags = {"constructors",})
public class ConstructorsApiImpl implements ConstructorsApi {

    @Autowired
    private RacesApiImpl racesApi;

    @Override
    public ResponseEntity<List<Constructor>> getAllConstructors(@Valid String name, @Valid String nationality) {    ResponseEntity<List<Race>> data = racesApi.getAllRaces();
        return new ResponseEntity<>(Objects.requireNonNull(data.getBody()).get(0).
                getResults().stream().map(RaceResults::getConstructor).collect(Collectors.toList()), data.getStatusCode());
    }

    @Override
    public ResponseEntity<Constructor> getConstructorById(Integer constructorId) {
        ResponseEntity<List<Race>> data = racesApi.getAllRaces();

        return new ResponseEntity<>((Constructor) Objects.requireNonNull(
                data.getBody()).get(0).getResults().stream().filter(
                result -> Integer.valueOf(result.getConstructor().getConstructorId()).equals(constructorId)),
                data.getStatusCode());
    }

}
