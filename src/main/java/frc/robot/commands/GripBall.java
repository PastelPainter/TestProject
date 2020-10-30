package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Gripper;

public class GripBall extends SequentialCommandGroup {
    public GripBall() {
        addCommands(new GripperMove(Robot.gripper, Gripper.IN_SPEED.get()), new FeederMove(Feeder.setpoint, Robot.feeder, Feeder.kP, Feeder.kI, Feeder.kD, Feeder.tolerance, Robot.feeder::getEncoderValue).withTimeout(Feeder.FeederRunTime));
    }
}
