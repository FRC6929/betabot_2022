

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirDropper;

public class AirCommand extends CommandBase {
  AirDropper mAirDropper;
  int drop_value;
  int scraper;
  /** Creates a new AirCommander. */
  public AirCommand(AirDropper airDropper,int drop) {
    mAirDropper = airDropper;
    drop_value = drop;
    addRequirements(mAirDropper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mAirDropper.Dropper(drop_value);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mAirDropper.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
