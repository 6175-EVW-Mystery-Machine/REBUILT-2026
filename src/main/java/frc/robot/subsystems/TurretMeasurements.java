package frc.robot.subsystems;

import static edu.wpi.first.math.util.Units.inchesToMeters;
import static edu.wpi.first.math.util.Units.metersToInches;
import static edu.wpi.first.units.Units.Inches;
import static frc.robot.Constants.hubLocation;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretMeasurements extends SubsystemBase {

  public static double m_robotRelativeAngle;
  public static double m_fieldRelativeAngle;
  public static double distanceToHub;

  private Translation2d robotToTurret = new Translation2d(Inches.of(3.125), Inches.of(-8.375));

  public TurretMeasurements() {
  }

  public void getGearPosition(Pose2d target, Pose2d robotPose) {
      Translation2d turretPose = robotPose.getTranslation().plus(robotToTurret.rotateBy(robotPose.getRotation()));

    double tY = target.getY() - robotPose.getY();
    double tX = target.getX() - robotPose.getX();

    Rotation2d fieldRelativeAngle = Rotation2d.fromRadians(Math.atan2(tY, tX));
    Rotation2d robotRelativeAngle = fieldRelativeAngle.minus(robotPose.getRotation());

    m_robotRelativeAngle = robotRelativeAngle.getDegrees() / 360;
    m_fieldRelativeAngle = fieldRelativeAngle.getDegrees() / 360;
    
    SmartDashboard.putNumber("RobotPose", m_robotRelativeAngle);

    distanceToHub = metersToInches(turretPose.getDistance(hubLocation.getTranslation()));
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distance to Hub", distanceToHub);
  }
}
