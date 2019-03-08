/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class LiftJack extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private VictorSP ljack = new VictorSP(RobotMap.LEFT_JACK);
  private VictorSP rjack = new VictorSP(RobotMap.RIGHT_JACK);
  private VictorSP cjack = new VictorSP(RobotMap.CENTER_JACK);

  private Talon centerDrive = new Talon(RobotMap.CENTER_DRIVE);

  // Setting for lifting and moving to the platform
  double liftspeed = .20;
  private double leftJackSpeed = liftspeed;   // stays contant
  private double rightJackSpeed = liftspeed;  // adjusts with roll
  private double backJackSpeed = liftspeed;  // adjusts with pitch

  // Self-leveling parameters
  private double r = .01;
  private double threshold = 1.5;

  private boolean applyStopVoltage_front = false;
  double frontStopVolts = .10;

  private boolean applyStopVoltage_rear = false;
  double rearStopVolts = .15;

  private double pitch = 0.0;
  private double roll = 0.0;

  public LiftJack() {
    ljack.setInverted(false);
    rjack.setInverted(true);

    // get a speed from the dash
    SmartDashboard.putNumber("V", .3);
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void frontJack(double speed) {

    rjack.set(speed);
    ljack.set(speed);
  }

  public void liftFront() {

    frontJack(liftspeed); 
  }

  public void retractFront() {

    // no need for stop voltage if we are retracting
    applyStopVoltage_front = false;
    frontJack(-RobotMap.RETRACT_SPEED);
  }

  public void stopFront() {

    if (applyStopVoltage_front) {
      frontJack(frontStopVolts);
    } else {
      frontJack(0.0);
    }
  }

  public void centerJack(double speed) {

    cjack.set(speed);
  }

  public void liftCenter() {

    centerJack(RobotMap.LIFT_SPEED); 
  }

  public void retractCenter() {
    
    
    applyStopVoltage_rear = false;
    centerJack(-RobotMap.LIFT_SPEED);
  }
 
  public void stopCenter() {
    if (applyStopVoltage_rear) {
      centerJack(rearStopVolts);
    } else {
      centerJack(0.0);
    }
  }

  

  public void liftRobot() {
    // once we start lifting, the stop voltage will keep it from falling
    applyStopVoltage_front = true;
    applyStopVoltage_rear = true;

    // flat is currently around -.45
    pitch = Robot.dash.navx.getPitch();
    roll = Robot.dash.navx.getRoll();
    
    if ( roll < -threshold ) {
      // slow down left or speed up right?
      rightJackSpeed *= (1-r);
    } else if (roll > threshold ){
      rightJackSpeed *= (1+r);
    }

    if ( pitch > threshold ) {
      backJackSpeed *= (1-r/1.5);
    } else if (pitch < -threshold ){
      backJackSpeed *= (1+r/1.5);
    }

    SmartDashboard.putNumber("Lspeed", leftJackSpeed);
    SmartDashboard.putNumber("Rspeed", rightJackSpeed);
    SmartDashboard.putNumber("Bspeed", backJackSpeed);
    
    ljack.set(leftJackSpeed);
    rjack.set(rightJackSpeed);
    cjack.set(backJackSpeed);

  }

  public void driveJack(double d) {

    centerDrive.set(d);
  }

  public void resetJack() {
    rightJackSpeed = liftspeed;
    leftJackSpeed = liftspeed;
    backJackSpeed = liftspeed * .8;

    applyStopVoltage_front = false;
    applyStopVoltage_rear = false;

    liftspeed = SmartDashboard.getNumber("V", liftspeed);
    SmartDashboard.putNumber("V-actual", liftspeed);
  }

  
}
