/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;


/**
 * Add your docs here.
 */
public class DiscoDash {

    private static PowerDistributionPanel pdp = new PowerDistributionPanel(10);
    public final AHRS navx = new AHRS(SPI.Port.kMXP);

    public DiscoDash() {

        // navx.reset();
    }

    public void update() {
        
        accelerometer();
        power();
        
        boolean[] tracers = Robot.drive.getLineTracers();
        SmartDashboard.putBoolean("LT", tracers[0]);
        SmartDashboard.putBoolean("CT", tracers[1]);
        SmartDashboard.putBoolean("RT", tracers[2]);
        
    }

    public void accelerometer() {
        
        SmartDashboard.putNumber("roll",navx.getRoll());
        SmartDashboard.putNumber("pitch", navx.getPitch());
        SmartDashboard.putNumber("yaw", navx.getYaw());

    }

    public void power() {

        SmartDashboard.putNumber("PDP5", pdp.getCurrent(5));
        SmartDashboard.putNumber("PDP4", pdp.getCurrent(4));
        SmartDashboard.putNumber("PDP6", pdp.getCurrent(6));
        SmartDashboard.putNumber("PDP7", pdp.getCurrent(7));
        SmartDashboard.putNumber("PDP11", pdp.getCurrent(11));
    }

    public double getAngle() {
        return navx.getAngle();
    }

    public double getTilt() {
        return navx.getRawAccelZ();
    }
}
