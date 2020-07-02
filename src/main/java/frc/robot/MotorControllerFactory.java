package frc.robot;

// import static org.mockito.Mockito;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Victor;

public class MotorControllerFactory {

    private boolean isReal;

    public MotorControllerFactory(boolean isReal){
        this.isReal = isReal;
    }
    
    public VictorSPX getVictor(int id){
        if(isReal){return new VictorSPX(id);}
        // else{return mock(VictorSPX.class);}
        else{return new VictorSPX(99);}
    }

    public TalonSRX getTalon(int id){
        if(isReal){return new TalonSRX(id);}
        else{return null;}
    }
}