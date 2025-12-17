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
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Util.Positions;

@Autonomous(name = "AutoBlueBackWallMiddle", group = "Autonomous")
public class AutoBlueBackWall3 extends LinearOpMode {

    Double hopperUpTime = 0.5;
    Double hopperDownTime = 1.0;
    Double driveTime = 1.0;

    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(-12, -52, PI*0.0));
        Drive drive = new Drive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);


      //  customActions.update();

        waitForStart();

        Actions.runBlocking(
                new ParallelAction(
                        telemetryPacket -> {
                            localizer.update();
                            customActions.update();


                            telemetry.addData("X pos", Localizer.pose.getX());
                            telemetry.addData("Y pos", Localizer.pose.getY());
                            telemetry.addData("Heading pos",- Localizer.pose.getHeading());
                            //for(String string: customActions.getTelemetry()) telemetry.addLine(string);
                            telemetry.update();

                            return true;
                        },

                        new SequentialAction(
                                customActions.intakeForward,
                                customActions.shootMiddleBlue,
                                new SleepAction(driveTime),
                                Positions.ShootingPositionsBlueMiddle.runToExact,
                                customActions.stopDrive,
                                Positions.ShootingPositionsBlueMiddleTurn.runToExact,
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                Positions.BlueIntakeTape3Start.runToExact,
                                customActions.stopDrive,
                                //new SleepAction(driveTime),
                                customActions.slowIntake,
                                Positions.BlueIntakeTape3End.runToExact,
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                Positions.BlueIntakeTape3Start.runToExact,
                                customActions.stopDrive,
                                //new SleepAction(driveTime),
                                Positions.ShootingPositionsBlueMiddleTurn.runToExact,
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                Positions.BlueIntakeTape2MidStart.runToExact,
                                customActions.stopDrive,
                                //new SleepAction(driveTime),
                                customActions.slowIntake,
                                Positions.BlueIntakeTape2MidEnd.runToExact,
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                Positions.BlueIntakeTape2MidStart.runToExact,
                                customActions.stopDrive,
                                //new SleepAction(driveTime),
                                Positions.ShootingPositionsBlueMiddleTurn.runToExact,
                                customActions.stopDrive,
                                new SleepAction(driveTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                customActions.hopperUp,
                                new SleepAction(hopperUpTime),
                                customActions.hopperDown,
                                new SleepAction(hopperDownTime),
                                Positions.NewEndingBlue.runToExact,
                                customActions.stopDrive,
                                customActions.stopShooter,
                                customActions.stopIntake
                        )
                )
        );
    }
}
