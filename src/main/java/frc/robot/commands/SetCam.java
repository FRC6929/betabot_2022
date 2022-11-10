// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Camera;

public class SetCam extends CommandBase {
  /** Creates a new SetMode. */
  boolean mode;
  Camera m_camera;

  public SetCam(Camera c, boolean mode) {
    m_camera = c;
    this.mode = mode;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotState.mode = mode;

    if(RobotState.mode){
      m_camera.look_down();
    }
    else{
      m_camera.look_forward();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
