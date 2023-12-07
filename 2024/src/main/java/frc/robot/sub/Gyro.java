package frc.robot.sub;

import com.kauailabs.navx.frc.AHRS;

public class Gyro extends ControlSubSystems 
{

    AHRS gyroscope;
    GyroSave gyroSave;

    private static Gyro mInstance = null;

    public static Gyro getInstance() 
    {
        if (mInstance == null) 
        {
            mInstance = new Gyro();
        }
        return mInstance;
    }
    
    public Gyro()   
    {
        gyroSave = new GyroSave();
    }

    private class GyroSave
    {
        public double yaw;
        public double roll;
        public double pitch;
        public double yawrate;
    }
    public void update() // if this is slow getting values, then rebuild this
    {
        gyroSave.yaw = gyroscope.getYaw();
        gyroSave.roll = gyroscope.getRoll();
        gyroSave.pitch = gyroscope.getPitch();
        gyroSave.yawrate = gyroscope.getRate();
    }

    public double yaw(){
        return gyroSave.yaw;
    }

    public double roll()
    {
        return gyroSave.roll;
    }

    public double pitch()
    {
        return gyroSave.pitch;
    }

    public double yawrate()
    {
        return gyroSave.yawrate;
    }

}