/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // Main drive joysticks
  public static final int LEFT_STICK = 0;
  public static final int RIGHT_STICK = 1;
  public static final int DRIVE_HORZ_AXIS = 0;
  public static final int DRIVE_VERT_AXIS =  1;


  // These are all for the xbox controller
  public static final int XBOX= 2;
  public static final int LEFT_AXIS_HORZ = 0;
  public static final int LEFT_AXIS = 1;
  public static final int RIGHT_AXIS_HORZ = 4;
  public static final int RIGHT_AXIS = 5;
  public static final int RIGHT_TRIGGER = 3;
  public static final int LEFT_TRIGGER = 2;
  public static final int RIGHT_BUMPER = 6;
  public static final int LEFT_BUMPER = 5;
  public static final int BACK_BUTTON = 7;
  public static final int START_BUTTON = 8;

  public static final int A_BUTTON = 1;
  public static final int B_BUTTON = 2;
  public static final int X_BUTTON = 3;
  public static final int Y_BUTTON = 4;
  

  //CAN IDs
  public static final int LEFT_MOTOR = 6;
  public static final int RIGHT_MOTOR = 3;
  public static final int ELEVATOR = 7;
  public static final int ARM = 8;

  public static final int LEFT_LINE_SENSOR = 0;
  public static final int CENTER_LINE_SENSOR = 1;
  public static final int RIGHT_LINE_SENSOR = 3;

  //PWM IDs

  public static final int LEFT_JACK = 0;
  public static final int CENTER_JACK = 1;
  public static final int RIGHT_JACK = 2;

  //LiftJack

  public static final double LIFT_SPEED = .5;
  public static final double RETRACT_SPEED = .7;


}
