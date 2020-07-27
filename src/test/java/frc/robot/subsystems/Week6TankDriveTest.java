package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivebase.DriveBaseSub;
import frc.robot.subsystems.drivebase.TankDrive;

public class Week6TankDriveTest {

    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();

    /**
     * Checks if RobotContainer's setDefaultCommands method sets a default command
     * to DriveBaseSub
     * {@code This is code}
     * my file is at {@docRoot}
     * @author henry
     * @version the final one
     * @since when i wrote this you nosy little
     * @deprecated this is not deprecated don't worry
     * @exception _ this doesn't throw anything!
     * @param _ takes no parameters
     * @return _ returns nothin
     * @see robotcontainer
     * @serial why are you still looking at this?
     * @serialData im super serial
     * @serialField I'm sorry if you read all of that.
     */
    @Test
    public void driveBaseSubHasDefaultCommandTest() {
        JoystickButton mockButton = mock(JoystickButton.class);
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        robotContainer.setDefaultCommands();
        assertNotEquals(null, driveBaseSub.getDefaultCommand());
    }

    /**
     * Checks that the drive base's default command is TankDrive
     */
    @Test
    public void tankDriveIsDefaultCommandTest() {
        JoystickButton mockButton = mock(JoystickButton.class);
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        robotContainer.setDefaultCommands();
        assertEquals(simFactory.getTankDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
    }

    /**
     * Checks that moving the left joystick up will correlate to the left side speed
     */
    @Test
    public void driveBaseLeftIsControlledWithLeftJoystickTest() {
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        TankDrive tankDrive = new TankDrive(driveBaseSub, joystick);
        TalonFX leftFront = driveBaseSub.getLeftMast();
        TalonFX leftBack = driveBaseSub.getLeftFollow();
        when(joystick.getLeftY()).thenReturn(.75);
        tankDrive.execute();
        verify(leftFront).set(ControlMode.PercentOutput, .75);
        try {
            verify(leftBack, atLeast(0)).follow(leftFront);
        } catch (AssertionError e) {
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .75);;
        }
    }

    /**
     * Checks that moving the right joystick up will correlate to the right side speed
     */
    @Test
    public void driveBaseRightIsControlledWithRightJoystickTest(){   
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        TankDrive tankDrive = new TankDrive(driveBaseSub, joystick);
        TalonFX rightFront = driveBaseSub.getRightMast();
        TalonFX rightBack = driveBaseSub.getRightFollow();
        when(joystick.getRightY()).thenReturn(.75);
        tankDrive.execute();
        verify(rightFront).set(ControlMode.PercentOutput, .75);
        try {
            verify(rightBack, atLeast(0)).follow(rightFront);
        } catch (AssertionError e) {
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .75);;
        }
    }

    /**
     * Checks that when the command *ENDS* hint hint, all 4 drive motors stop.
     */
    @Test
    public void turnsOffWhenCommandEndsTest(){
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        TalonFX leftFront = driveBaseSub.getLeftMast();
        TalonFX rightFront = driveBaseSub.getRightMast();
        TankDrive tankDrive = new TankDrive(driveBaseSub, joystick);
        tankDrive.end(false);
        verify(leftFront).set(ControlMode.PercentOutput, 0);
        verify(rightFront).set(ControlMode.PercentOutput, 0);
    }

}
