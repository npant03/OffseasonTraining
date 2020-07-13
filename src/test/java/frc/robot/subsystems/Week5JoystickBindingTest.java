package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.team7419.PaddedXbox;

import org.junit.Test;
import frc.robot.RealFactory;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.IntakeSub;

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

}
