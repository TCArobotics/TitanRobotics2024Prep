package frc.robot.data;

public enum PortMap {
    GAMEPADXBOX(0), //swap with GAMEPADFLIGHT port, 1, if driverstation port is swapped
    GAMEPADFLIGHT(1), //swap with GAMEPADXBOX port, 0, if driverstation port is swapped
    FRONTRIGHT(1), //And_You is 3, Menoteus is 1
    REARRIGHT(0),//And_You is 0, Menoteus is 0
    FRONTLEFT(3),//And_You is 2, Menoteus is 3
    REARLEFT(2),//And_You is 1, Menoteus in 2
    
    SWERVEFRONTLEFTANGLECANMOTOR(11), //11
    SWERVEFRONTRIGHTANGLECANMOTOR(8),
    SWERVEBACKLEFTANGLECANMOTOR(5), //5
    SWERVEBACKRIGHTANGLECANMOTOR(9), //9

    SWERVEFRONTLEFTSPEEDCANMOTOR(12), //12
    SWERVEFRONTRIGHTSPEEDCANMOTOR(14),
    SWERVEBACKLEFTSPEEDCANMOTOR(3), //3
    SWERVEBACKRIGHTSPEEDCANMOTOR(15), //15

    FRONTLEFTANGLEENCODER(9), //Bearbotics bot only for these // 0
    FRONTRIGHTANGLEENCODER(2),
    BACKLEFTANGLEENCODER(1), //1
    BACKRIGHTANGLEENCODER(3); //3

    public int portNumber;
    private PortMap(int portValue) //constructor
    {
        this.portNumber = portValue;
    }
}
