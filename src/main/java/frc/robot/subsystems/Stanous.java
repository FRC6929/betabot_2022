// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConsStanous;

public class Stanous extends SubsystemBase {
  private final CANSparkMax m_stanous = new CANSparkMax(ConsStanous.Motor_Scrape, MotorType.kBrushless);
  /** Creates a new Stanous. */
  public Stanous() {}

  @Override
  public void periodic() {}
  public void Stanous_mtn(Boolean a_nous){
    if (a_nous == true){
      m_stanous.set(.3);
    }
    else if (a_nous == false){
      m_stanous.set(-.3);
    }
  }
  public void Stop(){
    m_stanous.set(0);
  }
}