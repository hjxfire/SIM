package fire.Server;

/*
 * 开启服务端主界面的函数(程序入口),并初始化本机IP
 */

import java.awt.EventQueue;
import java.net.InetAddress;

public class StartServer {
	public static void main(String args[]){
		
		//获得本机IP　
		InetAddress addr=null;
		try{  
			addr=InetAddress.getLocalHost();  
			Global.LocalIP=addr.getHostAddress();  
			}catch(Exception e){  
				e.printStackTrace();  
	        }
		
		//开启主界面
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SIMServer frame = new SIMServer();
					frame.setVisible(true);
					frame.setFrame(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
