package org.firstinspires.ftc.teamcode.Amin;

import static org.firstinspires.ftc.teamcode.Amin.NU_MAI_POT.power_coborare;
import static org.firstinspires.ftc.teamcode.Amin.NU_MAI_POT.power_thing_slow;
import static org.firstinspires.ftc.teamcode.Amin.incercareDetectie3Patrate.NuSeMaiUmbla.LOW_POWER;
import static org.firstinspires.ftc.teamcode.Amin.incercareDetectie3Patrate.NuSeMaiUmbla.MEDIUM_POWER;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class Telemeu extends LinearOpMode {
    SampleMecanumDrive robot;
    double mana;
    double brat;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

//          MISCARE JOYSTICK
            robot.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );
            // miscari din dpad uri
            if (gamepad1.dpad_down) {
                robot.bagaViteza(-LOW_POWER, -LOW_POWER, -LOW_POWER, -LOW_POWER);
            }
            if (gamepad1.dpad_right) {
                robot.bagaViteza(MEDIUM_POWER, -MEDIUM_POWER, -MEDIUM_POWER, MEDIUM_POWER);
            }
            if (gamepad1.dpad_up) {
                robot.bagaViteza(LOW_POWER, LOW_POWER, LOW_POWER, LOW_POWER);
            }
            if (gamepad1.dpad_left) {
                robot.bagaViteza(-MEDIUM_POWER, MEDIUM_POWER, MEDIUM_POWER, -MEDIUM_POWER);
            }

            // rotiri fine din triggere
            if (gamepad1.right_trigger != 0) {
                robot.bagaViteza(LOW_POWER, -LOW_POWER, LOW_POWER, -LOW_POWER);
            }
            if (gamepad1.left_trigger != 0) {
                robot.bagaViteza(-LOW_POWER, LOW_POWER, -LOW_POWER, LOW_POWER);
            }

            if (gamepad2.dpad_up || gamepad2.dpad_down) {
                if (gamepad2.dpad_up)
                    brat = -1;
                else
                    brat = 1;
                robot.se_ridica_brat(brat);
            } else {
                robot.se_ridica_brat(0);

            }

//            ASTA NOUA
            if (gamepad2.dpad_right || gamepad2.dpad_left) {
                if (gamepad2.dpad_left)
                    mana = power_thing_slow;
                else if (gamepad2.dpad_right)
                    mana = -power_thing_slow;
                else
                    mana = 0;
                robot.rotesteThing(mana);
            } else
                robot.rotesteThing(0);

            if (gamepad2.left_bumper || gamepad2.right_bumper) {
                if (gamepad2.left_bumper)
                    brat = power_coborare;
                else
                    brat = 1;
                robot.se_ridica_brat(brat);

                if (gamepad2.left_trigger != 0 || gamepad2.right_trigger != 0) {
                    if (gamepad2.left_trigger != 0)
                        mana = gamepad2.left_trigger;
                    else if (gamepad2.right_trigger != 0)
                        mana = -gamepad2.right_trigger;
                    else
                        mana = 0;

                    robot.rotesteThing(mana);
                } else
                    robot.rotesteThing(0);

                if (gamepad2.a)
                    robot.apuca(NU_MAI_POT.poz_deschis_st, NU_MAI_POT.poz_deschis_dr);
                else if (gamepad2.b)
                    robot.apuca(NU_MAI_POT.poz_inchis_st, NU_MAI_POT.poz_inchis_dr);

            } else {
                robot.se_ridica_brat(0);

                if (gamepad2.left_trigger != 0 || gamepad2.right_trigger != 0) {
                    if (gamepad2.left_trigger != 0)
                        mana = gamepad2.left_trigger;
                    else if (gamepad2.right_trigger != 0)
                        mana = -gamepad2.right_trigger;
                    else
                        mana = 0;

                    robot.rotesteThing(mana);
                } else
                    robot.rotesteThing(0);

                if (gamepad2.a)
                    robot.apuca(NU_MAI_POT.poz_deschis_st, NU_MAI_POT.poz_deschis_dr);
                if (gamepad2.b)
                    robot.apuca(NU_MAI_POT.poz_inchis_st, NU_MAI_POT.poz_inchis_dr);
            }
        }
    }
}
