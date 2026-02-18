package frc.robot;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.CANBus.CANBusStatus;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.signals.RGBWColor;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;

import static com.ctre.phoenix6.signals.FeedbackSensorSourceValue.FusedCANcoder;

public final class Constants{

  private Constants() {
  }

  public static final CANBus CANIVORE = new CANBus("canivore");
  public static final CANBusStatus CANStatus = new CANBusStatus();


  public static class OdometryConstants {
    public static final Matrix<N3, N1> StateSTDDevs = VecBuilder.fill(
      0.1, //X: 10cm error per meter (Trust Wheels)
      0.1, //Y: 10cm error per meter
      0.05); //Theta: 0.05 radians (Trust Pigeon)
  }

  public static class TurretConstants {
    //TURRET FLYWHEEL
    public static int FlywheelLeaderID = 18;
    public static int FlywheelFollowerID = 19; 
    public static MotionMagicVelocityVoltage VelocityRequest = new MotionMagicVelocityVoltage(0);
      public static final SlotConfigs FlywheelConfig = new SlotConfigs()
      .withKP(10)
      .withKD(0)
      .withKS(0.05)
      .withKV(0.12)
      .withKA(0.1);
      public static final MotionMagicConfigs FlywheelMotionMagicConfig = new MotionMagicConfigs()
      .withMotionMagicAcceleration(1000)
      .withMotionMagicCruiseVelocity(1500)
      .withMotionMagicJerk(0);

    //TURRET GEAR
    public static int RingGearID = 16;
    public static MotionMagicVoltage PositionRequest = new MotionMagicVoltage(0);
      public static final SlotConfigs RingGearConfig = new SlotConfigs()
      .withKP(20)
      .withKD(0)
      .withKS(0.3)
      .withKV(0.12)
      .withKA(0.1);
      public static FeedbackConfigs RingGearFeedbackConfig = new FeedbackConfigs()
      .withFeedbackRemoteSensorID(17)
      .withFeedbackSensorSource(FusedCANcoder)
      .withRotorToSensorRatio(4)
      .withSensorToMechanismRatio(4);
      public static MotionMagicConfigs RingGearMotionMagicConfig = new MotionMagicConfigs()
      .withMotionMagicAcceleration(500)
      .withMotionMagicCruiseVelocity(1000)
      .withMotionMagicJerk(500);
      public static SoftwareLimitSwitchConfigs RingGearLimits = new SoftwareLimitSwitchConfigs()
      .withForwardSoftLimitEnable(true)
      .withReverseSoftLimitEnable(true)
      .withForwardSoftLimitThreshold(270/360) //270 degrees forwards
      .withReverseSoftLimitThreshold(5/360); //5 degrees backwards
  }

  public static class CANdle {
      //SLOTS
      public static int kRightIntake = 0;
      public static int kLeftIntake = 1;
      public static int kIndexer = 2;
      public static int kLeftTurretSupport = 3;
      public static int kRightTurretSupport = 4;

      //COLORS
      public static RGBWColor kYellow = new RGBWColor(255, 125, 0);
      public static RGBWColor kOrange = new RGBWColor(255, 50, 0);
      public static RGBWColor kRed = new RGBWColor(225, 0, 0);
      public static RGBWColor kBlue = new RGBWColor(0, 100, 255);
      public static RGBWColor kGreen = new RGBWColor(0, 200, 0);
      public static RGBWColor kPurple = new RGBWColor(150, 0, 200);
      public static RGBWColor kLime = new RGBWColor(125, 255, 0);
      public static RGBWColor kPink = new RGBWColor(255, 0, 150);
      public static RGBWColor kCyan = new RGBWColor(0, 255, 200);
  }

  // public static class VisionConstants() {

  // }

}