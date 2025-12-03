package com.parking.centers.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@Getter
@Setter
@ToString
public class ParkingOperation extends RepresentationModel<ParkingOperation> {
    private int regNumOfParkingAct;
    private Date startTime;
    private Date endTime;
    private String placeNo;
    private String parkingNodeName;
    private Car car;
}
