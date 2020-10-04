import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        final Restaurant restaurant = new Restaurant();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(new Waiter(restaurant, " Иван"));
        executorService.submit(new Cook(restaurant, " Номи"));
        executorService.submit(new Waiter(restaurant, " Анжела"));
        executorService.submit(new Customer(restaurant, "Посетитель 1"));
        executorService.submit(new Customer(restaurant, "Посетитель 2"));
        executorService.submit(new Customer(restaurant, "Посетитель 4"));
        executorService.submit(new Customer(restaurant, "Посетитель 5"));
        executorService.submit(new Customer(restaurant, "Посетитель 3"));
    }
}
