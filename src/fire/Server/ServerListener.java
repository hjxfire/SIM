package fire.Server;

/*
 * 监听线程
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread{
	public SIMServer simserver;
	public ServerListener(SIMServer simserver){
		this.simserver=simserver;
	}
	public void run(){
		ServerSocket ss;
		try {
			ss = new ServerSocket(Global.ServerPort);
			simserver.appendOut("服务器已开启");
			while(true)
			{
				Socket s=ss.accept();
				simserver.appendOut("用户"+s.getInetAddress().toString()+"已连接到服务器");
				ChatSocket chatsocket = new ChatSocket(s,simserver);
				chatsocket.start();
				ChatManager.getChatManager().add(chatsocket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
