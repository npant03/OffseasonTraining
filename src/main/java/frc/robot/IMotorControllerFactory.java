package frc.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public interface IMotorControllerFactory {
    
    public VictorSPX getVictor(int id);
    public TalonSRX getTalon(int id);
}