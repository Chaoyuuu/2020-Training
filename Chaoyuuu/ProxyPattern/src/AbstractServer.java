import WebServer.WebServer;
import WebServer.WebServer.WebServerFactory;

public abstract class AbstractServer implements Server {
    protected final String serverURL = "https://server.api/product?";
    protected WebServer server = WebServerFactory.create();

    public abstract void serverRequest(String url);
}
