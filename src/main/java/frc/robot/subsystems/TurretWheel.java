package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretWheel extends SubsystemBase {

  private final TalonFX m_flywheel = new TalonFX(16);
  private final TalonFX m_flywheelFollower = new TalonFX(17);
  private final TalonFXConfiguration m_config = new TalonFXConfiguration();
  private final VelocityVoltage m_flywheelRequest = new VelocityVoltage(0);

  public TurretWheel() {
    
    //PID SETUP
      m_config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
      m_config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      m_config.Slot0.kP = 1;
      m_config.Slot0.kA = 0;
      m_config.Slot0.kS = 0;
      m_config.Slot0.kV = 0;
      m_config.Slot0.kG = 0;

      m_config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;
      m_config.Feedback.SensorToMechanismRatio = 2;
      m_config.Feedback.RotorToSensorRatio = 1;

      m_flywheel.getConfigurator().apply(m_config);
      m_flywheelFollower.getConfigurator().apply(m_config);

      m_flywheelFollower.setControl(new Follower(16, MotorAlignmentValue.Opposed));
  }

  public void v_runWheel(double RPM) {
    m_flywheel.setControl(m_flywheelRequest.withVelocity(RPM/60).withAcceleration(0.1));
  }

  public void v_stopMotors() {
    m_flywheel.stopMotor();
  }

  @Override
  public void periodic() {
      SmartDashboard.putNumber("Turret Temp", m_flywheel.getDeviceTemp().getValueAsDouble());
      SmartDashboard.putNumber("Turret Current", Math.round(m_flywheel.getRotorVelocity().getValueAsDouble() * 10) / 10);
      SmartDashboard.putNumber("Turret ID", m_flywheel.getDeviceID());
      SmartDashboard.putNumber("Turret RPM", Math.round(m_flywheel.getVelocity().getValueAsDouble() * 10) / 10);
  }
}
