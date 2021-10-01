package com.icarros.formula1.api;


import com.icarros.formula1.model.F1Resource;
import com.icarros.formula1.model.Race;
import com.icarros.formula1.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = {"races",})
public class RacesApiImpl implements RacesApi {

    @Autowired
    private AllDataApiImpl allDataApi;

    @Override
    public ResponseEntity<List<Race>> getAllRaces() {
        log.info("Getting all races");
        ResponseEntity<F1Resource> data = allDataApi.getAllData();

        return new ResponseEntity<>(Objects.requireNonNull(data.getBody()).getMrData().getRaceTable().getRaces(),
                data.getStatusCode());
    }

    @Override
    public ResponseEntity<Race> getRaceByRound(Integer round) {
        log.info("Getting race round: {}", round);
        ResponseEntity<F1Resource> data = allDataApi.getAllData();

        Race result = Objects.requireNonNull(
                data.getBody()).getMrData().getRaceTable().getRaces().stream().filter(
                race -> race.getRound().equals(String.valueOf(round))).findFirst().get();

        return new ResponseEntity<>(result,
                data.getStatusCode());
    }

    @Override
    public ResponseEntity<List<Result>> getRacePodium(Integer round) {
        log.info("Getting the race podium from round {}", round);

        ResponseEntity<Race> data = this.getRaceByRound(round);

        List<Result> result = Objects.requireNonNull(data.getBody()).getResults().stream().filter(
                raceResult -> Integer.parseInt(raceResult.getPosition()) <= 3)
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, data.getStatusCode());

    }
}
