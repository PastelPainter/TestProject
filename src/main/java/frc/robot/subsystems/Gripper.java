package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

import java.awt.*;
import java.util.function.Supplier;

public class Gripper extends SubsystemBase {
    private SpeedController motor;
    private DigitalInput limitSwitch;
    public static final Supplier<Double> IN_SPEED = () -> 0.5;

    public Gripper(SpeedController motor, DigitalInput limitSwitch) {
        this.motor = motor;
        this.limitSwitch = limitSwitch;
    }

    public boolean getLimitSwitch() {
        return limitSwitch.get();
    }

    public void moveMotor(double motorSpeed) {
        if (motorSpeed >=0 && limitSwitch.get() && Robot.feeder.getBallsAmount() < Feeder.MAXIMUM_BALLS_AMOUNT){
            motor.set(motorSpeed);
        } else {
            stop();
        }
    }
    public void stop(){
        motor.stopMotor();
    }
}
