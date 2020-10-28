import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheProductServerProxy implements Server {

    private Server productServer;
    private Map<String, Product> products = new HashMap<>();


    public CacheProductServerProxy(Server productServer) {
        this.productServer = productServer;
    }

    @Override
    public List<String> getProductIdByKeyword(String keyword) {
        return productServer.getProductIdByKeyword(keyword);
    }

    @Override
    public Product getProductById(String id) {
        return searchProductFromCache(id);
    }

    private Product searchProductFromCache(String id) {
        return products.computeIfAbsent(id, this::requestProduct);
    }

    private Product requestProduct(String id) {
        return productServer.getProductById(id);
    }
}
