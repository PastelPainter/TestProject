package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

import java.util.function.Supplier;

public class FeederMove extends CommandBase {
    private Feeder feeder;
    private double timeOfRunningOut;
    private Supplier<Double> setpoint;
    private PIDController pidController;
    private Supplier<Double> kP,kI,kD;
    private Supplier<Double> tolerance;
    private Supplier<Integer> source;


    public FeederMove(Supplier<Double> setpoint, Feeder feeder, Supplier<Double> kP, Supplier<Double> kI, Supplier<Double> kD, Supplier<Double> tolerance, Supplier<Integer> source) {
        this.feeder = feeder;
        timeOfRunningOut = 0;
        addRequirements(feeder);
        this.setpoint = setpoint;
        this.pidController = new PIDController(kP.get(), kI.get(), kD.get());
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.tolerance = tolerance;
        this.source = source;
    }
    public void execute() {
        pidController.setPID(kP.get(), kI.get(), kD.get());
        pidController.setTolerance(tolerance.get());
        double pidOut = pidController.calculate(source.get(), setpoint.get());
        feeder.feederMove(pidOut);
    }

    @Override
    public boolean isFinished() {
        if (feeder.getBallsAmount() > 0) {
            timeOfRunningOut = Timer.getFPGATimestamp();
        }
        return Timer.getFPGATimestamp() - timeOfRunningOut > Feeder.WAIT_TIME;
    }

    public void end(boolean interrupted) {feeder.stop();}
}
