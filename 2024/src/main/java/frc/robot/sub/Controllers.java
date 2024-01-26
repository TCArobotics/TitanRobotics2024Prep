package frc.robot.sub;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.data.ButtonMap;
import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

public class Controllers { // may whant to rewright some time later 7-26-2023
    private XboxController xboxController;

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

    /**Input the ButtonMap name and axis and receive its value, double between -1 and 1 */
    public double getStick(ButtonMap stickAxis)
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
        catch (Exception AxisNotFound)
        {
            SmartDashboard.putString("ControllerError", "AxisNotFound");
            return 0;
        }
        }
        else
        {
        return 0;
        }
    }

}
