package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.PoseEstimate;
import frc.robot.RobotContainer;

public class Localization extends SubsystemBase {
  public Localization() {

    //LEFT-SIDE LIMELIGHT
    LimelightHelpers.setCameraPose_RobotSpace("limelight-left",
    -0.22225,
    -0.288925,
    0.38735,
    0,
    20,
    90);

    //FRONT-SIDE LIMELIGHT
    LimelightHelpers.setCameraPose_RobotSpace("limelight-front",
    0.32385,
    0.2667,
    0.3429,
    0,
    0,
    0);

    LimelightHelpers.SetIMUMode("limelight-left", 0); 
    LimelightHelpers.SetIMUMode("limelight-front", 0); 
  }

  @Override
  public void periodic() {
  }

  public void v_setLimelightPipeline(int pipeline) {
    LimelightHelpers.setPipelineIndex("limelight-left", pipeline);
    LimelightHelpers.setPipelineIndex("limelight-front", pipeline);
  }
}
