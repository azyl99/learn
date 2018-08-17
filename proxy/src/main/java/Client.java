import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int length = 3;
        String host = "localhost";
        int port = 8888;
        Socket[] sockets = new Socket[length];
        for (int i = 0; i < length; i++) {     // 试图建立100次连接
            sockets[i] = new Socket(host, port);
            System.out.println("第" + (i + 1) + "次连接成功");
        }
        Thread.sleep(3000);
        for (int i = 0; i < length; i++) {
            sockets[i].close();      //断开连接
        }
    }
}
