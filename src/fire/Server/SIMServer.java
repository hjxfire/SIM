package fire.Server;

/*
 * 服务端的主界面
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


public class SIMServer extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_Out;
	private SIMServer frame;

	/**
	 * Create the frame.
	 */
	public SIMServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("SIM服务端");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label_LocalIP = new JLabel(Global.LocalIP);
		
		textArea_Out = new JTextArea();
		textArea_Out.setEditable(false);
		
		JButton button_StartServer = new JButton("开启服务器");
		button_StartServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ServerListener sl=new ServerListener(frame);
				sl.start();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label_LocalIP, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
					.addComponent(button_StartServer))
				.addComponent(textArea_Out, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(button_StartServer)
						.addComponent(label_LocalIP, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea_Out, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setFrame(SIMServer frame){
		this.frame=frame;
	}
	public void appendOut(String msg){
		textArea_Out.append("\n"+msg);
	}
}
