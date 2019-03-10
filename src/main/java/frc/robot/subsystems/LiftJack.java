/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.kauailabs.navx.frc.AHRS;

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
  // ++++++++++++++++++++++++++++++++
  double liftspeed = .70;
  // ++++++++++++++++++++++++++++++++

  private double leftJackSpeed = liftspeed;   // stays contant
  private double rightJackSpeed = liftspeed;  // adjusts with roll
  private double backJackSpeed = liftspeed * 1.25;  // adjusts with pitch

  // Self-leveling parameters
  private double pitch_0 = Robot.dash.navx.getPitch();
  private double roll_0 = Robot.dash.navx.getRoll();
  private double r = .02;
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
    SmartDashboard.putNumber("V", liftspeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  //
  // ======= FRONT CONTROLS ==========
  // 

  public void balanceFrontSpeeds() {
    roll = Robot.dash.navx.getRoll();
    
    if ( roll < -threshold ) {
      // slow down left or speed up right?
      rightJackSpeed *= (1-r);
    } else if (roll > threshold ){
      rightJackSpeed *= (1+r);
    }
  }

  public void frontDirectDrive(double speed) {

    rjack.set(speed);
    ljack.set(speed);
    update();
  }

  public void retractFront() {
    // no need for stop voltage if we are retracting
    applyStopVoltage_front = false;
    frontDirectDrive(-liftspeed);
    update();
  }

  public void extendFront() {

    // extending should trigger stopVoltage
    applyStopVoltage_front = true;

    balanceCenterSpeed();
    rjack.set(rightJackSpeed);
    ljack.set(leftJackSpeed);
    update();
  }

  public void stopFront() {

    if (applyStopVoltage_front) {
      frontDirectDrive(frontStopVolts);
    } else {
      frontDirectDrive(0.0);
    }
  }

  //
  // ======= REAR/CENTER CONTROLS ==========
  // 
  public void balanceCenterSpeed() {
    pitch = Robot.dash.navx.getPitch();
    
    if ( pitch > threshold ) {
      backJackSpeed *= (1-r);
    } else if (pitch < -threshold ){
      backJackSpeed *= (1+r);
    }
  }

  public void centerJack(double speed) {
    cjack.set(speed);
    update();
  }

  public void retractCenter() {
    applyStopVoltage_rear = false;
    centerJack(-liftspeed);
  }
 
  public void extendRear() {
    applyStopVoltage_rear = true;
    balanceCenterSpeed();
    centerJack(backJackSpeed); 
  }

  public void stopCenter() {
    if (applyStopVoltage_rear) {
      centerJack(rearStopVolts);
    } else {
      centerJack(0.0);
    }
  }

  //
  // ======= GENERAL CONTROLS ==========
  // 
  public void update() {

    SmartDashboard.putNumber("Lspeed", leftJackSpeed);
    SmartDashboard.putNumber("Rspeed", rightJackSpeed);
    SmartDashboard.putNumber("Bspeed", backJackSpeed);
  }

  // forward and backward with the extended wheel
  public void driveJack(double d) {
    centerDrive.set(d);
  }

  public void resetJack() {
    rightJackSpeed = liftspeed;
    leftJackSpeed = liftspeed;
    backJackSpeed = liftspeed;

    applyStopVoltage_front = false;
    applyStopVoltage_rear = false;

    liftspeed = SmartDashboard.getNumber("V", liftspeed);
    SmartDashboard.putNumber("V-actual", liftspeed);

    // let's try setting the level points based on the current pitch and rol
    pitch_0 = Robot.dash.navx.getPitch();
    roll_0 = Robot.dash.navx.getRoll();
  }

  
}
