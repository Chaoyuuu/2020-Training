import Exception.WebException;
import WebServer.WebServer;
import WebServer.WebServer.WebServerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ProductServerProxy extends AbstractServer{

    private ProductServer productServer = new ProductServer(this);
    private Map<String, Product> products = new HashMap<>();

    @Override
    public void serverRequest(String keyword) {
        String url = serverURL + "name=" + keyword;
        try {
            Stream<String> productId = Arrays.stream(server.request(url).split(","));
            productId.forEach(this::searchProductCache);
        } catch (WebException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void searchProductCache(String id) {
        if (isProductInCache(id)) { //print it otu if find
            Product product = products.get(id);
            System.out.println(product);
        } else {    //keep searching it
            productServer.serverRequest(id);
        }
    }

    public boolean isProductInCache(String id) {
        return products.containsKey(id);
    }

    public void addProducts(Product product) {
        products.put(product.getId(), product);
    }
}
