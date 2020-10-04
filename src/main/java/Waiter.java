public class Waiter implements Runnable {

   private final String name;
    private final Restaurant rest;


    public Waiter(Restaurant rest, String name) {
        this.rest = rest;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Официант " + this.getName() + " вышел на работу");
        while (true) {
            waiterTakeOrder(this);
        }
    }

    public void returnOrder(Order order) {
        Customer customer = order.getCustomer();
        customer.eatAndLeft();
    }

    public void waiterTakeOrder(Waiter waiter) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Order order = rest.getOrder();
        System.out.printf("Официант %s взял заказ %d\n", waiter.getName(), order.getOrderNum());
        order.setWaiter(waiter);
        rest.orderToCook(order);
        System.out.printf("Официант %s передал заказ %d на изготовление \n", waiter.getName(), order.getOrderNum());
    }
}
