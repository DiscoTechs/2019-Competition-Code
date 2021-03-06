/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.FlipIt;
import frc.robot.commands.TimedDrive;
import frc.robot.commands.LiftJack.DriveJack;
import frc.robot.commands.LiftJack.ExtendFront;
import frc.robot.commands.LiftJack.LiftRobot;
import frc.robot.commands.LiftJack.ResetJack;
import frc.robot.commands.LiftJack.RetractCenter;
import frc.robot.commands.LiftJack.RetractFront;
import frc.robot.commands.LiftJack.StopLiftJack;
import frc.robot.commands.drive.StopLineTracing;
import frc.robot.commands.drive.ToCargoShip;
import frc.robot.subsystems.Drive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public static Joystick xbox = new Joystick(RobotMap.XBOX);
  
  public static Joystick lstick = new Joystick(RobotMap.LEFT_STICK);
  public static Joystick rstick = new Joystick(RobotMap.RIGHT_STICK);

  public OI(){
    
    // XBOX Controls
    Button aButton = new JoystickButton(xbox, RobotMap.A_BUTTON);
    Button bButton = new JoystickButton(xbox, RobotMap.B_BUTTON);
    Button xButton = new JoystickButton(xbox, RobotMap.X_BUTTON);
    Button yButton = new JoystickButton(xbox, RobotMap.Y_BUTTON);
    Button rBumper = new JoystickButton(xbox, RobotMap.RIGHT_BUMPER);
    Button lBumper = new JoystickButton(xbox, RobotMap.LEFT_BUMPER);
    Button backButton = new JoystickButton(xbox, RobotMap.BACK_BUTTON);
    Button startButton = new JoystickButton(xbox, RobotMap.START_BUTTON);
    Button rightPadButton = new JoystickButton(xbox, 10);


    // Driver Stick Controls
    Button rightButton2 = new JoystickButton(rstick, 2);
    Button rightButton3 = new JoystickButton(rstick, 3);
    Button rightButton4 = new JoystickButton(rstick, 4);
    Button rightButton5 = new JoystickButton(rstick, 5);
    Button rightButton6 = new JoystickButton(rstick, 6);
    Button rightButton11 = new JoystickButton(rstick, 11);

    Button leftButton8 = new JoystickButton(lstick, 8);
    Button leftButton9 = new JoystickButton(lstick, 9);

    //lift jack
    rBumper.whenPressed(new ExtendFront());
    rBumper.whenReleased(new StopLiftJack());

    lBumper.whenPressed(new RetractFront());
    lBumper.whenReleased(new StopLiftJack());

    backButton.whenPressed(new RetractCenter(RetractCenter.RETRACT));
    backButton.whenReleased(new StopLiftJack());

    rightPadButton.whenPressed(new RetractCenter(RetractCenter.EXTEND));
    rightPadButton.whenReleased(new StopLiftJack());

    yButton.whenPressed(new LiftRobot());
    yButton.whenReleased(new StopLiftJack());

    // Mini Flipper
    bButton.whenPressed(new FlipIt(-1.2));
    bButton.whenReleased(new FlipIt(0.0));
    
    aButton.whenPressed(new FlipIt(1.0));
    aButton.whenReleased(new FlipIt(0.0));

    startButton.whenPressed(new ResetJack());

    


    //elevator
    // aButton.whenPressed(new ElevatorDown());
    // aButton.whenReleased(new ElevatorStop());
    
    // bButton.whileHeld(new ElevatorUp());
    // bButton.whenReleased(new ElevatorStop());

    // arm
    // xButton.whileHeld(new ElevatorArmDown());
    // xButton.whenReleased(new ElevatorArmStop());


    //xButton.whenPressed(new ResetEncoders());
    //bButton.whenPressed(new DriveToPos(20_000));

    // Driver Controls
    rightButton3.whenPressed(new DriveJack(.30));
    rightButton3.whenReleased(new DriveJack(0.0));

    rightButton2.whenPressed(new DriveJack(-.30));
    rightButton2.whenReleased(new DriveJack(0.0));

    rightButton4.whenPressed(new ToCargoShip(Drive.LEFT));
    rightButton4.whenReleased(new StopLineTracing());

    rightButton5.whenPressed(new ToCargoShip(Drive.RIGHT));
    rightButton5.whenReleased(new StopLineTracing());

    leftButton8.whenPressed(new TimedDrive(.5, .66));
    leftButton9.whenPressed(new TimedDrive(1.0, .5));

    // close all the buttons?
    aButton.close();
    bButton.close();
    xButton.close();
    yButton.close();
    rBumper.close();
    lBumper.close();
    backButton.close();
    startButton.close();
    rightPadButton.close();

    rightButton2.close();
    rightButton3.close();
    rightButton4.close();
    rightButton5.close();
    rightButton6.close();
    rightButton11.close();

    leftButton8.close();
    leftButton9.close();
  }
}
