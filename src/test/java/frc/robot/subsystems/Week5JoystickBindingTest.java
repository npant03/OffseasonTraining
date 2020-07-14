package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import com.team7419.PaddedXbox;

import org.junit.Test;
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
        robotContainer.setDefaultCommands();
        assertNotEquals(null, intake.getDefaultCommand());
    }

    @Test
    public void runIntakeWithJoystick(){
        SimFactory simFactory = new SimFactory();
        RobotContainer robotContainer = new RobotContainer(simFactory);
        IntakeSub intake = simFactory.getIntakeSub();
        PaddedXbox joystick = simFactory.getPaddedXbox();
        robotContainer.setDefaultCommands();
        assertEquals(simFactory.getRunIntakeWithJoystick(joystick).getClass(), intake.getDefaultCommand().getClass());
    }

    @Test
    public void intakeIsControlledWithJoystick(){
        SimFactory simFactory = new SimFactory();
        IntakeSub intake = simFactory.getIntakeSub();
        PaddedXbox joystick = simFactory.getPaddedXbox();
        RunIntake runIntake = new RunIntake(intake, joystick);
        when(joystick.getLeftY()).thenReturn(0.75);
        runIntake.execute();
        assertEquals(0.75, joystick.getLeftY(), 0);
    }
}
