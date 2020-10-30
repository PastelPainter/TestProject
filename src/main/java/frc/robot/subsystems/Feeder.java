package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import java.util.function.Supplier;

public class Feeder extends SubsystemBase {

    private SpeedController motor;
    private DigitalInput limitSwitch;
    private Encoder source;

    private int ballsAmount;

    private boolean wasInLimitPressed;
    private boolean wasOutLimitPressed;

    public static final int MAXIMUM_BALLS_AMOUNT = 5;
    public static final Supplier<Double> SPEED = () -> 0.5;
    public static final double FeederRunTime = 5;
    public static final double WAIT_TIME = 1;

    public static final Supplier<Double> kP = () -> 0.5;
    public static final Supplier<Double> kI = () -> 0.0005;
    public static final Supplier<Double> kD = () -> 0.5;
    public static final Supplier<Double> tolerance = () -> 5.0;
    public static final Supplier<Double> setpoint = () -> 5.0;


    public Feeder(SpeedController motor, DigitalInput limitSwitch, Encoder source) {
        this.motor = motor;
        this.limitSwitch = limitSwitch;
        ballsAmount = 3;
        wasInLimitPressed = false;
        wasOutLimitPressed = false;
        this.source = source;
    }

    public int getEncoderValue() {
        return source.get();
    }

    public int getBallsAmount() {
        return ballsAmount;
    }

    public void feederMove(double motorSpeed) {
        if (motorSpeed >= 0) {
            motor.set(motorSpeed);
        } else {
            stop();
        }
    }

    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void periodic() {
        if (limitSwitch.get()) {
            if (!wasOutLimitPressed) {
                ballsAmount--;
            }
            wasOutLimitPressed = true;
        } else {
            wasOutLimitPressed = false;
        }
        if (Robot.gripper.getLimitSwitch()) {
            if (!wasInLimitPressed) {
                ballsAmount++;
            }
            wasInLimitPressed = true;
        } else {
            wasInLimitPressed = false;
        }
    }
}
