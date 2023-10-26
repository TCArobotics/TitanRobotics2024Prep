package frc.robot.data;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class PositionEstimate 
{

    //distance encoders(duh)
    public static Encoder LEFT_FRONT_DISTANCE_ENCODER;
    public static Encoder LEFT_REAR_DISTANCE_ENCODER;
    public static Encoder RIGHT_FRONT_DISTANCE_ENCODER;
    public static Encoder RIGHT_REAR_DISTANCE_ENCODER;

    //direction encoders(duh)
    public static Encoder LEFT_FRONT_DIRECTION_ENCODER;
    public static Encoder LEFT_REAR_DIRECTION_ENCODER;
    public static Encoder RIGHT_FRONT_DIRECTION_ENCODER;
    public static Encoder RIGHT_REAR_DIRECTION_ENCODER;

    public PositionEstimate()
    {
        

        LEFT_FRONT_DIRECTION_ENCODER.setDistancePerPulse(360/1024); //THIS IS IN DEGREES, 360 degrees over 1024 counts per revolution
        LEFT_REAR_DIRECTION_ENCODER.setDistancePerPulse(360/1024); 
        RIGHT_FRONT_DIRECTION_ENCODER.setDistancePerPulse(360/1024);
        RIGHT_REAR_DIRECTION_ENCODER.setDistancePerPulse(360/1024);
    }




}
