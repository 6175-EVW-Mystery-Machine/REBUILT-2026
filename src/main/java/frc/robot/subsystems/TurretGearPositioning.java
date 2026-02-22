// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.math.util.Units.inchesToMeters;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretGearPositioning extends SubsystemBase {

  public static double m_robotRelativeAngle;
  public static double m_fieldRelativeAngle;

  public TurretGearPositioning() {
  }

  public void getGearPosition(Pose2d target, Pose2d robotPose) {
      Pose2d turretPose = robotPose.plus(new Transform2d(
      new Translation2d(inchesToMeters(2.75), inchesToMeters(-9)),
      new Rotation2d()));

    double tY = target.getY() - turretPose.getY();
    double tX = target.getX() - turretPose.getX();

    Rotation2d fieldRelativeAngle = Rotation2d.fromRadians(Math.atan2(tY, tX));
    Rotation2d robotRelativeAngle = fieldRelativeAngle.minus(robotPose.getRotation());

    m_robotRelativeAngle = robotRelativeAngle.getDegrees() / 360;
    m_fieldRelativeAngle = fieldRelativeAngle.getDegrees() / 360;
    
    SmartDashboard.putNumber("RobotPose", m_robotRelativeAngle);
  }

  @Override
  public void periodic() {

  }
}
