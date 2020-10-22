import java.util.Scanner;

public class Client {
    private final Scanner in = new Scanner(System.in);
    private final Server serverInterface = new ProductServerProxy();

    public void search(){
        String keyword = in.next();
        serverInterface.serverRequest(keyword);
    }
}
