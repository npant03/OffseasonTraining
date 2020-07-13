package frc.robot;

import com.team7419.PaddedXbox;

import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;

public interface Factory {
    
    public IntakeSub getIntakeSub();
    public PaddedXbox getPaddedXbox();
    public RunIntake getRunIntakeWithPower(double power);
    public RunIntake getRunIntakeWithJoystick(PaddedXbox joystick);
}