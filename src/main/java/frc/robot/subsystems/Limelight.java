// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.reflect.Array;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  public double x = tx.getDouble(0.0);
  public double y = ty.getDouble(0.0);
  public double area = ta.getDouble(0.0);
  /** Creates a new Limelight. */
  public Limelight() {}
  @Override
  public void periodic() {
    SmartDashboard.putNumber("x2", x);
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    // This method will be called once per scheduler run
  }
}
//JE PENSE QUE TU VAS PAS BIEN DANS LA TÊTE NOAH DESS POUM POUM TROUM KOUM COUM BROOM(BALAIS);