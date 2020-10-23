import java.util.List;
import java.util.Scanner;

public class Client {
    private final Server server = new ProductServerProxy();
    private String url;

    public void search(String keyword){
        url = "https://server.api/product?name=" + keyword;
        printOutput(server.serverRequest(url));
    }

    private void printOutput(List<String> productDetails){
        productDetails.forEach(System.out::println);
    }
}
