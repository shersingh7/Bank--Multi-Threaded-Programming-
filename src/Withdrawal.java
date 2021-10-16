

public class Withdrawal extends Thread{

    private Shared account;
    private double[] balance;

    public Withdrawal(Shared acc, double[] b ){
        super("Withdrawing Money:");
        this.account = acc;
        this.balance = b;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){

            try{
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (account){
                account.Withdraw(1); // 1 at a time
            }
        }
    }
}
