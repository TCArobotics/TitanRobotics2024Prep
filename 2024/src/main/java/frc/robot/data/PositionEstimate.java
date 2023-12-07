package frc.robot.data;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.AnalogEncoder;
import frc.robot.data.PortMap;

public class PositionEstimate 
{
    //motor vars. I made them since working with the full swerve module causes errors, and I wanted the motors themselves. -Aiden
    public CANSparkMax motorFL;
    public CANSparkMax motorBL;
    public CANSparkMax motorFR;
    public CANSparkMax motorBR;

    //distance encoders
    public RelativeEncoder frontLeftDistanceEncoder;
    public RelativeEncoder backLeftDistanceEncoder;
    public RelativeEncoder frontRightDistanceEncoder;
    public RelativeEncoder backRightDistanceEncoder;

    //distance variables
    double frontLeftDistance;
    double backLeftDistance;
    double frontRightDistance;
    double backRightDistance;

    //direction encoders
    AnalogEncoder backRightAnalogEncoder;
    AnalogEncoder backLeftAnalogEncoder;
    AnalogEncoder frontRightAnalogEncoder;
    AnalogEncoder frontLeftAnalogEncoder;

    public PositionEstimate()
    {
        motorFL = new CANSparkMax(PortMap.SWERVEFRONTLEFTSPEEDCANMOTOR.portNumber, MotorType.kBrushless);
        motorBL = new CANSparkMax(PortMap.SWERVEFRONTRIGHTSPEEDCANMOTOR.portNumber, MotorType.kBrushless);
        motorFR = new CANSparkMax(PortMap.SWERVEBACKLEFTSPEEDCANMOTOR.portNumber, MotorType.kBrushless);
        motorBR = new CANSparkMax(PortMap.SWERVEBACKRIGHTSPEEDCANMOTOR.portNumber, MotorType.kBrushless);
        
        backRightAnalogEncoder = new AnalogEncoder(PortMap.BACKRIGHTANGLEENCODER.portNumber); //these are bearbotics bot only
        backLeftAnalogEncoder = new AnalogEncoder(PortMap.BACKLEFTANGLEENCODER.portNumber);
        frontRightAnalogEncoder = new AnalogEncoder(PortMap.FRONTRIGHTANGLEENCODER.portNumber);
        frontLeftAnalogEncoder = new AnalogEncoder(PortMap.FRONTLEFTANGLEENCODER.portNumber);

        frontLeftDistanceEncoder = motorFL.getEncoder();
        backLeftDistanceEncoder = motorBL.getEncoder();
        frontRightDistanceEncoder = motorFR.getEncoder();
        backRightDistanceEncoder = motorBR.getEncoder();
        
        flDistance = frontLeftDistanceEncoder.getPosition();
        blDistance = backLeftDistanceEncoder.getPosition();
        frDistance = frontRightDistanceEncoder.getPosition();
        brDistance = backRightDistanceEncoder.getPosition();
    }

    public void encoderTest() //test encoder values
    {
        System.out.println("FRONTLEFT: " + ffDistance);
        System.out.println("BACKLEFT: " + blDistance);
        System.out.println("FRONTRIGHT: " + frDistance);
        System.out.println("BACRIGHT: " + brDistance);
    }
}
