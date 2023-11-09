package frc.robot.sub;

import frc.robot.data.PortMap;
//import frc.robot.sub.ModifiedMotor;

public class Drive
{
    private final ModifiedMotor motorFrontLeft;
    private final ModifiedMotor motorRearLeft;
    private final ModifiedMotor motorFrontRight;
    private final ModifiedMotor motorRearRight;
    DriveSave driveSave;

    private static Drive mInstance = null;

    public static Drive getInstance() {
      if (mInstance == null) {
          mInstance = new Drive();
      }
      return mInstance;
  }

   public Drive() 
   {
          motorFrontLeft = new ModifiedMotor(PortMap.FRONTLEFT.portNumber);
          motorRearLeft = new ModifiedMotor(PortMap.REARLEFT.portNumber);
          motorFrontRight = new ModifiedMotor(PortMap.FRONTRIGHT.portNumber);
          motorRearRight = new ModifiedMotor(PortMap.REARRIGHT.portNumber); 
          driveSave = new DriveSave();
   }

/**  Control Type: Left Stick controls Left side of Robot; Right Stick Control Right side of Robot (Used in tank)*/
   public void drive(double left, double right, double speed)// determins drriving type
   { 
     this.driveSave.frontleft = left;
     this.driveSave.backleft = left;
     this.driveSave.frontright = right;
     this.driveSave.backright = right; 
   }
/**  Control Type: Left Stick controls speed; Right Stick controls direction (Used in tank)*/
   public void drive(double forward, double turn)
   { 
     this.driveSave.frontleft = (-forward + (0.35 * turn));// + teleopDriftCorrect);  //subtract 0.02 here from leftY for Menoetius
     this.driveSave.backleft = (-forward + (0.35 * turn));// + teleopDriftCorrect); //add 0.02 here to leftY for Menoetius
     this.driveSave.frontright = (forward + (0.35 * turn));// + teleopDriftCorrect); //add 0.015 here to rightY for And-You
     this.driveSave.backright = (forward + (0.35 * turn));// + teleopDriftCorrect); //add 0.015 here to rightY forAnd-You
   }

   /**Saves the current state the motors should be in */
   private class DriveSave
   {
     public double frontleft;
     public double frontright;
     public double backleft;
     public double backright;
   }

   @Override
   /*Updates the state the motors are in */
   public void update()
   {
     motorFrontLeft.set(this.driveSave.frontleft);
     motorRearRight.set(this.driveSave.backright);
     motorRearLeft.set(this.driveSave.backleft);
     motorFrontRight.set(this.driveSave.frontright);
   }

    void tank()
   {

   }

}
