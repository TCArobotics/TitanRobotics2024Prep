package frc.robot.Auto.Missions;

import frc.robot.Auto.AutoModeEndedException;
import frc.robot.Auto.Actions.DriveForTimeAction;
import frc.robot.Auto.Actions.ParallelAction;
import frc.robot.Auto.Actions.SwerveForwardsTime;

public class SwerveTest extends MissionBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        // TODO Auto-generated method stub
        runAction(new SwerveForwardsTime(3, 0.25));// may be milliseconds
    }
}
