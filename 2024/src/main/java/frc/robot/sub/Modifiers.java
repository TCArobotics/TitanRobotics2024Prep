package frc.robot.sub;
import edu.wpi.first.math.controller.PIDController;

public class Modifiers {
    
    public PIDController PIDcontrollermath;

    public double speed = 0.5;
    public double direction =  0;
    public double distance = 0;
    
    public void PIDvalues()
    {
        double setPoint = 0.0;
        PIDcontrollermath.getIntergratorRange(-1000, 1000);
        PIDcontrollermath.getPositionTolerance();
    }

    public void Slowmo()
    {
        double speed = 0.25;
    }
}
