package frc.robot.Auto.Actions;
//import frc.robot.Auto.Actions.Actions;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.sub.Drive;
import frc.robot.sub.Gyro;

public class TurningWithTheGyroAction implements Actions
{   
    Timer timer;
    double angle;
    double speed;
    private Drive mDrive = null;
    /**
     * Run code once when the action is started, for setup
     */
    public TurningWithTheGyroAction(double angle, double speed)
    {
        this.angle = angle;
        this.speed = speed;
    }

    @Override
    public void start(){
        angle = 0;
       angle = (angle - getyah()) + getyah();
       }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns true. Iterative logic lives in this
     * method
     */
    @Override
    public void update(){
    mDrive.drive(this.speed,this.angle);
    }

    /**
     * Returns whether or not the code has finished execution. When implementing this interface, this method is used by
     * the runAction method every cycle to know when to stop running the action
     *
     * @return boolean
     */
    @Override
    public boolean isFinished(){
        if (timer.get() >= angle){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Run code once when the action finishes, usually for clean up
     */
    @Override
    public void done(){
        mDrive.drive(0, 0);
    }

}
