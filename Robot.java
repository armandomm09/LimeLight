// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Robot extends TimedRobot {
 

private static int motorDerAdelante = 1;
private static int motorDerAtras = 2;
private static int motorIzqAdelante = 3;
private static int motorIzqAtras = 4;

  CANSparkMax m_derAdelante = new CANSparkMax(motorDerAdelante, MotorType.kBrushless);
  CANSparkMax m_derAtras = new CANSparkMax(motorDerAtras, MotorType.kBrushless);
  CANSparkMax m_izqAdelante = new CANSparkMax(motorIzqAdelante, MotorType.kBrushless);
  CANSparkMax m_izqAtras = new CANSparkMax(motorIzqAtras, MotorType.kBrushless);

  MotorControllerGroup chasisIzq = new MotorControllerGroup(m_izqAdelante, m_izqAtras);
  MotorControllerGroup chasisDer = new MotorControllerGroup(m_derAdelante, m_derAtras);

  DifferentialDrive chasis = new DifferentialDrive(chasisIzq, chasisDer);

  Joystick control = new Joystick(0);

  private boolean objetivoVisto = false;
  private double comandoAvance = 0.0;
  private double comandoGiro = 0.0;


  @Override
  public void robotInit() {
    chasisDer.setInverted(false);
    chasisIzq.setInverted(!chasisDer.getInverted());
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = table.getEntry("tx");

    NetworkTableEntry ty = table.getEntry("ty");

    NetworkTableEntry ta = table.getEntry("ta");

    NetworkTableEntry tv = table.getEntry("tv");

    double x = tx.getDouble(0.0);

    double y = ty.getDouble(0.0);

    double a = ta.getDouble(0.0);

    double v = tv.getDouble(0.0);

    if(v > 0){
      objetivoVisto = true;
    } else{
      objetivoVisto = false;
    } 
     
    if( a > 5 && v > 0 && a < 10){
      comandoAvance = -0.3;
    } else if( a < 2.5 && v > 0 ){
      comandoAvance = 0.3;
    } else if(x > 5){
      comandoGiro = 0.37;
    } else if( x < -5) {
      comandoGiro = -0.37;
    } else {
     comandoAvance = 0.0;
     comandoGiro = 0.0;
    }
    
    chasis.arcadeDrive(comandoAvance, comandoGiro);
   /*  if( a > 5 && v > 0 && a < 10){
      chasis.arcadeDrive(-0.3, 0.0);
    } else if( a < 2.5 && v > 0 ){
      chasis.arcadeDrive(0.3, 0.0);
    } else if(x > 5){
      chasis.arcadeDrive(0.0, 0.37);
    } else if( x < -5) {
      chasis.arcadeDrive(0.0, -0.37);
    } else {
      chasis.arcadeDrive(0.0, 0.0);
    }*/
    
    /*else if(x < 3){
      chasis.arcadeDrive(-0.3, 0.0);
    }*/
    
    SmartDashboard.putNumber("X", x);
    SmartDashboard.putNumber("Y", y);
    SmartDashboard.putNumber("A", a);
    SmartDashboard.putBoolean("objetivo?", objetivoVisto);



  
    
   /*  actualizarLimelight();

    if(objetivoVisto = true){
      chasis.arcadeDrive(comandoAvance, comandoGiro);
    } else {
      chasis.arcadeDrive(0.0, 0.0);
    }
    
  }

  public void actualizarLimelight(){
      final double constanteAvance = 0.25;

      final double constanteGiro = 0.3;

      final double areaAprilTagDeseada = 13;

      final double velocidadMax = 0.5;

      // definir que pipeline se va a usar.
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
      
      // hay objetivo a la vista?
      double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

      // offset horizontal 
      double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

      // offset vertical
      double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);

      // area de 0% a 100% del objetivo
      double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);

      if(tv < 1.0){

        objetivoVisto = false;
        comandoAvance = 0.0;
        comandoGiro = 0.0;
        return;
      } else {

        objetivoVisto = true;

        comandoGiro = tx * constanteGiro;
        comandoAvance = (areaAprilTagDeseada - ta) * constanteAvance;

        if( comandoAvance>= velocidadMax){
          comandoAvance = velocidadMax;
        }
      }
*/
  }
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {


   /* NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
   
     // hay objetivo a la vista?
     double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

     // offset horizontal 
     double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

     // offset vertical
     double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);

     // area de 0% a 100% del objetivo
     double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);

     
    //NetworkTable tabla = new NetworkTable.get

     if(tv > 0){
       objetivoVisto = true;
     } else {
      objetivoVisto = false;
     }


     System.out.println(objetivoVisto);
     System.out.println("tx" + tx);
     System.out.println("ty" + ty);
     System.out.println("ta" + ta);
     
     

     SmartDashboard.putBoolean("objetivo?", objetivoVisto);

     SmartDashboard.putNumber("tx", tx);

     SmartDashboard.putNumber("ty", ty);

     SmartDashboard.putNumber("ta", ta);
 */
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
