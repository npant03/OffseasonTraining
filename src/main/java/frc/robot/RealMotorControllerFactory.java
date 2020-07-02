package frc.robot;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class RealMotorControllerFactory implements IMotorControllerFactory{

	@Override
	public VictorSPX getVictor(int id) {
        return new VictorSPX(id);
	}
}