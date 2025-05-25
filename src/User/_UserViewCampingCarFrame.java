package User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class _UserViewCampingCarFrame {

	private JFrame frame;
	private Connection conn;
	
	private JScrollPane scrollPane;
	private JPanel carListPanel;
	/**
	 * Create the application.
	 */
	public _UserViewCampingCarFrame() {
		initialize();
		//loadCarList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn = DBUtil.getUserConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 1240, 661);
		frame.getContentPane().add(scrollPane);
		
		carListPanel = new JPanel();
		carListPanel.setLayout(new BoxLayout(carListPanel, BoxLayout.Y_AXIS));
		carListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scrollPane.setViewportView(carListPanel);
		
	}

	private void loadCarList() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cars")) {

            while (rs.next()) {
                int carId = rs.getInt("CarID");
                String carName = rs.getString("CarName");
                String carDesc = rs.getString("CarDesc");
                String carImgURL = rs.getString("CarImgURL");

                JPanel carPanel = new JPanel(new BorderLayout());
                carPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                carPanel.setMaximumSize(new Dimension(1200, 150));

                // 이미지
                JLabel imgLabel = new JLabel();
                try {
                	try {
                	    URI uri = URI.create(carImgURL);          // 문자열을 안전하게 URI로 변환
                	    URL url = uri.toURL();                    // URL 객체로 변환
                	    ImageIcon icon = new ImageIcon(url);
                	    Image scaled = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                	    imgLabel.setIcon(new ImageIcon(scaled));
                	} catch (Exception ex) {
                	    imgLabel.setText("이미지 로딩 실패");
                	}

                // 설명
                JLabel nameLabel = new JLabel("<html><b>" + carName + "</b><br>" + carDesc + "</html>");
                //nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

                // 버튼
                JButton detailBtn = new JButton("자세히 보기");
                /*detailBtn.addActionListener(e -> {
                    new CarDetailFrame(carId, carName).setVisible(true);
                });
                */

                // 구성
                carPanel.add(imgLabel, BorderLayout.WEST);
                carPanel.add(nameLabel, BorderLayout.CENTER);
                carPanel.add(detailBtn, BorderLayout.EAST);

                carListPanel.add(carPanel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "캠핑카 조회 실패: " + e.getMessage());
        }
    }
    
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
