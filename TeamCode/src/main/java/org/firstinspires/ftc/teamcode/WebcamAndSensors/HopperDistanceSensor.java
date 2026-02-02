package org.firstinspires.ftc.teamcode.WebcamAndSensors;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class HopperDistanceSensor {
    public static HopperDistanceSensor instance;
    public DistanceSensor distanceSensor;

    public HopperDistanceSensor(HardwareMap hardwareMap){
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");
        instance = this;
    }

    public double getFilteredDistance() throws InterruptedException {
        final int filter_count = 2;
        double totalDistance = 0;

        for (int i = 0; i < filter_count; i++) {
            totalDistance += distanceSensor.getDistance(DistanceUnit.CM);
            sleep(10);
        }

        double averageDistance = totalDistance / filter_count;

        return averageDistance;
    }
    boolean distanceSensorWorking(double distance){
        return !Double.isNaN(distance) && distance > 0 && distance < 200;
    }

    public boolean isBallinHopper() throws InterruptedException {
        double distance = getFilteredDistance();
        if (distance <= 10) {
            return true;
        } else {
            return false;
        }
    }

}
