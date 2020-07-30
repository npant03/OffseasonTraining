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

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivebase.ArcadeDrive;
import frc.robot.subsystems.drivebase.DriveBaseSub;
import frc.robot.subsystems.drivebase.TankDrive;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Week6And7DriveTrainTest {

    // DO NOT PUT ROBOTCONTAINER UP HERE.
    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();
    DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
    TalonFX leftFront = driveBaseSub.getLeftMast();
    TalonFX leftBack = driveBaseSub.getLeftFollow();
    TalonFX rightFront = driveBaseSub.getRightMast();
    TalonFX rightBack = driveBaseSub.getRightFollow();
    JoystickButton mockButton = mock(JoystickButton.class);
    TankDrive tankDrive = simFactory.getTankDrive(joystick);
    ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);

    /**
     * Checks if RobotContainer's setDefaultCommands method sets a default command to DriveBaseSub
     */
    @Test
    public void driveBaseSubHasDefaultCommandTest() {
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        robotContainer.setDefaultCommands();
        assertNotEquals(null, driveBaseSub.getDefaultCommand());
    }

    /**
     * Checks that the drive base's default command is TankDrive OR ArcadeDrive
     */
    @Test
    public void aDefaultCommandTest() {
        try{
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        robotContainer.setDefaultCommands();
        assertEquals(simFactory.getTankDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        System.out.println("DriveBaseSub's default command is TankDrive!");
    } catch(AssertionError e){
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        robotContainer.setDefaultCommands();
        assertEquals(simFactory.getArcadeDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        System.out.println("DriveBaseSub's default command is ArcadeDrive!");
        }
    }

    /**
     * Checks that the left joystick correlates to drivetrain speed
     */
    @Test
    public void driveBaseIsControlledWithLeftJoystickTest() {
        try{
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getTankDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        when(joystick.getLeftY()).thenReturn(.75);
        tankDrive.execute();
        verify(leftFront).set(ControlMode.PercentOutput, .75);
        verify(rightFront).set(ControlMode.PercentOutput, 0.0);
            try {
            verify(leftBack, atLeast(0)).follow(leftFront);
            verify(rightBack, atLeast(0)).follow(rightFront);
            } catch (AssertionError e) {
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .75);
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, 0);
            }
        } catch(AssertionError e){
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getArcadeDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        when(joystick.getLeftY()).thenReturn(.75);
        when(joystick.getRightX()).thenReturn(0.0);
        arcadeDrive.execute();
        verify(leftFront).set(ControlMode.PercentOutput, .375);
        verify(rightFront).set(ControlMode.PercentOutput, .375);
        try {
            verify(leftBack, atLeast(0)).follow(leftFront);
            verify(rightBack, atLeast(0)).follow(rightFront);
        } catch (AssertionError f) {
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .375);
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .375);;
            }
        }
    }

    /**
     * Checks that the right joystick correlates to drivetrain speed
     */
    @Test
    public void driveBaseRightIsControlledWithRightJoystickTest(){   
        try {
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getTankDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        when(joystick.getRightY()).thenReturn(.75);
        tankDrive.execute();
        verify(rightFront).set(ControlMode.PercentOutput, .75);
        verify(leftFront).set(ControlMode.PercentOutput, 0);
            try {
            verify(rightBack, atLeast(0)).follow(rightFront);
            verify(leftBack, atLeast(0)).follow(leftFront);
            } catch (AssertionError e) {
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .75);
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, 0);;
            }
        } catch (AssertionError e){
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getArcadeDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        when(joystick.getLeftY()).thenReturn(0.0);
        when(joystick.getRightX()).thenReturn(.9);
        arcadeDrive.execute();
        verify(leftFront).set(ControlMode.PercentOutput, .27);
        verify(rightFront).set(ControlMode.PercentOutput, -.27);
            try {
            verify(leftBack, atLeast(0)).follow(leftFront);
            verify(rightBack, atLeast(0)).follow(rightFront);
            } catch (AssertionError f) {
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .27);
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, -.27);;
            }
        }
    }
    
    /**
     * Checks that moving the left and right joystick will move the drivetrain correctly
     */
    @Test
    public void theDriveWorks(){  
        try{
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getTankDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        when(joystick.getLeftY()).thenReturn(.633);
        when(joystick.getRightY()).thenReturn(.112);
        tankDrive.execute();
        verify(leftFront).set(ControlMode.PercentOutput, .633);
        verify(rightBack).set(ControlMode.PercentOutput, .112);
        try {
            verify(leftBack, atLeast(0)).follow(leftFront);
            verify(rightBack, atLeast(0)).follow(rightFront);
        } catch (AssertionError e) {
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .633);
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .112);;
            }
        } catch(AssertionError e){
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getArcadeDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        when(joystick.getLeftY()).thenReturn(.5);
        when(joystick.getRightX()).thenReturn(.5);
        arcadeDrive.execute();
        verify(leftFront).set(ControlMode.PercentOutput, .4);
        verify(rightFront).set(ControlMode.PercentOutput, .1);
        try {
            verify(leftBack, atLeast(0)).follow(leftFront);
            verify(rightBack, atLeast(0)).follow(rightFront);
        } catch (AssertionError f) {
            verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .4);
            verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .1);;
            }
        }
    }

    /**
     * Checks that when the command *ENDS* hint hint, all 4 drive motors stop.
     */
    @Test
    public void turnsOffWhenCommandEndsTest(){
        try {
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getTankDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        tankDrive.end(false);
        verify(leftFront).set(ControlMode.PercentOutput, 0);
        verify(rightFront).set(ControlMode.PercentOutput, 0);
        verify(leftBack).set(ControlMode.PercentOutput, 0);
        verify(rightBack).set(ControlMode.PercentOutput, 0);
        } catch(AssertionError e){
            when(joystick.getA()).thenReturn(mockButton);
            RobotContainer robotContainer = new RobotContainer(simFactory);
            robotContainer.setDefaultCommands();
            assertEquals(simFactory.getArcadeDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
        arcadeDrive.end(false);
        verify(leftFront).set(ControlMode.PercentOutput, 0);
        verify(rightFront).set(ControlMode.PercentOutput, 0);
        verify(leftBack).set(ControlMode.PercentOutput, 0);
        verify(rightBack).set(ControlMode.PercentOutput, 0);
        }
    }

}
