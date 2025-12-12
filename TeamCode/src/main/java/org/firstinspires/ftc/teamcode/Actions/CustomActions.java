package org.firstinspires.ftc.teamcode.Actions;
import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Subsystems.Hopper;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.roadrunner.Action;

public class CustomActions {
    Shooter shooter = Shooter.instance;
    Hopper hopper = Hopper.instance;
    Intake intake = Intake.instance;
    public Drive drive = Drive.instance;
    public static CustomActions instance;

    public CustomActions(HardwareMap hardwareMap) {
        instance = this;
    }

    public void update() {

        hopper.update();
        shooter.update();
        intake.update();
    }

    public Action stopDrive = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            drive.stopDrive();

            return false;
        }
    };

    public Action hopperUp = new Action() {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            hopper.state = Hopper.State.UP;

            return !hopper.isTargetReached;
        }

    };

    public Action hopperDown = new Action() {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            hopper.state = Hopper.State.DOWN;

            return !hopper.isTargetReached;
        }
    };

    public Action intakeForward = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.state = Intake.State.FORWARD;

            return !intake.isTargetReached;
        }
    };

    public Action shootFront = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.state = Shooter.State.CLOSE;

            return !shooter.isVelReached;
        }
    };
    public Action shootMiddle = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.state = Shooter.State.MIDDLE;

            return !shooter.isVelReached;
        }
    };
    public Action shootFar = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.state = Shooter.State.FAR;

            return !shooter.isVelReached;
        }
    };

    public Action shootMiddleBlue = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.state = Shooter.State.SHOOTMIDBLUE;

            return !shooter.isVelReached;
        }
    };


    public Action shootBack = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.state = Shooter.State.SHOOTBACK;

            return !shooter.isVelReached;
        }
    };

    public Action stopShooter = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shooter.state = Shooter.State.REST;

            return !shooter.isVelReached;
        }
    };
    public Action stopIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.state = Intake.State.OFF;

            return !Intake.instance.isTargetReached;
        }
    };



}
