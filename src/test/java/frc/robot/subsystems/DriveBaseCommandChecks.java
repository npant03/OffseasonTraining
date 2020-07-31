package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivebase.ArcadeDrive;
import frc.robot.subsystems.drivebase.DriveBaseSub;
import frc.robot.subsystems.drivebase.TankDrive;

public class DriveBaseCommandChecks {

    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();
    DriveBaseSub driveBase = simFactory.getDriveBaseSub();
    TalonFX leftMast = driveBase.getLeftMast();
    TalonFX leftFollow = driveBase.getLeftFollow();
    TalonFX rightMast = driveBase.getRightMast();
    TalonFX rightFollow = driveBase.getRightFollow();
    boolean following = false;
    boolean leftFollowing = false;
    boolean rightFollowing = false;
        
    JoystickButton mockButton = mock(JoystickButton.class);
    TankDrive tankDrive = simFactory.getTankDrive(joystick);
    ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);

    @Test
    public void checkDefaultCommand(){
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        robotContainer.setDefaultCommands();
        Class defaultClass = driveBase.getDefaultCommand().getClass();
        assertEquals(defaultClass == TankDrive.class | defaultClass == ArcadeDrive.class, true);
    }


    @Test
    public void checkIfFollowing(){
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
              leftFollowing = true;
              return null;
            }
        }).when(leftFollow).follow(leftMast);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
              rightFollowing = true;
              return null;
            }
        }).when(rightFollow).follow(rightMast);

        driveBase = new DriveBaseSub(leftFollow, leftMast, rightFollow, rightMast);
        if(leftFollowing & rightFollowing){following = true;}
    }

    @Test
    public void checkTankDrive(){
        if(following){
            rightFollow = rightMast;
            leftFollow = leftMast;
        }

        when(joystick.getLeftY()).thenReturn(.75);
        when(joystick.getRightY()).thenReturn(.5);
        tankDrive.execute();
        checkLeftRightSpeeds(0.75, 0.5);

        when(joystick.getLeftY()).thenReturn(-.75);
        when(joystick.getRightY()).thenReturn(0.0);
        tankDrive.execute();
        checkLeftRightSpeeds(-0.75, 0.0);
    }

    @Test
    public void checkEndMethod(){
        tankDrive.end(true);
        checkLeftRightSpeeds(0, 0);
    }

    private void checkLeftRightSpeeds(double left, double right){
        verify(leftMast).set(ControlMode.PercentOutput, left);
        verify(leftFollow).set(ControlMode.PercentOutput, left);
        verify(rightMast).set(ControlMode.PercentOutput, right);
        verify(rightFollow).set(ControlMode.PercentOutput, right);
    }
}