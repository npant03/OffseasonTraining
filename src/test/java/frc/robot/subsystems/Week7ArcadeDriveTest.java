// package frc.robot.subsystems;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;
// import static org.mockito.Mockito.atLeast;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.team7419.PaddedXbox;

// import org.junit.Test;

// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.RobotContainer;
// import frc.robot.subsystems.drivebase.ArcadeDrive;
// import frc.robot.subsystems.drivebase.DriveBaseSub;

// public class Week7ArcadeDriveTest {

//     SimFactory simFactory = new SimFactory();
//     PaddedXbox joystick = simFactory.getPaddedXbox();

//     /**
//      * Checks if RobotContainer's setDefaultCommands method sets a default command
//      * to DriveBaseSub
//      */
//     @Test
//     public void driveBaseSubHasDefaultCommandTest() {
//         JoystickButton mockButton = mock(JoystickButton.class);
//         when(joystick.getA()).thenReturn(mockButton);
//         RobotContainer robotContainer = new RobotContainer(simFactory);
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         robotContainer.setDefaultCommands();
//         assertNotEquals(null, driveBaseSub.getDefaultCommand());
//     }

//     /**
//      * Checks that the drive base's default command is ArcadeDrive
//      */
//     @Test
//     public void arcadeDriveIsDefaultCommandTest() {
//         JoystickButton mockButton = mock(JoystickButton.class);
//         when(joystick.getA()).thenReturn(mockButton);
//         RobotContainer robotContainer = new RobotContainer(simFactory);
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         robotContainer.setDefaultCommands();
//         assertEquals(simFactory.getArcadeDrive(joystick).getClass(), driveBaseSub.getDefaultCommand().getClass());
//     }
    
//     /**
//      * Checks that drive base moves with left joystick (but algorithm COULD BE wrong)
//      */
//     @Test
//     public void driveBaseMovesWithLeftJoystickY() {
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);
//         TalonFX leftFront = driveBaseSub.getLeftMast();
//         TalonFX leftBack = driveBaseSub.getLeftFollow();
//         TalonFX rightFront = driveBaseSub.getRightMast();
//         TalonFX rightBack = driveBaseSub.getRightFollow();
//         when(joystick.getLeftY()).thenReturn(.75);
//         when(joystick.getRightX()).thenReturn(0.0);
//         arcadeDrive.execute();
//         assertNotEquals(null, leftFront);
//         assertNotEquals(null, leftBack);
//         assertNotEquals(null, rightFront);
//         assertNotEquals(null, rightBack);
//     }

//     /**
//      * Checks that moving the left joystick up will correlate to both sides (but algorithm COULD BE wrong)
//      */
//     @Test
//     public void driveBaseIsControlledWithLeftJoystickYTest() {
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);
//         TalonFX leftFront = driveBaseSub.getLeftMast();
//         TalonFX leftBack = driveBaseSub.getLeftFollow();
//         TalonFX rightFront = driveBaseSub.getRightMast();
//         TalonFX rightBack = driveBaseSub.getRightFollow();
//         when(joystick.getLeftY()).thenReturn(.75);
//         when(joystick.getRightX()).thenReturn(0.0);
//         arcadeDrive.execute();
//         verify(leftFront).set(ControlMode.PercentOutput, .375);
//         verify(rightFront).set(ControlMode.PercentOutput, .375);
//         try {
//             verify(leftBack, atLeast(0)).follow(leftFront);
//             verify(rightBack, atLeast(0)).follow(rightFront);
//         } catch (AssertionError e) {
//             verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .375);
//             verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .375);;
//         }
//     }

//     /**
//      * Checks that moving the right joystick X will correlate to both sides
//      */
//     @Test
//     public void driveBaseRightIsControlledWithRightJoystickTest(){   
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);
//         TalonFX leftFront = driveBaseSub.getLeftMast();
//         TalonFX leftBack = driveBaseSub.getLeftFollow();
//         TalonFX rightFront = driveBaseSub.getRightMast();
//         TalonFX rightBack = driveBaseSub.getRightFollow();
//         when(joystick.getLeftY()).thenReturn(0.0);
//         when(joystick.getRightX()).thenReturn(.9);
//         arcadeDrive.execute();
//         verify(leftFront).set(ControlMode.PercentOutput, .27);
//         verify(rightFront).set(ControlMode.PercentOutput, -.27);
//         try {
//             verify(leftBack, atLeast(0)).follow(leftFront);
//             verify(rightBack, atLeast(0)).follow(rightFront);
//         } catch (AssertionError e) {
//             verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .27);
//             verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, -.27);;
//         }
//     }

//     /**
//      * Checks that moving the left joystick's Y and the right joystick's X will move the drivetrain correctly.
//      */
//     @Test
//     public void arcadeDriveWorks(){   
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);
//         TalonFX leftFront = driveBaseSub.getLeftMast();
//         TalonFX leftBack = driveBaseSub.getLeftFollow();
//         TalonFX rightFront = driveBaseSub.getRightMast();
//         TalonFX rightBack = driveBaseSub.getRightFollow();
//         when(joystick.getLeftY()).thenReturn(.5);
//         when(joystick.getRightX()).thenReturn(.5);
//         arcadeDrive.execute();
//         verify(leftFront).set(ControlMode.PercentOutput, .4);
//         verify(rightFront).set(ControlMode.PercentOutput, .1);
//         try {
//             verify(leftBack, atLeast(0)).follow(leftFront);
//             verify(rightBack, atLeast(0)).follow(rightFront);
//         } catch (AssertionError e) {
//             verify(leftBack, atLeast(0)).set(ControlMode.PercentOutput, .4);
//             verify(rightBack, atLeast(0)).set(ControlMode.PercentOutput, .1);;
//         }
//     }

//     /**
//      * Checks that when the command *ENDS* hint hint, all 4 drive motors stop.
//      */
//     @Test
//     public void turnsOffWhenCommandEndsTest(){
//         DriveBaseSub driveBaseSub = simFactory.getDriveBaseSub();
//         TalonFX leftFront = driveBaseSub.getLeftMast();
//         TalonFX rightFront = driveBaseSub.getRightMast();
//         TalonFX leftBack = driveBaseSub.getLeftFollow();
//         TalonFX rightBack = driveBaseSub.getRightFollow();
//         ArcadeDrive arcadeDrive = simFactory.getArcadeDrive(joystick);
//         arcadeDrive.end(false);
//         verify(leftFront).set(ControlMode.PercentOutput, 0);
//         verify(rightFront).set(ControlMode.PercentOutput, 0);
//         verify(leftBack).set(ControlMode.PercentOutput, 0);
//         verify(rightBack).set(ControlMode.PercentOutput, 0);
//     }

// }
