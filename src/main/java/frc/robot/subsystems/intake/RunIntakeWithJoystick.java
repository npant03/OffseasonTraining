package frc.robot.subsystems.intake;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeWithJoystick extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private IntakeSub intake;
  private PaddedXbox joystick;

  /**
   * Creates a RunIntake which takes an intake (IntakeSub) and a factory (real or sim)
   */
  public RunIntakeWithJoystick(IntakeSub intake, PaddedXbox joystick) {
    this.intake = intake;
    this.joystick = joystick;
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
