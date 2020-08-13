package frc.robot.subsystems;

import static org.mockito.Mockito.mock;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import frc.robot.Factory;
import frc.robot.PowerConstants;
import frc.robot.Constants.CanIds;
import frc.robot.snippits.StraightPowerTime;
import frc.robot.snippits.TurnPowerTime;
import frc.robot.subsystems.drivebase.ArcadeDrive;
import frc.robot.subsystems.drivebase.DriveBaseSub;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;
import frc.robot.subsystems.intake.RunIntakeWithJoystick;
import frc.robot.subsystems.drivebase.TankDrive;

public class SimFactory implements Factory{
    IntakeSub intakeSub;
    PaddedXbox paddedXbox;
    DriveBaseSub driveBaseSub;

    private VictorSPX getVictor(int id){
       return mock(VictorSPX.class);
    }

    private TalonFX getTalonFX(int id){
        return mock(TalonFX.class);
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
    
    @Override
    public DriveBaseSub getDriveBaseSub(){
        if (driveBaseSub == null){
            driveBaseSub = new DriveBaseSub(this.getTalonFX(CanIds.leftBack.id), this.getTalonFX(CanIds.leftFront.id), 
            this.getTalonFX(CanIds.rightBack.id), this.getTalonFX(CanIds.rightFront.id));
        }
            return driveBaseSub;
    }

    @Override 
    public TankDrive getTankDrive(PaddedXbox joystick){
        return new TankDrive(this.getDriveBaseSub(), joystick);
    }

    @Override 
    public ArcadeDrive getArcadeDrive(PaddedXbox joystick){
        return new ArcadeDrive(this.getDriveBaseSub(), joystick, PowerConstants.DriveBaseStraight.val, PowerConstants.DriveBaseTurn.val);
    }
    
    @Override
    public StraightPowerTime getStraightPowerTime(double power, double time){
        return new StraightPowerTime(this.getDriveBaseSub(), power, time);
    }
    
    @Override
    public TurnPowerTime getTurnPowerTime(String direction, double power, double time){
        return new TurnPowerTime(this.getDriveBaseSub(), direction, power, time);
    }
}
