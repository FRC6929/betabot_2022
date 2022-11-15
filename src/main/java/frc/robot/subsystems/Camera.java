// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  private Servo m_servo = new Servo(9);
  /** Creates a new Camera. */
  public Camera() {}
  
  public void look_down(){
    SmartDashboard.putNumber("servo", m_servo.getPosition());
    m_servo.set(.5);
    m_servo.setPosition(0);
  }

  public void look_forward(){
    m_servo.set(.5);
    m_servo.setPosition(0.35);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
