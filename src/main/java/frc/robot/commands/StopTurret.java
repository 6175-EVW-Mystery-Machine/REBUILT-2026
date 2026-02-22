package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.TurretRing;

public class StopTurret extends InstantCommand {
  private final TurretRing m_turret;

  public StopTurret(TurretRing m_turret) {
    this.m_turret = m_turret;
    addRequirements(m_turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public void execute() {
    m_turret.v_stopMotor();
  }
}
