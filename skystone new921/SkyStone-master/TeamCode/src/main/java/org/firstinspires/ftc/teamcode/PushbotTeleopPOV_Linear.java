package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;


@TeleOp(name="Teleop", group="Teleop")
public class PushbotTeleopPOV_Linear extends OpMode
{
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor tiltMotor = null;
    private DcMotor extendMotor = null;
    private Servo grabber = null;
    private Servo tilt = null;
    private ElapsedTime runtime = new ElapsedTime();


//***********************************************************************
    public void init()
    {
        tilt = hardwareMap.get(Servo.class, "tilt");
        grabber = hardwareMap.get(Servo.class, "grabber");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        tiltMotor = hardwareMap.dcMotor.get("tilt_motor");
        extendMotor = hardwareMap.dcMotor.get("extend_motor");
        telemetry.addData("Status", "Initialized");
    }
//************************************************************************


    public void loop()
    {
        double throttle = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double leftspeed = throttle - turn;
        double rightspeed = throttle + turn;
        boolean tiltMotor_DirectionUp = gamepad2.dpad_up;
        boolean tiltMotor_DirectionDown = gamepad2.dpad_down;
        boolean tiltMotor_DirectionRight = gamepad2.dpad_right;
        boolean tiltMotor_DirectionLeft = gamepad2.dpad_left;


//********************DRIVE
        leftMotor.setPower(leftspeed * 0.5);
        rightMotor.setPower(rightspeed * 0.5);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
//*********************TILTING LINEAR SLIDE
        if(tiltMotor_DirectionDown == true)
        {
            tiltMotor.setPower(0.5);
        }
        else
            tiltMotor.setPower(0);

        if(tiltMotor_DirectionUp == true)
        {
            tiltMotor.setPower(-0.5);
        }
        else
            tiltMotor.setPower(0);


//********************EXTENDING LINEAR SLIDE
        if(tiltMotor_DirectionRight == true)
        {
            extendMotor.setPower(0.5);
        }
        if(tiltMotor_DirectionLeft == true)
        {
            extendMotor.setPower(-0.5);
        }
//********************GRABBING BLOCK
        if (gamepad2.a) {
            grabber.setPosition(0);
        } else if (gamepad2.b) {
            grabber.setPosition(0.5);
        }
//********************TILTING GRABBER-need to find right value

          double pos = tilt.getPosition();
            while (gamepad2.right_trigger > 0.3)
            {
                    pos = (pos + 0.001);
                    tilt.setPosition(pos);
                telemetry.addData("Status", "servo pos " + tilt.getPosition());
            }
            while (gamepad2.left_trigger > 0.3)
            {
                   pos = (pos - 0.001);
                   tilt.setPosition(pos);
                telemetry.addData("Status", "servo pos " + tilt.getPosition());
            }
    }
}