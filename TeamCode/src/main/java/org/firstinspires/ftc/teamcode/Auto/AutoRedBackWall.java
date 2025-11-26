package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Positions;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Action.CustomActions;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@Autonomous(name = "AutoRedBackWall", group = "Auto")
public class AutoRedBackWall extends LinearOpMode {

    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(0, 0, PI*0.0));
        Drive drive = new Drive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);

        customActions.update();

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
                                customActions.shootMiddle,
                                new SleepAction(2),
                                Positions.MoveForward.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
                                Positions.TurningRed.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(1),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(1),
                                customActions.hopperUp,
                                new SleepAction(1),
                                customActions.hopperDown,
                                new SleepAction(1),
                                Positions.EndingRed.runToExact,
                                customActions.stopDrive,
                                customActions.stopShooter


                        )
                )
        );
    }
}
