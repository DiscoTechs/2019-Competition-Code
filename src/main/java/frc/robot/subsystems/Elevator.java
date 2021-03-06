/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.elevator.TeleArm;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //private TalonSRX elevator = new TalonSRX(6);
  //private TalonSRX arm = new TalonSRX(6);

  public Elevator() {

  }

  @Override
  public void initDefaultCommand() { 
    // Set the default command for a subsystem here.
    setDefaultCommand(new TeleArm());
  }

  public void move(double speed) {
    //elevator.set(ControlMode.PercentOutput, speed);
  }

  public void elevatorUp() {
    move(.25);
  }

  public void elevatorStop() {
    move(0);
  }

  public void elevatorDown() {
    move(-.25);
  }

  public void moveArm(double speed) {
    //arm.set(ControlMode.PercentOutput, speed);
  }

  public void armUp() {
    moveArm(.25);
  }

  public void armUp(double speed) {
    moveArm(speed);
  }

  public void armStop() {
    moveArm(0);
  }

  public void armDown() {
    moveArm(-.25);
  }

  public void armDown(double speed) {
    moveArm(-speed);
  }
} 
