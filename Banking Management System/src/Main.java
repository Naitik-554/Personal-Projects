import java.util.*; //importing packages*
class LowBalanceException extends Exception
{
    public LowBalanceException(String s)
    {
        super(s);
    }
}
class NegativeAmountException extends Exception
{
    public NegativeAmountException(String s)
    {
        super(s);
    }
}
class BankAccount
{
    int accNo;
    String custName;
    String accType;
    float balance;
    //Constructor*
    BankAccount(int accNo,String custName, String accType,float balance)
    {
        this.accNo=accNo;
        this.custName=custName;

        this.accType=accType;
        this.balance=balance;
    }
    void Deposit(float amt)
    {
        balance=balance+amt;
        System.out.println("Present available balance: "+balance);
    }
    void Withdraw(String accType,float amt)
    {
        if(accType.equals("Savings"))
        {
            try
            {
                float a=balance-amt;
                if(a<1000)
                {
                    throw new LowBalanceException("Amount cannot be less than 1000\n");
                }
                balance=a;
                System.out.println("Present available balance: "+balance);
            }
            catch(LowBalanceException e)
            {
                System.out.println("Insufficient amount exception---you cannot withdraw\n "+e);
            }
        }
        else if(accType.equals("Current"))
        {
            try
            {
                float a=balance-amt;
                if(a<5000)
                {
                    throw new LowBalanceException("Amount cannot be less than 5000\n");
                }
                balance=a;
                System.out.println("Present available balance: "+balance);
            }
            catch(LowBalanceException e)
            {
                System.out.println("Insufficient amount exception----you cannot withdraw \n"+e);
            }
        }
    }
    float getbalance()
    {
        System.out.println("Account number: "+accNo);
        System.out.println("Custmor name: "+custName);
        System.out.println("Account type: "+accType);
        if(accType=="Savings")
        {
            if(balance<1000)
            {
                System.out.println("Low balance");
            }
        }
        else if(accType=="Current")
        {
            if(balance<5000)
            {
                System.out.println("Low balance");
            }
        }
        return balance;
    }
}
public class Main {
    public static void main(String[] args)
    {
        float amt,balance;
        String name,acctype;

        int accno;
        int k=1,c;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter account number: ");
        accno=sc.nextInt();
        System.out.print("Enter customer name : ");
        sc.nextLine();
        name=sc.nextLine();
        System.out.print("Enter account type: ");
        acctype=sc.nextLine();
        System.out.print("Enter opening amount to deposit: ");
        balance=sc.nextFloat();
        BankAccount ac=new BankAccount(accno,name,acctype,balance);
        while(k==1)
        {
            System.out.println("Enter 1 to Deposit\nEnter 2 to Withdraw\nEnter 3 to Check balance");
            c=sc.nextInt();
            switch(c)
            {
                case 1:
                    try
                    {
                        System.out.print("Enter amount to be deposited:");
                        amt=sc.nextFloat();
                        if(amt<0)
                        {
                            throw new NegativeAmountException("Negative value exception\n");
                        }
                        ac.Deposit(amt);
                    }
                    catch(NegativeAmountException e)
                    {
                        System.out.print("Enter a positive value\n "+e);
                    }
                    break;
                case 2:
                    try
                    {
                        System.out.print("Enter amount to be withdrawed:");
                        amt=sc.nextFloat();
                        if(amt<0)
                        {
                            throw new NegativeAmountException("Negative value exception\n");
                        }
                        ac.Withdraw(acctype,amt);
                    }
                    catch(NegativeAmountException e)
                    {
                        System.out.print("Enter a positive value \n"+e);
                    }
                    break;
                case 3:
                    float bal=ac.getbalance();
                    System.out.println("Your Balance: "+bal);
                    break;
                default:
                    break;
            }
            System.out.print("Enter 1 to continue banking\nEnter 2 to exit");
            k=sc.nextInt();
        }
    }
}