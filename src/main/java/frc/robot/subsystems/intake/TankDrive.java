package frc.robot.subsystems.intake;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase {
  
  private DriveBaseSub driveBaseSub;
  private PaddedXbox joystick;


  /**
   * Creates a new TankDrive.
   */
  public TankDrive(DriveBaseSub driveBaseSub, PaddedXbox joystick) {
    this.driveBaseSub = driveBaseSub;
    this.joystick = joystick;
    addRequirements(driveBaseSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    driveBaseSub.setLeftPower(joystick.getLeftY());
    driveBaseSub.setRightPower(joystick.getRightY());
  }

  @Override
  public void end(boolean interrupted) {
    driveBaseSub.setAll(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
