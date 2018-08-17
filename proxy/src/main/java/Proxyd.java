import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import proxy.HttpProxyMainThread;

public class Proxyd {

	public static void main(String[] args) {
		
		if (args.length > 0 && args[0].equals("-port")) {
			int port = Integer.parseInt(args[1]);
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("The proxy have start on port:" + port + "\n");
				while (true) {
					Socket socket = null;
					try {
						socket = serverSocket.accept();
						new HttpProxyMainThread(socket).start();//有一个请求就启动一个线程
                        System.out.println(socket.getInetAddress().getHostAddress()
                                + " " + socket.getInetAddress().getHostName());
					} catch (Exception e) {
						System.out.println("Thread start fail");
					}
				}
			} catch (IOException e1) {
				System.out.println("Proxyd start fail\n");
			}finally{
				try {
					serverSocket.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}else{
			System.out.println("parameter error");
			for (String arg: args) {
				System.out.println(arg);
			}
		}
	}
	
}
