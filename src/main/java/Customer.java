import java.util.Random;

public class Customer implements Runnable {
    private final String name;
    private final Restaurant rest;


    public Customer(Restaurant rest, String name) {
        this.name = name;
        this.rest = rest;
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Посетитель %s вошел в ресторан \n", this.getName());
//        try {
//         //   Thread.sleep(new Random().nextInt(10000) + 5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        order(this);
    }

    public void eatAndLeft() {
        System.out.printf("Посетитель %s ест \n", this.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Посетитель %s ушел из ресторана \n", this.getName());
        if (rest.getIncome().addAndGet(1) == 5) {
            System.out.println("RESTAURANT CLOSED");
            System.exit(0);
        }


    }

    public void order(Customer customer) {
        try {
            Thread.sleep(new Random().nextInt(3000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Посетитель %s готов сделать заказ\n", customer.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Order order = new Order();
        order.setCustomer(customer);
        rest.createOrder(order);
        System.out.printf("Посетитель %s сделал заказ №%d\n", customer.getName(), order.getOrderNum());
    }
}
