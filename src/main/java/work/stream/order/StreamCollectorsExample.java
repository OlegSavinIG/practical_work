package work.stream.order;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        List<Order> mostExpensive = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .toList();
        System.out.println("Три самых дорогих продукта");
        mostExpensive.forEach(System.out::println);

        List<String> namesMostExpensive = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .limit(3)
                .map(Order::getProduct)
                .toList();
        int costMostExpensive = mostExpensive.stream()
                .mapToInt(order -> (int) order.getCost())
                .sum();
        System.out.println("Список трех самых дорогих продуктов и их общая стоимость.");
        System.out.println(namesMostExpensive);
        System.out.println(costMostExpensive);

        List<Order> sorted = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .toList();
        System.out.println("Продукты по убыванию общей стоимости.");
        sorted.forEach(System.out::println);

        List<Order> sortedByName = orders.stream()
                .sorted(Comparator.comparing(Order::getProduct))
                .toList();
        System.out.println("Заказы по продуктам");
        sortedByName.forEach(System.out::println);

        Map<String, Double> groupedByName = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)));
        System.out.println("Для каждого продукта общая стоимость всех заказов.");
        System.out.println(groupedByName);

    }
}
