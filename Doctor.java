import java.lang.*;

public class Doctor{
  int doctor_id;
  String user_id;
  String name;
  int consultation_fee;
  String specialization;
  int experience;
  public Doctor(){}
  public Doctor(int id,String user,String n,int fee,String skill,int ex){doctor_id=id; user_id=user; name=n; consultation_fee=fee; specialization=skill; experience=ex;}
  public int getdocid(){return doctor_id;}
  public void setdocid(int docid){doctor_id=docid;}
  public String getusrid(){return user_id;}
  public void setusrid(String usrid){user_id=usrid;}
  public String getname(){return name;}
  public void setname(String n){name=n;}
  public int getdocfee(){return consultation_fee;}
  public void setdocfee(int confee){consultation_fee=confee;}
  public String getskill(){return specialization;}
  public void setskill(String skill){specialization=skill;}
  public int getexp(){return experience;}
  public void setexp(int exp){experience=exp;}
  public void print(){ System.out.println("Doctor_name = "+name); System.out.println("Consultation_fee = "+consultation_fee); System.out.println("Experience = "+experience);}
};
