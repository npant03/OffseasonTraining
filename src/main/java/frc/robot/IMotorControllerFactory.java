package frc.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public interface IMotorControllerFactory {
    
    public VictorSPX getVictor(int id);
}