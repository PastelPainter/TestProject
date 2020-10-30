package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

public class Shooter extends SubsystemBase {
    private WPI_TalonSRX motor;
    public static final double WAIT_TIME = 2;
    public static final Supplier<Double> SPEED = () -> 0.5;

    public static final Supplier<Double> kP = () -> 0.5;
    public static final Supplier<Double> kI = () -> 0.0005;
    public static final Supplier<Double> kD = () -> 0.5;
    public static final Supplier<Double> tolerance = () -> 5.0;
    public static final Supplier<Double> setpoint = () -> 5.0;

    private Encoder encoder;

    public double getVelocity() {
        return motor.getSelectedSensorVelocity();
    }


    public Shooter(WPI_TalonSRX motor) {
        this.motor = motor;
    }
    public void shoot(double motorSpeed) {
        motor.set(motorSpeed);
    }
    public void stop() {
        motor.stopMotor();
    }
}
