package Bank_Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;
public class Bank//Creating the Class
{
	Scanner sc=new Scanner(System.in);//scanner class by Dynamic Inputs
	String driver="oracle.jdbc.OracleDriver";//driver name
	String urlname="jdbc:oracle:thin:@localhost:1521:xe";//jdbc urlname
	String uname="system";//sql user name
	String upwd="hr";//sql password
	static int amount;
	public void meth1()//method
	{
		System.out.println("Enter the Amount more than 500 Rupees");
		int iiamount=sc.nextInt();//more than 500
		if(iiamount>500)//if the amount more than 500 goto the if statement block
		{
		Random a=new Random();//Generating random numbers
		int b=a.nextInt(1000000000);
		try
		{
			Class.forName(driver);//loading the driver
			Connection con=DriverManager.getConnection(urlname, uname, upwd);//connecting the database
			PreparedStatement pstm1=con.prepareStatement("insert into bank (anumber,aname,iamount)values(?,?,?)");//prepare statement by inserting the data
			PreparedStatement pstm2=con.prepareStatement("update bank set deposit=? where anumber=?");//prepare statement by updating the deposit row in the particular table
			PreparedStatement pstm3=con.prepareStatement("update bank set withdraw=? where anumber=?");//prepare statement by updating the withdraw row in the particular table
			PreparedStatement pstm4=con.prepareStatement("update bank set balance=? where anumber=?");//prepare statement by updating the balance row in the particular table
			PreparedStatement pstm6=con.prepareStatement("select * from bank");
			while(true)//if the while is true go to the condition 
			{			System.out.println("Welcome To Banking Management System");
				System.out.println("1.creating the account\n2.Deposit the amount\n3.withdraw the amount\n4.Check the balance\n5.Total History of the Transaction\n6.Exit");
				int choice=sc.nextInt();
				switch (choice)//choose the number by particular task
				{
				case 1://creating the basic details by the bank
					System.out.println("Enter the Account number: \n"+b);
					System.out.println("Enter the name:");
					String name=sc.next();
					System.out.println("Enter the initial amount");
					int iamount=sc.nextInt();
					pstm1.setInt(1, b);
					pstm1.setString(2, name);
					pstm1.setInt(3, iamount);
					int rowcount=pstm1.executeUpdate();
					amount=iamount;
					System.out.println("Account create sucessfully\n");
					break;
				case 2://Deposit the amount
					System.out.println("Enter the deposit Amount");
					int deposit=sc.nextInt();
					System.out.println("Enter the account number");
					long a_n1=sc.nextLong();
					pstm2.setInt(1, deposit);
					pstm2.setLong(2, a_n1);
					int rowcount2=pstm2.executeUpdate();
					if(rowcount2>0)
					{
						if(deposit>0)
						{
							amount+=deposit;
							System.out.println("After the Deposit the amount: "+amount+"\n");
						}
						else
						{
							System.out.println("Invalid the Amount\n");
						}
					}
					break;
				case 3://Withdraw the amount
					System.out.println("Enter the withdraw balance");
					int wbalance=sc.nextInt();
					System.out.println("Enter the account number");
					long a_n2=sc.nextLong();
					pstm3.setInt(1, wbalance);
					pstm3.setLong(2, a_n2);
					int rowcount3=pstm3.executeUpdate();
					if(rowcount3>0)
					{
						if(wbalance>0 && wbalance<=amount)
						{
							amount=amount-wbalance;
							System.out.println("Balance: "+amount+"\n");						
						}
						else
						{
							System.out.println("Invlid amount\n");
						}
					}
					break;
				case 4://Check the balance
					System.out.println("Enter the account number");
					long a_n5=sc.nextLong();
					pstm4.setInt(1, amount);
					pstm4.setLong(2, a_n5);
					int rowcount4=pstm4.executeUpdate();
					if(rowcount4>0)
					{
						System.out.println("Final Balance: "+amount+"\n");
					}
					break;
				case 5://Total Transaction history
					System.out.println("History of the transaction");
					ResultSet rs1=pstm6.executeQuery();
					while(rs1.next())
					{
						System.out.println(rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getInt(3)+" "+rs1.getInt(4)+" "+rs1.getInt(5)+" "+rs1.getInt(6)+"\n");
					}
					break;
				case 6://Exit
					System.out.println("Thank you Vist Again!!!!\n");
					break;
				default:
					System.out.println("Default number");
				}
			}
		}
		catch (Exception e)//Exception 
		{
			e.printStackTrace();
		}	
		}
		else//less the 500 go to the else block
		{
			System.out.println("You can't deposit the amount");
		}
	}
	public static void main(String[] args)//main method
	{
		new Bank().meth1();
	}
}
