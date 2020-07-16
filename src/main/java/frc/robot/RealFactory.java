package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import frc.robot.Constants.CanIds;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;
import frc.robot.subsystems.intake.RunIntakeWithJoystick;

public class RealFactory implements Factory{
    IntakeSub intakeSub;
    PaddedXbox paddedXbox;

	private VictorSPX getVictor(int id) {
        return new VictorSPX(id);
    }
    
    private TalonSRX getTalon(int id){
        return new TalonSRX(id);
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
        if (paddedXbox == null){
            paddedXbox = new PaddedXbox();
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
