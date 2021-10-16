

import java.text.DecimalFormat;

public class Shared {

    private double balance;
    private String currency;

    public Shared(double balance, String currency) {
        this.balance = balance;
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    DecimalFormat df = new DecimalFormat("###0.00");

    public synchronized void Deposit(double b, String c){

        while(getBalance() != 0 && !getCurrency().equals(c)){
            try {
                System.out.println("Single currency allowed. Remove other currencies\n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setCurrency(c);
        System.out.println(Thread.currentThread().getName() + " " + df.format(b) + " " + getCurrency() + ".");

        setBalance(getBalance() + b);
        System.out.println("Current balance: " + df.format(getBalance()) + " " + c + "." + "\n");

        notify();
    }

    public  synchronized  void Withdraw(double b){

        while(getBalance() < b){

            try{
                System.out.println("Unable to withdraw. 0 balance\n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " " + df.format(b) + " " + getCurrency() + ".");
        setBalance(getBalance() - b);
        System.out.println("Current Balance: " + df.format(getBalance()) + "." + "\n");

        notify();
    }
}
