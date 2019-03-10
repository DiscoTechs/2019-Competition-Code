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
    TalonSRX leftFollow = new TalonSRX(RobotMap.LEFT_FOLLOWER);
    
    TalonSRX rightDrive = new TalonSRX(RobotMap.RIGHT_MOTOR);
    TalonSRX rightFollow = new TalonSRX(RobotMap.RIGHT_FOLLOWER);
    

    // line tracers
    DigitalInput ltrace = new DigitalInput(RobotMap.LEFT_LINE_SENSOR);
    DigitalInput rtrace = new DigitalInput(RobotMap.RIGHT_LINE_SENSOR);
    DigitalInput ctrace = new DigitalInput(RobotMap.CENTER_LINE_SENSOR);

    public Drive(){
        leftDrive.setInverted(false);
        leftFollow.follow(leftDrive);
        leftFollow.setInverted(false);

        rightDrive.setInverted(true);
        rightFollow.follow(rightDrive);
        rightFollow.setInverted(true);
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

    double traceSpeed = 0.28;
    public void traceLine() {

        // tracer ? true = NO : false = YES;
        int traceState = rtrace.get() ? 0 : 1;
        traceState += ctrace.get() ? 0 : 2;
        traceState += ltrace.get() ? 0 : 4;
        
        // 000      0   do nothing
        // 001      1   rotate right (CW)
        // 010      2   go straight;
        // 011      3   rotate right (CW)
        // 100      4   rotate left (CCW)
        // 101      5   impossible?
        // 110      6   rotate left (CCW)
        // 111      7   Perpendicular?

        if (traceState == 2 ){

            leftDrive.set(ControlMode.PercentOutput, traceSpeed);
            rightDrive.set(ControlMode.PercentOutput, traceSpeed);
            
        } else if (traceState == 1 || traceState == 3 ) {   // CW
            leftDrive.set(ControlMode.PercentOutput, traceSpeed * .75);
            rightDrive.set(ControlMode.PercentOutput, -traceSpeed * .75);
            
        } else if (traceState == 4 || traceState == 6  ) {
            leftDrive.set(ControlMode.PercentOutput, -traceSpeed * .75);
            rightDrive.set(ControlMode.PercentOutput, traceSpeed * .75); 
        } 


    }
    private boolean lineFound = false;

    public boolean islineFound(){
        return lineFound;
    }

    public void resetLineFound(){
        lineFound = false;
    }


    public static final int RIGHT = 1;
    public static final int LEFT = -1;
    public boolean findLine(int direction){

        if (ctrace.get() == true){
            leftDrive.set(ControlMode.PercentOutput, direction * 0.22);
            rightDrive.set(ControlMode.PercentOutput, -direction * 0.22);
        } else {
            leftDrive.set(ControlMode.PercentOutput, 0);
            rightDrive.set(ControlMode.PercentOutput, 0);
            lineFound = true;
        }

        return lineFound;
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

    public boolean[] getLineTracers() {
        boolean[] tracers = new boolean[3];

        tracers[0] = ltrace.get();
        tracers[1] = ctrace.get();
        tracers[2] = rtrace.get();
        
        return tracers;
    }
    
}
