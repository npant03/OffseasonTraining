package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntakeWithJoystick;

public class Week5JoystickBindingTest {

    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();

    /**
     * Checks if RobotContainer's setDefaultCommands method sets a default command to IntakeSub
     */
    @Test
    public void defaultCommandTest() {
        JoystickButton mockButton = mock(JoystickButton.class);       
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        IntakeSub intake = simFactory.getIntakeSub();
        robotContainer.setDefaultCommands();
        assertNotEquals(null, intake.getDefaultCommand());
    }

    /**
     * Checks that intake's default command is RunIntake (and takes a param joystick)
     */
    @Test
    public void runIntakeWithJoystick(){
        JoystickButton mockButton = mock(JoystickButton.class);       
        when(joystick.getA()).thenReturn(mockButton);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        IntakeSub intake = simFactory.getIntakeSub();
        robotContainer.setDefaultCommands();
        assertEquals(simFactory.getRunIntakeWithJoystick(joystick).getClass(), intake.getDefaultCommand().getClass());
    }

    /**
     * checks that moving the joystick up (or down) will correlate to speed
     */
    @Test
    public void intakeIsControlledWithJoystick(){   
        IntakeSub intake = mock(IntakeSub.class);
        RunIntakeWithJoystick runIntake = new RunIntakeWithJoystick(intake, joystick);
        when(joystick.getLeftY()).thenReturn(0.75);
        runIntake.execute();
        ArgumentCaptor<Double> arguments = ArgumentCaptor.forClass(Double.class);
        verify(intake).setPower(arguments.capture());
        assertEquals(0.75, arguments.getValue(), 0);
    }

    @Test
    public void turnsOffWhenCommandEnds(){
        IntakeSub intake = simFactory.getIntakeSub();
        VictorSPX victor = intake.getVictor();
        RunIntakeWithJoystick runIntake = new RunIntakeWithJoystick(intake, joystick);
        runIntake.end(false);
        verify(victor).set(ControlMode.PercentOutput, 0);
    }

}
