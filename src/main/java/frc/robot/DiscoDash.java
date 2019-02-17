/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.analog.adis16470.frc.ADIS16470_IMU;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;


/**
 * Add your docs here.
 */
public class DiscoDash {

    private static PowerDistributionPanel pdp = new PowerDistributionPanel();
    private static BuiltInAccelerometer acc = new BuiltInAccelerometer();
    public static final AHRS navx = new AHRS(SPI.Port.kMXP);


    private static double[] aData = new double[3];
    private static double[] pData = new double[8];

    public DiscoDash() {
    }
    public void update() {
        
        accelerometer();
       // power();        
    }

    public void accelerometer() {
        
        SmartDashboard.putNumber("roll",navx.getRoll());
        SmartDashboard.putNumber("pitch", navx.getPitch());
        SmartDashboard.putNumber("yaw", navx.getYaw());
        SmartDashboard.putNumber("angle", navx.getAngle());

        SmartDashboard.putNumber("Y", navx.getRawAccelY());
        SmartDashboard.putNumber("Z", navx.getRawAccelZ());
    }

    public void power() {

        for (int i = 0; i < pData.length; i++) {
            pData[i] = pdp.getCurrent(i);
            SmartDashboard.putNumber("PDP" + i, pData[i]);
        }
    }

    public double getAngle() {
        return navx.getAngle();
    }

    public double getTilt() {
        return navx.getRawAccelZ();
    }
}
