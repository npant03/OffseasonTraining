package frc.robot.subsystems.intake;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntake extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private IntakeSub intake;
  private double power;

  /**
   * Creates a RunIntake which takes an intake (IntakeSub) and a power (double).
   */
  public RunIntake(IntakeSub intake, double power){
    this.intake = intake;
    this.power = power;
  }

  public double getPower() {
    return this.power;
  }

  @Override
  public void initialize() {
    intake.coast(); //this isn't necessary but i want it.
  }

  @Override
  public void execute() {
    intake.setPower(power);
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
