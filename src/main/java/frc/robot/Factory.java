package frc.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.subsystems.intake.IntakeSub;

public interface Factory {
    
    public VictorSPX getVictor(int id);
    public TalonSRX getTalon(int id);
    public IntakeSub getIntakeSub();
}