package frc.robot.subsystems;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.junit.Test;
import org.mockito.InjectMocks;

import frc.robot.subsystems.intake.IntakeSub;

public class Week2SubsystemTest {

    VictorSPX victor = mock(VictorSPX.class);

    @InjectMocks
    IntakeSub intakeMock = new IntakeSub(victor);
   
   @Test
   public void testConstructor() {
       assertFalse(intakeMock.getInverted());
   }

   @Test
   public void isInitialized(){
    verify(victor).neutralOutput();
    verify(victor).setSensorPhase(false);
    verify(victor).configNominalOutputForward(0,0);
    verify(victor).configNominalOutputReverse(0, 0);
    verify(victor).configClosedloopRamp(.2,0);
   }
}