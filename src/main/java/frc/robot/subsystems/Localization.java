package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.math.util.Units.degreesToRadians;
import static edu.wpi.first.math.util.Units.inchesToMeters;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;

public class Localization extends SubsystemBase {

    private RobotContainer m_robotContainer;
    public static Pose2d robotPose;

  public Localization() {
    LimelightHelpers.setCameraPose_RobotSpace("",
    inchesToMeters(7),
    inchesToMeters(9),
    inchesToMeters(13.5),
    0,
    0,
    90);
  }


  @Override
  public void periodic() {
    var driveState = m_robotContainer.drivetrain.getState();
    double headingDeg = driveState.Pose.getRotation().getDegrees();
    double angVelocity = Units.radiansToRotations(driveState.Speeds.omegaRadiansPerSecond);

    robotPose = m_robotContainer.drivetrain.getState().Pose;

    LimelightHelpers.SetRobotOrientation("", headingDeg, 0, 0, 0, 0, 0);
      var limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("");

      if(limelightMeasurement != null && limelightMeasurement.tagCount > 0 && Math.abs(angVelocity) < 2.0) {
        m_robotContainer.drivetrain.addVisionMeasurement(limelightMeasurement.pose, limelightMeasurement.timestampSeconds);
      }
    }
  }