public class Product {
    private String id;
    private String detail;

    public Product(String id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return detail;
    }

    public String getDetail() {
        return detail;
    }
}
