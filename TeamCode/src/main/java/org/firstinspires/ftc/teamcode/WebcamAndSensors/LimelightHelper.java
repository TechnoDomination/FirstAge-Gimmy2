package org.firstinspires.ftc.teamcode.WebcamAndSensors;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LimelightHelper {
    private Limelight3A limelight;
    public double MIN_TX = 10.0;
    public double MAX_TX = 13.0;
    public double MIN_TY = 10.0;
    public double MAX_TY = 14.0;

    public LimelightHelper(HardwareMap hardwareMap){
        limelight = hardwareMap.get(Limelight3A.class,"limelight");
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
    }

    public LLResult getLatestResult(){
        return limelight.getLatestResult();
    }

    public boolean isReadyToShoot(){
        LLResult result = getLatestResult();
        if (result != null && result.isValid()){
            return ((Math.abs(result.getTx()) > MIN_TX && Math.abs(result.getTx()) < MAX_TX) && (Math.abs(result.getTy()) > MIN_TY && Math.abs(result.getTy()) < MAX_TY)) ;
        }
        return false;
    }

    public void stopLimelight() {
        limelight.stop();
    }
}
