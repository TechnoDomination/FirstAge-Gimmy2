package org.firstinspires.ftc.teamcode.Actions;

import static org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer.pose;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Angle;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class SetDriveTimer implements Action {


    public double driveSpeed;
    public double maxTime;
    Poses pose;

    ElapsedTime timer = new ElapsedTime();
    public boolean started = false;

    public SetDriveTimer(Poses pose) {
        this.pose = pose;
    }

    /*public SetDriveTimer(Poses pose, double driveSpeed, double maxTime) {
        targetPose = pose;
        this.driveSpeed = driveSpeed;
        this.maxTime = maxTime;
    }

    public SetDriveTimer(Poses pose) {
        this(pose, 1.0, 8.0);
    }

     */


    public boolean run(@NonNull TelemetryPacket packet) {

        if (!started) {
            started = true;
            timer.reset();
            SharedPose.targetPose = pose;

        }


        double xError = Localizer.pose.getX() - pose.getX();
        double yError = Localizer.pose.getY() - pose.getY();
        double headingError = Angle.INSTANCE.wrap(
                -Localizer.pose.getHeading() + pose.getHeading()
            );


            boolean targetReached =
                    Math.abs(xError) <= 3.0 &&
                            Math.abs(yError) <= 3.0 &&
                            Math.abs(headingError) <= Math.toRadians(5.0);

            boolean isComplete = targetReached || timer.seconds() > maxTime;
            return isComplete;

        //return false;
    }
}