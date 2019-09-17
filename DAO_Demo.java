//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO_Demo {
	public static void main(String[] args) {
		try{
			DAO_Factory daoFactory = new DAO_Factory();

			daoFactory.activateConnection();

			Scanner sc = new Scanner(System.in);
			PatientDAO pdao = daoFactory.getPatientDAO();

			while(true){
				System.out.println("Press 1 to View available Specializations in the hospital");
				System.out.println("Press 2 to Display the Total cost of visit");
				System.out.println("Press 3 to Pay the bill i.e to insert bill into BillPayments");
				System.out.println("Press 4 to exit");

				int choice = sc.nextInt();
				if(choice == 1){
					ArrayList<Doctor> docs = pdao.viewSpecialization();
					for(int i=0;i<docs.size();i++){
						docs.get(i).print();
					}
				}
				else if(choice == 2){
					int  cost = pdao.showCost();
					System.out.println("Total cost of the visit = "+cost);
				}
				else if(choice == 3){
					pdao.insertBillIntoBillpayments();
				}
				else if(choice == 4){
					break;
				}
				else{
					System.out.println("Please enter a valid option.");
				}
			}
			// System.out.println("Press 4 to insert into Past Medical Records");
			// pdao.insertIntoMedicalRecords();
			daoFactory.deactivateConnection();
		}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}//end main
}//end FirstExample
