package InsertFrames;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Font;

public class _InsertTableMainFrame {

	private JFrame frame;
	private Connection conn;

	/**
	 * Create the application.
	 */
	public _InsertTableMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 1, 1));
		
		JButton CompanyBtn = new JButton("Company Table 삽입");
		CompanyBtn.addActionListener(e -> CompanyAction());
		CompanyBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(CompanyBtn);
		
		JButton CarsBtn = new JButton("Cars Table 삽입");
		CarsBtn.addActionListener(e -> CarsAction());
		CarsBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(CarsBtn);
		
		JButton	CarMaintenanceBtn = new JButton("CarMaintenance Table 삽입");
		CarMaintenanceBtn.addActionListener(e->CarMaintenanceAction());
		CarMaintenanceBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(CarMaintenanceBtn);
		
		JButton PartsBtn = new JButton("Parts Table 삽입");
		PartsBtn.addActionListener(e->PartsAction());
		PartsBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(PartsBtn);
		
		JButton EmployeeBtn = new JButton("Employee Table 삽입");
		EmployeeBtn.addActionListener(e -> EmployeeAction());
		EmployeeBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(EmployeeBtn);
		
		JButton CustomerBtn = new JButton("Customer Table 삽입");
		CustomerBtn.addActionListener(e->CustomerAction());
		CustomerBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(CustomerBtn);
		
		JButton RentBtn = new JButton("Rent Table 삽입");
		RentBtn.addActionListener(e -> RentAction());
		RentBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(RentBtn);
		
		JButton RepairShopBtn = new JButton("RepairShop Table 삽입");
		RepairShopBtn.addActionListener(e -> RepairShopAction());
		RepairShopBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(RepairShopBtn);
		
		JButton CustRepairBtn = new JButton("CustRepair Table 삽입");
		CustRepairBtn.addActionListener(e->CustRepairAction());
		CustRepairBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		frame.getContentPane().add(CustRepairBtn);
	}
	
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
    
    private void CompanyAction() {
    	new _CompanyFrame().setVisible(true);
    }
    
    private void CarsAction() {
    	new _CarsFrame().setVisible(true);
    }
    
    private void CarMaintenanceAction() {
    	new _CarMaintenanceFrame().setVisible(true);
    }
    
    private void PartsAction() {
    	new _PartsFrame().setVisible(true);
    }
    
    private void EmployeeAction() {
    	new _EmployeeFrame().setVisible(true);
    }
    
    private void CustomerAction() {
    	new _CustomerFrame().setVisible(true);
    }
    
    private void RentAction() {
    	new _RentFrame().setVisible(true);
    }
    
    private void RepairShopAction() {
    	new _RepairShopFrame().setVisible(true);
    }
    
    private void CustRepairAction() {
    	new _CustRepairFrame().setVisible(true);
    }
}
