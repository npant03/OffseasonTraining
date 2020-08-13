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
import frc.robot.snippits.TurnPowerTime;
import frc.robot.subsystems.drivebase.DriveBaseSub;

public class Week9TurnPowerTimeTest{

    // DO NOT PUT ROBOTCONTAINER UP HERE.
    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();
    DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
    TalonFX leftFront = driveBaseSub.getLeftMast();
    TalonFX leftBack = driveBaseSub.getLeftFollow();
    TalonFX rightFront = driveBaseSub.getRightMast();
    TalonFX rightBack = driveBaseSub.getRightFollow();
    JoystickButton mockButton = mock(JoystickButton.class);
    TurnPowerTime turnPowerTime = simFactory.getTurnPowerTime("LEFT", .2, 300);


    /**
     * Tests if StraightPowerTime.java sets the motor powers correctly and then shuts off correctly
     */
    @Test
    public void checkLeftPowers(){
        turnPowerTime.execute();
        verifyLeftRightPowers(-.2, .2);
    }

    @Test
    public void checkTimeStamp() throws InterruptedException {
        turnPowerTime.initialize();
        Thread.sleep(305);
        boolean done = turnPowerTime.isFinished();
        assertEquals(done, true);
    }

    @Test
    public void checkInit(){
        turnPowerTime.initialize();
        verifyNeutralMode(NeutralMode.Coast);
    }

    /**
     * Makes sure your end method stops the motors **AND SETS TO BRAKE MODE**
     */
    @Test
    public void checkEnd(){
        turnPowerTime.end(false);
        verifyLeftRightPowers(0, 0);
        verifyNeutralMode(NeutralMode.Brake);
    }  

    @Test
    public void rightChecks() throws InterruptedException {
        TurnPowerTime turnPowerTime = simFactory.getTurnPowerTime("RIGHT", .5, 700); 
        turnPowerTime.execute();
        verifyLeftRightPowers(.5, -.5);

        turnPowerTime.initialize();
        Thread.sleep(705);
        boolean done = turnPowerTime.isFinished();
        assertEquals(done, true);
    }

    public void verifyLeftRightPowers(double left, double right){
        verify(leftFront).set(ControlMode.PercentOutput, left);
        verify(leftBack).set(ControlMode.PercentOutput, left);
        verify(rightFront).set(ControlMode.PercentOutput, right);
        verify(rightBack).set(ControlMode.PercentOutput, right);
    }

    public void verifyNeutralMode(NeutralMode mode){
        verify(leftFront).setNeutralMode(mode);
        verify(leftBack).setNeutralMode(mode);
        verify(rightFront).setNeutralMode(mode);
        verify(rightBack).setNeutralMode(mode);
    }
}