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
  /** Creates a new AirDropper. */
  public AirDropper() {
     m_drop_AR.setInverted(true);
  }
  public void Dropper(int dropvalue){
    if (dropvalue == 1){
      m_drop_AR.set(.3);
      m_drop_AM.set(.3);
    }else if (dropvalue == -1){
      m_drop_AL.set(.3);
      m_drop_AM.set(-.3);
    }
  }

  public void Stop(){
    m_drop_AL.set(0);
    m_drop_AR.set(0);
    m_drop_AM.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
