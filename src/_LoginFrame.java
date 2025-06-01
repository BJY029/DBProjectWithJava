import java.awt.Color;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Admin._AdminFrame;


public class _LoginFrame {

	private JFrame frame;
	private Connection conn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_LoginFrame window = new _LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public _LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new WindowAdapter(){
			//창 닫을 때, DB와 연결되어 있는 경우, DB 연결을 끊는 이벤트 연결
			public void windowClosing(WindowEvent e) {
				DBUtil.closeConnection();
			}
		});
		
		JLabel Title = new JLabel("캠핑카 대여 시스템");
		Title.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(12, 60, 1240, 89);
		frame.getContentPane().add(Title);
		
		
		
		JButton AdminBtn = new JButton("관리자용 ");
		AdminBtn.addActionListener(e -> AdminAction());
		AdminBtn.setBackground(Color.PINK);
		AdminBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		AdminBtn.setOpaque(true);  
		AdminBtn.setBounds(200, 250, 300, 300);
		frame.getContentPane().add(AdminBtn);
		
		
		
		
		JButton UserBtn = new JButton("사용자용");
		UserBtn.setBackground(new Color(152, 251, 152));
		UserBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		UserBtn.addActionListener(e -> UserAction());
		UserBtn.setBounds(800, 250, 300, 300);
		frame.getContentPane().add(UserBtn);
		
	}

	
	private void AdminAction() {
		//DB를 연결하는 함수를 호출한다.
		conn = DBUtil.getConnection();
		//만약 연결된 DB가 없는 경우
		if(conn == null) {
			//오류 메시지를 표시한다.
			JOptionPane.showMessageDialog(frame, "DB 연결 실패!", "ERROR", JOptionPane.ERROR_MESSAGE );
		}
		else {//정상적으로 DB가 연결된 경우
			//관리자 전용 창을 연다.
			new _AdminFrame("관리자").setVisible(true);
			//현재 로그인 창은 닫는다.
			frame.dispose();
		}
	}
	
	private void UserAction() {
		//DB를 연결하는 함수를 호출한다.
		conn = DBUtil.getUserConnection();
		//만약 연결된 DB가 없는 경우
		if(conn == null) {
			//오류 메시지를 표시한다.
			JOptionPane.showMessageDialog(frame, "DB 연결 실패!", "ERROR", JOptionPane.ERROR_MESSAGE );
		}
		else {//정상적으로 DB가 연결된 경우
			//관리자 전용 창을 연다.
			new _UserLoginFrame().setVisible(true);
			//현재 로그인 창은 닫는다.
			frame.dispose();
		}
		
	}
	
}
