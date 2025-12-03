package com.parking.centers.controller;

import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Locale;

@RestController
@RequestMapping(value = "parkingCenters")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @Autowired
    MessageSource messages;

    @GetMapping(value = "/{parkingNodeName}/{parkingPlaceNo}/{id}/{carRegNum}")
    public ResponseEntity<ParkingOperation> getParkingOperation(
            @PathVariable("id") int regOperationNo,
            @PathVariable("carRegNum") String carRegNum,
            @PathVariable("parkingNodeName") String parkingNodeName,
            @PathVariable("parkingPlaceNo") String parkingPlaceNo,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        ParkingOperation parkingOperation = parkingService.getParkingOperation(regOperationNo, parkingNodeName, parkingPlaceNo, new Car(carRegNum));

        parkingOperation.add(linkTo(methodOn(ParkingController.class).getParkingOperation(regOperationNo, carRegNum, parkingNodeName,parkingPlaceNo, locale)).withSelfRel());
        parkingOperation.add(linkTo(methodOn(ParkingController.class).createParkingOperation(parkingNodeName, parkingPlaceNo, parkingOperation, null)).withRel(messages.getMessage("parkingSystem.create.link.message", null, locale)));
        parkingOperation.add(linkTo(methodOn(ParkingController.class).updateParkingOperation(carRegNum, parkingOperation,null)).withRel(messages.getMessage("parkingSystem.update.link.message", null, locale)));
        parkingOperation.add(linkTo(methodOn(ParkingController.class).deleteParkingOperation(regOperationNo, null)).withRel(messages.getMessage("parkingSystem.delete.link.message", null, locale)));

        return ResponseEntity.ok(parkingOperation);
    }

    @PostMapping("/{parkingNodeName}/{parkingPlaceNo}")
    public ResponseEntity<String> createParkingOperation(
            @PathVariable ("parkingNodeName") String parkingNodeName,
            @PathVariable ("parkingPlaceNo") String parkingPlaceNo,
            @RequestBody ParkingOperation parkingOperation,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        //System.out.println(parkingService.createParkingOperation(parkingOperation, parkingNodeName, parkingPlaceNo, locale));
        System.out.println((messages.getMessage("parkingSystem.create.message", null, locale)));
        System.out.println(locale.getLanguage().toString());
        return ResponseEntity.ok(parkingService.createParkingOperation(parkingOperation, parkingNodeName, parkingPlaceNo, locale));
    }

    @PutMapping("/updParking/{newCarRegNum}")
    public  ResponseEntity<String> updateParkingOperation(
            @PathVariable ("newCarRegNum") String newCarRegNum,
            @RequestBody ParkingOperation parkingOperation,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ) {
        System.out.println((messages.getMessage("parkingSystem.update.message", null, locale)));
        System.out.println(locale.getLanguage().toString());
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

