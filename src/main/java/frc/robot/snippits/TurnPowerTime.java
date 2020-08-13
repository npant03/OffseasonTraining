package frc.robot.snippits;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PowerConstants;
import frc.robot.subsystems.drivebase.DriveBaseSub;

public class TurnPowerTime extends CommandBase {

  private DriveBaseSub driveBase;
  private double power;
  private String direction;
  private double time;
  private double timestamp;

  public TurnPowerTime(DriveBaseSub driveBase, String direction, double power, double time) {
    this.power = power;
    this.time = time;
    this.direction = direction;
    this.driveBase = driveBase;
  }

  @Override
  public void initialize() {
    driveBase.coast();
    timestamp = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    double negative;
    if(direction == "LEFT"){negative = 1;}
    else{negative = -1;}
    driveBase.setLeftPower(power * negative);
    driveBase.setRightPower(-power * negative);
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
