package frc.robot.sub;

import java.lang.Math;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.AnalogEncoder;

import frc.robot.data.PortMap;
import frc.robot.data.Settings;
import frc.robot.sub.Controllers;

public class SwerveDrive extends ControlSubSystems
{
    public final double Length = 1; //change to actual measurement of distance between axles on each side of robot
    public final double Width = 1; //change to actual measurement of distance between axles on each side of robot

    double backRightSpeed;
    double backLeftSpeed;
    double frontRightSpeed;
    double frontLeftSpeed;

    double backRightAngle;
    double backLeftAngle;
    double frontRightAngle;
    double frontLeftAngle;    

    AnalogEncoder backRightEncoder;
    AnalogEncoder backLeftEncoder;
    AnalogEncoder frontRightEncoder;
    AnalogEncoder frontLeftEncoder;
    
    SwerveModule mFL;
    SwerveModule mFR;
    SwerveModule mBL;
    SwerveModule mBR;

    private static SwerveDrive mSwerveInstance = null;

    public static SwerveDrive getInstance() {
      if (mSwerveInstance == null) {
          mSwerveInstance = new SwerveDrive();
      }
      return mSwerveInstance;
  }


    public SwerveDrive()
    {
        backRightEncoder = new AnalogEncoder(PortMap.BACKRIGHTANGLEENCODER.portNumber); //these are bearbotics bot only
        backLeftEncoder = new AnalogEncoder(PortMap.BACKLEFTANGLEENCODER.portNumber);
        frontRightEncoder = new AnalogEncoder(PortMap.FRONTRIGHTANGLEENCODER.portNumber);
        frontLeftEncoder = new AnalogEncoder(PortMap.FRONTLEFTANGLEENCODER.portNumber);

        mFL = new SwerveModule(new CANSparkMax(PortMap.SWERVEFRONTLEFTSPEEDCANMOTOR.portNumber, MotorType.kBrushless), new CANSparkMax(PortMap.SWERVEFRONTLEFTANGLECANMOTOR.portNumber, MotorType.kBrushless), frontLeftEncoder); //Front Left
        mFR = new SwerveModule(new CANSparkMax(PortMap.SWERVEFRONTRIGHTSPEEDCANMOTOR.portNumber, MotorType.kBrushless), new CANSparkMax(PortMap.SWERVEFRONTRIGHTANGLECANMOTOR.portNumber, MotorType.kBrushless), frontRightEncoder);
        mBL = new SwerveModule(new CANSparkMax(PortMap.SWERVEBACKLEFTSPEEDCANMOTOR.portNumber, MotorType.kBrushless), new CANSparkMax(PortMap.SWERVEBACKLEFTANGLECANMOTOR.portNumber, MotorType.kBrushless), backLeftEncoder);
        mBR = new SwerveModule(new CANSparkMax(PortMap.SWERVEBACKRIGHTSPEEDCANMOTOR.portNumber, MotorType.kBrushless), new CANSparkMax(PortMap.SWERVEBACKRIGHTANGLECANMOTOR.portNumber, MotorType.kBrushless), backRightEncoder);
    }

