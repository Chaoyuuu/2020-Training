import java.util.List;
import Exception.WebException;

public class Client {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void search(String keyword) {
        try {
            List<String> productIds = server.getProductIdByKeyword(keyword);
            productIds.forEach(id -> {
                Product product = server.getProductById(id);
                System.out.println(product.getDetail());
            });
        } catch (WebException e) {
            System.err.println(e.getMessage());
        }
    }
}
