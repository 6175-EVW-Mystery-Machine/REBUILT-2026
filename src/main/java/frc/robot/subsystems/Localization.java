package frc.robot.subsystems;

import java.util.function.Consumer;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.util.Units;

import static edu.wpi.first.math.util.Units.degreesToRadians;
import static edu.wpi.first.math.util.Units.inchesToMeters;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;
import frc.robot.LimelightHelpers.PoseEstimate;

public class Localization extends SubsystemBase {

    private RobotContainer m_robotContainer;

  public Localization() {
    LimelightHelpers.setCameraPose_RobotSpace("",
    inchesToMeters(0),
    0,
    inchesToMeters(0),
    0,
    degreesToRadians(0),
    0);
  }


  @Override
  public void periodic() {
    var driveState = m_robotContainer.drivetrain.getState();
    double headingDeg = driveState.Pose.getRotation().getDegrees();
    double omegaRps = Units.radiansToRotations(driveState.Speeds.omegaRadiansPerSecond);

    LimelightHelpers.SetRobotOrientation("", headingDeg, 0, 0, 0, 0, 0);
      var limelightMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("");

      if(limelightMeasurement != null && limelightMeasurement.tagCount > 0 && Math.abs(omegaRps) < 2.0) {
        m_robotContainer.drivetrain.addVisionMeasurement(limelightMeasurement.pose, limelightMeasurement.timestampSeconds);
    }
    }
  }