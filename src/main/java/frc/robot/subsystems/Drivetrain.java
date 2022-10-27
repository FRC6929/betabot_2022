// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_drive_fl = new CANSparkMax(Constants.ConsDrivetrain.Motor_Fl, MotorType.kBrushless);
  private final CANSparkMax m_drive_fr = new CANSparkMax(Constants.ConsDrivetrain.Motor_Fr, MotorType.kBrushless);
  private final CANSparkMax m_drive_bl = new CANSparkMax(Constants.ConsDrivetrain.Motor_Bl, MotorType.kBrushless);
  private final CANSparkMax m_drive_br = new CANSparkMax(Constants.ConsDrivetrain.Motor_Br, MotorType.kBrushless);
  private double ySpeed;
  private double xSpeed;
  private double zSpeed;


  /** Creates a new drivetrain. */
  public Drivetrain() {
    m_drive_fr.setInverted(true);
    m_drive_br.setInverted(true);
  }

  private final MecanumDrive m_MecanumDrive = new MecanumDrive(m_drive_fl, m_drive_bl, m_drive_fr, m_drive_br );


  public void drive(double y, double x, double z, double angle) {
  if (Math.abs(y) > 0.2){
    ySpeed = (Math.abs(y)-0.2)/0.8*(Math.abs(y)/y);
  }else{
    ySpeed = 0;
  }
  if (Math.abs(x) > 0.2){
    xSpeed = (Math.abs(x)-0.2)/0.8*(Math.abs(x)/x);
  }else{
    xSpeed = 0;
  }
  if (Math.abs(z) > 0.4){
    zSpeed = .25*(Math.abs(z)-0.4)/0.6*(Math.abs(z)/z);
    System.out.println(zSpeed);
  }else{
    zSpeed = 0;
  }
    SmartDashboard.putNumber("x", xSpeed);
    SmartDashboard.putNumber("y", ySpeed);
    SmartDashboard.putNumber("z", zSpeed);
    m_MecanumDrive.driveCartesian(ySpeed, -xSpeed, -zSpeed, -angle);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("drivetrain speed", get_speed());
    //SmartDashboard.putNumber("drivetrain encoder", get_encoder());
    //SmartDashboard.putNumber("gyro angle", m_ahrs.getPitch());
    // This method will be called once per scheduler run
  }
}