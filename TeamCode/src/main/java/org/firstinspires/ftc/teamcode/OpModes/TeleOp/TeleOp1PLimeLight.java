package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Actions.CustomActions;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterHood;
import org.firstinspires.ftc.teamcode.Util.AllianceManager;
import org.firstinspires.ftc.teamcode.WebcamAndSensors.LimelightHelper;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name = "TeleOp 1P LimeLight", group = "TeleOp")
public class TeleOp1PLimeLight extends LinearOpMode {


    private List<Action> runningActions = new ArrayList<>();
    double motorMaxRPM = 6000;
    double ticksPerRev = 28;
    double desiredRPM;
    double velocityB = 0;
    double velocityA;
    double velocityY;
    double velocityX;
    double shooterPowerDistance = 0.0;

    public Servo rgblight = null;
    private DistanceSensor distanceSensor;

    boolean isStarted = false;


    @Override
    public void runOpMode() {


        Drive drive = new Drive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        ShooterHood shooterHood = new ShooterHood(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);
        AllianceManager allianceManager = new AllianceManager();
        rgblight = hardwareMap.get(Servo.class, "Rgblight");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");
        LimelightHelper limelightHelper = new LimelightHelper(hardwareMap);

        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) distanceSensor;

        if (!allianceManager.isRedAlliance && !allianceManager.isBlueAlliance) {
            allianceManager.isRedAlliance = true;
        }

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            hopper.update();
            shooter.update();
            intake.update();
            telemetry.update();
            shooterHood.update();

            //double distance = distanceSensor.getDistance(DistanceUnit.CM);
            double distance = getFilteredDistance();
            if (distance <= 10) {
                rgblight.setPosition(0.7);
            } else {
                rgblight.setPosition(0);
            }



            limelightHelper.isReadyToShoot();
            shooterPowerDistance = shooter.ShooterPowerDistance(limelightHelper.getDistance());
            telemetry.addData("limelight telemetry: ", limelightHelper.getLimelightTelemetry());
            telemetry.addData("Shooter Power Distance: ", shooterPowerDistance);
            telemetry.addData("Shooter telemetry: ", shooter.getShooterTelemetry());
            telemetry.addData("Alliance telemetry: ", allianceManager.getAllianceManagerTelemetry() );
            telemetry.update();

            if (!isStarted){
                isStarted = true;
                hopper.state = Hopper.State.DOWN;
                shooter.state = Shooter.State.CLOSE;
                //shooter.setVelocityRPM(shooter.ShooterPowerDistance(limelightHelper.getDistance()));
                intake.state = Intake.State.FORWARD;
                shooterHood.state = ShooterHood.State.CLOSE;
            }


            drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            shooter.setVelocityRPM(shooterPowerDistance);


            if (shooterPowerDistance < 4000){
                shooterHood.state = ShooterHood.State.DOWN;
                //shooter.state = Shooter.State.CLOSE;
            } else if (shooterPowerDistance >= 4000 && shooterPowerDistance < 7200) {
                shooterHood.state = ShooterHood.State.CLOSE;
            }else if (shooterPowerDistance >= 7200 && shooterPowerDistance < 9500) {
                shooterHood.state = ShooterHood.State.MIDDLE;
                //shooter.state = Shooter.State.MIDDLE;
            } else {
                shooterHood.state = ShooterHood.State.UP;
                //shooter.state = Shooter.State.FAR;
            }


            if (gamepad1.a) {
               // shooter.state = Shooter.State.REST;
                //shooter.state = Shooter.State.REST;
                //shooter.state = Shooter.State.TOOCLOSE;
                shooterHood.state = ShooterHood.State.DOWN;
            }
            if (gamepad1.b) {
                //shooter.setVelocityRPM(2000); //setPower(0.47)
                //shooter.state = Shooter.State.MIDDLE;
                shooterHood.state = ShooterHood.State.MIDDLE;
               // shooter.setVelocityRPM(shooter.ShooterPowerDistance(80));
            }
            if (gamepad1.y) {
                //shooter.setVelocityRPM(3100);
                //shooter.state = Shooter.State.CLOSE;
                shooterHood.state = ShooterHood.State.MIDDLE;
               // shooter.setVelocityRPM(shooter.ShooterPowerDistance(60));
            }
            if (gamepad1.x) {
               // shooter.state = Shooter.State.FAR; //setPower(0.7)
                //shooter.state = Shooter.State.FAR;
                shooterHood.state = ShooterHood.State.UP;
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




          /*  telemetry.addData("Shooter Power For Left Motor:", shooter.ShooterMotorLeft.getVelocity());
            //telemetry.addData("Shooter Power For Right Motor:", shooter.ShooterMotorRight.getVelocity());
            telemetry.addData("Left PIDFCoeff : ", shooter.ShooterMotorLeft.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
            telemetry.addData("State Shooter:" , shooter.state);
            telemetry.addData("Shooter telemetry: ", shooter.getShooterTelemetry());
            telemetry.addData("Hopper: ", hopper.getHopperTelemetry());
            telemetry.addData("Intake Telemetry: ", intake.getIntakeTelemetry());
            telemetry.addData("deviceName", distanceSensor.getDeviceName());
            telemetry.addData("range", distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("ShooterHood telemetry", shooterhood.getShooterHoodTelemetry());
            telemetry.update();

           */
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
                        customActions.stopIntake,
                        new SleepAction(0.25),
                        customActions.hopperUp,
                        new SleepAction(0.35),
                        customActions.hopperDown,
                        new SleepAction(0.35),
                        customActions.intakeFeed,
                        new SleepAction(.5),
                        //customActions.stopIntake,
                        //new SleepAction(0.1),
                        customActions.hopperUp,
                        new SleepAction(0.35),
                        customActions.hopperDown,
                        new SleepAction(0.35),
                        customActions.hopperUp,
                        new SleepAction(0.35),
                        customActions.hopperDown,
                       // new SleepAction(2),
                        customActions.intakeForward

                ));
            }
        }
    }

    private double getFilteredDistance() {
        final int filter_count = 2;
        double totalDistance = 0;

        for (int i = 0; i < filter_count; i++) {
            totalDistance += distanceSensor.getDistance(DistanceUnit.CM);
            sleep(10);
        }

        double averageDistance = totalDistance / filter_count;

        return averageDistance;
    }
}
