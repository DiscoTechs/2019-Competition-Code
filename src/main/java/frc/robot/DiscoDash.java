/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;


/**
 * Add your docs here.
 */
public class DiscoDash {

    private static PowerDistributionPanel pdp = new PowerDistributionPanel(10);
    private static BuiltInAccelerometer acc = new BuiltInAccelerometer();
    public final AHRS navx = new AHRS(SPI.Port.kMXP);


    private static double[] aData = new double[3];
    private static double[] pData = new double[8];

    public DiscoDash() {

        // navx.reset();
    }
    public void update() {
        
        accelerometer();
       power();        

       SmartDashboard.putNumber("Time Check", System.currentTimeMillis());

    }

    public void accelerometer() {
        
        SmartDashboard.putNumber("roll",navx.getRoll());
        SmartDashboard.putNumber("pitch", navx.getPitch());
        SmartDashboard.putNumber("yaw", navx.getYaw());

    }

    public void power() {

    SmartDashboard.putNumber("PDP5", pdp.getCurrent(5));
    SmartDashboard.putNumber("PDP4", pdp.getCurrent(4));
    
    }

    public double getAngle() {
        return navx.getAngle();
    }

    public double getTilt() {
        return navx.getRawAccelZ();
    }
}
