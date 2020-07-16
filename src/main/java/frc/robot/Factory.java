package frc.robot;

import com.team7419.PaddedXbox;

import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;
import frc.robot.subsystems.intake.RunIntakeWithJoystick;

public interface Factory {
    
    public IntakeSub getIntakeSub();
    public PaddedXbox getPaddedXbox();
    public RunIntake getRunIntake(double power);
    public RunIntakeWithJoystick getRunIntakeWithJoystick(PaddedXbox joystick);

}
