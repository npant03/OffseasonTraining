package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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

    /**
     * Tests if StraightPowerTime.java sets the motor powers correctly and then shuts off correctly
     */
    @Test
    public void straightPowerTimeTest(){
        StraightPowerTime straightPowerTime = simFactory.getStraightPowerTime(0.5, 5);
        straightPowerTime.execute();
        verify(leftFront).set(ControlMode.PercentOutput, 0.5);
        verify(leftBack).set(ControlMode.PercentOutput, 0.5);
        verify(rightFront).set(ControlMode.PercentOutput, 0.5);
        verify(rightBack).set(ControlMode.PercentOutput, 0.5);
        
        double iTime = System.currentTimeMillis();
        if(System.currentTimeMillis() - iTime > 5){
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
        if(System.currentTimeMillis() - iTime < PowerConstants.AutoStraightTime.val*1000){
            verify(leftFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(leftBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        } 
        if (System.currentTimeMillis() - iTime < PowerConstants.AutoStraightTime.val*1000 && System.currentTimeMillis() - iTime >
        PowerConstants.AutoStraightTime.val*1000 - 0.01){
            verify(leftFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(leftBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightFront).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
            verify(rightBack).set(ControlMode.PercentOutput, PowerConstants.AutoStraightPower.val);
        } 
        if (System.currentTimeMillis() - iTime > PowerConstants.AutoStraightTime.val*1000){
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

}
