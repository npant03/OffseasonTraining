package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.*;
import frc.robot.subsystems.drivebase.*;

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

        DriveBaseSub dummyDriveBase = new DriveBaseSub(leftFollow, leftMast, rightFollow, rightMast);
        if(leftFollowing & rightFollowing){following = true;}
    }

    @Test
    public void checkTankDriveControls(){
        configureFollowers();

        // check both sides are independent
        setJoystickValues(0, .75, 0, .5);
        tankDrive.execute();
        verifyLeftRightSpeeds(0.75, 0.5);

        // check setting negatives works
        setJoystickValues(0, -.5, 0, -.3);
        tankDrive.execute();
        verifyLeftRightSpeeds(-0.5, -.3);

        // check setting random things doesnt move
        setJoystickValues(1, 0, 1, 0);
        tankDrive.execute();
        verifyLeftRightSpeeds(0, 0);
    }

    @Test
    public void checkTankEndMethod(){
        configureFollowers();
        tankDrive.end(true);
        verifyLeftRightSpeeds(0, 0);
    }

    @Test
    public void checkArcadeDriveControls(){
        configureFollowers();
        double kStraight = PowerConstants.DriveBaseStraight.val;
        double kTurn = PowerConstants.DriveBaseTurn.val;

        // check straight
        setJoystickValues(0, .2, 0, 0);
        arcadeDrive.execute();
        verifyLeftRightSpeeds(kStraight * 0.2, kStraight * 0.2);

        // check left turn
        setJoystickValues(0, 0, -.5, 0);
        arcadeDrive.execute();
        verifyLeftRightSpeeds(-kTurn * 0.5, kTurn * 0.5);

        // check right turn
        setJoystickValues(0, 0, .9, 0);
        arcadeDrive.execute();
        verifyLeftRightSpeeds(kTurn * 0.9, -kTurn * 0.9);

        // check straight and right together
        setJoystickValues(0, 0.7, 0.4, 0);
        arcadeDrive.execute();
        verifyLeftRightSpeeds(kStraight * 0.7 + kTurn * .4, kStraight * 0.7 - kTurn * .4);
    }

    @Test
    public void checkArcadeEndMethod(){
        configureFollowers();
        arcadeDrive.end(true);
        verifyLeftRightSpeeds(0, 0);
    }

    private void verifyLeftRightSpeeds(double left, double right){
        verify(leftMast).set(ControlMode.PercentOutput, left);
        verify(leftFollow).set(ControlMode.PercentOutput, left);
        verify(rightMast).set(ControlMode.PercentOutput, right);
        verify(rightFollow).set(ControlMode.PercentOutput, right);
    }

    private void setJoystickValues(double leftX, double leftY, double rightX, double rightY){
        when(joystick.getLeftX()).thenReturn(leftX);
        when(joystick.getLeftY()).thenReturn(leftY);
        when(joystick.getRightX()).thenReturn(rightX);
        when(joystick.getRightY()).thenReturn(rightY);
    }

    private void configureFollowers(){
        if(following){
            rightFollow = rightMast;
            leftFollow = leftMast;
        }
    }
}