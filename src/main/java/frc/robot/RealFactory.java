package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

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

    VictorSPX intakeVictor = this.getVictor(CanIds.intakeVictor.id);
    IntakeSub intakeSub = new IntakeSub(intakeVictor);
    @Override
    public IntakeSub getIntakeSub(){
        return intakeSub;
    }

    PaddedXbox paddedXbox = new PaddedXbox();
    @Override
    public PaddedXbox getPaddedXbox(){
        return paddedXbox;
    }

}