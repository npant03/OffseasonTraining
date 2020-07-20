package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBaseSub extends SubsystemBase {
  
  private TalonFX leftBack;
  private TalonFX leftFront;
  private TalonFX rightBack;
  private TalonFX rightFront;


  /**
   * Creates a new DriveBaseSub.
   */
  public DriveBaseSub(TalonFX leftBack, TalonFX leftFront, TalonFX rightBack, TalonFX rightFront) {
    this.leftBack = leftBack;
    this.leftFront = leftFront;
    this.rightBack = rightBack;
    this.rightFront = rightFront;

    rightBack.setSensorPhase(false);
    rightBack.setInverted(true);
    rightFront.setSensorPhase(false);
    rightFront.setInverted(true);

    rightBack.follow(rightFront);
    leftBack.follow(leftFront);
  }

  @Override
  public void periodic() {
  }

  public TalonFX getLeftMast(){return leftFront;}
  public TalonFX getRightMast(){return rightFront;}
  
  public TalonFX getLeftFollow(){return leftBack;}
  public TalonFX getRightFollow(){return rightBack;}

  public void setLeftPower(double power){
    leftBack.set(ControlMode.PercentOutput, power);
    leftFront.set(ControlMode.PercentOutput, power);
  }
  public void setRightPower(double power){
    rightBack.set(ControlMode.PercentOutput, power);
    rightFront.set(ControlMode.PercentOutput, power);
  }

  public void setAll(double power){
    setLeftPower(power);
    setRightPower(power);
  }

  public void stop(){this.setAll(0);}


}
