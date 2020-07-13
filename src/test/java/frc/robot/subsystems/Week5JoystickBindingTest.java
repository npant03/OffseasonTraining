package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.team7419.PaddedXbox;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Factory;
import frc.robot.RealFactory;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;

public class Week5JoystickBindingTest {

    /**
     * Checks if RobotContainer's setDefaultCommands method sets a default command to IntakeSub
     */
    @Test
    public void defaultCommandTest() {
        RealFactory realFactory = new RealFactory();
        RobotContainer robotContainer = new RobotContainer(realFactory);
        IntakeSub intake = realFactory.getIntakeSub();
        // RobotContainer robotContainer = new RobotContainer(simFactory);
        // RobotContainer robotContainer = mock(RobotContainer.class, CALLS_REAL_METHODS);
        // RobotContainer robotContainer = spy(RobotContainer.class);
        //basically setDefaultCommands from robotcontainer never gets called (bc this is a test)
        //but when you run it for real it will get called.
        //so we need to check that it is called (because if kids are stupid and mess w robot.java), and that it does is what we want.
        // when(robotContainer.setDefaultCommands()).thenCallRealMethod(); cant bc void method
        // doCallRealMethod().when(robotContainer).setDefaultCommands(); null pointer bc instance in rC
        // doCallRealMethod().when(robotContainer).setDefaultCommands();
        // intake.setDefaultCommand(realFactory.getRunIntakeWithJoystick(joystick));
        // assertEquals(intake.getDefaultCommand(), realFactory.getRunIntakeWithJoystick(joystick));
        robotContainer.setDefaultCommands();
        assertNotEquals(null, intake.getDefaultCommand());
    }

    @Test
    public void runIntakeWithJoystick(){
        Factory factory = mock(Factory.class);
        RealFactory realFactory = new RealFactory();
        SimFactory simFactory = new SimFactory();
        // RobotContainer robotContainer = new RobotContainer(realFactory);
        // RobotContainer robotContainer = mock(RobotContainer.class);
        RobotContainer robotContainer = new RobotContainer(simFactory);
        // IntakeSub intake = mock(IntakeSub.class);
        IntakeSub intake = simFactory.getIntakeSub();
        PaddedXbox joystick = simFactory.getPaddedXbox();
        // IntakeSub intakeSpy = spy(simFactory.getIntakeSub());
        // IntakeSub intakeSpy = spy(IntakeSub.class);
        // doCallRealMethod().when(robotContainer).setDefaultCommands();
        // doCallRealMethod().when(intake).setDefaultCommand(factory.getRunIntakeWithJoystick(joystick));
        ArgumentCaptor<RunIntake> arguments = ArgumentCaptor.forClass(RunIntake.class);
        // Command defaultCommand = intake.getDefaultCommand();
        robotContainer.setDefaultCommands();
        // factory.getRunIntakeWithJoystick(joystick);
        // assertEquals(intake.getDefaultCommand(), realFactory.getRunIntakeWithJoystick(joystick));
        // verify(robotContainer).setDefaultCommands().getRunIntakeWithJoystick(joystick);
        // verify(simFactory).getRunIntakeWithJoystick(joystick);
        //verify that whateverFactory.getRunIntakeWithJoystick() is called, then argument captor the (joystick) part
        // verify(robotContainer).setDefaultCommands();
        // verify(factory).getRunIntakeWithJoystick(joystick);
        // verify(intakeSpy).setDefaultCommand(arguments.capture());
        // assertEquals(RunIntake.class, arguments.getValue().getClass());

        assertEquals(simFactory.getRunIntakeWithJoystick(joystick).getClass(), intake.getDefaultCommand().getClass());
    }

}
