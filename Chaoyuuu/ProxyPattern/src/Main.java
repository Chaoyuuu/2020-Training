import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Client client = new Client(new CacheProductServerProxy(new ProductServer()));

        while (true) {
            System.out.println("Give me a keyword... ");
            client.search(in.next());
        }
    }
}
