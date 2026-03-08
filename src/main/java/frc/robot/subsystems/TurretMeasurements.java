package frc.robot.subsystems;

import static edu.wpi.first.math.util.Units.metersToInches;
import static edu.wpi.first.units.Units.Inches;
import static frc.robot.Constants.blueHubLocation;
import static frc.robot.Constants.hubLocation;
import static frc.robot.Constants.redHubLocation;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretMeasurements extends SubsystemBase {

  public static double turretAngle;
  public static double m_fieldRelativeAngle;
  public static double distanceToTarget;

  private Translation2d robotToTurret = new Translation2d(Inches.of(3.125), Inches.of(-8.375));

  public TurretMeasurements() {
  }

  public void getGearPosition(Pose2d robotPose, ChassisSpeeds robotSpeedSupplier) {
    
    //HUB LOCATION CHOOSING
    if (DriverStation.getAlliance().isEmpty()) {
      return;
    }
      hubLocation = DriverStation.getAlliance().get() == Alliance.Blue ? blueHubLocation : redHubLocation;


    //ROBOT SPEED READINGS
    var robotSpeeds = robotSpeedSupplier;
      var vRobot = new Translation2d(robotSpeeds.vxMetersPerSecond, 0).times(5);

    //SHOOTER TRANSLATION SETUP
    Translation2d turretPose = robotPose.getTranslation().plus(robotToTurret.rotateBy(robotPose.getRotation()));

      Translation2d predictedTargetTranslation = hubLocation.getTranslation().minus(vRobot);

    double tY = hubLocation.getY() - robotPose.getY();
    double tX = hubLocation.getX() - robotPose.getX();


    //SETTING SHOOTER TARGET
    Rotation2d fieldRelativeAngle = Rotation2d.fromRadians(Math.atan2(tY, tX));
    Rotation2d robotRelativeAngle = fieldRelativeAngle.minus(robotPose.getRotation());

    turretAngle = (robotRelativeAngle.getDegrees() - vRobot.getX()) / 360;
    m_fieldRelativeAngle = fieldRelativeAngle.getDegrees() / 360;

    SmartDashboard.putNumber("Shooting Offset X", vRobot.getX());
    SmartDashboard.putNumber("Shooting Offset Y", vRobot.getY());

    distanceToTarget = metersToInches(turretPose.getDistance(hubLocation.getTranslation()));
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distance to Target", distanceToTarget);
    SmartDashboard.putNumber("Turret Angle", turretAngle * 360);
  }
}
