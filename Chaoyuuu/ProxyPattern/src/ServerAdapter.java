import WebServer.WebServer;
import Exception.WebException;
public class ServerAdapter {
    private WebServer webServer = WebServer.WebServerFactory.create();

    public String request(String url){
        String output = null;
        try {
            output = webServer.request(url);
        } catch (WebException | InterruptedException e){
            System.err.println(e.getMessage());
        }
        return output;
    }
}
