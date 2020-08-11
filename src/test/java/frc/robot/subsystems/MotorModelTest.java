package frc.robot.subsystems;

import com.team7419.motors.DcMotor;
import com.team7419.motors.MotorType;
import org.knowm.xchart.demo.*;
import org.junit.Test;
// import ptolemy.plot.*;

public class MotorModelTest {

    DcMotor motor = new DcMotor(MotorType.Cim);

    @Test
    public void runningMotor(){
        System.out.println(motor.getPosition());
        for(int i = 0; i <200; i++){
            motor.step(Math.pow(-1, i) * 12, 1, 10, .01);
            // System.out.println(motor.getPosition());    
        }    

        double[] xData = new double[] { 0.0, 1.0, 2.0 };
        double[] yData = new double[] { 2.0, 1.0, 0.0 };
        
        // Create Chart

        // XChartDemo x
    }
    
}