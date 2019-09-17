import java.lang.*;
import java.util.ArrayList;

public interface PatientDAO{
  public ArrayList<Doctor> viewSpecialization();
  public int showCost();
  public void insertBillIntoBillpayments();
  //public void insertIntoMedicalRecords();
}