    public void SwerveDriveMath (double X, double Y, double Z) //X axis on the flight controller controls sideways component of strafing, Y axis on the flight controller controls forward/back component of strafing, Z axis controls rotation
    {
        double r = Math.sqrt ((Length * Length) + (Width * Width)); //Distance from corner wheel to opposite corner wheel of bot (ex. frontLeft to backRight); Uses Pythagorean Theorem
        Y *= -1; //flips sign (- to +, or + to -)

        double a = X - Z * (Length / r); //Left/Right part of the desired vector for back side motors
        double b = X + Z * (Length / r); //Left/Right part of the desired vector for front side motors
        double c = Y - Z * (Width / r); //Forward/Backward part of the desired vector for left side motors
        double d = Y + Z * (Width / r); //Forward/Backward part of the desired vector for right side motors


        backRightSpeed = Math.sqrt((a * a) + (d * d)); //Vector for total speed of back right motor found using Pythagorean Theorem (a^2 + d^2 = speed^2)
        backLeftSpeed = Math.sqrt((a * a) + (c * c)); //Vector for total speed of back left motor found using Pythagorean Theorem (a^2 + c^2 = speed^2)
        frontRightSpeed = Math.sqrt((b * b) + (d * d)); //Vector for total speed of front right motor found using Pythagorean Theorem (b^2 + d^2 = speed^2)
        frontLeftSpeed = Math.sqrt((b * b) + (c * c)); //Vector for total speed of front left motor found using Pythagorean Theorem (b^2 + c^2 = speed^2)

        backRightAngle = Math.atan2(a, d) / Math.PI; //Angle for the back right wheel found from the arctan of the left/right part and the forward/backward part of the desired vector for the motor, divided by pi to convert it to a value between -1 and 1 for the direction motor
        backLeftAngle = Math.atan2(a, c) / Math.PI; //Angle for the back left wheel found from the arctan of the left/right part and the forward/backward part of the desired vector for the motor, divided by pi to convert it to a value between -1 and 1 for the direction motor
        frontRightAngle = Math.atan2(b, d) / Math.PI; //Angle for the front right wheel found from the arctan of the left/right part and the forward/backward part of the desired vector for the motor, divided by pi to convert it to a value between -1 and 1 for the direction motor
        frontLeftAngle = Math.atan2(b, c) / Math.PI; //Angle for the front left wheel found from the arctan of the left/right part and the forward/backward part of the desired vector for the motor, divided by pi to convert it to a value between -1 and 1 for the direction motor
    }

    private class SwerveModule 
    {
        private CANSparkMax angleMotor; //change if different motors are used
        private CANSparkMax speedMotor;
        //public RelativeEncoder angleMotorEncoder;
        public AnalogEncoder angleMotorEncoder;
        public PIDController pidController;
        ModuleSave moduleSave;
        public SwerveModule (CANSparkMax speedMotor, CANSparkMax angleMotor, AnalogEncoder analogEncoder)
        {
            pidController = new PIDController(Settings.SwerveAngleKp, Settings.SwerveAngleKi, Settings.SwerveAngleKd);
            this.angleMotor = angleMotor;
            this.speedMotor = speedMotor;
            //angleMotorEncoder = angleMotor.getEncoder();
            angleMotorEncoder = analogEncoder;
            angleMotorEncoder.setDistancePerRotation(2);

            moduleSave = new ModuleSave();
        }

        private class ModuleSave 
        {
            public double driveMotorSpeed;
            public double angleMotorSpeed;
            public double desiredAngle;
            public double currentAngle;

        }

        public void angleMotorMath ()
        {
           moduleSave.angleMotorSpeed = pidController.calculate(moduleSave.currentAngle, moduleSave.desiredAngle);
        }

        public void desiredVector(double desiredSpeed, double desiredAngle)
        {
            moduleSave.driveMotorSpeed = desiredSpeed;
            moduleSave.desiredAngle = desiredAngle;
        }

        public void update()
        {
           // moduleSave.currentAngle = angleMotorEncoder.getPosition();
            moduleSave.currentAngle = angleMotorEncoder.getDistance();
            angleMotorMath();
            speedMotor.set(moduleSave.driveMotorSpeed);
            angleMotor.set(moduleSave.angleMotorSpeed);
        }

    }

    /*Saves the current state the motors should be in */

    @Override
    /*Updates the state the motors are in */
    public void update()
    {
      SwerveDriveMath(Length, Width, Length);

      mBR.desiredVector(backRightSpeed, backRightAngle);
      mBL.desiredVector(backLeftSpeed, backLeftAngle);
      mFR.desiredVector(frontRightSpeed, frontRightAngle);
      mFL.desiredVector(frontLeftSpeed, frontLeftAngle);

      mBR.update();
      mBL.update();
      mFR.update();
      mFL.update();
    }

    void swerve() 
    {

    }
}