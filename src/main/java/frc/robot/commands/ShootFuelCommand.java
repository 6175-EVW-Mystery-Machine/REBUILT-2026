package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.TurretFlywheel;

public class ShootFuelCommand extends Command {
  private final TurretFlywheel turretFlywheel;
  private final Indexer indexer;
  private final Feeder feeder;
  private final CTRE_CANdle CANdle;
  private final CommandXboxController controller;

  public ShootFuelCommand(TurretFlywheel turretFlywheel, Indexer indexer, Feeder feeder, CTRE_CANdle CANdle, CommandXboxController controller) {
    this.turretFlywheel = turretFlywheel;
    this.indexer = indexer;
    this.feeder = feeder;
    this.CANdle = CANdle;
    this.controller = controller;
    addRequirements(turretFlywheel, indexer, feeder, CANdle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    CANdle.v_turretShoot();
    controller.setRumble(RumbleType.kRightRumble, 1);
    turretFlywheel.v_runWheel(1000);
    new WaitCommand(0.25);
      feeder.v_runWheels(4400);
      new WaitCommand(0.1);
        indexer.v_runWheels(1500);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controller.setRumble(RumbleType.kRightRumble, 0);
    CANdle.v_clearIndexer();
    CANdle.v_clearTurretRails();
      turretFlywheel.v_stopMotors();
        indexer.v_stopMotor();
          feeder.v_stopMotor();
  }
}
