/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
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

  public void retractFront() {
    
    frontJack(-RobotMap.RETRACT_SPEED);
  }

  public void stopFront() {
    double speed= 0.0; 
    if (liftJackEngaged)
    {
      speed = 0.0;
    }
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
      speed = 0.0;
    }
    centerJack(speed);
  }

  double liftspeed = .30;
  private double backspeed = liftspeed * 1.0;
  private double r = .0250; // % adjustment

  private boolean liftJackEngaged = false;



  public void liftRobot() {
    liftJackEngaged = true;
    // flat is currently around -.45
    double tilt = Robot.dash.getTilt();
    SmartDashboard.putNumber("TILT", tilt);
    
    // as tilt goes negative, the back is down
    
    if (tilt < .42) {
      backspeed *= (1+r);
    } else if (tilt > .52) {
      backspeed *= (1-r);
    }
    

    frontJack(liftspeed);
    centerJack(backspeed);
  }

  public void driveJack(double d) {
    centerDrive.set(d);
  }

  public void resetJack() {
    backspeed = liftspeed;
    liftJackEngaged = false;
  }
}
