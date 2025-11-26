package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


public class Shooter {

    public static Shooter instance;
    public State state = State.CLOSE;
    public boolean isTargetReached = false;
    public DcMotorEx ShooterMotorLeft;
    public DcMotorEx ShooterMotorRight;
    DcMotorEx motorExLeft;
    public boolean isVelReached = true;
    public static final double NEW_P = 50.0;
    public static final double NEW_I = 0.0;
    public static final double NEW_D = 0.0;
    public static final double NEW_F = 0.000357;
    PIDFCoefficients pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);

    public Shooter(HardwareMap hardwareMap) {
        ShooterMotorLeft = hardwareMap.get(DcMotorEx.class, "ShooterMotorLeft");
        ShooterMotorRight = hardwareMap.get(DcMotorEx.class, "ShooterMotorRight");
        ShooterMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //ShooterMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ShooterMotorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
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
        CLOSE,
        MIDDLE,
        FAR,
        REST,
        SHOOTMID,
        SHOOTMIDBLUE,
        SHOOTBACK
    }

    public void update() {
        switch (state) {
            case CLOSE:
                setVelocityRPM(3100);
                break;
            case MIDDLE:
                setVelocityRPM(3600);
                break;
            case FAR:
                setVelocityRPM(4600);
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

        if (state == Shooter.State.CLOSE && ShooterMotorLeft.getVelocity() == 3100) {
            isTargetReached = true;
        } else if (state == Shooter.State.MIDDLE && ShooterMotorLeft.getVelocity() == 3600) {
            isTargetReached = true;
        } else if (state == Shooter.State.REST && ShooterMotorLeft.getPower() == 0) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }


    }
}

