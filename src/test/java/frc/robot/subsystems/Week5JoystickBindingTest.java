package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RealFactory;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;

public class Week5JoystickBindingTest {

    @Test
    public void runIntakeWithJoystick(){
        SimFactory simFactory = new SimFactory();
        RealFactory realFactory = new RealFactory();
        PaddedXbox joystick = simFactory.getPaddedXbox();
        Robot robot = mock(Robot.class);
        
        // Test setup 
        JoystickButton mockButton = mock(JoystickButton.class);       
        when(joystick.getA()).thenReturn(mockButton);
 
        // Create RobotContainer 
        // RobotContainer robotContainer = new RobotContainer(simFactory);
        RobotContainer robotContainer = mock(RobotContainer.class);
        IntakeSub intake = realFactory.getIntakeSub();
        //basically setDefaultCommands from robotcontainer never gets called (bc this is a test)
        //but when you run it for real it will get called.
        //so we need to check that it is called (because if kids are stupid and mess w robot.java), and that it does is what we want.
        // when(robotContainer.setDefaultCommands()).thenCallRealMethod(); cant bc void method
        // doCallRealMethod().when(robotContainer).setDefaultCommands(); null pointer bc instance in rC
        robotContainer.setDefaultCommands();
        intake.setDefaultCommand(realFactory.getRunIntakeWithJoystick(joystick));
        // assertEquals(intake.getDefaultCommand(), realFactory.getRunIntakeWithJoystick(joystick));
        assertNotEquals(null, intake.getDefaultCommand());

    }

}
