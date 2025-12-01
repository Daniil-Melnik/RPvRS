package com.parking.centers.controller;

import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "parkingCenters/{parkingNodeName}/{parkingPlaceNo}")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping(value = "/{id}/{carRegNum}/{carModel}/{carColor}")
    public ResponseEntity<ParkingOperation> getParkingOperation(
            @PathVariable("id") int regOperationNo,
            @PathVariable("carRegNum") String carRegNum,
            @PathVariable("carModel") String carModel,
            @PathVariable("carColor") String carColor,
            @PathVariable("parkingNodeName") String parkingNodeName,
            @PathVariable("parkingPlaceNo") String parkingPlaceNo
    ){
        ParkingOperation parkingOperation = parkingService.getParkingOperation(regOperationNo, parkingNodeName, parkingPlaceNo, new Car(carRegNum, carModel, carColor));
        return ResponseEntity.ok(parkingOperation);
    }

    @PostMapping
    public ResponseEntity<String> createParkingOperation(
            @PathVariable ("parkingNodeName") String parkingNodeName,
            @PathVariable ("parkingPlaceNo") String parkingPlaceNo,
            @RequestBody ParkingOperation parkingOperation
    ){
        return ResponseEntity.ok(parkingService.createParkingOperation(parkingOperation, parkingNodeName, parkingPlaceNo));
    }
}
