package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.TurretWheel;

public class ShootFuel extends SequentialCommandGroup {

  public ShootFuel(Indexer m_indexer, Feeder m_feeder, CTRE_CANdle m_CANdle, TurretWheel m_turretWheel) {
    addCommands(
      // new InstantCommand(() -> m_CANdle.v_turretShoot())
        new InstantCommand(() -> m_turretWheel.v_runWheel(1500)),
          new InstantCommand(() -> m_feeder.v_runWheels(0.4)),
            new InstantCommand(() -> m_indexer.v_runWheels(700))
    );
  }
}
