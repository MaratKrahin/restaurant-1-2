import java.util.Random;

public class Order {

    private Customer customer;
    private Waiter waiter;

    private final int orderNum = new Random().nextInt(10);

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
