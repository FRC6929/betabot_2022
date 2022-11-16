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

  double y;
  double x;
  double z;
  double correct_modif; 
  double current_angle;
  double init_angle;
  boolean temp;
  public double slider;
  /** Creates a new DriveCommand. */
  public DriveCommand(Joystick j, XboxController g, Drivetrain drivetrain){
    m_drivetrain = drivetrain;
    m_Joystick = j;
    m_Gamepad = g;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.resetNavx();
    //m_navx.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Joystick mode
   /* SmartDashboard.putNumber(   "IMU_Yaw",              m_navx.getYaw());
    SmartDashboard.putNumber(   "IMU_Pitch",            m_navx.getPitch());
    SmartDashboard.putNumber(   "IMU_Roll",             m_navx.getRoll());
    SmartDashboard.putNumber("yaw", m_navx.getYaw());
    SmartDashboard.putNumber("zero angle", zero_angle);
    SmartDashboard.putNumber("real angle", real_angle);
*/
    SmartDashboard.putNumber("angle dans commande", m_drivetrain.getHeading());
    if(RobotState.joystick==true){
      y = -m_Joystick.getY();
      x = -m_Joystick.getX();
      if(Math.abs(z) < 0.4 & (Math.abs(x) > .2 | Math.abs(y) > .2)){
        if(temp == false){
          init_angle = m_drivetrain.getHeading();
          temp = true;
        }
        correct_modif = (init_angle - current_angle);
       if(correct_modif/300 < 0) z= correct_modif/300 -0.4;
       if(correct_modif/300 > 0) z= correct_modif/300 +0.4;
       if(correct_modif == 0) z = 0;
      }
      else  z = -m_Joystick.getZ(); temp = false;
      slider = (m_Joystick.getRawAxis(3) - 1)/ -2;
    }
    else{
      x = -m_Gamepad.getLeftX();
      y = -m_Gamepad.getLeftY();
      z = -m_Gamepad.getRawAxis(2);
    }
    current_angle = m_drivetrain.getHeading();
   // if(Math.abs(z) > 0.4 | (Math.abs(x) < .2 & Math.abs(y) < .2)) {
     // current_angle = m_drivetrain.getHeading();
    //}
   // current_angle = m_drivetrain.getHeading() - init_angle;
    //if(Math.abs(z) < 0.4 & (Math.abs(x) > .2 | Math.abs(y) > .2)) corrected_angle = (Math.abs(current_angle)/current_angle * 0.4 + current_angle/30);
    //else corrected_angle = z;
    SmartDashboard.putNumber("init_angel", init_angle);
    SmartDashboard.putNumber("correct_modif", correct_modif/300);
    SmartDashboard.putNumber("current_angle", current_angle);
    SmartDashboard.putNumber("xcommand", x);
    SmartDashboard.putNumber("ycommand", y);
    SmartDashboard.putNumber("zcommand", z);
   // m_drivetrain.drive(y,x,z, m_drivetrain.getHeading(), slider);
   m_drivetrain.drive(y,x,z, m_drivetrain.getHeading(), slider);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0.0,0.0,0.0,0.0,0.0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
