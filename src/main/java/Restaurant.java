import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {

    private final Queue<Order> orders = new ArrayDeque<>();
    private final Queue<Order> ordersToCook = new ArrayDeque<>();
    private final AtomicInteger income = new AtomicInteger();

    public AtomicInteger getIncome() {
        return income;
    }

    public synchronized void createOrder(Order order) {
        orders.add(order);
        notifyAll();
    }

    public synchronized Order getOrder() {
        Order order = orders.poll();
        if (order != null) {
            return order;
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return orders.poll();
        }
    }

    public synchronized void orderToCook(Order order) {
        ordersToCook.add(order);
        notifyAll();
    }

    public synchronized Order getOrderToCook() {
        Order order = ordersToCook.poll();
        if (order != null) {
            return order;
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ordersToCook.poll();
        }
    }

}
