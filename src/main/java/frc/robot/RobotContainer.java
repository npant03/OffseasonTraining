package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.CanIds;
import frc.robot.subsystems.intake.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private Factory factory;
  private IntakeSub intake;
  private PaddedXbox joystick;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(Factory factory) {
    this.factory = factory;
    intake = factory.getIntakeSub();
    joystick = factory.getPaddedXbox();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. 
   * We're going to teach you how to use this later.
   */
  public void configureButtonBindings() {
    // joystick.getA().whenPressed(factory.getRunIntake(.5));
  }

  public void setDefaultCommand(){intake.setDefaultCommand(factory.getRunIntake(joystick));}
  
}
