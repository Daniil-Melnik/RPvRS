package com.parking.centers.service;

import com.parking.centers.config.ServiceConfig;
import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.repository.CarRepository;
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
    private CarRepository carRepository;
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

    public String createParkingOperation(ParkingOperation parkingOperation,
                                         String parkingCenterName,
                                         String parkingPlaceNo,
                                         Locale locale) {

        String responseMessage = "poor";

        if (parkingOperation != null) {
            // Сохраняем автомобиль
            Car car = parkingOperation.getCar();
            carRepository.save(car);

            // Устанавливаем параметры
            parkingOperation.setParkingNodeName(parkingCenterName);
            parkingOperation.setPlaceNo(parkingPlaceNo);
            parkingOperation.setCar(car);

            // Сохраняем в базу
            ParkingOperation saved = parkingRepository.save(parkingOperation);

            responseMessage = String.format(
                    messages.getMessage("parkingSystem.create.message", null, locale),
                    saved.toString()
            );
            System.out.println("NOT POOR - Created with ID: " + saved.getRegNumOfParkingAct());
        }

        return responseMessage;
    }

    public String updateParkingOperation(ParkingOperation parkingOperation,
                                         String newRegNum,
                                         Locale locale) {

        String responseMessage = null;

        if (parkingOperation != null) {
            // Получаем существующую запись
            ParkingOperation existing = parkingRepository
                    .findById(parkingOperation.getRegNumOfParkingAct())
                    .orElse(null);

            if (existing != null) {
                // Обновляем автомобиль
                Car newCar = new Car(newRegNum);
                carRepository.save(newCar);
                existing.setCar(newCar);

                // Обновляем другие поля если они есть
                if (parkingOperation.getEndTime() != null) {
                    existing.setEndTime(parkingOperation.getEndTime());
                }
                if (parkingOperation.getStartTime() != null) {
                    existing.setStartTime(parkingOperation.getStartTime());
                }
                if (parkingOperation.getPlaceNo() != null) {
                    existing.setPlaceNo(parkingOperation.getPlaceNo());
                }
                if (parkingOperation.getParkingNodeName() != null) {
                    existing.setParkingNodeName(parkingOperation.getParkingNodeName());
                }

                // Сохраняем изменения
                parkingRepository.save(existing);

                responseMessage = String.format(
                        messages.getMessage("parkingSystem.update.message", null, locale),
                        existing.getRegNumOfParkingAct(),
                        newRegNum
                );
            }
        }

        return responseMessage;
    }

    public String deleteParkingOperation(int id, Locale locale) {
        // Удаляем из базы
        parkingRepository.deleteById(id);

        return String.format(
                messages.getMessage("parkingSystem.delete.message", null, locale),
                id
        );
    }
}

