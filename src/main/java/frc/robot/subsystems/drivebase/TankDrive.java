package frc.robot.subsystems.drivebase;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase {
  
  private DriveBaseSub driveBase;
  private PaddedXbox joystick;

  public TankDrive(DriveBaseSub driveBase, PaddedXbox joystick) {
    addRequirements(driveBase);
    this.driveBase = driveBase;
    this.joystick = joystick;
  }
  

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    driveBase.setLeftPower(joystick.getLeftY());
    driveBase.setRightPower(joystick.getRightY());
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.setAll(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
