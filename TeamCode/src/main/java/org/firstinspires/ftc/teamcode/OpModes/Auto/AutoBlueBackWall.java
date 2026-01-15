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
import org.firstinspires.ftc.teamcode.Util.AllianceManager;
import org.firstinspires.ftc.teamcode.Util.Positions;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Actions.CustomActions;
import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.WebcamAndSensors.LimelightHelper;


@Autonomous(name = "AutoBlueBackWall", group = "Autonomous")
public class AutoBlueBackWall extends LinearOpMode {

    @Override
    public void runOpMode() {

        Localizer localizer = new Localizer(hardwareMap, new Poses(0, 0, PI*0.0));
        Drive drive = new Drive(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);
        LimelightHelper limelightHelper = new LimelightHelper(hardwareMap);
        AllianceManager alliance = new AllianceManager();
        limelightHelper.setAlliance(false);

      //  customActions.update();

        waitForStart();

        Actions.runBlocking(
                new ParallelAction(
                        telemetryPacket -> {
                            localizer.update();
                            customActions.update();
                            alliance.blueAlliance();
                            alliance.offRedAlliance();

                            telemetry.addData("X pos", Localizer.pose.getX());
                            telemetry.addData("Y pos", Localizer.pose.getY());
                            telemetry.addData("Heading pos",- Localizer.pose.getHeading());
                            //for(String string: customActions.getTelemetry()) telemetry.addLine(string);
                            telemetry.update();

                            return true;
                        },

                        new SequentialAction(
                                customActions.shootMiddleBlue,
                                new SleepAction(2),
                                Positions.MoveForward.runToExact,
                                customActions.stopDrive,
                                new SleepAction(1),
                                Positions.TurningBlue.runToExact,
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
                                Positions.EndingBlue.runToExact,
                                customActions.stopDrive,
                                customActions.stopShooter
                        )
                )
        );
    }
}
