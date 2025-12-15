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
import org.firstinspires.ftc.teamcode.Util.Positions;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@Autonomous(name = "AutoBlueBackWall", group = "Autonomous")
public class AutoBlueBackWall2 extends LinearOpMode {

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
                                customActions.shootFar,
                                customActions.intakeForward,
                                new SleepAction(1),
                                Positions.NewTurningBlue.runToExact,
                                new SleepAction(1),
                                customActions.stopDrive,
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(2),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(2),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(2),
                                Positions.BlueIntakeTape3Start.runToExact,
                                customActions.stopDrive,
                                customActions.slowIntake,
                                new SleepAction(1),
                                Positions.BlueIntakeTape3End.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
                                Positions.NewTurningBlue2.runToExact,
                                new SleepAction(1),
                                customActions.stopDrive,
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(2),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(2),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(2),
                                Positions.NewEndingBlue.runToExact,
                                customActions.stopDrive,
                                customActions.stopShooter,
                                customActions.stopIntake
                        )
                )
        );
    }
}
