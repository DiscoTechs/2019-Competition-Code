package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drive.TeleDrive;



public class Drive extends Subsystem {
    TalonSRX leftDrive = new TalonSRX(RobotMap.LEFT_MOTOR);
    TalonSRX rightDrive = new TalonSRX(RobotMap.RIGHT_MOTOR);

    // line tracers
    DigitalInput ltrace = new DigitalInput(RobotMap.LEFT_LINE_SENSOR);
    DigitalInput rtrace = new DigitalInput(RobotMap.RIGHT_LINE_SENSOR);
    DigitalInput ctrace = new DigitalInput(RobotMap.CENTER_LINE_SENSOR);

    public Drive(){
        leftDrive.setInverted(false);
        rightDrive.setInverted(true);

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

    double traceSpeed = 0.4;
    public void traceLine() {
        if (ltrace.get() == true) {
            //drive right
            leftDrive.set(ControlMode.PercentOutput, 0);
            rightDrive.set(ControlMode.PercentOutput, traceSpeed);
            
        }
        else if (rtrace.get() == true){
//drive left
         leftDrive.set(ControlMode.PercentOutput, traceSpeed);
        rightDrive.set(ControlMode.PercentOutput, 0);
            
        } else if (ctrace.get() == true){

            leftDrive.set(ControlMode.PercentOutput, 0);
        rightDrive.set(ControlMode.PercentOutput, 0);

        } else {

        }

    }

    public void driveAtAngle(double angle) {

        double currentAngle = Robot.dash.getAngle();

        if ( angle - currentAngle > 3 ){
            //overdrive left
        }
        else if (angle - currentAngle < -3) {
            // overdrive right
        } else {
            // drive equal
        }

    }
    
}
