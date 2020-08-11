package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.GetPowerConstants;
import frc.robot.PowerConstants;
import frc.robot.RobotContainer;
import frc.robot.snippits.StraightPowerTime;
import frc.robot.subsystems.drivebase.DriveBaseSub;

public class Week8StraightPowerTimeTest{

    // DO NOT PUT ROBOTCONTAINER UP HERE.
    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();
    DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
    TalonFX leftFront = driveBaseSub.getLeftMast();
    TalonFX leftBack = driveBaseSub.getLeftFollow();
    TalonFX rightFront = driveBaseSub.getRightMast();
    TalonFX rightBack = driveBaseSub.getRightFollow();
    JoystickButton mockButton = mock(JoystickButton.class);
    StraightPowerTime straightPowerTime = simFactory.getStraightPowerTime(PowerConstants.AutoStraightPower.val, 
    PowerConstants.AutoStraightTime.val);
    GetPowerConstants powerConstants;


    /**
     * Tests if StraightPowerTime.java sets the motor powers correctly and then shuts off correctly
     */
    @Test
    public void straightPowerTimeTest(){
        straightPowerTime.execute();
        verify(leftFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        verify(leftBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        verify(rightFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        verify(rightBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        
        double iTime = System.currentTimeMillis();
        if(System.currentTimeMillis() - iTime > PowerConstants.AutoStraightTime.val){
            verify(leftFront).set(ControlMode.PercentOutput, 0);
            verify(leftBack).set(ControlMode.PercentOutput, 0);
            verify(rightFront).set(ControlMode.PercentOutput, 0);
            verify(rightBack).set(ControlMode.PercentOutput, 0);
        }
    }

    /**
     * Tests if you dependency injected the right variables
     */
    @Test
    public void dependencyInjectedCorrectlyTest(){
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        Command mockCommand = robotContainer.getAutoCommand(); // I know, it's not a mock. relax, I'm just bad at names
        mockCommand.execute();        
        double iTime = System.currentTimeMillis();
        if(System.currentTimeMillis() - iTime < PowerConstants.AutoStraightTime.val){
            verify(leftFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(leftBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        } else if (System.currentTimeMillis() - iTime < PowerConstants.AutoStraightTime.val && System.currentTimeMillis() - iTime >
        PowerConstants.AutoStraightTime.val - 0.01){
            verify(leftFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(leftBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        } else if (System.currentTimeMillis() - iTime > PowerConstants.AutoStraightTime.val){
            verify(leftFront).set(ControlMode.PercentOutput, 0);
            verify(leftBack).set(ControlMode.PercentOutput, 0);
            verify(rightFront).set(ControlMode.PercentOutput, 0);
            verify(rightBack).set(ControlMode.PercentOutput, 0);
        }
    }

    /**
     * Makes sure your end method stops the motors **AND SETS TO BRAKE MODE**
     */
    @Test
    public void straightPowerTimeEndTest(){
        straightPowerTime.end(false);
        verify(leftFront).set(ControlMode.PercentOutput, 0);
        verify(leftBack).set(ControlMode.PercentOutput, 0);
        verify(rightFront).set(ControlMode.PercentOutput, 0);
        verify(rightBack).set(ControlMode.PercentOutput, 0);
        
        verify(leftFront).setNeutralMode(NeutralMode.Brake);
        verify(leftBack).setNeutralMode(NeutralMode.Brake);
        verify(rightFront).setNeutralMode(NeutralMode.Brake);
        verify(rightBack).setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Tests if you set your auto in robot container (with the right method name, getAutoCommand)
     */
    @Test
    public void robotContainerSetsAutoTest(){
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        robotContainer.getAutoCommand();
        assertEquals(straightPowerTime.getClass(), robotContainer.getAutoCommand().getClass());
    }

    @Test
    public void hashingTest(){

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
            //   leftFollowing = true;
              return null;
            }
        }).when(leftBack).follow(leftFront);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
            //   rightFollowing = true;
              return null;
            }
        }).when(rightBack).follow(rightFront);
        powerConstants = simFactory.getPowerConstants();
        when(powerConstants.getAutoStraightPower()).thenReturn(.82);
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        Command autoCommand = robotContainer.getAutoCommand();
        System.out.println(autoCommand.getName());
        autoCommand.execute();
        verify(powerConstants).getAutoStraightPower();
        // verify(leftFront).set(ControlMode.PercentOutput, .82);
        // verify(leftBack).set(ControlMode.PercentOutput, .82);
        // verify(rightFront).set(ControlMode.PercentOutput, .82);
        // verify(rightBack).set(ControlMode.PercentOutput, .82);
    }
}
