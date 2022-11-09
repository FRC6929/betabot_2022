// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConsAirDropper;

public class AirDropper extends SubsystemBase {
  private final CANSparkMax m_drop_AL = new CANSparkMax(ConsAirDropper.Motor_AL, MotorType.kBrushless);
  private final CANSparkMax m_drop_AM = new CANSparkMax(ConsAirDropper.Motor_AM, MotorType.kBrushless);
  private final CANSparkMax m_drop_AR = new CANSparkMax(ConsAirDropper.Motor_AR, MotorType.kBrushless);
  private final CANSparkMax m_drop_Scraper = new CANSparkMax(ConsAirDropper.Motor_Scrape, MotorType.kBrushless);
  /** Creates a new AirDropper. */
  public AirDropper() {
     m_drop_AR.setInverted(true);
  }
  public void dropper(int dropvalue, int scraper){
    if (dropvalue == 1){
      m_drop_AR.set(.5);
      m_drop_AM.set(.5);
    }if (dropvalue == -1){
      m_drop_AL.set(.5);
      m_drop_AM.set(-.5);
    }
    if (scraper == 1) {
      m_drop_Scraper.set(.3);
    }if (scraper == -1) {
      m_drop_Scraper.set(-.3);
    }
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
