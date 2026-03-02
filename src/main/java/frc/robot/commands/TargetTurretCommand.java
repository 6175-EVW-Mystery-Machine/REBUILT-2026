package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CTRE_CANdle;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.TurretFlywheel;
import frc.robot.subsystems.TurretRing;

public class TargetTurretCommand extends Command {
  private final CTRE_CANdle CANdle;
  private final TurretRing turretRing;
  private final TurretFlywheel turretFlywheel;
  private final Indexer indexer;
  private final Feeder feeder;
  private final CommandXboxController driverController;


  public TargetTurretCommand(CTRE_CANdle CANdle,
    TurretRing turretRing,
    TurretFlywheel turretFlywheel,
    Indexer indexer,
    Feeder feeder,
    CommandXboxController driverController) {
      this.turretRing = turretRing;
      this.CANdle = CANdle;
      this.feeder = feeder;
      this.indexer = indexer;
      this.turretFlywheel = turretFlywheel;
      this.driverController = driverController;
    addRequirements(turretRing, CANdle, feeder, indexer, turretFlywheel);
  }

  @Override
  public void initialize() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    
      CANdle.v_turretShoot();
      driverController.setRumble(RumbleType.kRightRumble, 1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}