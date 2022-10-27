// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends CommandBase {
  private final Drivetrain m_drivetrain;

  private final Joystick m_Joystick;
  private final XboxController m_Gamepad;
  private final AHRS m_navx;

  double y;
  double x;
  double z;
  /** Creates a new DriveCommand. */
  public DriveCommand(Joystick j, XboxController g, Drivetrain drivetrain, AHRS navx){
    m_drivetrain = drivetrain;
    m_Joystick = j;
    m_Gamepad = g;
    m_navx = navx;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_navx.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Joystick mode
    SmartDashboard.putNumber(   "IMU_Yaw",              m_navx.getYaw());
    SmartDashboard.putNumber(   "IMU_Pitch",            m_navx.getPitch());
    SmartDashboard.putNumber(   "IMU_Roll",             m_navx.getRoll());
    if(RobotState.joystick==true){
    y = -m_Joystick.getY();
    x = -m_Joystick.getX();
    z = -m_Joystick.getZ();
    }
    else{
      x = -m_Gamepad.getRawAxis(0);
      y = m_Gamepad.getRawAxis(1);
      z = -m_Gamepad.getRawAxis(2);
    }
    m_drivetrain.drive(y,x,z, 0.0);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0.0,0.0,0.0,m_navx.getYaw());
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
