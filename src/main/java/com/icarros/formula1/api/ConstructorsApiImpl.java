package com.icarros.formula1.api;

import com.icarros.formula1.model.Constructor;
import com.icarros.formula1.model.Race;
import com.icarros.formula1.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = {"constructors",})
public class ConstructorsApiImpl implements ConstructorsApi {

    @Autowired
    private RacesApiImpl racesApi;

    @Override
    public ResponseEntity<List<Constructor>> getAllConstructors(@Valid String name, @Valid String nationality) {
        log.info("Getting all constructors");

        ResponseEntity<List<Race>> data = racesApi.getAllRaces();

        List<Constructor> result = Objects.requireNonNull(data.getBody()).get(0).
                getResults().stream().map(Result::getConstructor).collect(Collectors.toList());

        //  Filtra por nacionalidade
        if (nationality != null && !nationality.isEmpty()) {

            log.info("Filtering by nationality: {}", nationality);
            result = result.stream().filter(constructor -> constructor.getNationality().contains(nationality)).collect(Collectors.toList());
        }

        // Filtra por nome
        if (name != null && !name.isEmpty()) {
            log.info("Filtering by name:  {}", name);
            result = result.stream().filter(constructor -> constructor.getName().contains(name)).collect(Collectors.toList());
        }

        //  Por razões paranormais o .distinct() das streams não tava removendo as duplicatas, então, fiz a implementação a seguir:
        Set<Constructor> uniques = new HashSet<>(result);

        return new ResponseEntity<>(new ArrayList<>(uniques), data.getStatusCode());
    }

    @Override
    public ResponseEntity<Constructor> getConstructorById(Integer constructorId) {
        log.info("Getting constructor of ID: {}", constructorId);

        ResponseEntity<List<Constructor>> data = this.getAllConstructors(null, null);

        Constructor result = Objects.requireNonNull(data.getBody()).stream().filter(
                constructor -> constructor.getConstructorId().equals(String.valueOf(constructorId))).findFirst().get();

        return new ResponseEntity<>(result, data.getStatusCode());
    }

}
