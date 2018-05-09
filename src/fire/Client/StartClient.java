package fire.Client;

/*
 * 开启客户端主界面的函数(程序入口)
 */

import java.awt.EventQueue;
import java.net.InetAddress;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

public class StartClient {
	public static void main(String args[]) throws InvalidKeySpecException{

		//获得本机IP　
		InetAddress addr=null;
		try{  
			addr=InetAddress.getLocalHost();  
			Global.LocalIP=addr.getHostAddress();  
		}catch(Exception e){  
			e.printStackTrace();  
		}
		//初始化加解密
		try {
			Global.tdes=new TDES();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//开启主界面
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SIMClient frame = new SIMClient();
					frame.setVisible(true);
					ChatTrans.getCT().setSIMClient(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
