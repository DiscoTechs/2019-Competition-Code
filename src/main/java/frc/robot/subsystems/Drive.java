package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drive.TeleDrive;



public class Drive extends Subsystem {
    TalonSRX leftDrive = new TalonSRX(RobotMap.LEFT_MOTOR);
    TalonSRX rightDrive = new TalonSRX(RobotMap.RIGHT_MOTOR);

    // line tracers
    DigitalInput ltrace = new DigitalInput(RobotMap.LEFT_LINE_SENSOR);

    public Drive(){
        leftDrive.setInverted(true);
        rightDrive.setInverted(false);

    }

    @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TeleDrive());
  }

    public void vDrive(double left, double right){
        leftDrive.set(ControlMode.PercentOutput, left);
        rightDrive.set(ControlMode.PercentOutput, right);

        SmartDashboard.putNumber("left speed", left);
        SmartDashboard.putNumber("right speed", right);

        int lpos = leftDrive.getSelectedSensorPosition();
        SmartDashboard.putNumber("lpos", (double)lpos);

        
        int rpos = rightDrive.getSelectedSensorPosition();
        SmartDashboard.putNumber("rpos", (double)rpos);
    }

    public void resetEncoders(){
        leftDrive.setSelectedSensorPosition(0);
        rightDrive.setSelectedSensorPosition(0);
    }

    public int getLeftEncoder(){
        return leftDrive.getSelectedSensorPosition();
    }
    
    public int getRightEncoder(){
        return rightDrive.getSelectedSensorPosition();
    }

    public void traceLine() {
        if (ltrace.get() == true) {
            
        }
    }
}
