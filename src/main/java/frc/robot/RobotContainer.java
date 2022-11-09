// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AirCommander;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.align;
import frc.robot.commands.kill;
import frc.robot.subsystems.AirDropper;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
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
  // The robot's subsystems and commands are defined here...
  private final Joystick m_Joystick = new Joystick(0);
  private final XboxController m_Controller = new XboxController(1);
  private final Joystick m_Copilote = new Joystick(2);
  private final AHRS ahrs = new AHRS(SPI.Port.kMXP);
  
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
     JoystickButton jo_3 = new JoystickButton(m_Joystick, 3);
     JoystickButton con_lb = new JoystickButton(m_Controller, 5);
     JoystickButton kill_button = new JoystickButton(m_Joystick, 8);
     //ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS
     JoystickButton ldrop = new JoystickButton(m_Copilote, 1);
     JoystickButton rdrop = new JoystickButton(m_Copilote, 2);
     JoystickButton f_scraper = new JoystickButton(m_Copilote, 3);
     JoystickButton b_scraper = new JoystickButton(m_Copilote, 4);
     //ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS ATTENTION VÉRIFER SI C'EST LES BONS BOUTONS
     jo_3.whenHeld(new align(m_drivetrain, m_lime, 3, m_Joystick, m_Controller));
     con_lb.whenHeld(new align(m_drivetrain, m_lime, 5, m_Joystick, m_Controller));
     kill_button.whenHeld(new kill(m_drivetrain, m_lime));
     ldrop.whenHeld(new AirCommander(m_airdropper, -1,0));
     rdrop.whenHeld(new AirCommander(m_airdropper, 1,0));
     f_scraper.whenHeld(new AirCommander(m_airdropper, 0,1));
     b_scraper.whenHeld(new AirCommander(m_airdropper, 0,-1));
    m_drivetrain.setDefaultCommand(new DriveCommand(m_Joystick, m_Controller, m_drivetrain, ahrs));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
   // return m_autoCommand;
   return null;
  }
}
