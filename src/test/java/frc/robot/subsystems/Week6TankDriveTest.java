package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.DriveBaseSub;
import frc.robot.subsystems.intake.TankDrive;

public class Week6TankDriveTest {

    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();

    /**
     * Checks if RobotContainer's setDefaultCommands method sets a default command to DriveBaseSub
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
    public void tankDriveIsDefaultCommandTest(){
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
    public void driveBaseLeftIsControlledWithLeftJoystickTest(){   
        DriveBaseSub driveBaseSub = mock(DriveBaseSub.class);
        TankDrive tankDrive = new TankDrive(driveBaseSub, joystick);
        when(joystick.getLeftY()).thenReturn(0.75);
        tankDrive.execute();
        ArgumentCaptor<Double> arguments = ArgumentCaptor.forClass(Double.class);
        verify(driveBaseSub).setLeftPower(arguments.capture());
        assertNotEquals(0, arguments.getValue());
    }

    /**
     * Checks that moving the right joystick up will correlate to the right side speed
     */
    @Test
    public void driveBaseRightIsControlledWithRightJoystickTest(){   
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        TankDrive tankDrive = new TankDrive(driveBaseSub, joystick);
        TalonFX rightBack = driveBaseSub.getRightFollow();
        when(joystick.getRightY()).thenReturn(0.75);
        tankDrive.execute();
        ArgumentCaptor<Double> arguments = ArgumentCaptor.forClass(Double.class);
        verify(rightBack).set(ControlMode.PercentOutput, any());
        // assertNotEquals(0, arguments.getValue());
    }

    /**
     * Checks that when the command *ENDS* hint hint, the motors stop.
     */
    @Test
    public void turnsOffWhenCommandEndsTest(){
        DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
        TalonFX leftFront = driveBaseSub.getLeftMast();
        TalonFX rightFront = driveBaseSub.getRightMast();
        TalonFX leftBack = driveBaseSub.getLeftFollow();
        TalonFX rightBack = driveBaseSub.getRightFollow();
        TankDrive tankDrive = new TankDrive(driveBaseSub, joystick);
        tankDrive.end(false);
        verify(leftFront).set(ControlMode.PercentOutput, 0);
        verify(rightFront).set(ControlMode.PercentOutput, 0);
        verify(leftBack).set(ControlMode.PercentOutput, 0);
        verify(rightBack).set(ControlMode.PercentOutput, 0);
    }


}
