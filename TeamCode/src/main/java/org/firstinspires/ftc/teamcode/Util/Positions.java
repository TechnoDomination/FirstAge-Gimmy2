package org.firstinspires.ftc.teamcode.Util;


import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.Actions.P2P;

public enum Positions {

    //Auto Red Goal - LOCALIZER = x:48, y:55, rotation:PI*0.95
    MoveOutTriangleRedGoal(new Vector2d(40, 15), PI*.25),
    ShootingPositionsRed(new Vector2d(34, 28), PI*.25),
    RedIntakeTape1Start(new Vector2d(40, 13), PI*0.5),
    RedIntakeTape1End(new Vector2d(70, 15), PI*0.5),
    RedIntakeTape3Start(new Vector2d(30, -15), PI*0.5),
    RedIntakeTape3End(new Vector2d(70, -15), PI*0.5),
    ParkPositionsRed(new Vector2d(60, 25), PI*0.25),
    TurningRed(new Vector2d(0,  90), PI*0.25),
    NewTurningRed(new Vector2d(8,-42), PI*0.115),
    EndingRed(new Vector2d(20,  60), PI*0.25),
    NewEndingRed(new Vector2d(20,-30), PI*0.25),
    ShootingPositionsRedMiddle(new Vector2d(12, 27), PI*.25),

    //Auto Blue Goal - LOCALIZER = x:-47.4, y:56.3, rotation:-PI*0.95
    MoveRightBlueGoal(new Vector2d(-13 , 55.8), 0.0),
    ShootingPositionsBlue(new Vector2d(-20, 15), PI*-0.275),
    BlueIntakeTape1Start(new Vector2d(-25, -5), PI*-0.5),
    BlueIntakeTape1End(new Vector2d(-49.5, -5), PI*-0.5),
    BlueIntakeTape3Start(new Vector2d(-20, -31.5), PI*-0.5),
    BlueIntakeTape3End(new Vector2d(-49.5, -31.5), PI*-0.5),
    ParkPositionsBlue(new Vector2d(-50, 0), PI*-0.25),
    TurningBlue(new Vector2d(0,  70), PI*-0.25),
    NewTurningBlue(new Vector2d(-8, -48),PI*-0.125),
    EndingBlue(new Vector2d(-20,  60), PI*-0.25),
    NewEndingBlue(new Vector2d(-20,-30), PI*-0.25),
    ShootingPositionsBlueMiddle(new Vector2d(-12, 24), PI*-0.25),

    MoveForward(new Vector2d(30,  0), 0.0),
    NewMoveForward(new Vector2d(12,  -46), 0.0),

    Test(new Vector2d(0.0,23.0),0.0),
    Test2(new Vector2d(0,0),0.0),
    TestStart(new Vector2d(0.0,0.0),0.0),
    TestRight(new Vector2d(40.0,0.0),0.0);


    Positions(Vector2d vector, Double rotation) {
        runToExact = new P2P(vector, rotation);
    }


    public final P2P runToExact;
}
