package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

import java.util.function.Supplier;

public class ShooterMove extends CommandBase {
    private Shooter shooter;
    private double timeOfRunningOut;

    private Supplier<Double> setpoint;
    private PIDController pidController;
    private Supplier<Double> kP,kI,kD;
    private Supplier<Double> tolerance;
    private Supplier<Double> source;
    private Feeder feeder;

    public ShooterMove(Supplier<Double> setpoint, Shooter shooter, Supplier<Double> kP, Supplier<Double> kI, Supplier<Double> kD, Supplier<Double> tolerance, Supplier<Double> source, Feeder feeder) {
        this.shooter = shooter;
        timeOfRunningOut = 0;
        addRequirements(shooter);
        this.setpoint = setpoint;
        this.pidController = new PIDController(kP.get(), kI.get(), kD.get());
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.tolerance = tolerance;
        this.source = source;
        this.feeder = feeder;
    }

    @Override
    public boolean isFinished() {
       return feeder.getBallsAmount() == 0;
    }

    public void execute() {
        pidController.setPID(kP.get(), kI.get(), kD.get());
        pidController.setTolerance(tolerance.get());
        double pidOut = pidController.calculate(source.get(), setpoint.get());
        shooter.shoot(pidOut);
    }
    public void end(boolean interrupted) {shooter.stop();}
}

