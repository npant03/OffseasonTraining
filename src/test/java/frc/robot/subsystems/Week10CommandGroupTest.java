package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.PowerConstants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.snippits.StraightPowerTime;
import frc.robot.snippits.StraightThenTurn;
import frc.robot.snippits.TurnPowerTime;
import frc.robot.subsystems.drivebase.DriveBaseSub;

public class Week10CommandGroupTest{

    // DO NOT PUT ROBOTCONTAINER UP HERE.
    SimFactory simFactory = new SimFactory();
    PaddedXbox joystick = simFactory.getPaddedXbox();
    DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
    TalonFX leftFront = driveBaseSub.getLeftMast();
    TalonFX leftBack = driveBaseSub.getLeftFollow();
    TalonFX rightFront = driveBaseSub.getRightMast();
    TalonFX rightBack = driveBaseSub.getRightFollow();
    JoystickButton mockButton = mock(JoystickButton.class);
    StraightPowerTime straightPowerTime = simFactory.getStraightPowerTime(PowerConstants.AutoStraightPower.val, 
    PowerConstants.AutoStraightTime.val);
    TurnPowerTime turnPowerTime = simFactory.getTurnPowerTime("LEFT", .2, 300);
    // StraightThenTurn straightThenTurn = simFactory.getStraightThenTurn(.2, 500, "LEFT");
    Robot robot = new Robot();
    private CommandScheduler scheduler = null;
    // CommandScheduler.getInstance().run();

    @Before
    public void setup(){
        scheduler = CommandScheduler.getInstance();
        // scheduler.run();
    }
    
    /**
     * @throws InterruptedException
     * 
     */
    @Test
    public void testSequentialCommandGroup() throws InterruptedException {
        StraightThenTurn straightThenTurn = simFactory.getStraightThenTurn(.2, 500, "LEFT");
        robot.robotPeriodic();
        CommandScheduler.getInstance().run();
        // straightThenTurn.schedule();
        // straightPowerTime.execute();
        // scheduler.run();
        // straightPowerTime.schedule();
        scheduler.schedule(straightPowerTime);
        scheduler.run();
        // CommandScheduler.getInstance().schedule(straightPowerTime);
        // scheduler.schedule(straightThenTurn);
        for(int i = 0; i < 2; i++){
        // scheduler.run();
        // Thread.sleep(20);
        }
        Thread.sleep(6000);
        System.out.println(straightPowerTime.isScheduled());
        // verify(rightFront).set(ControlMode.PercentOutput, .2);
        // verify(leftFront).set(ControlMode.PercentOutput, .2);

    }

}
