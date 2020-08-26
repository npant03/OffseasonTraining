package frc.robot.subsystems;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team7419.PaddedXbox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.GetPowerConstants;
import frc.robot.PowerConstants;
import frc.robot.RobotContainer;
import frc.robot.snippits.StraightPowerTime;
import frc.robot.subsystems.drivebase.DriveBaseSub;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StraightPowerTime.class)
public class Week9TurnPowerTimeTest /*extends PowerMockTestCase*/ {

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
    GetPowerConstants getPowerConstants = simFactory.getPowerConstants();

    @Test
    public void enumMockTest() throws Exception {
      // when(getPowerConstants.getAutoStraightPower()).thenReturn(5.13);
      when(joystick.getA()).thenReturn(mockButton);
      RobotContainer robotContainer = new RobotContainer(simFactory);
      Command autoCommand = robotContainer.getAutoCommand();
      robotContainer.getAutoCommand();
      PowerMockito.whenNew(StraightPowerTime.class).withArguments(driveBaseSub, getPowerConstants.getAutoStraightPower(),
      getPowerConstants.getAutoStraightTime()).thenAnswer(new Answer(){
        public StraightPowerTime answer(InvocationOnMock invocation){
          System.out.println("Hello world");
          return straightPowerTime;
        }
      });
      //I need a verifyNew with a whenNew
      
      // PowerMockito.verifyNew(StraightPowerTime.class).withArguments(any(), any(), any());
      // eq(getPowerConstants.getAutoStraightTime()));  
      System.out.println(autoCommand);
      System.out.println(straightPowerTime);
      // ArgumentCaptor<Double> arguments = ArgumentCaptor.forClass(Double.class);
      // ArgumentCaptor<Double> arguments2 = ArgumentCaptor.forClass(Double.class);
      // verify(simFactoryMock).getStraightPowerTime(arguments.capture(), arguments2.capture());
      // PowerConstants powerConstants = PowerMockito.mock(PowerConstants.class);
      // System.out.println(powerConstants.AutoStraightPower.val);
      // Class<SimFactory> clz = SimFactory.class;
      // for (Method m : clz.getDeclaredMethods()) {
      // System.err.println(m.getName());
      // for (Parameter p : m.getParameters()) {
      // System.err.println("  " + p.getName());
      //    }
      // }
    }
    
    // @Test
    // public void enumMethodsTest() throws Exception {
    //     SimFactory simFactoryMock = mock(SimFactory.class);
    //     doAnswer(new Answer<Void>() {
    //         public Void answer(InvocationOnMock invocation) {
    //           return null;
    //         } 
    //     }).when(leftBack).follow(leftFront);

    //     doAnswer(new Answer<Void>() {
    //         public Void answer(InvocationOnMock invocation) {
    //           return null;
    //         }
    //     }).when(rightBack).follow(rightFront); 

    //     // PowerConstants powerConstants = mock(PowerConstants.class);
    //     // when(getPowerConstants.getAutoStraightPower()).thenReturn(.82);
    //     GetPowerConstants getPowerConstantsReal = new GetPowerConstants();
    //     when(joystick.getA()).thenReturn(mockButton);
    //     RobotContainer robotContainer = new RobotContainer(simFactory);
    //     Command autoCommand = robotContainer.getAutoCommand();
    //     robotContainer.getAutoCommand();
    //     assertEquals(StraightPowerTime.class, autoCommand.getClass());
    //     // System.out.println(autoCommand.getName());
    //     // verify(getPowerConstants).getAutoStraightPower();
    //     autoCommand.execute();
    //     ArgumentCaptor<Double> arguments = ArgumentCaptor.forClass(Double.class);
    //     // ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
    //     // PowerMockito.verifyNew(StraightPowerTime.class, atLeastOnce()).withArguments(arguments.capture(), eq(anyDouble()));
    //     verify(leftFront).set(eq(ControlMode.PercentOutput), arguments.capture());
    //     // verify(simFactoryMock).getStraightPowerTime(arguments.capture(), anyDouble());
    //     System.out.println(arguments.getValue());
    //     System.out.println(getPowerConstantsReal.getAutoStraightPower());
    //     // verify(leftFront).set(ControlMode.PercentOutput, .82);
    //     // verify(leftBack).set(ControlMode.PercentOutput, .82);
    //     // verify(rightFront).set(ControlMode.PercentOutput, .82);
    //     // verify(rightBack).set(ControlMode.PercentOutput, .82);

    // }
}
