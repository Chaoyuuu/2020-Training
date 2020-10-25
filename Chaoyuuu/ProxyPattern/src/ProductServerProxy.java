import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProductServerProxy extends AbstractServer {

    private Server productServer = new ProductServer();
    private Map<String, Product> products = new HashMap<>();

    @Override
    public List<String> serverRequest(String url) {
        // get product ids and return product details
        String[] productIds = productServer.serverRequest(url).get(0).split(",");
        return getProductDetails(productIds);
    }

    private List<String> getProductDetails(String[] productIds) {
        List<String> productDetails = new LinkedList<>();
        for (String id : productIds) {
            productDetails.add(searchProductCache(id));
        }
        return productDetails;
    }

    private String searchProductCache(String id) {
        String productDetail = null;
        if (isProductInCache(id)) {
            Product product = products.get(id);
            productDetail = product.getDetail();
        } else {
            productDetail = getProductDetail(id);
            addProducts(new Product(id, productDetail));
        }
        return productDetail;
    }

    private String getProductDetail(String id){
        String url = "https://server.api/product?id=" + id;
        return productServer.serverRequest(url).get(0);
    }

    private boolean isProductInCache(String id) {
        return products.containsKey(id);
    }

    private void addProducts(Product product) {
        products.put(product.getId(), product);
    }
}
