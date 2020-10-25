import java.util.List;

public abstract class AbstractServer implements Server {
//    protected ServerAdapter serverAdapter = new ServerAdapter();

    public abstract List<String> serverRequest(String url);
}
