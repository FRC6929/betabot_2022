// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AirCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.SetCam;
import frc.robot.commands.StanousCommand;
import frc.robot.commands.align;
import frc.robot.commands.Autonome.Automove;
import frc.robot.commands.Autonome.Delay;
import frc.robot.subsystems.AirDropper;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Stanous;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public final Drivetrain m_drivetrain = new Drivetrain();
  public final AirDropper m_airdropper = new AirDropper();
  public final Stanous m_stanous = new Stanous();
  public final Camera m_camera = new Camera();

  // The robot's subsystems and commands are defined here...
  private final Joystick m_Joystick = new Joystick(0);
  private final XboxController m_Controller = new XboxController(1);
  private final Joystick m_Copilote = new Joystick(2);
  
  public final Limelight m_lime = new Limelight();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton align_jo = new JoystickButton(m_Joystick, 3);
    JoystickButton align_co = new JoystickButton(m_Copilote, 7);
    JoystickButton camdown = new JoystickButton(m_Joystick, 4);//copilote
    JoystickButton camup = new JoystickButton(m_Joystick, 6);//copilote
    //ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS
    JoystickButton ldrop = new JoystickButton(m_Copilote, 1);
    JoystickButton rdrop = new JoystickButton(m_Copilote, 2);
    JoystickButton satnousdown_jo = new JoystickButton( m_Copilote, 8);//copilote
    JoystickButton satnousup_jo = new JoystickButton( m_Copilote, 9);//copilote
    //ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS
    align_jo.whenHeld(new align(m_drivetrain, m_lime, 3, m_Joystick, m_Controller));
    align_co.whenHeld(new align(m_drivetrain, m_lime, 3, m_Joystick, m_Controller));
    ldrop.whenHeld(new AirCommand(m_airdropper, -1));
    rdrop.whenHeld(new AirCommand(m_airdropper, 1));
    satnousdown_jo.whenHeld(new StanousCommand(m_stanous, false));
    satnousup_jo.whenHeld(new StanousCommand(m_stanous, true));
    camdown.whenActive(new SetCam(m_camera, false));
    camup.whenActive(new SetCam(m_camera, true));
    m_drivetrain.setDefaultCommand(new DriveCommand(m_Joystick, m_Controller, m_drivetrain));
  }
  public double getHeading(){
  return m_drivetrain.getHeading();
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_drivetrain.reset_encoders();

    Command m_auto = new Delay(2000);
    m_auto = m_auto.andThen(new Automove(m_drivetrain,1));
    
    return m_auto;
    // An ExampleCommand will run in autonomous
   // return m_autoCommand;
  }
}
