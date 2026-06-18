package frc.robot.subsystems;

import static frc.robot.Constants.TurretConstants.VelocityRequest;
import static frc.robot.Constants.ManipulatorCanivore;
import static frc.robot.Constants.FeederConstants.FeederConfig;
import static frc.robot.Constants.FeederConstants.FeederCurrentLimits;
import static frc.robot.Constants.FeederConstants.FeederFeedbackConfig;
import static frc.robot.Constants.FeederConstants.FeederOutputConfig;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {

  private final TalonFX m_krakenX44 = new TalonFX(15, ManipulatorCanivore);
  private boolean feeding = false;

  public Feeder() {
    ConfigureMotor();
  }

  public void v_runWheels(double RPM) {
    m_krakenX44.setControl(VelocityRequest.withVelocity(RPM / 60));
    feeding = true;
  }

  public void v_stopMotor() {
    m_krakenX44.stopMotor();
    feeding = false;
  }

  public void ConfigureMotor() {
    TalonFXConfiguration m_config = new TalonFXConfiguration()
    .withSlot0(Slot0Configs.from(FeederConfig))
    .withCurrentLimits(FeederCurrentLimits)
    .withFeedback(FeederFeedbackConfig)
    .withMotorOutput(FeederOutputConfig);

    m_krakenX44.getConfigurator().apply(m_config);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Feeder Temp", m_krakenX44.getDeviceTemp().getValueAsDouble());
    SmartDashboard.putNumber("Feeder Current", Math.round(m_krakenX44.getStatorCurrent().getValueAsDouble() * 10) / 10);
    SmartDashboard.putNumber("Feeder CAN ID", m_krakenX44.getDeviceID());
    SmartDashboard.putNumber("Feeder RPM", Math.round(m_krakenX44.getRotorVelocity().getValueAsDouble() * 10) / 10 * 60);

    SmartDashboard.putBoolean("Feeding Fuel?", feeding);
  }
}
