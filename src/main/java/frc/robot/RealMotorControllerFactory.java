package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class RealMotorControllerFactory implements IMotorControllerFactory{

	@Override
	public VictorSPX getVictor(int id) {
        return new VictorSPX(id);
    }
    
    @Override
    public TalonSRX getTalon(int id){
        return new TalonSRX(id);
    }
}