package frc.robot.sub;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.data.ButtonMap;
import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

public class Controllers extends ControlSubSystems { // may want to rewrite some time later 7-26-2023
    private XboxController xboxController; 
    Joystick flightController = new Joystick(1);
    
    private HashMap<ButtonMap, Double> buttons;
    private double debouncePeriod = 0.1; //The time before a button is allowed to be pressed again in seconds
    public Controllers()
    {
        try 
        {
            this.xboxController = new XboxController(PortMap.GAMEPADXBOX.portNumber);
        }
        catch (Exception xboxControllerNotAssigned) 
        {
            this.xboxController = null;
            SmartDashboard.putNumber("XboxControllerNotFound", PortMap.GAMEPADXBOX.portNumber);
        }
        this.buttons = new HashMap<ButtonMap, Double>();
        
       /* try
        {
            Joystick flightController = new Joystick(1);
        }
        catch (Exception flightControllerNotAssigned)
        {
            this.flightController = null;
        } */

        init();
    }
    
    private void init()
    {
        for(ButtonMap i : ButtonMap.values())
        {
            buttons.put(i, Timer.getFPGATimestamp());
        }
    }

    /**Input the ButtonMap name and receive if button is pressed, boolean true or false; has debounce (time before button can output true again) */
    public boolean getButtonXboxPressed(ButtonMap buttonName) 
    {
    
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - buttons.get(buttonName) > this.debouncePeriod && this.xboxController != null)
        {
            buttons.replace(buttonName, currentTime);
            return xboxController.getRawButton(buttonName.value);
        }
        else
        {
            return false;
        }
    }

    public boolean getButtonFlightPressed(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; has debounce (time before button can output true again)
    {
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - buttons.get(buttonName) > this.debouncePeriod)
        {
            buttons.replace(buttonName, currentTime);
            return flightController.getRawButton(buttonName.value);
        }
        else
        {
            return false;
        }
    }

    /**Input the ButtonMap name and receive if button is pressed, boolean true or false; does not have debounce (allows for motors to be triggered by press and hold until button is released) */
    public boolean getButtonXboxPressedDebounceOff(ButtonMap buttonName) 
    {   if (this.xboxController != null)
        {
            return xboxController.getRawButton(buttonName.value);
        }
        else 
        {
            return false;
        }
    }
    
    public boolean getButtonFlightPressedDebounceOff(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; does not have debounce (allows for motors to be triggered by press and hold until button is released)
    {
        return flightController.getRawButton(buttonName.value);
    }
    
    /**Input the ButtonMap name and axis and receive its value, double between -1 and 1 */
    public double getStickXbox(ButtonMap stickAxis) 
    {  if (this.xboxController != null)
        {   try {
            switch(stickAxis)
            {
                case XboxLEFTSTICKX:
                    return xboxController.getRawAxis(0);
                case XboxLEFTSTICKY:
                    return xboxController.getRawAxis(1);
                case XboxRIGHTSTICKX:
                    return xboxController.getRawAxis(4); 
                case XboxRIGHTSTICKY:
                    return xboxController.getRawAxis(5); 
                default:
                    return 0;
            }
        }  
        catch (Exception XboxAxisNotFound) 
        {
            SmartDashboard.putString("XboxControllerError", "AxisNotFound");
            return 0;
        }
        } 
        else 
        {
        return 0;
        }     
    }
    public double getStickFlight(ButtonMap stickAxis)
    {   try {
        switch(stickAxis)
            {
                case FlightSTICKX:
                    return flightController.getRawAxis(0); //gives the value from -1 to 1 for the specified axis (axis shown in driverstation for controller when controller is plugged in)
                case FlightSTICKY:
                    return flightController.getRawAxis(1); //gives the value from -1 to 1 for the specified axis (axis shown in driverstation for controller when controller is plugged in)
                case FlightSTICKZ:
                    return flightController.getRawAxis(2); //gives the value from -1 to 1 for the specified axis (axis shown in driverstation for controller when controller is plugged in)
                case FlightSLIDER:
                    return flightController.getRawAxis(3); //gives the value from -1 to 1 for the specified axis (axis shown in driverstation for controller when controller is plugged in)     
                default:
                    return 0;
            }
        }
        catch (Exception FlightAxisNotFound)
        {
            SmartDashboard.putString("FlightControllerError", "AxisNotFound");
            return 0;
        }
    }
}