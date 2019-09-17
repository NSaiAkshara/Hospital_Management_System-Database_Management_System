import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class PatientDAO_JDBC implements PatientDAO{
  Connection dbConnection;
  public PatientDAO_JDBC(Connection dbconn){
    dbConnection = dbconn;
  }

  @Override
  public ArrayList<Doctor> viewSpecialization(){
      ArrayList<Doctor> doctors = new ArrayList<Doctor>();
      String sql,sql1,input=null;
      Statement stmt1 =null,stmt=null;
      Scanner sc = new Scanner(System.in);
      try{
          stmt1 = dbConnection.createStatement();
          sql1 = "select distinct specialization from doctor;";
          ResultSet rs1 = stmt1.executeQuery(sql1);
          ArrayList<String> skills = new ArrayList<String>();
          int i=0;
          String sp=null;
          System.out.println("Select a VALID Specialization Number from below.");
          while(rs1.next()){
            sp = rs1.getString("specialization");
            System.out.println("Press "+i+" for "+sp);
            i++;
            skills.add(sp);
          }

          int choice = sc.nextInt();
          for (int j = 0; j<skills.size(); j++) {
            if(choice == j){
              input = skills.get(j);
            }
          }
          stmt = dbConnection.createStatement();
          sql = "select name, consultation_fee, experience from doctor where specialization = \""+input+"\"";
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
            Doctor d = new Doctor();
            String name = rs.getString("name");
            int fee = rs.getInt("consultation_fee");
            int exp = rs.getInt("experience");
            d.setname(name);
            d.setdocfee(fee);
            d.setexp(exp);
            doctors.add(d);
          }
      }catch (SQLException ex){
        System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
      }
      return doctors;
  }

  @Override
  public int showCost(){
      String sql;
      int cost=0;
      Statement stmt =null;
      try{
        stmt = dbConnection.createStatement();
        sql = "select cost as Totalcost from visit where visit_id in (select max(visit_id) from visit);";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
          cost = rs.getInt("Totalcost");
          break;
        }
      }catch (SQLException ex){
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
      }
      return cost;
  }

  @Override
  public void insertBillIntoBillpayments(){
    PreparedStatement preparedStatement = null;
    String sql,sql1;
    String input;
    sql = "insert into billpayments values(0,(select visit_id from visit where visit_id in (select max(visit_id) from visit)),?, (select cost from visit where visit_id = (select visit_id from visit where visit_id in (select max(visit_id) from visit))), (select timeslot from visit where visit_id in (select max(visit_id) from visit)));";
    System.out.println("Select the Transaction Mode of Bill Payment:");
    System.out.println("Press 1 to pay through CASH else ");
    System.out.println("Press ANYKEY expect 1 to pay through CARD");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
      if(choice == 1){
        input = "cash";
      }
      else{
        input = "card";
      }
    try{
        preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1,input);
        preparedStatement.executeUpdate();
        System.out.println("Payment successfully");
    }catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    sql1 = "insert into medicalrecords values(0,(select visit.patient_id from visit inner join patient on visit.patient_id = patient.patient_id where visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),(select doctorvisit.doctor_id from doctorvisit INNER JOIN (visit INNER JOIN patient on visit.patient_id = patient.patient_id) on doctorvisit.visit_id = visit.visit_id where visit.visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),(select doctorvisit.reason from doctorvisit INNER JOIN (visit INNER JOIN patient on visit.patient_id = patient.patient_id) on doctorvisit.visit_id = visit.visit_id where visit.visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),(select billpayments.bill_date from billpayments INNER JOIN (visit INNER JOIN patient on visit.patient_id = patient.patient_id) on billpayments.visit_id = visit.visit_id where visit.visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),?);";
    try{
        preparedStatement = dbConnection.prepareStatement(sql1);
        preparedStatement.setString(1,null);
        preparedStatement.executeUpdate();
        System.out.println("Inserted into medicalrecords successfully");
    }catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
  }

  // @Override
  // public void insertIntoMedicalRecords(){
  //   PreparedStatement preparedStatement = null;
  //   String sql;
  //   String input;
  //   sql = "insert into medicalrecords values(0,(select visit.patient_id from visit inner join patient on visit.patient_id = patient.patient_id where visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),(select doctorvisit.doctor_id from doctorvisit INNER JOIN (visit INNER JOIN patient on visit.patient_id = patient.patient_id) on doctorvisit.visit_id = visit.visit_id where visit.visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),(select doctorvisit.reason from doctorvisit INNER JOIN (visit INNER JOIN patient on visit.patient_id = patient.patient_id) on doctorvisit.visit_id = visit.visit_id where visit.visit_id in (select visit_id from visit where visit_id in (select max(visit_id) from visit))),(select billpayments.bill_date from billpayments INNER JOIN (visit INNER JOIN patient on visit.patient_id = patient.patient_id) on billpayments.visit_id = visit.visit_id where visit.visit_id in (select visit_id f
  //rom visit where visit_id in (select max(visit_id) from visit))),null);";
  //   // System.out.println("Press 1 to change the Transaction Mode of bill payment to cash or else press anykey:");
  //   // Scanner sc = new Scanner(System.in);
  //   // int choice = sc.nextInt();
  //   //   if(choice == 1){
  //   //     input = "cash";
  //   //   }
  //   //   else{
  //   //     input = "card";
  //   //   }
  //   try{
  //       preparedStatement = dbConnection.prepareStatement(sql);
  //       //preparedStatement.setString(1,input);
  //       preparedStatement.executeUpdate();
  //       System.out.println("Inserted successfully");
  //   }catch (SQLException e) {
 	// 		System.out.println(e.getMessage());
 	// 	}
  //   try{
	// 		if (preparedStatement != null) {
	// 			preparedStatement.close();
	// 		}
	// 	} catch (SQLException e) {
 	// 		System.out.println(e.getMessage());
  //   }
  // }

}
