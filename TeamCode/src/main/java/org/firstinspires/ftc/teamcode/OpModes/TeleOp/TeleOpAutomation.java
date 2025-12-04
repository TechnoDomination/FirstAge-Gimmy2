package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
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

    boolean isStarted = false;


    @Override
    public void runOpMode() {


        Drive drive = new Drive(hardwareMap);

        Shooter shooter = new Shooter(hardwareMap);
        Hopper hopper = new Hopper(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        CustomActions customActions = new CustomActions(hardwareMap);




        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            hopper.update();
            shooter.update();
            intake.update();
            telemetry.update();

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




            telemetry.addData("Shooter Power For Left Motor:", shooter.ShooterMotorLeft.getVelocity());
            //telemetry.addData("Shooter Power For Right Motor:", shooter.ShooterMotorRight.getVelocity());
            telemetry.addData("Left PIDFCoeff : ", shooter.ShooterMotorLeft.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
            telemetry.addData("State Shooter:" , shooter.state);
            telemetry.addData("Shooter telemetry: ", shooter.getShooterTelemetry());
            telemetry.update();
            //hopper
            if (gamepad1.right_bumper) {
                if (shooter.state == Shooter.State.CLOSE || shooter.state == Shooter.State.FAR || shooter.state == Shooter.State.MIDDLE) {
                    hopper.state = Hopper.State.UP;
                }
            }
            if (gamepad1.left_bumper) {
                hopper.state = Hopper.State.DOWN;
            }


            telemetry.addData("Hopper: ", hopper.getHopperTelemetry());

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

            if (gamepad1.dpad_right) {
                runningActions.add(new SequentialAction(
                   customActions.hopperUp,
                   new SleepAction(0.5),
                   customActions.hopperDown
                ));
            }
        }
    }
}
