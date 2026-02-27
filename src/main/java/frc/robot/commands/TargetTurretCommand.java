package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.TurretRing;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TargetTurretCommand extends Command {
  private final CTRE_CANdle CANdle;
  private final TurretRing turretRing;
  private final CommandXboxController driverController;

  public TargetTurretCommand(CTRE_CANdle CANdle, TurretRing turretRing, CommandXboxController driverController) {
    this.turretRing = turretRing;
    this.CANdle = CANdle;
    this.driverController = driverController;
    addRequirements(CANdle, turretRing);
  }

  @Override
  public void initialize() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
      turretRing.v_positionTurret();
      CANdle.v_turretAim();
      if (turretRing.TargetRumble = false) {
      new ControllerRumble(driverController);
      turretRing.TargetRumble = true;
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}