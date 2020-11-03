import java.util.List;

public interface Server {
    Product getProductById(String id);
    List<String> getProductIdByKeyword(String keyword);
}
