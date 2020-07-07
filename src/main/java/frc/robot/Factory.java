package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import frc.robot.subsystems.intake.IntakeSub;

public interface Factory {
    
    public VictorSPX getVictor(int id);
    public TalonSRX getTalon(int id);
    public IntakeSub getIntakeSub();
    public PaddedXbox getPaddedXbox();
}