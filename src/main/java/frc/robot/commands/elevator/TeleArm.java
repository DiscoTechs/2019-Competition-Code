package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleArm extends Command {
  public TeleArm() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double ltrig = -OI.xbox.getRawAxis(RobotMap.LEFT_TRIGGER);
    double rtrig = -OI.xbox.getRawAxis(RobotMap.RIGHT_TRIGGER);
    
    if (rtrig > 0.05) {
      Robot.elevator.armUp(rtrig);
    } else if (ltrig > 0.05) {
      Robot.elevator.armDown(ltrig);
    }
    

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