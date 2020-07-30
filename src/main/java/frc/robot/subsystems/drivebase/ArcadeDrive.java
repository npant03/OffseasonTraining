package frc.robot.subsystems.drivebase;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {
  
  private DriveBaseSub driveBase;
  private PaddedXbox joystick;
  private double kStraight;
  private double kTurn;
  
  public ArcadeDrive(DriveBaseSub driveBase, PaddedXbox joystick, double kStraight, double kTurn) {
    addRequirements(driveBase);
    this.driveBase = driveBase;
    this.joystick = joystick;
    this.kStraight = kStraight;
    this.kTurn = kTurn;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double leftPower = kStraight * joystick.getLeftY() + kTurn * joystick.getRightX();
    double rightPower = kStraight * joystick.getRightY() + kTurn * -joystick.getRightX();

    driveBase.setLeftPower(leftPower);
    driveBase.setRightPower(rightPower);
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
