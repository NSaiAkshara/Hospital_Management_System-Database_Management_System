import java.lang.*;

public class Patient{
  int patient_id;
  String name;
  int age;
  char sex;
  public Patient(){}
  public Patient(int id, String n, int a,char s){ patient_id = id; name=n; age=a; sex=s;}
  public int getid(){ return patient_id;}
  public void setid(int id){ patient_id=id; }
  public String getname(){ return name;}
  public void setname(String n){ name=n;}
  public int getage(){ return age;}
  public void setage(int a){ age=a; }
  public char getsex(){ return sex;}
  public void setname(char s){ sex=s;}
  public void print(){ System.out.println("Patient_ID = "+patient_id); System.out.println("Patient_name = "+name); System.out.println("Patient_age = "+age); System.out.println("Patient_sex = "+sex);}
};
