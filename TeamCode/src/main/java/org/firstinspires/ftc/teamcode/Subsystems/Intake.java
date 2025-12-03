package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Intake {
    public static Intake instance;
    //public Intake.State state = Intake.State.CLOSE;
    public DcMotorEx IntakeMotor;
    public Intake.State state = Intake.State.OFF;
    public boolean isTargetReached = false;
    public static final double NEW_P = 50.0;
    public static final double NEW_I = 0.0;
    public static final double NEW_D = 0.0;
    public static final double NEW_F = 0.000357;
    PIDFCoefficients pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);

    public Intake(HardwareMap hardwareMap) {
        IntakeMotor = hardwareMap.get(DcMotorEx.class, "IntakeMotor");
        IntakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);
        instance = this;
    }

    public void stopMotor() {
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        IntakeMotor.setPower(0.0);
    }

    public enum State {
        OFF,
        ON
    }

    public void update() {
        switch (state) {
            case OFF:
                IntakeMotor.setPower(0);
                break;
            case ON:
                IntakeMotor.setPower(0.25);
                break;
        }

        if (state == State.OFF && IntakeMotor.getPower() == 0) {
            isTargetReached = true;
        } else if (state == State.ON && IntakeMotor.getPower() == 1) {
            isTargetReached = true;
        } else {
            isTargetReached = false;
        }

    }
}
