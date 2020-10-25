import java.util.LinkedList;
import java.util.List;

public class ProductServer extends AbstractServer{

    private ServerAdapter serverAdapter = new ServerAdapter();

    @Override
    public List<String> serverRequest(String url) {
        List<String> productDetail = new LinkedList<>();
        productDetail.add(serverAdapter.request(url));
        return productDetail;
    }
}
