package com.parking.centers.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ParkingOperation {
    private int regNumOfParkingAct;
    private Date startTime;
    private Date endTime;
    private String placeNo;
    private ParkingNode parkingNode;
}
