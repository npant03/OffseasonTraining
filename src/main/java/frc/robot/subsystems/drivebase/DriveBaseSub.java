package frc.robot.subsystems.drivebase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBaseSub extends SubsystemBase {
  private TalonFX leftMast;
  private TalonFX rightMast;
  private TalonFX leftFollow;
  private TalonFX rightFollow;



  public DriveBaseSub(TalonFX leftMast, TalonFX rightMast, TalonFX leftFollow, TalonFX rightFollow) {
    this.leftMast = leftMast;
    this.rightMast = rightMast;
    this.leftFollow = leftFollow;
    this.rightFollow = rightFollow;

    leftFollow.follow(leftMast);
    rightFollow.follow(rightMast);
  }

  public TalonFX getLeftMast() {return this.leftMast;}
  public TalonFX getRightMast() {return this.rightMast;}
  public TalonFX getLeftFollow() {return this.leftFollow;}
  public TalonFX getRightFollow() {return this.rightFollow;}

  public void setAll(double power){
    setLeftPower(power);
    setRightPower(power);
  }

  public void setLeftPower(double power){
    leftMast.set(ControlMode.PercentOutput, power);
    leftFollow.set(ControlMode.PercentOutput, power);
  }
  public void setRightPower(double power){
    rightMast.set(ControlMode.PercentOutput, power);
    rightFollow.set(ControlMode.PercentOutput, power);
  }

  public void brake(){
    rightMast.setNeutralMode(NeutralMode.Brake);
    rightFollow.setNeutralMode(NeutralMode.Brake);
    leftFollow.setNeutralMode(NeutralMode.Brake);
    leftMast.setNeutralMode(NeutralMode.Brake);
  }

  public void coast(){
    rightMast.setNeutralMode(NeutralMode.Coast);
    rightFollow.setNeutralMode(NeutralMode.Coast);
    leftFollow.setNeutralMode(NeutralMode.Coast);
    leftMast.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
  }
}
