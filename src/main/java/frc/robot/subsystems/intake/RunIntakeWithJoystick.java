package frc.robot.subsystems.intake;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakeSub;

public class RunIntakeWithJoystick extends CommandBase {
  
  private IntakeSub intake;
  private PaddedXbox joystick;

  public RunIntakeWithJoystick(IntakeSub intake, PaddedXbox joystick) {
      this.intake = intake;
      this.joystick = joystick;
      addRequirements(intake);
    }

  @Override
  public void initialize() {
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
