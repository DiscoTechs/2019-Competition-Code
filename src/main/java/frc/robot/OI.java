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
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.drive.DriveToPos;
import frc.robot.commands.drive.ResetEncoders;
import frc.robot.commands.drive.TeleDrive;
import frc.robot.commands.elevator.ElevatorArmDown;
import frc.robot.commands.elevator.ElevatorArmStop;
import frc.robot.commands.elevator.ElevatorArmUp;
import frc.robot.commands.elevator.ElevatorDown;
import frc.robot.commands.elevator.ElevatorStop;
import frc.robot.commands.elevator.ElevatorUp;

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
  public OI(){
    
    Button aButton = new JoystickButton(xbox, RobotMap.A_BUTTON);
    Button bButton = new JoystickButton(xbox, RobotMap.B_BUTTON);
    Button xButton = new JoystickButton(xbox, RobotMap.X_BUTTON);
    Button yButton = new JoystickButton(xbox, RobotMap.Y_BUTTON);

    yButton.whileHeld(new ElevatorUp());
    yButton.whenReleased(new ElevatorStop());

    aButton.whenPressed(new ElevatorDown());
    aButton.whenReleased(new ElevatorStop());


    xButton.whileHeld(new ElevatorArmUp());
    xButton.whenReleased(new ElevatorArmStop());

    bButton.whenPressed(new ElevatorArmDown());
    bButton.whenReleased(new ElevatorArmStop());

    //xButton.whenPressed(new ResetEncoders());
    //bButton.whenPressed(new DriveToPos(20_000));


  }
}
