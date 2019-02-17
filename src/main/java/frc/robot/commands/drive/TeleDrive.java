/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.DiscoDash;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleDrive extends Command {
  public TeleDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double lspeed = -OI.lstick.getRawAxis(RobotMap.EXTREME_Y);
    double rspeed = -OI.rstick.getRawAxis(RobotMap.EXTREME_Y);
    
    lspeed = Math.copySign(1, lspeed)*lspeed*lspeed;
    rspeed = Math.copySign(1, rspeed)*rspeed*rspeed;
    Robot.drive.vDrive(lspeed, rspeed);

    // Since this command should (almost) always be running
    // It it a good place to put the DashBoard call
    // DiscoDash.update();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
