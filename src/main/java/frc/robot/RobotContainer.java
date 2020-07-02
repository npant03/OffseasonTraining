package frc.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
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


  private IMotorControllerFactory factory;
  
  private VictorSPX intakeVictor;
  private final IntakeSub intake;
  
  // The commands that run on those subsystems are defined here
  private final RunIntake runIntake;

  private PaddedXbox xbox;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(IMotorControllerFactory factory) {
    this.factory = factory;
    intakeVictor = factory.getVictor(CanIds.intakeVictor.id);
    intake = new IntakeSub(intakeVictor);
    runIntake = new RunIntake(intake, .5);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. 
   * We're going to teach you how to use this later.
   */
  public void configureButtonBindings() {
    // new JoystickButton(xbox, PaddedXbox.F310Map.kGamepadButtonX.value)
    // .whenPressed(new RunIntake(intake, .5));
  }
}
