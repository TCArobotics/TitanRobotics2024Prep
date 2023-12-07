package frc.robot.sub;

import frc.robot.data.PortMap;
//import frc.robot.sub.ModifiedMotor;

public class TankDrive extends ControlSubSystems
{
    private final ModifiedMotor motorTankFrontLeft;
    private final ModifiedMotor motorTankRearLeft;
    private final ModifiedMotor motorTankFrontRight;
    private final ModifiedMotor motorTankRearRight;
    DriveSave driveSave;

    private static TankDrive mInstance = null;

    public static TankDrive getInstance() {
      if (mInstance == null) {
          mInstance = new TankDrive();
      }
      return mInstance;
  }

   public TankDrive() 
   {
          motorTankFrontLeft = new ModifiedMotor(PortMap.FRONTLEFT.portNumber);
          motorTankRearLeft = new ModifiedMotor(PortMap.REARLEFT.portNumber);
          motorTankFrontRight = new ModifiedMotor(PortMap.FRONTRIGHT.portNumber);
          motorTankRearRight = new ModifiedMotor(PortMap.REARRIGHT.portNumber); 
          driveSave = new DriveSave();
   }

/**  Control Type: Left Stick controls Left side of Robot; Right Stick Control Right side of Robot (Used in tank)*/
   public void tankDrive(double left, double right, double speed)// determines driving type
   { 
     this.driveSave.tankFrontLeft = left;
     this.driveSave.tankBackLeft = left;
     this.driveSave.tankFrontRight = right;
     this.driveSave.tankBackRight = right; 
   }
/**  Control Type: Left Stick controls speed; Right Stick controls direction (Used in tank)*/
   public void tankDrive(double forward, double turn)
   { 
     this.driveSave.tankFrontLeft = (-forward + (0.35 * turn));// + teleopDriftCorrect);  //subtract 0.02 here from leftY for Menoetius
     this.driveSave.tankBackLeft = (-forward + (0.35 * turn));// + teleopDriftCorrect); //add 0.02 here to leftY for Menoetius
     this.driveSave.tankFrontRight = (forward + (0.35 * turn));// + teleopDriftCorrect); //add 0.015 here to rightY for And-You
     this.driveSave.tankBackRight = (forward + (0.35 * turn));// + teleopDriftCorrect); //add 0.015 here to rightY forAnd-You
   }

   /**Saves the current state the motors should be in */
   private class DriveSave
   {
     public double tankFrontLeft;
     public double tankFrontRight;
     public double tankBackLeft;
     public double tankBackRight;
   }

   @Override
   /*Updates the state the motors are in */
   public void update()
   {
     motorTankFrontLeft.set(this.driveSave.tankFrontLeft);
     motorTankRearRight.set(this.driveSave.tankBackRight);
     motorTankRearLeft.set(this.driveSave.tankBackLeft);
     motorTankFrontRight.set(this.driveSave.tankFrontRight);
   }

    void tank()
   {

   }

}