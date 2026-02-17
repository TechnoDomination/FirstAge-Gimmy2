package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.CustomActions;
import org.firstinspires.ftc.teamcode.Actions.SharedPose;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterHood;
import org.firstinspires.ftc.teamcode.Util.AllianceManager;
import org.firstinspires.ftc.teamcode.Util.Positions;
import org.firstinspires.ftc.teamcode.WebcamAndSensors.LimelightHelper;

@Autonomous(name = "AutoRedBackWallMiddle", group = "Autonomous")
public class AutoRedBackWall3 extends LinearOpMode {

    Double hopperUpTime = 0.5;
    Double hopperDownTime = 1.0;
    Double driveTime = 1.0;
    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(12, -52, PI*0.0));
        Drive drive = new Drive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        ShooterHood shooterHood = new ShooterHood(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);
        LimelightHelper limelightHelper = new LimelightHelper(hardwareMap);
        AllianceManager alliance = new AllianceManager();
        limelightHelper.setAlliance(true);




        //   customActions.update();

        waitForStart();

        Actions.runBlocking(
                new ParallelAction(
                        telemetryPacket -> {
                            localizer.update();
                            customActions.update();
                            alliance.redAlliance();
                            alliance.offBlueAlliance();
                            SharedPose.runToExactAlways(SharedPose.targetPose);

                            telemetry.addData("X pos", Localizer.pose.getX());
                            telemetry.addData("Y pos", Localizer.pose.getY());
                            telemetry.addData("Heading pos",- Localizer.pose.getHeading());
                            //for(String string: customActions.getTelemetry()) telemetry.addLine(string);
                            telemetry.update();

                            return true;
                        },

                        new SequentialAction(
                                customActions.intakeForward,
                                customActions.shootMiddleRed,
                                //new SleepAction(driveTime),
                                Positions.ShootingPositionsRedMiddle.runToExact(),
                                customActions.stopDrive,
                                Positions.ShootingPositionsRedMiddleTurn.runToExact(),
                                customActions.stopDrive,
                               new SleepAction(driveTime),
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
                                new SleepAction(0.4),
                                customActions.hopperUp,
                                new SleepAction(0.35),
                                customActions.hopperDown,
                                Positions.RedIntakeTape3Start.runToExact(),
                                customActions.stopDrive,
                                //new SleepAction(driveTime),
                                customActions.slowIntake,
                                Positions.RedIntakeTape3End.runToExact(),
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                Positions.RedIntakeTape3Start.runToExact(),
                                customActions.stopDrive,
                                Positions.ShootingPositionsRedMiddleTurn.runToExact(),
                                customActions.stopDrive,
                                new SleepAction(driveTime),
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
                                new SleepAction(0.4),
                                customActions.hopperUp,
                                new SleepAction(0.35),
                                customActions.hopperDown,
                                Positions.RedIntakeTape2MidStart.runToExact(),
                                customActions.stopDrive,
                                customActions.slowIntake,
                                Positions.RedIntakeTape2MidEnd.runToExact(),
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                Positions.RedIntakeTape2MidStart.runToExact(),
                                customActions.stopDrive,
                                Positions.ShootingPositionsRedMiddleTurn.runToExact(),
                                customActions.stopDrive,
                                new SleepAction(driveTime),
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
                                new SleepAction(0.4),
                                customActions.hopperUp,
                                new SleepAction(0.35),
                                customActions.hopperDown,
                                new SleepAction(1),
                                Positions.NewEndingRed.runToExact(),
                                customActions.stopDrive,
                                customActions.stopShooter,
                                customActions.stopIntake


                        )
                )
        );
    }
}
