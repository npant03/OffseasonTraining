package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;

public interface Factory {
    
    public IntakeSub getIntakeSub();
    public PaddedXbox getPaddedXbox();
    public RunIntake getRunIntake(double power);
}