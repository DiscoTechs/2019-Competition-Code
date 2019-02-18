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
import edu.wpi.first.wpilibj.SPI;
import frc.robot.OI;
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

  public LiftJack() {
    ljack.setInverted(false);
    rjack.setInverted(false);

    // get a speed from the dash
    liftspeed = SmartDashboard.getNumber("V", liftspeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void frontJack(double speed) {

    rjack.set(-speed);
    ljack.set(speed);
  }

  public void liftFront() {

    frontJack(RobotMap.LIFT_SPEED * .85); 
  }

  boolean toggle = false;
  public void retractFront() {

    liftJackEngaged = false;

    frontJack(-RobotMap.RETRACT_SPEED);
  }

  public void stopFront() {
    double speed= 0.0; 
    
    double trig = OI.xbox.getRawAxis(3);

    speed = trig * .2;
    frontJack(speed);
  }

  public void centerJack(double speed) {

    cjack.set(speed);
  }

  public void liftCenter() {

    centerJack(RobotMap.LIFT_SPEED); 
  }

  public void retractCenter() {
    
    centerJack(-RobotMap.LIFT_SPEED);
  }

  public void stopCenter() {
    double speed= 0.0;

    if (liftJackEngaged) {
      speed = .15;
    }
    centerJack(speed);
  }

  double liftspeed = .30;
  private double leftJackSpeed = liftspeed;
  private double rightJackSpeed = liftspeed;
  private double backJackSpeed = liftspeed;

  private double r = .01; // % adjustment

  private boolean liftJackEngaged = false;


  private double pitch = 0.0;
  private double roll = 0.0;

  private double threshold = 2.5;

  public void liftRobot() {
    liftJackEngaged = true;
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
      // slow down left or speed up right?
      backJackSpeed *= (1-r);
    } else if (pitch < -threshold ){
      backJackSpeed *= (1+r);
    }

    ljack.set(leftJackSpeed);
    rjack.set(-rightJackSpeed);
    cjack.set(backJackSpeed);

  }

  public void driveJack(double d) {
    centerDrive.set(d);
  }

  public void resetJack() {
    rightJackSpeed = liftspeed;
    leftJackSpeed = liftspeed;
    backJackSpeed = liftspeed;

    liftJackEngaged = false;

    // toggle = !toggle;
  }

  
}
