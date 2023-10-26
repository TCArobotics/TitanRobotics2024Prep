package frc.robot.data;

public enum PortMap {
    GAMEPADXBOX(0), //swap with GAMEPADFLIGHT port, 1, if driverstation port is swapped
    GAMEPADFLIGHT(1), //swap with GAMEPADXBOX port, 0, if driverstation port is swapped
    FRONTRIGHT(1), //And_You is 3, Menoteus is 1
    REARRIGHT(0),//And_You is 0, Menoteus is 0
    FRONTLEFT(3),//And_You is 2, Menoteus is 3
    REARLEFT(2),//And_You is 1, Menoteus in 2
    SWERVEFRONTLEFTANGLECANMOTOR(0),
    SWERVEFRONTRIGHTANGLECANMOTOR(0),
    SWERVEBACKLEFTANGLECANMOTOR(0),
    SWERVEBACKRIGHTANGLECANMOTOR(0),

    SWERVEFRONTLEFTSPEEDCANMOTOR(0),
    SWERVEFRONTRIGHTSPEEDCANMOTOR(0),
    SWERVEBACKLEFTSPEEDCANMOTOR(0),
    SWERVEBACKRIGHTSPEEDCANMOTOR(0);

    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}
