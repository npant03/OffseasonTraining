package frc.robot.subsystems.intake;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Factory;

public class RunIntakeWithJoystick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private IntakeSub intake;
  private PaddedXbox joystick;
  private Factory factory;

  /**
   * Creates a RunIntake which takes an intake (IntakeSub) and a factory (real or sim)
   */
  public RunIntakeWithJoystick(IntakeSub intake, Factory factory) {
    this.intake = intake;
    this.factory = factory;
    this.joystick = factory.getPaddedXbox();
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.coast();
  }

  @Override
  public void execute() {
    intake.setPower(joystick.getLeftY());
  }

  @Override
  public void end(boolean interrupted) {
    intake.setPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
