package fire.Client;

/*
 * 通信函数,负责连接,发送和接收
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class ChatTrans {
	//使ChatTrans单例化
	private ChatTrans(){}
	private static final ChatTrans chattrans=new ChatTrans();
	public static ChatTrans getCT(){
		return chattrans;
	}
	
	protected SIMClient simclient;
	protected Socket s;
	protected PrintWriter sendmsg;
	protected BufferedReader receivemsg;


	//连接+接受函数
	public void connect(String serverIP){
		new Thread(){
			public void run(){
				try {
					s=new Socket(serverIP,Global.ServerPort);
					sendmsg=new PrintWriter(s.getOutputStream());
					receivemsg=new BufferedReader(new InputStreamReader(s.getInputStream()));
					String msg_receive;
					while((msg_receive=receivemsg.readLine())!=null)
					{
						//解密
						try {
							msg_receive=Global.tdes.Decryptor(msg_receive);
						} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
							e.printStackTrace();
						}

						simclient.appendOut(msg_receive);
					}
					//关闭输入输出流
					sendmsg.close();
					receivemsg.close();
					sendmsg=null;
					receivemsg=null;
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	//发送函数
	public void send(String msg_send){
		if(sendmsg!=null)
		{
			simclient.appendOut("我:"+msg_send);
			msg_send=Global.LocalIP+":"+msg_send;

			//加密
			try {
				msg_send=Global.tdes.Encrytor(msg_send);
			} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			sendmsg.write(msg_send+"\n");
			sendmsg.flush();
		}
		else
		{
			simclient.appendOut("连接已断开!");
		}
	}

	//取得主界面地址
	public void setSIMClient(SIMClient simclient){
		this.simclient=simclient;
	}
}
