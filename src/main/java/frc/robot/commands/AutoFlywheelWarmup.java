package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.TurretFlywheel;

public class AutoFlywheelWarmup extends Command {
  private final TurretFlywheel flywheel;
  public AutoFlywheelWarmup(TurretFlywheel flywheel) {
    this.flywheel = flywheel;
    addRequirements(flywheel);
  }

  @Override
  public void execute() {
    flywheel.v_trenchSetShot();
  }
}
