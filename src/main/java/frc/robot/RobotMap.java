package frc.robot;

public class RobotMap {
    public interface PWM{
        int LEFT_VICTOR_1 = 0;
        int LEFT_VICTOR_2 = 1;
        int RIGHT_VICTOR_3 = 2;
        int RIGHT_VICTOR_4 = 3;
        int GRIPPER_MOTOR_5 = 4;
        int FEEDER_MOTOR_6 = 5;
    }
    public interface DIO{
        int GRIPPER_LIMIT_1 = 0;
        int FEEDER_LIMIT_2 = 1;
    }
    public interface CAN{
        int SHOOTER_MOTOR_1 = 1;
        int SHOOTER_MOTOR_2 = 2;
    }
}
