package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hopper {
    private final Servo HopperServo;
    public State state = State.REST;
    public boolean isTargetReached = false;
    public static Hopper instance;
    private final double upPos = 0.35;
    private final double downPos = 1;
    private final double restPos = 1;

    public enum State {
        UP,
        DOWN,
        REST
    }

    public Hopper (HardwareMap hardwareMap) {
        HopperServo = hardwareMap.get(Servo.class, "HopperServo");

        instance = this;
    }
    public void update() {
        switch (state) {
            case UP:
                HopperServo.setPosition(upPos);
                break;
            case DOWN:
                HopperServo.setPosition(downPos);
                break;
            case REST:
                HopperServo.setPosition(restPos);
                break;
        }

        if (state == State.UP && HopperServo.getPosition() == upPos) {
            isTargetReached = true;
        } else if (state == State.DOWN && HopperServo.getPosition() == downPos) {
            isTargetReached = true;
        } else if (state == State.REST && HopperServo.getPosition() == downPos) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }
    }

    public String getHopperTelemetry() {
        String telemetry = "";
        telemetry = telemetry + "\n Hopper Pos = " + HopperServo.getPosition();
        telemetry = telemetry + "\n ";
        return telemetry;
    }

}
