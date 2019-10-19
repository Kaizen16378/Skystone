/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot", group="Pushbot")
// @Disabled
public class PushbotTeleopPOV_Linear extends OpMode {
    private DcMotor leftMotor = null;
    private DcMotor linearSlideMotor = null;
    private DcMotor rightMotor = null;
    private ElapsedTime runtime = new ElapsedTime();
    private Servo pitch_clamp = null;
    private Servo yaw_clamp = null;
    private Servo roll_clamp = null;

    public PushbotTeleopPOV_Linear() {
    }

    public void init() {
        telemetry.addData("Status", "Initialized");


        pitch_clamp = hardwareMap.get(Servo.class, "pitch_clamp");
        yaw_clamp = hardwareMap.get(Servo.class, "yaw_clamp");
        roll_clamp = hardwareMap.get(Servo.class, "roll_clamp");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        linearSlideMotor = hardwareMap.dcMotor.get("slide_motor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Status", "Initialized");
    }

    public void loop() {
        double throttle = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double backslidespeed = gamepad1.left_trigger;
        double frontslidespeed = gamepad1.right_trigger;
        double leftspeed = throttle - turn;
        double rightspeed = throttle + turn;
        leftMotor.setPower(leftspeed);
        linearSlideMotor.setPower(backslidespeed/2.5);
        rightMotor.setPower(rightspeed);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        if (gamepad1.a) {
            pitch_clamp.setPosition(0);
        } else if (gamepad1.b) {
            pitch_clamp.setPosition(0.5);


        }
        if (gamepad1.y) {
            yaw_clamp.setPosition(0.5);
        } else if (gamepad1.x) {
            yaw_clamp.setPosition(-.1);
        }


        if (gamepad1.right_bumper) {
            roll_clamp.setPosition(.98);
        } else if (gamepad1.left_bumper) {
            roll_clamp.setPosition(.5);
            //added 0.5

        }
    }
}