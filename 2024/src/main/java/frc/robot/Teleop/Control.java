package frc.robot.Teleop;

import frc.robot.sub.TankDrive;
import frc.robot.sub.SwerveDrive;
import frc.robot.data.ButtonMap;
import frc.robot.sub.Controllers;

public class Control extends Teleopsubsystem {
    private TankDrive mTankDrive = null;
    private SwerveDrive mSwerveDrive = null;
    //private SwerveDrive mSwerveDrive = null;
    private final Controllers controllers;

    public Control()
    {
        mSwerveDrive = SwerveDrive.getInstance();
        //mTankDrive = TankDrive.getInstance();
        controllers = new Controllers();
    }

    /**Performs all updates needed to be executed durning Telop */
    public void telopUpdate()
    {
        //this.tank();
        this.swerve();
        //mTankDrive.update();
        mSwerveDrive.update();
    } 

    /**
     * Tank is when the the left joystick controls the left side of the robot
     * and the right joystick controls the right side of the robot. 
     */
    public void tank(){
        double xboxLeftStickYInput = controllers.getStickXbox(ButtonMap.XboxLEFTSTICKY);
        double xboxRightStickXInput = controllers.getStickXbox(ButtonMap.XboxRIGHTSTICKX);
        mTankDrive.tankDrive(xboxLeftStickYInput, xboxRightStickXInput);
    }
    
    public void swerve(){
        double xboxLeftStickYInput = controllers.getStickXbox(ButtonMap.XboxLEFTSTICKY);
        double xboxLeftStickXInput = controllers.getStickXbox(ButtonMap.XboxLEFTSTICKX);
        double xboxRightStickXInput = controllers.getStickXbox(ButtonMap.XboxRIGHTSTICKX);
        //double flightAxisXInput = controllers.getStickFlight(ButtonMap.FlightSTICKX);
        //double flightAxisYInput = controllers.getStickFlight(ButtonMap.FlightSTICKY);
        //double flightAxisZInput = controllers.getStickFlight(ButtonMap.FlightSTICKZ);
        //mSwerveDrive.SwerveDriveMath(flightAxisXInput, flightAxisYInput, flightAxisZInput);
        mSwerveDrive.SwerveDriveMath(xboxLeftStickXInput, xboxLeftStickYInput, xboxRightStickXInput);
        //System.out.println(flightAxisXInput);
    }

}
