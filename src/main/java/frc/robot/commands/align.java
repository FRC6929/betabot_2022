// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotState;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class align extends CommandBase {
  Drivetrain mDrivetrain;
  int bouton;
  Limelight limel;
  Joystick joojo;
  XboxController controler;
  double y;
  double z;
  boolean go = false;
  /** Creates a new align. */
  public align(Drivetrain drivetrain, Limelight lime, int boton, Joystick joystick, XboxController controller) {
    mDrivetrain = drivetrain;
    limel = lime;
    bouton = boton;//fait par nao
    joojo = joystick;
    controler = controller;
    addRequirements(mDrivetrain, limel);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("xi", limel.x);
    SmartDashboard.putNumber("yi", limel.y);
    SmartDashboard.putNumber("areai", limel.area);
    SmartDashboard.putNumber("speed", -((limel.x/25*0.4) + (Math.abs(limel.x)/limel.x * 0.2)));
    if (RobotState.joystick==true){
      y = joojo.getY()*-.5;
      z = joojo.getZ()*-0.7;
    }else{
      y = -controler.getLeftY()*.5;
      z = -controler.getRawAxis(2);
    } 
    if(joojo.getRawButtonPressed(5))
    {
      go = true;
    }
    else if(joojo.getRawButtonReleased(5))
    {
      go = false;
    }
    if(go == true){
    mDrivetrain.drive(.3, 0, 0, 0, 1, false);
    }else 
    {
      if (limel.y!=0 ){
        mDrivetrain.drive(y,-((limel.x/25*0.4) + (Math.abs(limel.x)/limel.x * 0.21)), z, 0, 1, false);
      }
    }
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
