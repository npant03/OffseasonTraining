package frc.robot.snippits;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.drivebase.DriveBaseSub;

public class StraightThenTurn extends SequentialCommandGroup {
  
  /**
   * Creates a new StraightThenTurn.
   * @param DriveBaseSub driveBase
   * @param Double power
   * @param Double time
   * @param String direction
   */
  public StraightThenTurn(DriveBaseSub driveBase, double power, double time, String direction) {
    addCommands(new StraightPowerTime(driveBase, power, time));
    addCommands(new TurnPowerTime(driveBase, direction, power, time));
  }
}
