package frc.robot.subsystems;

import static org.mockito.Mockito.mock;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import frc.robot.Factory;
import frc.robot.Constants.CanIds;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;
import frc.robot.subsystems.intake.RunIntakeWithJoystick;

public class SimFactory implements Factory{
    IntakeSub intakeSub;
    PaddedXbox paddedXbox;

    private VictorSPX getVictor(int id){
       return mock(VictorSPX.class);
    }

    private TalonSRX getTalon(int id){
        return mock(TalonSRX.class);
    }

    @Override
    public IntakeSub getIntakeSub(){
        if (intakeSub == null){
            intakeSub = new IntakeSub(this.getVictor(CanIds.intakeVictor.id));
        }
        return intakeSub;
    }
    
    @Override
    public PaddedXbox getPaddedXbox(){
        if(paddedXbox == null){
            paddedXbox = mock(PaddedXbox.class);
        }
        return paddedXbox;
    }

    @Override
    public RunIntake getRunIntakeWithPower(double power){
        return new RunIntake(this.getIntakeSub(), power);
    }

    /**
     * Always pass SimFactory through this method. If you want it to be real, use the other method.
     */
    @Override
    public RunIntakeWithJoystick getRunIntakeWithJoystick(PaddedXbox joystick){
        return new RunIntakeWithJoystick(this.getIntakeSub(), joystick);
    }
    
}
