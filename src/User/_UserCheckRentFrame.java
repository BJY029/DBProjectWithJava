package User;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

public class _UserCheckRentFrame {
	private Connection conn;
	private JFrame frame;
	private int userID;

	private JTable table1;
	private DefaultTableModel tableModel1;
	private JTable table2;
	private DefaultTableModel tableModel2;
	
	private JTextField D_startTextField;
	private JTextField D_endTextField;

	private JComboBox<String> CarNameComboBox;
	private JComboBox<String> CarsComboBox;
	private JComboBox<String> RepairShopComboBox;
	private JTextField changeStartTextField;
	private JTextField changeEndTextField;
	private JTextField etcDetailsTextField;
	private JTextField repairDateTextField;

	/**
	 * Create the application.
	 */
	public _UserCheckRentFrame() {
		initialize();
		loadCarsNames();
		loadRepairShopNames();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getUserConnection();

		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("예약내역 확인 및 변경");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 1240, 33);
		frame.getContentPane().add(lblNewLabel);

		JLabel Label = new JLabel("변경하고자 하는 예약 정보를 입력하세요");
		Label.setBackground(new Color(64, 128, 128));
		Label.setForeground(new Color(255, 255, 255));
		Label.setOpaque(true);
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		Label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		Label.setBounds(0, 183, 1264, 32);
		frame.getContentPane().add(Label);

		JLabel carNameLabel = new JLabel("차량이름");
		carNameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		carNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		carNameLabel.setBounds(144, 230, 57, 15);
		frame.getContentPane().add(carNameLabel);

		JLabel startLabel = new JLabel("대여 시작일");
		startLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		startLabel.setBounds(497, 230, 81, 15);
		frame.getContentPane().add(startLabel);

		JLabel lblNewLabel_1 = new JLabel("대여 종료일");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(835, 230, 81, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("나의 예약 내역");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(22, 53, 203, 30);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 84, 1230, 89);
		frame.getContentPane().add(scrollPane);

		tableModel1 = new DefaultTableModel();
		table1 = new JTable(tableModel1);
		scrollPane.setViewportView(table1);

		CarNameComboBox = new JComboBox<>();
		CarNameComboBox.setBounds(217, 226, 222, 23);
		frame.getContentPane().add(CarNameComboBox);

		D_startTextField = new JTextField();
		D_startTextField.setText("yyyy-mm-dd");
		D_startTextField.setToolTipText("yyyy-mm-dd");
		D_startTextField.setBounds(580, 227, 186, 21);
		frame.getContentPane().add(D_startTextField);
		D_startTextField.setColumns(10);

