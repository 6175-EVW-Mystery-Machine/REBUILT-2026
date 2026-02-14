package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TurretWheel;

public class SnowblowFuel extends SequentialCommandGroup {

  public SnowblowFuel(Intake m_intake, CTRE_CANdle m_CANdle, Feeder m_feeder, Indexer m_indexer, TurretWheel m_turret) {
    addCommands(
      new InstantCommand(() -> m_CANdle.v_snowblowTurret())
        .alongWith(new InstantCommand(() -> m_intake.v_runWheels(0.5))),
        new WaitCommand(0.5),
          new InstantCommand(() -> m_feeder.v_runWheels(0.3)),
          new WaitCommand(0.25),
            new InstantCommand(() -> m_turret.v_runWheel(2500)),
            new WaitCommand(0.25),
              new InstantCommand(() -> m_indexer.v_runWheels(0.4))
    );
  }
}
