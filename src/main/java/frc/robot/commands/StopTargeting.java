package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.TurretRing;

public class StopTargeting extends InstantCommand {
  private final TurretRing turretRing;
  private final CTRE_CANdle CANdle;

  public StopTargeting(TurretRing turretRing, CTRE_CANdle CANdle) {
    this.turretRing = turretRing;
    this.CANdle = CANdle;
    addRequirements(turretRing, CANdle);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public void execute() {
    turretRing.v_stopMotor();
    turretRing.TargetRumble = false;
    CANdle.v_clearTurretRails();
  }
}
