import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import User._UserFrame;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class _UserLoginFrame {

	private JFrame frame;
	private JTextField IDField;
	private JTextField PWField;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_UserLoginFrame window = new _UserLoginFrame();
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
	public _UserLoginFrame() {
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
		
		JLabel TitleLabel = new JLabel("아이디와 비밀번호를 입력하세요");
		TitleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBounds(310, 140, 628, 62);
		frame.getContentPane().add(TitleLabel);
		
		JLabel UserIDLabel = new JLabel("ID");
		UserIDLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		UserIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserIDLabel.setBounds(310, 262, 155, 36);
		frame.getContentPane().add(UserIDLabel);
		
		JLabel UserPWLabel = new JLabel("PASSWORD");
		UserPWLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		UserPWLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UserPWLabel.setBounds(310, 326, 155, 36);
		frame.getContentPane().add(UserPWLabel);
		
		IDField = new JTextField();
		IDField.setBounds(507, 262, 314, 36);
		frame.getContentPane().add(IDField);
		IDField.setColumns(10);
		
		PWField = new JTextField();
		PWField.setBounds(507, 326, 314, 36);
		frame.getContentPane().add(PWField);
		PWField.setColumns(10);
		
		JButton LoginBtn = new JButton("LOGIN");
		LoginBtn.addActionListener(e -> LoginAction());
		LoginBtn.setBounds(838, 262, 100, 100);
		frame.getContentPane().add(LoginBtn);
		
		JButton SignInBtn = new JButton("Sign in");
		SignInBtn.addActionListener(e->SignInAction());
		SignInBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		SignInBtn.setBounds(524, 408, 239, 43);
		frame.getContentPane().add(SignInBtn);
		
		
	}
	
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	private void LoginAction() {
		//각 아이디와 비번 TextField에서 문자열을 받아온다.
		//trim()은 문자열 앞,뒤에 공백을 무시한다.
		String UserID = IDField.getText().trim();
		String UserPW = PWField.getText().trim();
		
		//둘 중 하나라도 빈 경우, 경고문을 표시하고 로그인 시도를 하지 않는다.
		if(UserID.isEmpty() || UserPW.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 모두 입력해주세요.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//연결 객체를 받아온다.
		conn = DBUtil.getUserConnection();
		
		try {
			//SQL 템플릿 정의, ? 는 바인딩 파라미터로 실제 값은 나중에 저장
			String sql = "select * from customer where custLogID = ? and custLogPassword = ?";
			//위에서 정의한 SQL을 미리 컴파일 된 준비된 문장으로 만든다.
			PreparedStatement psm = conn.prepareStatement(sql);
			//SQL 쿼리에서 각 ? 자리에 정보를 넣는다.
			psm.setString(1, UserID);
			psm.setString(2, UserPW);
			//쿼리를 실행하고 결과를 받아온다. 결과 값은 ResultSet 객체로 반환된다.
			ResultSet rs = psm.executeQuery();
			
			//결과 집합에 읽을 행이 있으면 true 없으면 false을 반환하는 rs.next()
			//위 쿼리를 실행해서, 반횐된 객체가 존재하는 경우, 로그인 성공으로 간주한다.
			//읽을 행이 없는 경우, 로그인 실패로 간주한다.
			if(rs.next()) {
				String name = rs.getString("custname");
				JOptionPane.showMessageDialog(null, name + "님, 환영합니다!");
				new _UserFrame(name).setVisible(true);
				frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다.", "로그인 실패!", JOptionPane.WARNING_MESSAGE);
			}
			
			rs.close();
			psm.close();
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "DB 오류: " + ex.getMessage());
            ex.printStackTrace();
		}
	}
	
	private void SignInAction() {
		new _SignUpFrame().setVisible(true);
	}
}
