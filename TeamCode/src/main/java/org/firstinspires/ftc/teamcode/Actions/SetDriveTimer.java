package org.firstinspires.ftc.teamcode.Actions;

import static org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Localizer.pose;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Angle;
import org.firstinspires.ftc.teamcode.GoBildaPinPointOdo.Poses;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

public class SetDriveTimer {

    public final Poses targetPose;
    public final double driveSpeed;
    public final double maxTime;
    Vector2d targetVector;
    double rotation;

    public final ElapsedTime timer = new ElapsedTime();
    public boolean started = false;

    public SetDriveTimer(Vector2d vector2d, double rotation2d) {
        this.targetVector = vector2d;
        this.rotation = rotation2d;
    }

    public SetDriveTimer(Poses pose, double driveSpeed, double maxTime) {
        this.targetPose = pose;
        this.driveSpeed = driveSpeed;
        this.maxTime = maxTime;
    }

    public SetDriveTimer(Poses pose) {
        this(pose, 1.0, 8.0);
    }


    public boolean run(@NonNull TelemetryPacket packet) {

        if (!started) {
            started = true;
            timer.reset();



            double xError = targetPose.getX() - pose.getX();
            double yError = targetPose.getY() - pose.getY();
            double headingError = Angle.INSTANCE.wrap(
                    -targetPose.getHeading() + pose.getHeading()
            );



            boolean targetReached =
                    Math.abs(xError) <= 3.0 &&
                            Math.abs(yError) <= 3.0 &&
                            Math.abs(headingError) <= Math.toRadians(5.0);

            boolean isComplete = targetReached || timer.seconds() > maxTime;
            return isComplete;
        }
    }
}
