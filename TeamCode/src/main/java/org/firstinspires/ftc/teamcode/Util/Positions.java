package org.firstinspires.ftc.teamcode.Util;


import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.Actions.P2P;

public enum Positions {

    //Auto Red Goal - LOCALIZER = x:48, y:55, rotation:PI*0.95
    MoveOutTriangleRedGoal(new Vector2d(40, 15), PI*.25),
    ShootingPositionsRed(new Vector2d(30, 25), PI*.25),
    RedIntakeTape1Start(new Vector2d(40,13), PI*0.5),
    RedIntakeTape1End(new Vector2d(68, 15), PI*0.5),
    RedIntakeTape2Start(new Vector2d(40, -12), PI*0.5),
    RedIntakeTape2End(new Vector2d(75, -12), PI*0.5),
    RedIntakeTape2MidStart(new Vector2d(40, 5), PI*0.5),
    RedIntakeTape2MidEnd(new Vector2d(67, 5), PI*0.5),
    RedIntakeTape3Start(new Vector2d(30, -15), PI*0.5),
    RedIntakeTape3End(new Vector2d(70, -15), PI*0.5),
    ParkPositionsRed(new Vector2d(60, 25), PI*0.25),
    TurningRed(new Vector2d(0,  90), PI*0.25),
    NewTurningRed(new Vector2d(8,-46), PI*0.15),
    NewTurningRed2(new Vector2d(10,-50), PI*0.125),
    EndingRed(new Vector2d(20,  60), PI*0.25),
    NewEndingRed(new Vector2d(30,-10), PI*0.25),
    ShootingPositionsRedMiddle(new Vector2d(15, 22), 0.0),
    ShootingPositionsRedMiddleTurn(new Vector2d(15, 25), PI*.25),

    //Auto Blue Goal - LOCALIZER = x:-47.4, y:56.3, rotation:-PI*0.95
    MoveRightBlueGoal(new Vector2d(-13 , 55.8), 0.0),
    ShootingPositionsBlue(new Vector2d(-20, 12), PI*-0.25),
    BlueIntakeTape1Start(new Vector2d(-25, -5), PI*-0.5),
    BlueIntakeTape1End(new Vector2d(-49.5, -5), PI*-0.5),
    BlueIntakeTape2Start(new Vector2d(-25, -30), PI*-0.5),
    BlueIntakeTape2End(new Vector2d(-55, -30), PI*-0.5),
    BlueIntakeTape3Start(new Vector2d(-20, -33), PI*-0.5),
    BlueIntakeTape3End(new Vector2d(-55, -33), PI*-0.5),
    BlueIntakeTape2MidStart(new Vector2d(-20, -12), PI*-0.5),
    BlueIntakeTape2MidEnd(new Vector2d(-55, -12), PI*-0.5),
    ParkPositionsBlue(new Vector2d(-50, 0), PI*-0.25),
    TurningBlue(new Vector2d(0,  70), PI*-0.25),
    NewTurningBlue(new Vector2d(-8, -48),PI*-0.125),
    NewTurningBlue2(new Vector2d(-10, -50),PI*-0.125),
    EndingBlue(new Vector2d(-20,  60), PI*-0.25),
    NewEndingBlue(new Vector2d(-20,-30), PI*-0.25),
    ShootingPositionsBlueMiddle(new Vector2d(-12, 12), 0.0),
    ShootingPositionsBlueMiddleTurn(new Vector2d(-12, 15), PI*-0.225),
    NewShootingPositionsBlueMiddleTurn(new Vector2d(-12, 12), PI*-0.25),

    MoveForward(new Vector2d(30,  0), 0.0),
    NewMoveForward(new Vector2d(12,  -46), 0.0),

    Test(new Vector2d(0.0,23.0),0.0),
    Test2(new Vector2d(0,0),0.0),
    TestStart(new Vector2d(0.0,0.0),0.0),
    TestRight(new Vector2d(40.0,0.0),0.0);

    public double maxTime,heading;
    public double x,y;

    Positions(Vector2d vector, Double rotation){
        this.x = vector.x;
        this.y = vector.y;
        this.heading = rotation;
        runToExact = new P2P(vector, rotation);
    }

    Positions(Vector2d vector, Double rotation, Double maxTime){
        this.x = vector.x;
        this.y = vector.y;
        this.maxTime = maxTime;
        runToExact = new P2P(vector, rotation, maxTime);
    }


    public final P2P runToExact;
}
