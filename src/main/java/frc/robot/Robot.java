/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DrivetrainMove;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Shooter;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Drivetrain drivetrain;
  public static Gripper gripper;
  public static Feeder feeder;
  public static Shooter shooter;



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    SpeedControllerGroup leftController = new SpeedControllerGroup(new VictorSP(RobotMap.PWM.LEFT_VICTOR_1), new VictorSP(RobotMap.PWM.LEFT_VICTOR_2));
    SpeedControllerGroup rightController = new SpeedControllerGroup(new VictorSP(RobotMap.PWM.RIGHT_VICTOR_3), new VictorSP(RobotMap.PWM.RIGHT_VICTOR_4));
    drivetrain = new Drivetrain(leftController, rightController);
    gripper = new Gripper(new VictorSP(RobotMap.PWM.GRIPPER_MOTOR_5), new DigitalInput(RobotMap.DIO.GRIPPER_LIMIT_1));
    feeder = new Feeder(new VictorSP(RobotMap.PWM.FEEDER_MOTOR_6), new DigitalInput(RobotMap.DIO.FEEDER_LIMIT_2));
    WPI_TalonSRX talonSRX = new WPI_TalonSRX(RobotMap.CAN.SHOOTER_MOTOR_1);
    new WPI_VictorSPX(RobotMap.CAN.SHOOTER_MOTOR_2).follow(talonSRX);
    shooter = new Shooter(talonSRX);
    OI oi = new OI();
    drivetrain.setDefaultCommand(new DrivetrainMove(oi::getLeftJoystickY, oi::getRightJoystickY, drivetrain));


  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
