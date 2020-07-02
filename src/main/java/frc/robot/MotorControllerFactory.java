package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class MotorControllerFactory {

    private boolean isReal;

    public MotorControllerFactory(boolean isReal){
        this.isReal = isReal;
    }
    
    public VictorSPX getVictor(int id){
        if(isReal){return new VictorSPX(id);}
        else{return null;}
    }

    public TalonSRX getTalon(int id){
        if(isReal){return new TalonSRX(id);}
        else{return null;}
    }
}