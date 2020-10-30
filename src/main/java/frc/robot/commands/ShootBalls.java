package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class ShootBalls extends ParallelCommandGroup {
    public ShootBalls() {
        addCommands(new FeederMove(Feeder.setpoint, Robot.feeder, Feeder.kP, Feeder.kI, Feeder.kD, Feeder.tolerance,
                Robot.feeder::getEncoderValue), new ShooterMove(Shooter.setpoint, Robot.shooter, Shooter.kP, Shooter.kI,
                Shooter.kD, Shooter.tolerance, Robot.shooter::getVelocity, Robot.feeder));
    }
}
