import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {

    private final Queue<Order> orders = new ArrayDeque<>();
    private final Queue<Order> ordersToCook = new ArrayDeque<>();
    private final AtomicInteger income = new AtomicInteger();

    private final Object firstLock = new Object();
    private final Object secondLock = new Object();

    public AtomicInteger getIncome() {
        return income;
    }

    public void createOrder(Order order) {
        synchronized (firstLock) {
            orders.add(order);
            firstLock.notify();
        }
    }

    public synchronized Order getOrder() {
        synchronized (firstLock) {
            Order order = orders.poll();
            if (order != null) {
                return order;
            } else {
                try {
                    firstLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return orders.poll();
            }
        }
    }

    public void orderToCook(Order order) {
        synchronized (secondLock) {
            ordersToCook.add(order);
            secondLock.notify();
        }
    }

    public Order getOrderToCook() {
        synchronized (secondLock) {
            Order order = ordersToCook.poll();
            if (order != null) {
                return order;
            } else {
                try {
                    secondLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ordersToCook.poll();
            }
        }
    }

}
