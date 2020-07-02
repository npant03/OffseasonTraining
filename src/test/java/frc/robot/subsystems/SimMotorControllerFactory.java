package frc.robot.subsystems;

import static org.mockito.Mockito.mock;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.MotorControllerFactory;

public class SimMotorControllerFactory extends MotorControllerFactory{

    private boolean isReal;

    public SimMotorControllerFactory(boolean isReal){
        super(isReal);
        this.isReal = isReal;
    }
    
    @Override
    public VictorSPX getVictor(int id){
        if(isReal){return new VictorSPX(id);}
        else{return mock(VictorSPX.class);}
    }
}