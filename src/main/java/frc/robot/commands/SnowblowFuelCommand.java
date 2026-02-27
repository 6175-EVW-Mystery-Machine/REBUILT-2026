package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TurretFlywheel;

public class SnowblowFuelCommand extends Command {
  private final TurretFlywheel turretFlywheel;
  private final Indexer indexer;
  private final Feeder feeder;
  private final Intake intake;
  private final CTRE_CANdle CANdle;
  private final CommandXboxController controller;

  public SnowblowFuelCommand(TurretFlywheel turretFlywheel, Indexer indexer, Feeder feeder, Intake intake, CTRE_CANdle CANdle, CommandXboxController controller) {
    this.turretFlywheel = turretFlywheel;
    this.indexer = indexer;
    this.feeder = feeder;
    this.intake = intake;
    this.CANdle = CANdle;
    this.controller = controller;
    addRequirements(turretFlywheel, indexer, feeder, CANdle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    controller.setRumble(RumbleType.kBothRumble, 1);
    CANdle.v_snowblowTurret();
    turretFlywheel.v_runWheel(1000);
    new WaitCommand(0.1);
    feeder.v_runWheels(2200);
      new WaitCommand(0.1);
      indexer.v_runWheels(1500);
          new WaitCommand(0.1);
          intake.v_runWheels(0.35);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    controller.setRumble(RumbleType.kBothRumble, 0);
    CANdle.v_stopAll();
      turretFlywheel.v_stopMotors();
        feeder.v_stopMotor();
          indexer.v_stopMotor();
            intake.v_stopMotor();
  }
}
