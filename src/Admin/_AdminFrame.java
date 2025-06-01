package Admin;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import InsertFrames._InsertTableMainFrame;
import InsertFrames._SelectFrame;
import InsertFrames._UpdateDeleteFrame;

import java.io.*;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;

public class _AdminFrame {

	private Connection conn;
	private JFrame frame;


	/**
	 * Create the application.
	 */
	public _AdminFrame(String name) {
		initialize(name);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame(name);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new WindowAdapter(){
			//창 닫을 때, DB와 연결되어 있는 경우, DB 연결을 끊는 이벤트 연결
			public void windowClosing(WindowEvent e) {
				DBUtil.closeConnection();
			}
		});
		
		JLabel lblNewLabel = new JLabel("관리자용 모드");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 10, 176, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JButton InitBtn = new JButton("캠핑카 DB 초기화하기");
		InitBtn.addActionListener(e->InitDBAction());
		InitBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		InitBtn.setBounds(32, 64, 297, 70);
		frame.getContentPane().add(InitBtn);
		
		JButton AllTableBtn = new JButton("모든 테이블 조회하기");
		AllTableBtn.addActionListener(e -> AllTableViewAction());
		AllTableBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		AllTableBtn.setBounds(32, 271, 297, 70);
		frame.getContentPane().add(AllTableBtn);
		
		JButton ViewCampingCars = new JButton("캠핑카 정비 기록 조회하기");
		ViewCampingCars.addActionListener(e->ViewCampingCarsAction());
		ViewCampingCars.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ViewCampingCars.setBounds(32, 489, 297, 70);
		frame.getContentPane().add(ViewCampingCars);
		
		JButton InsertTableBtn = new JButton("테이블 레코드 삽입하기");
		InsertTableBtn.addActionListener(e -> InsertTableAction());
		InsertTableBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		InsertTableBtn.setBounds(341, 271, 297, 70);
		frame.getContentPane().add(InsertTableBtn);
		
		JButton UpdateDeleteTableBtn = new JButton("테이블 레코드 수정/삭제");
		UpdateDeleteTableBtn.addActionListener(e->UpdateDeleteAction());
		UpdateDeleteTableBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		UpdateDeleteTableBtn.setBounds(650, 271, 297, 70);
		frame.getContentPane().add(UpdateDeleteTableBtn);
		
		JButton SelectBtn = new JButton("질의문 입력하기");
		SelectBtn.addActionListener(e->SelectAction());
		SelectBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		SelectBtn.setBounds(341, 489, 297, 70);
		frame.getContentPane().add(SelectBtn);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	
	//DB를 초기화 하는 함수
	private void InitDBAction() {
		try {
			//DB 연결 객체 생성
			conn = DBUtil.getConnection();
			//SQL 문 실행을 위한 Statement 객체 생성
			Statement stm = conn.createStatement();
			//여러 줄의 SQL 문장을 누적하기 위한 StringBuilder
			StringBuilder sqlBuilder = new StringBuilder();
			//SQL 스크립트 파일을 읽기 위한 BufferedReader 생성
			BufferedReader reader = new BufferedReader(new FileReader("Resources/202501-21011627-ini.sql"));
			String line;
			
			//파일의 모든 줄을 한 줄씩 읽으며 처리
			while((line = reader.readLine()) != null) {
				//앞 뒤 공백 제거
				line = line.trim();
				//주석이거나 빈 줄이면 무시하고 넘어간다.
				if(line.startsWith("--") || line.isEmpty()) continue;
				
				//유효한 SQL 라인이면 누적시킨다.
				sqlBuilder.append(line).append(" ");
				//만약 SQL 문이 세미콜론으로 끝난 경우 실행할 준비가 돤료되었다는 뜻
				if(line.endsWith(";")){
					//누적된 SQL문을 추출한다.
					 String sql = sqlBuilder.toString();
					 //끝에 붙은 세미콜론을 제거한다.
		                sql = sql.substring(0, sql.length() - 1); // 세미콜론 제거
		                try {
		                    stm.execute(sql); //해당 SQL문 실행
		                    //로그 출력
		                    System.out.println("실행됨: " + sql);
		                } catch (SQLException e) {
		                    System.err.println("오류 발생: " + sql);
		                    e.printStackTrace();
		                }
		                //다음 SQL 문장을 위한 stringBuilder 초기화
		                sqlBuilder.setLength(0); // 초기화
				}
			}
			//파일 및 statement 닫기
			reader.close();
			stm.close();
			
			//사용자에게 완료 메시지 표시
			JOptionPane.showMessageDialog(null, "SQL 스크립트 실행 완료, 초기화 성공");
			
		}catch(Exception ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "실행 실패: " + ex.getMessage());
		}
		
	}
	
	private void AllTableViewAction() {
		new _AllTablesFrame().setVisible(true);
	}
	
	private void ViewCampingCarsAction() {
		new _ViewCampingCars().setVisible(true);
	}
	
	private void InsertTableAction() {
		new _InsertTableMainFrame().setVisible(true);
	}
	
	private void UpdateDeleteAction() {
		new _UpdateDeleteFrame().setVisible(true);
	}
	
	private void SelectAction() {
		new _SelectFrame().setVisible(true);
	}
}
