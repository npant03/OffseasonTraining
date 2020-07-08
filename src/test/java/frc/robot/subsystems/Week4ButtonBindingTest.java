package frc.robot.subsystems;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.team7419.PaddedXbox;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.RunIntake;

public class Week4ButtonBindingTest {

    @Test
    public void runXbox(){
        System.out.println("test runs");
        SimFactory simFactory = new SimFactory();
        PaddedXbox xbox = simFactory.getPaddedXbox();

        // Test setup 
        JoystickButton mockButton = mock(JoystickButton.class);       
        when(xbox.getA()).thenReturn(mockButton);
 
        // Create RobotContainer 
        RobotContainer robotContainer = new RobotContainer(simFactory);
 
        // Check that xbox.getA() is called exactly once in RobotContainer constructor
        verify(xbox, times(1)).getA(); 
        // Capture params for 
        ArgumentCaptor<Command> argument = ArgumentCaptor.forClass(Command.class);
        // Check that whenPressed() is called on xbox.getA() JoystickButton
        verify(mockButton).whenPressed(argument.capture());
        // Check that RunIntake was created in whenPressed()
        assertEquals(RunIntake.class, argument.getValue().getClass());
        // Check that power for RunIntake == .5
        assertEquals(true, ((RunIntake)argument.getValue()).getPower() == .5);
        
    }

    @Test
    public void traceExample(){

        //when(mockButton.whenPressed(any())).thenReturn(mockButton);
        // when(mockButton.whenPressed(any())).thenAnswer(new Answer(){
        //     public JoystickButton answer(InvocationOnMock invocation) throws Throwable {
        //         System.out.println("when pressed");
        //         return (JoystickButton) mockButton;       
        //     }
        // });
    }
}
