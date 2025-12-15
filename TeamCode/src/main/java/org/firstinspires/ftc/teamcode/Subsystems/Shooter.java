package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;


public class Shooter {

    public static Shooter instance;
    public State state = State.REST;
    public boolean isTargetReached = false;
    public DcMotorEx ShooterMotorLeft;
    public DcMotorEx ShooterMotorRight;
    DcMotorEx motorExLeft;
    public boolean isVelReached = true;
    public static final double NEW_P = 55.0;
    public static final double NEW_I = 0.0;
    public static final double NEW_D = 0.0;
    public static final double NEW_F = 0.000357;
    PIDFCoefficients pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);

    public Shooter(HardwareMap hardwareMap) {
        ShooterMotorLeft = hardwareMap.get(DcMotorEx.class, "ShooterMotorLeft");
        //ShooterMotorRight = hardwareMap.get(DcMotorEx.class, "ShooterMotorRight");
        ShooterMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //ShooterMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ShooterMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //ShooterMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        ShooterMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //ShooterMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ShooterMotorLeft.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);
        instance = this;
    }

    public void setVelocityRPM(double targetRPM) {
        // Prevent setting a velocity above the motor's capability.
        // Convert RPM to ticks per second.
        double targetVelocityTPS = (targetRPM / 60) * 28;
        ShooterMotorLeft.setVelocity(targetVelocityTPS);
        //ShooterMotorRight.setVelocity(targetVelocityTPS);
    }

    public void stopMotor() {
        ShooterMotorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ShooterMotorLeft.setPower(0.0);
        //ShooterMotorRight.setPower(0.0);
    }

    public void setPower() {
        ShooterMotorLeft.setPower(0.75);
    }

    public enum State {
        AUTOCLOSE,
        CLOSE,
        MIDDLE,
        FAR,
        AUTOFAR,
        REST,
        SHOOTMID,
        SHOOTMIDBLUE,
        SHOOTBACK
    }

    public void update() {
        switch (state) {
            case AUTOCLOSE:
                setVelocityRPM(710);
                break;
            case CLOSE:
                setVelocityRPM(750);
                break;
            case MIDDLE:
                setVelocityRPM(820);
                break;
            case FAR:
                setVelocityRPM(1400);
            case AUTOFAR:
                setVelocityRPM(1400);
            case REST:
                ShooterMotorLeft.setPower(0);
                break;
            case SHOOTMID:
                setVelocityRPM(3400);
                break;
            case SHOOTMIDBLUE:
                setVelocityRPM(3200);
                break;
            case SHOOTBACK:
                setVelocityRPM(4600);
                break;
        }

       /* if ((state == State.CLOSE) && ((ShooterMotorLeft.getCurrent(CurrentUnit.AMPS) > 5 || ShooterMotorLeft.getCurrent(CurrentUnit.AMPS) > 5))) {
            ShooterMotorLeft.setPower(0);
            ShooterMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else if ((state == State.MIDDLE) && ((ShooterMotorLeft.getCurrent(CurrentUnit.AMPS) > 5 || ShooterMotorLeft.getCurrent(CurrentUnit.AMPS) > 5))) {
            ShooterMotorLeft.setPower(0);
            ShooterMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else if ((state == State.FAR) && ((ShooterMotorLeft.getCurrent(CurrentUnit.AMPS) > 5 || ShooterMotorLeft.getCurrent(CurrentUnit.AMPS) > 5))) {
            ShooterMotorLeft.setPower(0);
            ShooterMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }*/
        if (state == Shooter.State.AUTOCLOSE && ShooterMotorLeft.getVelocity() >= 700) {
            isTargetReached = true;
        } else if (state == Shooter.State.MIDDLE && ShooterMotorLeft.getVelocity() >= 100) {
            isTargetReached = true;
        } else if (state == State.AUTOFAR && ShooterMotorLeft.getPower() >=1300) {
            isTargetReached = true;
        } else if (state == Shooter.State.REST && ShooterMotorLeft.getPower() == 0) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }


    }


        public String getShooterTelemetry(){
            String telemetry = "";
            telemetry = telemetry + "\n Shooter Velocity = " + ShooterMotorLeft.getVelocity();
            telemetry = telemetry + "\n Shooter State = " + state;
            telemetry = telemetry + "\n ";
            return telemetry;
        }
    }

