// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Stanous;

public class StanousCommand extends CommandBase {
  Stanous mStanous;
  Boolean a_nous_mtn;
  /** Creates a new StanousCommand. */
  public StanousCommand(Stanous stanous,Boolean a_nous) {
    mStanous = stanous;
    a_nous_mtn = a_nous;
    addRequirements(mStanous);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mStanous.Stanous_mtn(a_nous_mtn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mStanous.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
