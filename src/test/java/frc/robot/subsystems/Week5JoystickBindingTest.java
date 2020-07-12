package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.RunIntake;

public class Week5JoystickBindingTest {

    @Test
    public void runIntakeWithJoystick(){
        SimFactory simFactory = new SimFactory();
        PaddedXbox joystick = simFactory.getPaddedXbox();

        // Test setup 
        JoystickButton mockButton = mock(JoystickButton.class);       
        when(joystick.getA()).thenReturn(mockButton);
 
        // Create RobotContainer 
        RobotContainer robotContainer = new RobotContainer(simFactory);
 
        // Check that xbox.getA() is called exactly once in RobotContainer constructor
        verify(joystick).getA(); 
        // Capture params for RunIntake instance
        ArgumentCaptor<RunIntake> argument = ArgumentCaptor.forClass(RunIntake.class);
        // Check that whenPressed() is called on xbox.getA() JoystickButton
        verify(mockButton).whenPressed(argument.capture());
        // Check that RunIntake was created in whenPressed()
        assertEquals(RunIntake.class, argument.getValue().getClass());

    }

}
