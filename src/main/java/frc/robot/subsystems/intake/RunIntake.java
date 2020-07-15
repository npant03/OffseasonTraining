package frc.robot.subsystems.intake;

import com.team7419.PaddedXbox;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntake extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private IntakeSub intake;
  private double power;
  private PaddedXbox joystick;
  private Boolean powerIfTrue;

  public RunIntake(IntakeSub intake, double power){
    this.intake = intake;
    this.power = power;
    this.powerIfTrue = true;
  }
  
  public RunIntake(IntakeSub intake, PaddedXbox joystick) {
    this.intake = intake;
    this.joystick = joystick;
    this.powerIfTrue = false;
    addRequirements(intake);
  }

  public double getPower() {
    if(this.power == 0){return this.joystick.getLeftY();}
    else{return this.power;}
  }

  @Override
  public void initialize() {
    intake.coast(); //this isn't necessary but i want it.
  }

  @Override
  public void execute() {
    if(powerIfTrue){intake.setPower(power);}
    else{intake.setPower(joystick.getLeftY());}
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
