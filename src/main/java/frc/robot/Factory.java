package frc.robot;

import com.team7419.PaddedXbox;

import frc.robot.snippits.StraightPowerTime;
import frc.robot.subsystems.drivebase.ArcadeDrive;
import frc.robot.subsystems.drivebase.DriveBaseSub;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;
import frc.robot.subsystems.intake.RunIntakeWithJoystick;
import frc.robot.subsystems.drivebase.TankDrive;

public interface Factory {
    
    public IntakeSub getIntakeSub();
    public PaddedXbox getPaddedXbox();
    public RunIntake getRunIntakeWithPower(double power);
    public RunIntakeWithJoystick getRunIntakeWithJoystick(PaddedXbox joystick);
    public DriveBaseSub getDriveBaseSub();
    public TankDrive getTankDrive(PaddedXbox joystick);
    public ArcadeDrive getArcadeDrive(PaddedXbox joystick);
    public StraightPowerTime getStraightPowerTime(double power, double time);

}
