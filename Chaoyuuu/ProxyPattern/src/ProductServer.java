import WebServer.WebServer;

import java.util.Arrays;
import java.util.stream.Stream;
import Exception.WebException;

public class ProductServer extends AbstractServer{

    private ProductServerProxy productServerProxy;
    public ProductServer(ProductServerProxy productServerProxy) {
        this.productServerProxy = productServerProxy;
    }

    @Override
    public void serverRequest(String id) {
        String url = serverURL + "id=" + id;
        try {
            String productDetail = server.request(url);
            Product product = new Product(id, productDetail);
            productServerProxy.addProducts(product);
            System.out.println(product);
        } catch (WebException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
