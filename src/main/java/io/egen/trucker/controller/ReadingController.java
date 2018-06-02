package io.egen.trucker.controller;

import io.egen.trucker.entity.Alert;
import io.egen.trucker.entity.Reading;
import io.egen.trucker.entity.Tyre;
import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.service.ReadingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@RestController
@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@Api(value = "Vehicle Reading and Alerts operations")
public class ReadingController {

    @Autowired
    ReadingService readingService;

    @ApiOperation(value = "POST Vehicle Readings",
            notes = "POST Vehicle Readings database and generates ALERTS")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.POST, value = "readings",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Async
    public void injectReadings(@RequestBody Reading reading) {
        readingService.injectReading(reading);
    }


    @ApiOperation(value = "Fetch vechicles ability to list it's geolocation within last 30minutes on a map",
            notes = "Fetch vechicles ability to list it's geolocation within last 30minutes on a map")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET, value = "fetchVehicleByGeo",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> fetchVehicleByGeo(@ApiParam(value = "Vehicle vin value",required = true)@RequestParam(value="vin", required=true)String vin,
                                           @ApiParam(value = "sort order. Takes ASC for ascending order and DESC for decending order only",required = true)@RequestParam(value="sort", required=true)String sort){
        List<Reading> readings = readingService.fetchVehicleByGeo(vin,sort);
        return readings;
    }


    @ApiOperation(value = "Ability to list a vehicle's all historical alerts",
            notes = "Ability to list a vehicle's all historical alerts")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET, value = "fetchAlertsByVehicle",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> fetchAlertsByVehicle(@ApiParam(value = "Vehicle vin value",required = true)@RequestParam(value="vin", required=true)String vin){
        List<Alert> alerts = readingService.fetchAlertsByVehicle(vin);
        return alerts;
    }
}
