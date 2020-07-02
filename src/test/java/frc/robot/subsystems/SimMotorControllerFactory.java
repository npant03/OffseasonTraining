package frc.robot.subsystems;

import static org.mockito.Mockito.mock;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.IMotorControllerFactory;

public class SimMotorControllerFactory implements IMotorControllerFactory{

    @Override
    public VictorSPX getVictor(int id){
       return mock(VictorSPX.class);
    }
}