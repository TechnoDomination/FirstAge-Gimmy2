package org.firstinspires.ftc.teamcode;


import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.Action.P2P;

public enum Positions {

    //Auto Red Goal - LOCALIZER = x:48, y:55, rotation:PI*0.95
    MoveOutTriangleRedGoal(new Vector2d(40, 15), PI*.25),
    ShootingPositionsRed(new Vector2d(35, 35), PI*0.25),
    ParkPositionsRed(new Vector2d(60, 25), PI*0.25),
    TurningRed(new Vector2d(0,  90), PI*0.25),
    EndingRed(new Vector2d(20,  60), PI*0.25),

    //Auto Blue Goal - LOCALIZER = x:-47.4, y:56.3, rotation:-PI*0.95
    MoveRightBlueGoal(new Vector2d(-13 , 55.8), 0.0),
    ShootingPositionsBlue(new Vector2d(-35, 15), PI*-0.25),
    ParkPositionsBlue(new Vector2d(-60, 15), PI*-0.25),
    MoveForward(new Vector2d(0,  50), 0.0),
    TurningBlue(new Vector2d(0,  70), PI*-0.25),
    EndingBlue(new Vector2d(-20,  60), PI*-0.25);


    Positions(Vector2d vector, Double rotation) {
        runToExact = new P2P(vector, rotation);
    }


    public final P2P runToExact;
}