		D_endTextField = new JTextField();
		D_endTextField.setText("yyyy-mm-dd");
		D_endTextField.setToolTipText("yyyy-mm-dd");
		D_endTextField.setBounds(919, 227, 186, 21);
		frame.getContentPane().add(D_endTextField);
		D_endTextField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setBounds(46, 275, 360, 202);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel CancelCarLabel = new JLabel("예약 취소");
		CancelCarLabel.setBounds(12, 10, 336, 22);
		panel.add(CancelCarLabel);
		CancelCarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CancelCarLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 255, 128));
		panel_1.setBounds(453, 275, 360, 202);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		CarsComboBox = new JComboBox<>();
		CarsComboBox.setBounds(60, 94, 241, 23);
		panel_1.add(CarsComboBox);

		JLabel ChangeLabel = new JLabel("변경 할 차량");
		ChangeLabel.setBounds(60, 69, 241, 15);
		panel_1.add(ChangeLabel);
		ChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ChangeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

		JLabel ChangeCarLabel = new JLabel("차량 변경");
		ChangeCarLabel.setBounds(12, 10, 336, 22);
		panel_1.add(ChangeCarLabel);
		ChangeCarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ChangeCarLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(128, 255, 255));
		panel_1_1.setBounds(862, 275, 360, 202);
		frame.getContentPane().add(panel_1_1);
		panel_1_1.setLayout(null);

		changeStartTextField = new JTextField();
		changeStartTextField.setBounds(85, 67, 186, 21);
		panel_1_1.add(changeStartTextField);
		changeStartTextField.setToolTipText("yyyy-mm-dd");
		changeStartTextField.setText("yyyy-mm-dd");
		changeStartTextField.setColumns(10);

		changeEndTextField = new JTextField();
		changeEndTextField.setBounds(85, 120, 186, 21);
		panel_1_1.add(changeEndTextField);
		changeEndTextField.setToolTipText("yyyy-mm-dd");
		changeEndTextField.setText("yyyy-mm-dd");
		changeEndTextField.setColumns(10);

		JLabel ChangeLabel_1_1 = new JLabel("대여 종료일");
		ChangeLabel_1_1.setBounds(12, 95, 336, 15);
		panel_1_1.add(ChangeLabel_1_1);
		ChangeLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		ChangeLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

		JLabel ChangeLabel_1 = new JLabel("대여 시작일");
		ChangeLabel_1.setBounds(12, 42, 336, 15);
		panel_1_1.add(ChangeLabel_1);
		ChangeLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		ChangeLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

		JLabel ChangeDaleLabel = new JLabel("일정 변경");
		ChangeDaleLabel.setBounds(12, 10, 336, 22);
		panel_1_1.add(ChangeDaleLabel);
		ChangeDaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ChangeDaleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		JButton DeleteBtn = new JButton("취소하기");
		DeleteBtn.setBounds(129, 99, 106, 23);
		panel.add(DeleteBtn);
		DeleteBtn.addActionListener(e -> CancelRent(CarNameComboBox.getSelectedItem().toString()));

		JButton ChangeCarButton = new JButton("차량 변경하기");
		ChangeCarButton.addActionListener(e -> ChangeCar(CarsComboBox.getSelectedItem().toString(),
				CarNameComboBox.getSelectedItem().toString()));
		ChangeCarButton.setBounds(118, 149, 115, 23);
		panel_1.add(ChangeCarButton);

		JButton ChangeDateBtn = new JButton("일정 변경하기");
		ChangeDateBtn.addActionListener(e -> ChangeDate(CarNameComboBox.getSelectedItem().toString()));
		ChangeDateBtn.setBounds(124, 168, 120, 23);
		panel_1_1.add(ChangeDateBtn);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 255));
		panel_2.setBounds(46, 508, 1176, 163);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel CancelCarLabel_1 = new JLabel("해당 차량 외부 정비소 의뢰하기");
		CancelCarLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		CancelCarLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		CancelCarLabel_1.setBounds(12, 10, 372, 22);
		panel_2.add(CancelCarLabel_1);

		RepairShopComboBox = new JComboBox<>();
		RepairShopComboBox.setBounds(22, 42, 224, 23);
		panel_2.add(RepairShopComboBox);

		JButton ShopSearchBtn = new JButton("정비소 선택");
		ShopSearchBtn.addActionListener(e->loadRepairShopData(RepairShopComboBox.getSelectedItem().toString()));
		ShopSearchBtn.setBounds(258, 42, 97, 23);
		panel_2.add(ShopSearchBtn);
		
		JScrollPane RepairScrollPane = new JScrollPane();
		RepairScrollPane.setBounds(12, 75, 691, 78);
		panel_2.add(RepairScrollPane);
		tableModel2 = new DefaultTableModel();
		table2 = new JTable(tableModel2);
		RepairScrollPane.setViewportView(table2);
		
		etcDetailsTextField = new JTextField();
		etcDetailsTextField.setBounds(718, 42, 322, 67);
		panel_2.add(etcDetailsTextField);
		etcDetailsTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("의뢰 내역(100자)");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(715, 12, 140, 22);
		panel_2.add(lblNewLabel_3);
		
		JButton requestBtn = new JButton("의뢰하기");
		requestBtn.addActionListener(e->requestRepair(RepairShopComboBox.getSelectedItem().toString(), CarNameComboBox.getSelectedItem().toString()));
		requestBtn.setBounds(1052, 45, 97, 108);
		panel_2.add(requestBtn);
		
		repairDateTextField = new JTextField();
		repairDateTextField.setText("yyyy-mm-dd");
		repairDateTextField.setToolTipText("yyyy-mm-dd");
		repairDateTextField.setBounds(718, 132, 322, 21);
		panel_2.add(repairDateTextField);
		repairDateTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("수리 날짜");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(718, 112, 137, 22);
		panel_2.add(lblNewLabel_4);

	}

	private void loadUserRentData() {
		try {
			String userLicense = getUserLicenseNum();
			// 선택된 테이블의 전체 레코드 조회
			String sql = "SELECT (SELECT carname from cars where cars.carid = rent.carid) as CarName"
					+ ", rentstart, DATE_ADD(rentstart, INTERVAL rentperiod DAY) as rentend, rentperiod, rentprice, paymentdate, etcdetails, etcprice"
					+ " FROM rent WHERE licenseNum like ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + userLicense + "%");
			ResultSet rs = pstmt.executeQuery();

			// 테이블의 행 수 가져오기
			ResultSetMetaData meta = rs.getMetaData();
			int colCnt = meta.getColumnCount();

			// 행 이름을 동적으로 벡터에 저장
			Vector<String> colNames = new Vector<>();
			for (int i = 1; i <= colCnt; i++) {
				String colname = meta.getColumnName(i);
				switch (colname) {
				case "RentStart":
					colNames.add("대여 시작일");
					break;
				case "rentend":
					colNames.add("반납일");
					break;
				case "RentPeriod":
					colNames.add("대여 기간(일)");
					break;
				case "RentPrice":
					colNames.add("대여 가격");
					break;
				case "PaymentDate":
					colNames.add("납입 기한");
					break;
				case "etcDetails":
					colNames.add("기타 내역");
					break;
				case "etcPrice":
					colNames.add("추가 비용");
					break;
				case "CarName":
					colNames.add("차량 이름");
					break;
				default:
					break;
				}
			}

			// 차량 이름을 저장할 벡터 생성
			Vector<Object> carNames = new Vector<>();

			// 각 레코드(행)의 값을 벡터로 담아 전체 데이터 벡터에 추가
			Vector<Vector<Object>> data = new Vector<>();
			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				for (int i = 1; i <= colCnt; i++) {
					if (i == 1)
						carNames.add(rs.getObject(i));
					row.add(rs.getObject(i));
				}
				data.add(row);
			}

			// DefaultTableModel에 데이터와 속성 이름을 설정
			// JTable 자동 업데이트
			tableModel1.setDataVector(data, colNames);

			// 취소 ComboBox 내용 채우기
			for (int i = 0; i < carNames.size(); i++) {
				CarNameComboBox.addItem(carNames.get(i).toString());
			}

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
		}
	}

	private void loadRepairShopData(String Name) {
		try {
			String sql = "SELECT rpname, rpaddress, rpphone, rpmngname, rpmngemail"
					+ " FROM repairshop"
					+ " WHERE rpname like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+Name+"%");
			ResultSet rs = pstmt.executeQuery();
			
			ResultSetMetaData meta = rs.getMetaData();
			int colCnt = meta.getColumnCount();
			
			Vector<String> colNames = new Vector<>();
			for (int i = 1; i <= colCnt; i++) {
				String colname = meta.getColumnName(i);
				switch (colname) {
				case "RPName":
					colNames.add("정비소 이름");
					break;
				case "RPAddress":
					colNames.add("정비소 주소");
					break;
				case "RPPhone":
					colNames.add("정비소 전화번호");
					break;
				case "RPMngName":
					colNames.add("담당자 이름");
					break;
				case "RpMngEmail":
					colNames.add("담당자 이메일");
					break;
				default:
					break;
				}
			}


			// 각 레코드(행)의 값을 벡터로 담아 전체 데이터 벡터에 추가
			Vector<Vector<Object>> data = new Vector<>();
			while (rs.next()) {
				Vector<Object> row = new Vector<>();
				for (int i = 1; i <= colCnt; i++) {
					row.add(rs.getObject(i));
				}
				data.add(row);
			}

			// DefaultTableModel에 데이터와 속성 이름을 설정
			// JTable 자동 업데이트
			tableModel2.setDataVector(data, colNames);


			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + e.getMessage());
		}
	}
	
	private void CancelRent(String carName) {
		String startDate = D_startTextField.getText();
		String endDate = D_endTextField.getText();

		if (startDate.isEmpty() || endDate.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "모든 요소를 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String CarID = getCarID(carName);
			LocalDate startD = LocalDate.parse(startDate);
			LocalDate endD = LocalDate.parse(endDate);
			String rentPeriod = String.valueOf(ChronoUnit.DAYS.between(startD, endD));

			// 과거 날짜 입력 시(오늘 이전 날짜)
			if (startD.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(frame, "해당 예약 내역은 취소 불가능 합니다.", "오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 캠핑카 이름이 중복된 경우 오류 발생 위험 존재
			String sql = "DELETE FROM rent WHERE carid = (SELECT carid FROM cars WHERE carname LIKE ?) "
					+ "AND rentstart = ? AND rentperiod = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carName);
			pstmt.setDate(2, java.sql.Date.valueOf(startD));
			pstmt.setString(3, rentPeriod);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				JOptionPane.showMessageDialog(frame, "성공적으로 예약이 취소되었습니다!");
				DeleteRepairRequest(CarID, startD, endD);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "취소 실패! 예약 정보를 정확하게 입력하세요!", "예약 취소 오류",
						JOptionPane.ERROR_MESSAGE);
			}
			pstmt.close();

		} catch (DateTimeParseException ed) { // 입력 날짜 형식 오류
			JOptionPane.showMessageDialog(frame, "날짜 형식이 잘못되었습니다. (예: 2025-06-01)", "입력 오류",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + ex.getMessage());
		}
	}


	private void ChangeCar(String carName, String originalCarName) {
		String startDate = D_startTextField.getText();
		String endDate = D_endTextField.getText();

		if (startDate.isEmpty() || endDate.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "모든 요소를 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			LocalDate startD = LocalDate.parse(startDate);
			LocalDate endD = LocalDate.parse(endDate);
			String rentPeriod = String.valueOf(ChronoUnit.DAYS.between(startD, endD));

			// 과거 날짜 입력 시(오늘 이전 날짜)
			if (startD.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(frame, "해당 예약 내역은 변경 불가능 합니다.", "오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			String carID = getCarID(carName);
			String originCarId = getCarID(originalCarName);
			if (carID == null || originalCarName == null)
				throw new Exception("차량 번호를 찾을 수 없습니다.");

			if (!isVaildInfo(originCarId, startD, rentPeriod)) {
				JOptionPane.showMessageDialog(frame, "예약 정보를 정확하게 입력하세요!!", "예약 정보 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (isCarAlreadyRented(carID, startD, endD)) {
				JOptionPane.showMessageDialog(frame, "변경하고자 하는 차량의 예약이 해당 날짜에 존재합니다.", "중복 예약",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			String sql = "UPDATE rent SET CarID = ?" + "WHERE carid = ? AND rentstart = ? AND rentperiod = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carID);
			pstmt.setString(2, originCarId);
			pstmt.setDate(3, java.sql.Date.valueOf(startD));
			pstmt.setString(4, rentPeriod);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				JOptionPane.showMessageDialog(frame, "성공적으로 차량이 변경되었습니다!");
				DeleteRepairRequest(originCarId, startD, endD);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "차량 변경 실패! 예약 정보를 정확하게 입력하세요!", "차량 변경 오류",
						JOptionPane.ERROR_MESSAGE);
			}
			pstmt.close();
		} catch (DateTimeParseException ed) { // 입력 날짜 형식 오류
			JOptionPane.showMessageDialog(frame, "날짜 형식이 잘못되었습니다. (예: 2025-06-01)", "입력 오류",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + ex.getMessage());
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(frame, ee);
		}
	}

	private void ChangeDate(String carName) {
		String startDate = D_startTextField.getText();
		String endDate = D_endTextField.getText();
		String C_startDate = changeStartTextField.getText();
		String C_endDate = changeEndTextField.getText();

		if (startDate.isEmpty() || endDate.isEmpty() || C_startDate.isEmpty() || C_endDate.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "모든 요소를 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String carID = getCarID(carName);
			if (carID == null)
				throw new Exception("차량 번호를 찾을 수 없습니다.");

			LocalDate startD = LocalDate.parse(startDate);
			LocalDate endD = LocalDate.parse(endDate);
			String rentPeriod = String.valueOf(ChronoUnit.DAYS.between(startD, endD));
			if (!isVaildInfo(carID, startD, rentPeriod)) {
				JOptionPane.showMessageDialog(frame, "예약 정보를 정확하게 입력하세요!!", "예약 정보 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			LocalDate C_startD = LocalDate.parse(C_startDate);
			LocalDate C_endD = LocalDate.parse(C_endDate);
			String C_rentPeriod = String.valueOf(ChronoUnit.DAYS.between(C_startD, C_endD));

			// 과거 날짜 입력 시(오늘 이전 날짜)
			if (startD.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(frame, "해당 예약 내역은 변경 불가능 합니다.", "오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 과거 날짜 입력 시(오늘 이전 날짜)
			if (C_startD.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(frame, "대여 시작일은 오늘 이후여야 합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 종료일이 시작일보다 빠른 경우
			if (C_endD.isBefore(C_startD)) {
				JOptionPane.showMessageDialog(frame, "반납일은 대여 시작일보다 이후여야 합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (Integer.parseInt(C_rentPeriod) == 0) {
				JOptionPane.showMessageDialog(frame, "대여는 하루 이상부터 가능합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (C_isCarAlreadyRented(carID, C_startD, C_endD, startD, rentPeriod)) {
				JOptionPane.showMessageDialog(frame, "변경하고자 하는 차량의 예약이 해당 날짜에 존재합니다.", "중복 예약",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			String sql = "UPDATE rent SET rentstart = ?, rentperiod = ?"
					+ "WHERE carid = ? AND rentstart = ? AND rentperiod = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, java.sql.Date.valueOf(C_startD));
			pstmt.setString(2, C_rentPeriod);
			pstmt.setString(3, carID);
			pstmt.setDate(4, java.sql.Date.valueOf(startD));
			pstmt.setString(5, rentPeriod);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				JOptionPane.showMessageDialog(frame, "성공적으로 날짜가 변경되었습니다!");
				DeleteRepairRequest(carID, startD, endD);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "날짜 변경 실패! 예약 정보를 정확하게 입력하세요!", "날짜 변경 오류",
						JOptionPane.ERROR_MESSAGE);
			}
			pstmt.close();
		} catch (DateTimeParseException ed) { // 입력 날짜 형식 오류
			JOptionPane.showMessageDialog(frame, "날짜 형식이 잘못되었습니다. (예: 2025-06-01)", "입력 오류",
					JOptionPane.WARNING_MESSAGE);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + ex.getMessage());
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(frame, ee);
		}

	}

	
	private void requestRepair(String RPName, String carName) {
		String startDate = D_startTextField.getText();
		String endDate = D_endTextField.getText();
		String Det = etcDetailsTextField.getText();
		String repairDate = repairDateTextField.getText();
		
		if (startDate.isEmpty() || endDate.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "모든 요소를 입력하세요", "입력 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			String RPID = getRPID(RPName);
			String CarID = getCarID(carName);
			String CompanyID = getCompanyID(carName);
			String LicNum = getUserLicenseNum();
			if(RPID.isEmpty() || CarID.isEmpty() || CompanyID.isEmpty() || LicNum.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "데이터를 불러오는데 실패했습니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
			

			LocalDate startD = LocalDate.parse(startDate);
			LocalDate endD = LocalDate.parse(endDate);
			LocalDate repairD = LocalDate.parse(repairDate);
			String rentPeriod = String.valueOf(ChronoUnit.DAYS.between(startD, endD));
			if (!isVaildInfo(CarID, startD, rentPeriod)) {
				JOptionPane.showMessageDialog(frame, "예약 정보를 정확하게 입력하세요!!", "예약 정보 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 과거 날짜 입력 시(오늘 이전 날짜)
			if (startD.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(frame, "해당 예약 내역은 변경 불가능 합니다.", "오류", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 과거 날짜 입력 시(오늘 이전 날짜)
			if (repairD.isBefore(startD) || repairD.isAfter(endD)) {
				JOptionPane.showMessageDialog(frame, "정비소 의뢰는 대여 기간동안만 가능합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String sql = "INSERT INTO custrepair(carid, rpid, companyid, licensenum, repairdetails, repairdate)"
					+ "Values(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CarID);
			pstmt.setString(2, RPID);
			pstmt.setString(3, CompanyID);
			pstmt.setString(4, LicNum);
			pstmt.setString(5, Det);
			pstmt.setDate(6, java.sql.Date.valueOf(repairD));
			
			int result = pstmt.executeUpdate();
				
			if (result > 0 ) {
				JOptionPane.showMessageDialog(frame, "성공적으로 등록이 완료되었습니다!");
    			frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "의뢰 실패", "의뢰 오류", JOptionPane.ERROR_MESSAGE);
			}
			pstmt.close();
			
		} catch (DateTimeParseException ed) { // 입력 날짜 형식 오류
			JOptionPane.showMessageDialog(frame, "날짜 형식이 잘못되었습니다. (예: 2025-06-01)", "입력 오류",
					JOptionPane.WARNING_MESSAGE);
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + ex.getMessage());
			
		}
	}
	
	
	private String getCarID(String carName) {
		try {
			String sql = "SELECT carid FROM cars WHERE carname like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+carName+"%");
			ResultSet rs = pstmt.executeQuery();

			String carID = null;
			if (rs.next()) {
				carID = rs.getString("CarID");
			}

			pstmt.close();
			rs.close();

			return carID;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + ex.getMessage());
			return null;
		}
	}
	
	private String getCompanyID(String carName) {
		try {
			String sql = "SELECT CompanyId FROM cars WHERE carname like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+carName+"%");
			ResultSet rs = pstmt.executeQuery();

			String companyId = null;
			if (rs.next()) {
				companyId = rs.getString("CompanyId");
			}

			pstmt.close();
			rs.close();

			return companyId;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패!: " + ex.getMessage());
			return null;
		}
	}
	
	private String getRPID(String RPName) {
		try {
			String sql = "SELECT RPID FROM repairshop WHERE RPName like ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, RPName);
			ResultSet rs = pstmt.executeQuery();

			String RPID = null;
			if (rs.next()) {
				RPID = rs.getString("RPID");
			}

			pstmt.close();
			rs.close();

			return RPID;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(frame, "데이터 조회 실패: " + ex.getMessage());
			return null;
		}
	}

 	private boolean isCarAlreadyRented(String carId, LocalDate startDate, LocalDate endDate) {
		String sql = "SELECT COUNT(*) FROM rent " + "WHERE carid = ? " + "AND ( "
				+ "  (rentstart <= ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) > ?) " + "  OR "
				+ "  (rentstart < ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) >= ?) " + "  OR"
				+ "   (rentstart >= ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) <= ?)" + ")";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, carId);
			pstmt.setDate(2, java.sql.Date.valueOf(startDate));
			pstmt.setDate(3, java.sql.Date.valueOf(startDate));
			pstmt.setDate(4, java.sql.Date.valueOf(endDate));
			pstmt.setDate(5, java.sql.Date.valueOf(endDate));
			pstmt.setDate(6, java.sql.Date.valueOf(startDate));
			pstmt.setDate(7, java.sql.Date.valueOf(endDate));

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0; // true면 예약 불가
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "예약 중복 확인 실패: " + e.getMessage());
		}
		return false;
	}

	// 자신의 예약 기록은 제외하고 조회하는 함수
	private boolean C_isCarAlreadyRented(String carId, LocalDate newStartDate, LocalDate newEndDate,
			LocalDate originStartDate, String originRentPeriod) {

		// 보다 간소화 된 SQL문!!
		String sql = "SELECT COUNT(*) FROM rent " + "WHERE carid = ? " + "AND NOT (rentstart = ? AND rentperiod = ?) "
				+ "AND (rentstart < ? AND DATE_ADD(rentstart, INTERVAL rentperiod DAY) > ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, carId);
			pstmt.setDate(2, java.sql.Date.valueOf(originStartDate));
			pstmt.setString(3, originRentPeriod);
			pstmt.setDate(4, java.sql.Date.valueOf(newEndDate));
			pstmt.setDate(5, java.sql.Date.valueOf(newStartDate));

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0; // true면 겹침 있음 → 예약 불가
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "예약 중복 확인 실패: " + e.getMessage());
		}
		return false;
	}

	private boolean isVaildInfo(String carId, LocalDate startDate, String rentPeriod) {
		String sql = "Select count(*) from rent " + "where carid = ? and rentstart = ? and rentperiod = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carId);
			pstmt.setDate(2, java.sql.Date.valueOf(startDate));
			pstmt.setString(3, rentPeriod);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "예약 유효성 확인 실패: " + e.getMessage());
		}
		return false;
	}
	
	private void DeleteRepairRequest(String carId, LocalDate startDate, LocalDate endDate) {
		String LicNum = getUserLicenseNum();
		try {
			String sql = "Select count(*) From custrepair"
					+ " Where carid = ? AND licensenum = ? AND "
					+ "repairdate BETWEEN ? AND ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carId);
			pstmt.setString(2, LicNum);
			pstmt.setDate(3, java.sql.Date.valueOf(startDate));
			pstmt.setDate(4, java.sql.Date.valueOf(endDate));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int count = rs.getInt(1);
				if(count <= 0) {
					pstmt.close();
					rs.close();
					return;
				}
			}
			
			System.out.println("1차 성공");
			
			sql = "DELETE FROM custrepair " +
				      "WHERE carid = ? AND licensenum = ? AND repairdate BETWEEN ? AND ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				pstmt2.setString(1, carId);
				pstmt2.setString(2, LicNum);
				pstmt2.setDate(3, java.sql.Date.valueOf(startDate));
				pstmt2.setDate(4, java.sql.Date.valueOf(endDate));
			
			int result = pstmt2.executeUpdate();
			if (result > 0) {
				JOptionPane.showMessageDialog(frame, "기존에 의뢰한 정비 내역은 자동 삭제되었습니다.");
				//frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "정비 의뢰 삭제 오류", "오류",
						JOptionPane.ERROR_MESSAGE);
			}
			
			pstmt.close();
			rs.close();
			pstmt2.close();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "의뢰 내역 삭제 실패: " + e.getMessage());
		}
	}

	private String getUserLicenseNum() {
		try {
			String LNum = null;

			String sql = "select licensenum from customer where custid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(userID));
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LNum = rs.getString("licensenum");
			}

			pstmt.close();
			rs.close();
			return LNum;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "사용자 정보(면허번호) 불러오기 오류" + e.getMessage());
			return null;
		}
	}
	


	private void loadCarsNames() {
		try {
			// 선택된 테이블의 전체 레코드 조회
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select carname from cars");

			while (rs.next()) {
				CarsComboBox.addItem(rs.getString("carname"));
			}
			rs.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "테이블 목록 로드 실패: " + e.getMessage());
		}
	}

	private void loadRepairShopNames() {
		try {
			// 선택된 테이블의 전체 레코드 조회
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select rpname from repairshop");

			while (rs.next()) {
				RepairShopComboBox.addItem(rs.getString("rpname"));
			}
			rs.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "테이블 목록 로드 실패: " + e.getMessage());
		}
	}

	public void setVisible(boolean b, int userId) {
		userID = userId;
		loadUserRentData();
		frame.setVisible(b);
	}
}
