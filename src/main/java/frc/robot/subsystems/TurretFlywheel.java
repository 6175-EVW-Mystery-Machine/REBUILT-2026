package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import static com.ctre.phoenix6.signals.MotorAlignmentValue.Opposed;
import static edu.wpi.first.math.util.Units.inchesToMeters;
import static frc.robot.Constants.TurretConstants.FlywheelConfig;
import static frc.robot.Constants.TurretConstants.FlywheelFeedback;
import static frc.robot.Constants.TurretConstants.FlywheelMotionMagicConfig;
import static frc.robot.Constants.TurretConstants.MotionMagicVelocityRequest;
import static frc.robot.Constants.TurretConstants.FlywheelLeaderID;
import static frc.robot.Constants.TurretConstants.FlywheelFollowerID;
import static frc.robot.Constants.TurretConstants.VelocityRequest;
import static frc.robot.subsystems.TurretMeasurements.distanceToHub;
import static frc.robot.Constants.CANIVORE;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretFlywheel extends SubsystemBase {

  private final TalonFX m_flywheel = new TalonFX(FlywheelLeaderID, CANIVORE);
  private final TalonFX m_flywheelFollower = new TalonFX(FlywheelFollowerID, CANIVORE);
  private boolean shooting = false;
  public boolean motorAtSpeed = false;

  public TurretFlywheel() {
    ConfigureMotors();
  }

  private void ConfigureMotors() {
    TalonFXConfiguration m_config = new TalonFXConfiguration()
    .withSlot0(Slot0Configs.from(FlywheelConfig))
    .withMotionMagic(FlywheelMotionMagicConfig)
    .withFeedback(FlywheelFeedback);
    // m_config.TorqueCurrent.TorqueNeutralDeadband = 16;

    m_flywheel.getConfigurator().apply(m_config);
    m_flywheelFollower.getConfigurator().apply(m_config);
    m_flywheelFollower.setControl(new Follower(FlywheelLeaderID, Opposed));
  }

  public void v_runWheel() {
    if (distanceToHub > 70 && distanceToHub < 85) {
      m_flywheel.setControl(VelocityRequest.withVelocity((distanceToHub * 10.5) / 60).withAcceleration(0.2));
    } else if (distanceToHub > 85 && distanceToHub < 100) {
      m_flywheel.setControl(VelocityRequest.withVelocity((distanceToHub * 9) / 60).withAcceleration(0.2));
    } else if (distanceToHub > 100 && distanceToHub < 130) {
    m_flywheel.setControl(VelocityRequest.withVelocity((distanceToHub * 7.8) / 60).withAcceleration(0.2));
    } else if (distanceToHub > 130 && distanceToHub < 190) {
      m_flywheel.setControl(VelocityRequest.withVelocity((distanceToHub * 7) / 60).withAcceleration(0.2));
    }
    shooting = true;
  }

  public void v_stopMotors() {
    m_flywheel.stopMotor();
    shooting = false;
  }

  @Override
  public void periodic() {
      SmartDashboard.putNumber("Turret Temp", m_flywheel.getDeviceTemp().getValueAsDouble());
      SmartDashboard.putNumber("Turret Current", Math.round(m_flywheel.getStatorCurrent().getValueAsDouble() * 10) / 10);
      SmartDashboard.putNumber("Turret ID", m_flywheel.getDeviceID());
      SmartDashboard.putNumber("Turret RPM", Math.round(m_flywheel.getVelocity().getValueAsDouble() * 60));

      SmartDashboard.putBoolean("Shooting Fuel?", shooting);
  }
}
