import WebServer.WebServer;
import Exception.WebException;

import java.util.Arrays;
import java.util.List;

public class ProductServer implements Server {

    private WebServer webServer = WebServer.WebServerFactory.create();

    @Override
    public List<String> getProductIdByKeyword(String keyword) throws WebException {
        List<String> productIds = null;
        String url = "https://server.api/product?name=" + keyword;
        try {
            String output = webServer.request(url);
            productIds = Arrays.asList(output.split(","));
        } catch (InterruptedException ignored) {}
        return productIds;
    }

    @Override
    public Product getProductById(String id) throws WebException {
        String details = null;
        String url = "https://server.api/product?id=" + id;
        try {
            details = webServer.request(url);
        } catch (InterruptedException ignored) {}
        return new Product(id, details);
    }
}
