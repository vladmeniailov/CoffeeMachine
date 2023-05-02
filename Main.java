import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) {
        System.out.println("©Кофемашина");
        Scanner scanner = new Scanner(System.in);

        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get("data/prices.txt"));
        } catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<Product> products = new ArrayList<>();
        for(String line : lines){
            String[] parts = line.split(" ");
            if(parts.length != 2|| parts == null){
                continue;
            }
            int price = Integer.parseInt(parts[0]);
            Product product = new Product(parts[1], price);
            products.add(product);
        }
        try {
                System.out.println("Введите сумму пополнения.");
                while (scanner.hasNextInt()) {
                    int moneyAmount = scanner.nextInt();
                    checkPrices(products, moneyAmount);
                    System.out.println("Выберите напиток.");
                }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void checkPrices(ArrayList<Product> products, int moneyAmount) {
        boolean canBuyAnything = false;

        for (Product product : products){
            if(moneyAmount >= product.getPrice()){
                System.out.println("Вы можете купить - " + product.getName());
                canBuyAnything = true;
            }
        }

        if(canBuyAnything == false){
            System.out.println("Недостаточно средств :( Изучайте Java и зарабатывайте много!))");
        }
    }
}
