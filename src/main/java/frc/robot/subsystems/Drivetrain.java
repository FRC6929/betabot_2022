// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_drive_fl = new CANSparkMax(Constants.ConsDrivetrain.Motor_Fl, MotorType.kBrushless);
  private final CANSparkMax m_drive_fr = new CANSparkMax(Constants.ConsDrivetrain.Motor_Fr, MotorType.kBrushless);
  private final CANSparkMax m_drive_bl = new CANSparkMax(Constants.ConsDrivetrain.Motor_Bl, MotorType.kBrushless);
  private final CANSparkMax m_drive_br = new CANSparkMax(Constants.ConsDrivetrain.Motor_Br, MotorType.kBrushless);

  private final MecanumDrive m_MecanumDrive = new MecanumDrive(m_drive_fl, m_drive_fr, m_drive_bl, m_drive_br );

  /** Creates a new drivetrain. */
  public Drivetrain() {

  }
  
  public void drive(double x, double y) {
    if (y != 0){
      m_drive_bl.set(y);
      m_drive_br.set(y);
      m_drive_fl.set(y);
      m_drive_fr.set(y);
    }
    else if (x != 0){
      m_drive_bl.set(-x);
      m_drive_br.set(x);
      m_drive_fl.set(x);
      m_drive_fr.set(-x);
    }
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("drivetrain speed", get_speed());
    //SmartDashboard.putNumber("drivetrain encoder", get_encoder());
    //SmartDashboard.putNumber("gyro angle", m_ahrs.getPitch());
    // This method will be called once per scheduler run
  }
}