package frc.robot.subsystems;

import static org.mockito.Mockito.mock;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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

    @Override
    public IntakeSub getIntakeSub(){
        VictorSPX intakeVictor = this.getVictor(CanIds.intakeVictor.id);
        return new IntakeSub(intakeVictor);
    }
}