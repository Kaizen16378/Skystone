package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Pushbot: Auto Drive By Encoder", group="Pushbot")
//@Disabled


public class AutonomousOfficial extends OpMode {
    ColorSensor colorSensor;
    private DcMotor leftMotor = null;
    private DcMotor linearSlideMotor = null;
    private DcMotor rightMotor = null;
    private ElapsedTime runtime = new ElapsedTime();


    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        colorSensor = hardwareMap.colorSensor.get("color");
    }

    public void loop() {
        double throttle = 5;
        double turn = 5;
        double leftspeed = throttle + turn;
        while (runtime.seconds() < 1.9) {
            leftMotor.setPower(leftspeed);
rightMotor.setPower(-10);
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);



        telemetry.addData("Status"," Red Color Value  "+ colorSensor.red());
        telemetry.addData("Status"," Blue Color Value  "+ colorSensor.blue());
        telemetry.addData("Status"," Green Color Value  "+ colorSensor.green());
        telemetry.addData("Status"," Red Color Value  "+ colorSensor.argb());
        telemetry.addData("Status", "Run Time: " + runtime.toString());


            }
        }
