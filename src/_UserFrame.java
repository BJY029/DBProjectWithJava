import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class _UserFrame {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public _UserFrame(String name) {
		initialize(name);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame(name);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter(){
			//창 닫을 때, DB와 연결되어 있는 경우, DB 연결을 끊는 이벤트 연결
			public void windowClosing(WindowEvent e) {
				DBUtil.closeConnection();
			}
		});
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
