package frc.robot.Auto.Actions;
//import frc.robot.Auto.Actions.Actions;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.sub.SwerveDrive;

public class SwerveForwardsTime implements Actions
{   
    Timer timer;
    double seconds;
    double speed;
    private SwerveDrive mSwerveDrive = null;
    /**
     * Run code once when the action is started, for setup
     */
    public SwerveForwardsTime(double seconds, double speed)
    {
        this.seconds = seconds;
        this.speed = speed;
    }

    @Override
    public void start(){
        timer = new Timer();
        timer.start();
    }

    /**
     * Called by runAction in AutoModeBase iteratively until isFinished returns true. Iterative logic lives in this
     * method
     */
    @Override
    public void update(){
    mSwerveDrive.SwerveDriveMath(0, speed, 0);
    }

    /**
     * Returns whether or not the code has finished execution. When implementing this interface, this method is used by
     * the runAction method every cycle to know when to stop running the action
     *
     * @return boolean
     */
    @Override
    public boolean isFinished(){
        if (timer.get() >= seconds){
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
        mSwerveDrive.SwerveDriveMath(0, 0, 0);
    }

}
