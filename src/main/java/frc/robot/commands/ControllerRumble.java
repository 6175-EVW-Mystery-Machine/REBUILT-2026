package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class ControllerRumble extends SequentialCommandGroup {

  public ControllerRumble(CommandXboxController driverController) {
    addCommands(
      new InstantCommand(() -> driverController.setRumble(RumbleType.kBothRumble, 1)),
        new WaitCommand(0.1),
      new InstantCommand(() -> driverController.setRumble(RumbleType.kBothRumble, 0)),
          new WaitCommand(0.1),
        new InstantCommand(() -> driverController.setRumble(RumbleType.kBothRumble, 1)),
          new WaitCommand(0.1),
        new InstantCommand(() -> driverController.setRumble(RumbleType.kBothRumble, 0))
    );
  }
}
