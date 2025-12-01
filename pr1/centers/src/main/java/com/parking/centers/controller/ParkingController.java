package com.parking.centers.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "parkingCenters/{parkingCenterName}/center")
public class ParkingController {
}
