package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.Initers;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * For your first code assignment, you're going to be writing a subsystem 
 * for the intake on our 2020 robot. There's a tutorial on Notion that's going 
 * to step you through 
 */

 //comment 
 
public class IntakeSub extends SubsystemBase {
  
  private VictorSPX victor;

  public IntakeSub(VictorSPX victor) {
    this.victor = victor;
    Initers.initVictors(victor);
  }

    public VictorSPX getVictor(){return victor;}

    public void setPower(double power){
        victor.set(ControlMode.PercentOutput, power);
    }

    // completely trivial, only for a unit test
    public boolean getInverted(){return victor.getInverted();}

    public void coast(){victor.setNeutralMode(NeutralMode.Coast);}
    public void brake(){victor.setNeutralMode(NeutralMode.Brake);}

}
