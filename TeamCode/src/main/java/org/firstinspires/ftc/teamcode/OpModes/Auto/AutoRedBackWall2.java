package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.CustomActions;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterHood;
import org.firstinspires.ftc.teamcode.Util.AllianceManager;
import org.firstinspires.ftc.teamcode.Util.Positions;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.WebcamAndSensors.LimelightHelper;

@Autonomous(name = "AutoRedBackWall", group = "Autonomous")
public class AutoRedBackWall2 extends LinearOpMode {

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


                            telemetry.addData("X pos", Localizer.pose.getX());
                            telemetry.addData("Y pos", Localizer.pose.getY());
                            telemetry.addData("Heading pos",- Localizer.pose.getHeading());
                            //for(String string: customActions.getTelemetry()) telemetry.addLine(string);
                            telemetry.update();

                            return true;
                        },

                        new SequentialAction(
                                customActions.shootFarRed,
                                customActions.intakeForward,
                               new SleepAction(1.5),
                                Positions.NewTurningRed.runToExact,
                                customActions.stopDrive,
                                new SleepAction(2.5),
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
                                Positions.RedIntakeTape3Start.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
                                customActions.slowIntake,
                                Positions.RedIntakeTape3End.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
                                Positions.NewTurningRed2.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
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
                                Positions.NewEndingRed.runToExact,
                                customActions.stopDrive,
                                customActions.stopShooter,
                                customActions.stopIntake


                        )
                )
        );
    }
}
