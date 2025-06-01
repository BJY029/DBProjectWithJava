package User;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class _UserFrame {
	private Connection conn;
	private JFrame frame;
	private int UserID;

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
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new WindowAdapter(){
			//창 닫을 때, DB와 연결되어 있는 경우, DB 연결을 끊는 이벤트 연결
			public void windowClosing(WindowEvent e) {
				DBUtil.closeConnection();
			}
		});
		
		JLabel lblNewLabel = new JLabel("User mode");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 105, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JButton ViewBtn = new JButton("캠핑카 조회/에약하기");
		ViewBtn.setBackground(new Color(255, 255, 128));
		ViewBtn.addActionListener(e->ViewCampingCarAction());
		ViewBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ViewBtn.setBounds(12, 120, 680, 68);
		frame.getContentPane().add(ViewBtn);
		
		JButton ShowBtn = new JButton("예약 내역 확인/변경하기 및 정비소 의뢰");
		ShowBtn.setBackground(new Color(0, 255, 128));
		ShowBtn.addActionListener(e->CheckRentAction());
		ShowBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ShowBtn.setBounds(12, 232, 680, 68);
		frame.getContentPane().add(ShowBtn);
		

		
	}

	public void setVisible(boolean b, int userID) {
		UserID = userID;
		frame.setVisible(b);
	}
	
	public void CheckRentAction() {
		new _UserCheckRentFrame().setVisible(true, UserID);
	}
	
	private void ViewCampingCarAction() {
		new _UserViewCampingCarFrame().setVisible(true, UserID);
	}
}
