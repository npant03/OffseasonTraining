package frc.robot.subsystems;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import frc.robot.Factory;
import frc.robot.Constants.CanIds;
import frc.robot.subsystems.intake.IntakeSub;

public class SimFactory implements Factory{

    @Override
    public VictorSPX getVictor(int id){
       return mock(VictorSPX.class);
    }

    @Override
    public TalonSRX getTalon(int id){
        return mock(TalonSRX.class);
    }

    VictorSPX intakeVictor = this.getVictor(CanIds.intakeVictor.id);
    IntakeSub intakeSub = new IntakeSub(intakeVictor);
    @Override
    public IntakeSub getIntakeSub(){
        return intakeSub;
    }

    PaddedXbox paddedXbox = spy(PaddedXbox.class);
    @Override
    public PaddedXbox getPaddedXbox(){
        return paddedXbox;
    }
    
}