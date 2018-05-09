package fire.Client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class SIMClient extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_Out;
	private JTextField textField_IP;
	private JTextField textField_In;
	private JButton button_Send;

	/**
	 * Create the frame.
	 */
	public SIMClient() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("SIM客户端");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textArea_Out = new JTextArea();
		textArea_Out.setEditable(false);
		textArea_Out.setColumns(10);
		
		textField_IP = new JTextField();
		textField_IP.setText(Global.LocalIP);
		textField_IP.setColumns(10);
		
		JButton button_Connect = new JButton("连接服务器");
		button_Connect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChatTrans.getCT().connect(textField_IP.getText());
			}
		});
		
		textField_In = new JTextField();
		textField_In.setColumns(10);
		
		button_Send = new JButton("发送");
		button_Send.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField_In.getText().length()<1) appendOut("请输入消息!");
				else
				{
					ChatTrans.getCT().send(textField_In.getText());
					textField_In.setText("");
				}
			}
		});
		
		JLabel label_LoacalIP = new JLabel(Global.LocalIP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_LoacalIP, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
							.addComponent(textField_IP, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_Connect))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_In, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_Send, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(textArea_Out, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(button_Connect)
							.addComponent(textField_IP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_LoacalIP, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea_Out, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_In, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_Send)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void appendOut(String msg){
		textArea_Out.append("\n"+msg);
	}
}
