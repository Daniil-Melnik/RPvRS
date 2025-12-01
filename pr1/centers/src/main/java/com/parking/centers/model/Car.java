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
    private CarBrand carBrand;
    private CarModel carModel;
    private Colors color;
}
