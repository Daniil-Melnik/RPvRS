package com.parking.centers.service;

import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Service
public class ParkingService {
    public ParkingOperation getParkingOperation(int id, String parkingNodeName, String placeNo, Car car){
        ParkingOperation parkingOperation = new ParkingOperation();

        parkingOperation.setRegNumOfParkingAct(id);
        parkingOperation.setParkingNodeName(parkingNodeName);
        parkingOperation.setStartTime(new Date());
        parkingOperation.setEndTime(null);
        parkingOperation.setPlaceNo(placeNo);
        parkingOperation.setCar(car);

        return parkingOperation;
    }

    public String createParkingOperation(ParkingOperation parkingOperation, String parkingCenterName, String parkingPlaceNo){
        String responseMessage = null;

        if (parkingOperation != null){
            parkingOperation.setParkingNodeName(parkingCenterName);
            parkingOperation.setPlaceNo(parkingPlaceNo);
            responseMessage = String.format("This is the post and the object is: %s", parkingOperation.toString());
        }
        return responseMessage;
    }

    public ParkingOperation updateParkingOperation(ParkingOperation parkingOperation,String newRegNum){
        if (parkingOperation != null){
            parkingOperation.setCar(new Car(newRegNum));
        }
        return parkingOperation;
    }

    public String deleteParkingOperation(int id){
        return String.format("Parking operation with id = %d was deleted", id);
    }
}
