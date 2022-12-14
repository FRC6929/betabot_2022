// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI;
public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_drive_fl = new CANSparkMax(Constants.ConsDrivetrain.Motor_Fl, MotorType.kBrushless);
  private final CANSparkMax m_drive_fr = new CANSparkMax(Constants.ConsDrivetrain.Motor_Fr, MotorType.kBrushless);
  private final CANSparkMax m_drive_bl = new CANSparkMax(Constants.ConsDrivetrain.Motor_Bl, MotorType.kBrushless);
  private final CANSparkMax m_drive_br = new CANSparkMax(Constants.ConsDrivetrain.Motor_Br, MotorType.kBrushless);
  private double ySpeed;
  private double xSpeed;
  private double zSpeed;
  private final AHRS ahrs = new AHRS(SPI.Port.kMXP);


  /** Creates a new drivetrain. */
  public Drivetrain() {
    resetNavx();
    m_drive_fr.setInverted(true);
    m_drive_br.setInverted(true);
  }
  public double getHeading() {
    double heading = -ahrs.getYaw();
    if (heading  > 180 || heading < 180) {
      heading = Math.IEEEremainder(heading, 360);
    }
    return heading;
  }
  public void resetNavx(){
  ahrs.reset();
  }

  public double get_encoder(){
    return (-m_drive_fl.getEncoder().getPosition()-m_drive_bl.getEncoder().getPosition()+m_drive_fr.getEncoder().getPosition()+m_drive_br.getEncoder().getPosition())/4;
  }

  public void reset_encoders(){
    m_drive_fr.getEncoder().setPosition(0);
    m_drive_fl.getEncoder().setPosition(0);
    m_drive_br.getEncoder().setPosition(0);
    m_drive_bl.getEncoder().setPosition(0);
  }

  private final MecanumDrive m_MecanumDrive = new MecanumDrive(m_drive_fl, m_drive_bl, m_drive_fr, m_drive_br );
  public void drive(double y, double x, double z, double angle, double slider, boolean des) {
    if (Math.abs(y) > 0.2){
      if(ySpeed != (Math.abs(y)-0.2)/0.8*(Math.signum(y)))
      ySpeed += Math.signum(y - ySpeed) * 0.05;
    }else{
      if(Math.abs(ySpeed) < 0.09) ySpeed = 0;
      if(ySpeed != 0) {
        ySpeed += Math.signum(0 - ySpeed) * 0.09; 
      }
    }
    if (Math.abs(x) > 0.2){
      if(xSpeed != (Math.abs(x)-0.2)/0.8*(Math.signum(x)) & des)
      xSpeed += Math.signum(x - xSpeed) * 0.05;
      else xSpeed = (Math.abs(x)-0.2)/0.8*(Math.signum(x));
    }else{
      if(Math.abs(xSpeed) < 0.09) xSpeed = 0;
      if(xSpeed != 0) {
        xSpeed += Math.signum(0 - xSpeed) * 0.09; 
      }
    }
    if (Math.abs(z) > 0.4){
      if(zSpeed != .6*(Math.abs(z)-0.4)/0.4*(Math.signum(z))) {
        zSpeed += Math.sin(z - zSpeed) * 0.04;
      }
    }else{
      if(Math.abs(zSpeed) < 0.09) zSpeed = 0;
      if(zSpeed != 0) {
        zSpeed += Math.signum(0 - zSpeed) * 0.09; 
      }
    }
    m_MecanumDrive.driveCartesian(ySpeed*slider, -xSpeed*slider, -zSpeed*slider);
    SmartDashboard.putNumber("x", xSpeed*slider);
    SmartDashboard.putNumber("y", ySpeed*slider);
    SmartDashboard.putNumber("z", zSpeed*slider);
    SmartDashboard.putNumber("anglee", angle);
    SmartDashboard.putNumber("encoder", get_encoder());
  }
  public void drive2(double y, double x, double z, double angle) {
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
      zSpeed = .3*(Math.abs(z)-0.4)/0.6*(Math.abs(z)/z);
    }else{
      zSpeed = 0;
    }
    m_MecanumDrive.driveCartesian(ySpeed, -xSpeed, -zSpeed, angle);
    SmartDashboard.putNumber("x2", xSpeed);
    SmartDashboard.putNumber("y2", ySpeed);
    SmartDashboard.putNumber("z2", zSpeed);
    SmartDashboard.putNumber("anglee2", angle);
  }
  @Override
  public void periodic() {
    //SmartDashboard.putNumber("drivetrain speed", get_speed());
    //SmartDashboard.putNumber("drivetrain encoder", get_encoder());
    //SmartDashboard.putNumber("gyro angle", m_ahrs.getPitch());
    // This method will be called once per scheduler run
  }
}