/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
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

  public LiftJack() {
    ljack.setInverted(true);
    rjack.setInverted(false);
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

    frontJack(RobotMap.LIFT_SPEED); 
  }

  public void retractFront() {
    
    frontJack(-RobotMap.RETRACT_SPEED);
  }

  public void stopFront() {

    frontJack(0);
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

    centerJack(0);
  }

  public void liftRobot() {

    liftFront();
    liftCenter();
  }
}
