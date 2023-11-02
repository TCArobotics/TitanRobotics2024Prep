package frc.robot.data;

public enum PortMap {
    GAMEPADXBOX(0), //swap with GAMEPADFLIGHT port, 1, if driverstation port is swapped
    GAMEPADFLIGHT(1), //swap with GAMEPADXBOX port, 0, if driverstation port is swapped
    FRONTRIGHT(1), //And_You is 3, Menoteus is 1
    REARRIGHT(0),//And_You is 0, Menoteus is 0
    FRONTLEFT(3),//And_You is 2, Menoteus is 3
    REARLEFT(2),//And_You is 1, Menoteus in 2
    
    SWERVEFRONTLEFTANGLECANMOTOR(5),
    SWERVEFRONTRIGHTANGLECANMOTOR(11),
    SWERVEBACKLEFTANGLECANMOTOR(9),
    SWERVEBACKRIGHTANGLECANMOTOR(8),

    SWERVEFRONTLEFTSPEEDCANMOTOR(3),
    SWERVEFRONTRIGHTSPEEDCANMOTOR(12),
    SWERVEBACKLEFTSPEEDCANMOTOR(15),
    SWERVEBACKRIGHTSPEEDCANMOTOR(14),

    FRONTLEFTANGLEENCODER(0), //Bearbotics bot only for these
    FRONTRIGHTANGLEENCODER(1),
    BACKLEFTANGLEENCODER(2),
    BACKRIGHTANGLEENCODER(3);

    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}
