package frc.robot.sub;

public class Gyro 
{
    
    private static Gyro mInstance = null;

    public static Gyro getInstance()
    {
      if (mInstance == null) {
          mInstance = new Gyro();
      }
      return mInstance;
    
    }
    
    public Gyro()
    {
    }


    public void start()
    {

    }
    
    public void update()
    {
        
    }
}
