package com.parking.centers.controller;

import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "parkingCenters")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping(value = "/{parkingNodeName}/{parkingPlaceNo}/{id}/{carRegNum}")
    public ResponseEntity<ParkingOperation> getParkingOperation(
            @PathVariable("id") int regOperationNo,
            @PathVariable("carRegNum") String carRegNum,
            @PathVariable("parkingNodeName") String parkingNodeName,
            @PathVariable("parkingPlaceNo") String parkingPlaceNo
    ){
        ParkingOperation parkingOperation = parkingService.getParkingOperation(regOperationNo, parkingNodeName, parkingPlaceNo, new Car(carRegNum));
        return ResponseEntity.ok(parkingOperation);
    }

    @PostMapping("/{parkingNodeName}/{parkingPlaceNo}")
    public ResponseEntity<String> createParkingOperation(
            @PathVariable ("parkingNodeName") String parkingNodeName,
            @PathVariable ("parkingPlaceNo") String parkingPlaceNo,
            @RequestBody ParkingOperation parkingOperation,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        return ResponseEntity.ok(parkingService.createParkingOperation(parkingOperation, parkingNodeName, parkingPlaceNo, locale));
    }

    @PutMapping("/updParking/{newCarRegNum}")
    public  ResponseEntity<String> updateParkingOperation(
            @PathVariable ("newCarRegNum") String newCarRegNum,
            @RequestBody ParkingOperation parkingOperation,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ) {

        return ResponseEntity.ok(parkingService.updateParkingOperation(parkingOperation, newCarRegNum, locale));
    }

    @DeleteMapping("/deleteParkingOperation/{id}")
    public ResponseEntity<String> deleteParkingOperation(
            @PathVariable("id") int id,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        return ResponseEntity.ok(parkingService.deleteParkingOperation(id, locale));
    }
}

