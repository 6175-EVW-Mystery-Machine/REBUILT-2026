package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.TurretRing;

public class TargetTurret extends ParallelCommandGroup {

  public TargetTurret(TurretRing m_turretGear, CTRE_CANdle m_CANdle) {
    addCommands(
      new InstantCommand(() -> m_turretGear.v_runTurret(LimelightHelpers.getTX(""))),
      new InstantCommand(() -> m_CANdle.v_turretLock())
    );
  }
}
