package io.egen.trucker.controller;

import io.egen.trucker.entity.Vehicle;
import io.egen.trucker.service.VehicleService;
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
@Api(value = "Vehicle operations")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @ApiOperation(value = "PUT Vehicles",
                    notes = "PUT vehicles into database")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.PUT, value="vehicles",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Async
    public void updateVehicles(@RequestBody List<Vehicle> vehicles) {
         vehicleService.updateVechicles(vehicles);
    }

    @RequestMapping(method = RequestMethod.GET, value = "fetchVehicles",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle fetchVehicles(@RequestParam(value = "vin",required = true)String vin){
        Vehicle vehicle = vehicleService.fetchVehicle(vin);
        return vehicle;
    }

    @ApiOperation(value = "Fetch ALL Vehicles",
            notes = "fetches all vehicles")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET, value = "fetchAllVehicles",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> fetchAllVehicles() {
        List<Vehicle> vehicles = vehicleService.fetchAllVehicles();
        return vehicles;
    }

    @ApiOperation(value = "Fetch Vehicles By Alert Priority",
            notes = "Fetch HIGH alerts within last 2 hours for all the vehicles and ability to sort list of vehicles based on it")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(method = RequestMethod.GET, value = "fetchVehiclesByAlerts",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> fetchVehiclesByAlerts(@ApiParam(value = "alert type. Takes HIGH, MEDIUM and LOW values only",required = true) @RequestParam(value="alert", required=true)String alert,
                                               @ApiParam(value = "sort order. Takes ASC for ascending order and DESC for decending order only",required = true) @RequestParam(value="sort", required=true)String sort){
        List<Vehicle> vehicles = vehicleService.fetchVehicleByAlerts(alert,sort);
        return vehicles;
    }
}
