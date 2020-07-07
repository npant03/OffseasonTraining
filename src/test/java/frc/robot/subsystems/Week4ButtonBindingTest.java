package frc.robot.subsystems;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.team7419.PaddedXbox;

import org.junit.Test;

import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.IntakeSub;
import frc.robot.subsystems.intake.RunIntake;

public class Week4ButtonBindingTest {

    @Test
    public void runXbox(){
        System.out.println("test runs");
        SimFactory simFactory = new SimFactory();
        RobotContainer robotContainer = new RobotContainer(simFactory);
        PaddedXbox xbox = simFactory.getPaddedXbox();
        IntakeSub intake = simFactory.getIntakeSub();
        robotContainer.configureButtonBindings();
        verify(xbox, times(2)).getA().whenPressed(any()); //new RunIntake(intake, .5)
        // verify(xbox, times(2)).getA();
    }
    
}
