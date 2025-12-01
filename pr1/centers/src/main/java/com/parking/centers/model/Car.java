package com.parking.centers.model;

import com.parking.centers.Enums.CarBrand;
import com.parking.centers.Enums.CarModel;
import com.parking.centers.Enums.Colors;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Car {
    private String regNumber;
    private String carModel;
    private String color;

    public Car(String regNumber, String carModel, String color){
        this.regNumber = regNumber;
        this.carModel = carModel;
        this.color = color;
    }

    public Car(){
        this(null, null, null);
    }
}
