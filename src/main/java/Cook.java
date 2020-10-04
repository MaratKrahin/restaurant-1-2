public class Cook implements Runnable {
    private String name;
    private Restaurant rest;

    public Cook(Restaurant rest, String name) {
        this.name = name;
        this.rest = rest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRest() {
        return rest;
    }

    public void setRest(Restaurant rest) {
        this.rest = rest;
    }

    public void cookCooking() {
        Order order = rest.getOrderToCook();
        if (order != null) {
            System.out.printf("Повар взял заказ №%s\n", order.getOrderNum());
            try {
                Thread.sleep(2000);
                System.out.printf("Повар приготовил заказ № %s \n", order.getOrderNum());
                Waiter waiter = order.getWaiter();
                waiter.returnOrder(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void run() {
        System.out.println("Повар " + this.getName() + " вышел на работу");
        while (true) {
            cookCooking();
        }
    }
}
