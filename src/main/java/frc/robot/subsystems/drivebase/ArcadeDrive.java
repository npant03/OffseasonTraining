package frc.robot.subsystems.drivebase;

import com.team7419.PaddedXbox;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {

  private DriveBaseSub driveBase;
  private double kStraight;
  private double kTurn;
  private PaddedXbox joystick;

/**
 * Creates a new ArcadeDrive
 * @param joystick
 * @param driveBase
 * @param kStraight
 * @param kTurn
 */
  public ArcadeDrive(PaddedXbox joystick, DriveBaseSub driveBase, double kStraight, double kTurn){
    this.joystick = joystick;
    this.driveBase = driveBase;
    this.kStraight = kStraight;
    this.kTurn = kTurn;
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
    // driveBase.factoryResetAll();    
    driveBase.coast();
  }

  @Override
  public void execute() {
    
    // double leftPower = kTurn * joystick.getRightX() - kStraight * joystick.getLeftY();
    // double rightPower = kTurn * -joystick.getRightX() - kStraight * joystick.getLeftY();

    double leftPower = kStraight * joystick.getLeftY() + kTurn * joystick.getRightX();
    double rightPower = kStraight * joystick.getLeftY() + kTurn * -joystick.getRightX();

    driveBase.setLeftPower(leftPower);
    driveBase.setRightPower(rightPower);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.setAll(0);
  }

}
