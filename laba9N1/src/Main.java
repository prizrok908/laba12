import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Tool> tools = new HashMap<>();
        tools.put("Молоток", new Hammer("Молоток1"));
        tools.put("Отвёртка", new Screwdriver("Отвёртка1"));

        System.out.println("Ключи:");
        for (String key : tools.keySet()) {
            System.out.println(key);
        }
        System.out.println("Имена:");
        for (Tool tool : tools.values()) {
            System.out.println(tools);
        }

    }
}