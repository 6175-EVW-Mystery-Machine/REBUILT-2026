package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.Intake;

public class IntakeFuel extends SequentialCommandGroup {

  public IntakeFuel(Intake m_intake, CTRE_CANdle m_CANdle) {
    addCommands(
      new InstantCommand(() -> m_CANdle.v_intakeLights())
        .alongWith(new InstantCommand(() -> m_intake.v_runWheels(0.5)))
    );
  }
}
