import java.lang.*;
import java.util.Date;

public class BillPayments{
  int bill_id;
  int visit_id;
  String transaction_mode;
  float amount;
  Date bill_date;
  public BillPayments(){}
  public BillPayments(int id, int vid, String tm, float a,Date d){ bill_id = id; visit_id=vid; transaction_mode=tm; amount=a; bill_date=d;}
  public int getid(){ return bill_id;}
  public void setid(int id){ bill_id=id;}
  public int get_visitid(){ return visit_id;}
  public void set_visitid(int id){ visit_id=id;}
  public String get_trasMode(){ return transaction_mode;}
  public void set_trasMode(String tm){ transaction_mode=tm;}
  public float getamt(){ return amount;}
  public void setamt(float a){ amount=a; }
  public Date getdate(){ return bill_date;}
  public void setdate(Date bd){ bill_date=bd;}
  public void print(){ System.out.println("Bill_id = "+bill_id); System.out.println("visit_id = "+visit_id); System.out.println("transaction_mode = "+transaction_mode); System.out.println("amount = "+amount); System.out.println("bill_date = "+bill_date);}
};
