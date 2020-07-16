package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntake extends CommandBase {
  /**
   * Creates a new RunIntake.
   */
  private IntakeSub intake;
  private double power;

  public RunIntake(IntakeSub intake, double power) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.power = power;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  public double getPower() {
    this.power = 0.5;
    return power;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setPower(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}