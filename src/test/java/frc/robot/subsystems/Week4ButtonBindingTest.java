package frc.robot.subsystems;

import static org.mockito.Mockito.mock;

import com.team7419.PaddedXbox;

import org.junit.Test;

import frc.robot.RobotContainer;

public class Week4ButtonBindingTest {


    @Test
    public void runXbox(){
        System.out.println("test runnigns");
        
        SimFactory factory = new SimFactory();
        RobotContainer robotContainer = new RobotContainer(factory); 

        // robotContainer.configureButtonBindings();
        /**
         * 
         * call a method w return type JoystickButton on the PaddedXbox object
         * it'll probably look like 
         * public JoystickButton getX(){
         *      return new JoystickButton(this, PaddedXbox.F310Map.kGamepadButtonX.value)
         * }
         * so this test can just do this
         * verify(xbox).getX().whenPressed(new RunIntake(intake, .5)) or whatever
         */

    }
    
}