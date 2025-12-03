package com.parking.centers.service;

import com.parking.centers.config.ServiceConfig;
import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ParkingService {
    @Autowired
    MessageSource messages;
    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    ServiceConfig config;

    public List<ParkingOperation> getParkingOperationsByNode(String nodeName) {
        return parkingRepository.findByParkingNodeName(nodeName);
    }

    public List<ParkingOperation> getActiveParkings() {
        return parkingRepository.findByEndTimeIsNull();
    }


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

    public String createParkingOperation(ParkingOperation parkingOperation, String parkingCenterName, String parkingPlaceNo, Locale locale){
        String responseMessage = "poor";

        if (parkingOperation != null){
            parkingOperation.setParkingNodeName(parkingCenterName);
            parkingOperation.setPlaceNo(parkingPlaceNo);
            responseMessage = String.format(messages.getMessage("parkingSystem.create.message", null, locale), parkingOperation.toString());
            System.out.println("NOT POOR");
        }
        return responseMessage;
    }

    public String updateParkingOperation(ParkingOperation parkingOperation,String newRegNum, Locale locale){
        String responceMessage = null;
        if (parkingOperation != null){
            parkingOperation.setCar(new Car(newRegNum));
            responceMessage = String.format(messages.getMessage("parkingSystem.update.message", null, locale), parkingOperation.getRegNumOfParkingAct(), newRegNum);
        }
        return responceMessage;
    }

    public String deleteParkingOperation(int id, Locale locale){
        return String.format(messages.getMessage("parkingSystem.delete.message", null, locale), id);
    }
}
