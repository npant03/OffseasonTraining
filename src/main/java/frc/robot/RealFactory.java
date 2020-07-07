package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants.CanIds;
import frc.robot.subsystems.intake.IntakeSub;

public class RealFactory implements Factory{

	@Override
	public VictorSPX getVictor(int id) {
        return new VictorSPX(id);
    }
    
    @Override
    public TalonSRX getTalon(int id){
        return new TalonSRX(id);
    }

    @Override
    public IntakeSub getIntakeSub(){
        VictorSPX intakeVictor = this.getVictor(CanIds.intakeVictor.id);
        return new IntakeSub(intakeVictor);
    }

}