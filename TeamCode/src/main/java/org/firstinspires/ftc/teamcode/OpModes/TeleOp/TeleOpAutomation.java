package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Actions.CustomActions;

import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOpAutomation extends LinearOpMode {


    private List<Action> runningActions = new ArrayList<>();
    double motorMaxRPM = 6000;
    double ticksPerRev = 28;
    double desiredRPM;
    double velocityB = 0;
    double velocityA;
    double velocityY;
    double velocityX;

    public Servo rgblight = null;
    private DistanceSensor distanceSensor;

    boolean isStarted = false;


    @Override
    public void runOpMode() {


        Drive drive = new Drive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);
        rgblight = hardwareMap.get(Servo.class, "Rgblight");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");

        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) distanceSensor;


        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            hopper.update();
            shooter.update();
            intake.update();
            telemetry.update();

            double distance = distanceSensor.getDistance(DistanceUnit.CM); //getFilteredDistance();
            if (distance <= 10) {
                rgblight.setPosition(0.7);
            } else {
                rgblight.setPosition(0);
            }

            if (!isStarted){
                isStarted = true;
                hopper.state = Hopper.State.DOWN;
                shooter.state = Shooter.State.CLOSE;
                intake.state = Intake.State.FORWARD;
            }


            drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);



            if (gamepad1.a) {
                //shooter.stopMotor();
                shooter.state = Shooter.State.REST;
            }
            if (gamepad1.b) {
                //shooter.setVelocityRPM(2000); //setPower(0.47)
                shooter.state = Shooter.State.MIDDLE;

            }
            if (gamepad1.y) {
                //shooter.setVelocityRPM(3100);
                shooter.state = Shooter.State.CLOSE;
            }
            if (gamepad1.x) {
                shooter.state = Shooter.State.FAR; //setPower(0.7)
            }
            if (gamepad1.dpad_up) {
                intake.state = Intake.State.FORWARD;
            }
            if (gamepad1.dpad_down) {
                intake.state = Intake.State.BACKWARD;
            }
            if (gamepad1.dpad_left) {
                intake.state = Intake.State.REST;
            }
            if (gamepad1.dpad_right) {
                intake.state = Intake.State.REST;
            }




            telemetry.addData("Shooter Power For Left Motor:", shooter.ShooterMotorLeft.getVelocity());
            //telemetry.addData("Shooter Power For Right Motor:", shooter.ShooterMotorRight.getVelocity());
            telemetry.addData("Left PIDFCoeff : ", shooter.ShooterMotorLeft.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
            telemetry.addData("State Shooter:" , shooter.state);
            telemetry.addData("Shooter telemetry: ", shooter.getShooterTelemetry());
            telemetry.addData("Hopper: ", hopper.getHopperTelemetry());
            telemetry.addData("Intake Telemetry: ", intake.getIntakeTelemetry());
            telemetry.addData("deviceName", distanceSensor.getDeviceName());
            telemetry.addData("range", distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.update();
            //hopper
          /*  if (gamepad1.right_bumper) {
                    hopper.state = Hopper.State.UP;
            }*/
            if (gamepad1.left_bumper) {
                hopper.state = Hopper.State.DOWN;
            }




            //automations
            TelemetryPacket packet = new TelemetryPacket();

            // update running actions
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            if (gamepad1.right_bumper) {
                runningActions.add(new SequentialAction(
                   customActions.hopperUp,
                   new SleepAction(0.5),
                   customActions.hopperDown
                ));
            }
        }
    }

    private double getFilteredDistance() {
        final int filter_count = 5;
        double totalDistance = 0;

        for (int i = 0; i < filter_count; i++) {
            totalDistance += distanceSensor.getDistance(DistanceUnit.CM);
            sleep(10);
        }

        double averageDistance = totalDistance / filter_count;

        return averageDistance;
    }
}
