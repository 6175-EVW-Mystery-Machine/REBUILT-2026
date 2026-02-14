package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.TurretWheel;

public class TestFeederToShooter extends SequentialCommandGroup {
  public TestFeederToShooter(TurretWheel m_flywheel, Feeder m_feeder) {
    addCommands(
      new InstantCommand(() -> m_feeder.v_runWheels(0.4))
        .alongWith(new InstantCommand(() -> m_flywheel.v_runWheel(1500)))
    );
  }
}
