package frc.robot.snippits;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PowerConstants;
import frc.robot.subsystems.drivebase.DriveBaseSub;

public class StraightPowerTime extends CommandBase {

  private DriveBaseSub driveBase;
  private double power;
  private double time;
  private double timestamp;

  public StraightPowerTime(DriveBaseSub driveBase, double power, double time) {
    this.power = power;
    this.time = time;
    this.driveBase = driveBase;
  }

  @Override
  public void initialize() {
    driveBase.coast();
    timestamp = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    driveBase.setAll(power);
  }


  @Override
  public void end(boolean interrupted) {
    driveBase.setAll(0);
    driveBase.brake();
  }

  @Override
  public boolean isFinished() {
      return System.currentTimeMillis() - timestamp > time;
  }

}
