package frc.robot.subsystems;

import com.ctre.phoenix6.configs.AudioConfigs;
import com.ctre.phoenix6.configs.ClosedLoopGeneralConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TorqueCurrentConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.TorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.ClosedLoopOutputType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretRing extends SubsystemBase {

  private final TalonFX m_krakenX44 = new TalonFX(13);
  private final TalonFXConfiguration m_config = new TalonFXConfiguration();
  private final MotionMagicVoltage m_gearPositionRequest = new MotionMagicVoltage(0).withEnableFOC(true);

  public TurretRing() {
      m_config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
      m_config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      m_config.Slot0.kP = 20;
      m_config.Slot0.kD = 0;
      m_config.Slot0.kA = 0.3;
      m_config.Slot0.kS = 0.1;
      m_config.Slot0.kV = 0.12;
      m_config.Slot0.kG = 0;
      m_config.TorqueCurrent.TorqueNeutralDeadband = 16;


      // m_config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
      // m_config.Feedback.FeedbackRemoteSensorID = 0;
      m_config.Feedback.SensorToMechanismRatio = 4;
      m_config.Feedback.RotorToSensorRatio = 4;

      m_krakenX44.getConfigurator().apply(m_config);
  }

  public void v_runTurret(double position) {
    m_krakenX44.setControl(m_gearPositionRequest.withPosition(position));
  }

  @Override
  public void periodic() {
      SmartDashboard.putNumber("Ring Temp", m_krakenX44.getDeviceTemp().getValueAsDouble());
      SmartDashboard.putNumber("Ring Current", Math.round(m_krakenX44.getRotorVelocity().getValueAsDouble() * 10) / 10);
      SmartDashboard.putNumber("Ring ID", m_krakenX44.getDeviceID());
      SmartDashboard.putNumber("Ring RPM", Math.round(m_krakenX44.getVelocity().getValueAsDouble() * 10) / 10);
  }

}
