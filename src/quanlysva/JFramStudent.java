package quanlysva;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

public class JFramStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textma;
	private JTextField textname;
	private JTextField textclass;
	private JTextField textdiem;
	private JTable table;
	DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramStudent frame = new JFramStudent();
					frame.setTitle("QLSV");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFramStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Mangerment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(418, 10, 366, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E3 sinh vi\u00EAn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(34, 90, 128, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(34, 156, 128, 46);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("L\u1EDBp");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(34, 221, 128, 46);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("\u0110i\u1EC3m trung b\u00ECnh");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(34, 277, 128, 46);
		contentPane.add(lblNewLabel_1_3);
		
		textma = new JTextField();
		textma.setBounds(211, 97, 265, 37);
		contentPane.add(textma);
		textma.setColumns(10);
		
		textname = new JTextField();
		textname.setColumns(10);
		textname.setBounds(211, 156, 265, 37);
		contentPane.add(textname);
		
		textclass = new JTextField();
		textclass.setColumns(10);
		textclass.setBounds(211, 221, 265, 37);
		contentPane.add(textclass);
		
		textdiem = new JTextField();
		textdiem.setColumns(10);
		textdiem.setBounds(211, 284, 265, 37);
		contentPane.add(textdiem);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed (ActionEvent e) {
		        Student st=new Student();
		        if(textma.getText().equals("")) {
		        	JOptionPane.showMessageDialog(null, "Không để trống mã sinh viên! ");
		        }else {
		        	st.setMasv(textma.getText());
		        }
		        st.setHoTen(textname.getText());
		        st.setLop(textclass.getText());
		        st.setDiem(Float.parseFloat (textdiem.getText()));
		        ConnectMysqlExample.insert(st);
		        JOptionPane.showMessageDialog(null, "Save Success! ");
		    }
		});
		btnNewButton.setBounds(52, 383, 105, 46);
		contentPane.add(btnNewButton);
		
		JButton btnShow = new JButton("Show");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShow.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed (ActionEvent e) {
		    	tableModel = (DefaultTableModel) table.getModel();
		    	showStudent();
		    }
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRefresh.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed (ActionEvent e) {
		    	textma.setText("");
		    	textname.setText("");
		    	textclass.setText("");
		    	textdiem.setText("");
		    }
		});
		btnRefresh.setBounds(371, 383, 105, 46);
		contentPane.add(btnRefresh);
		
		btnShow.setBounds(211, 383, 105, 46);
		contentPane.add(btnShow);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(534, 102, 503, 287);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 sinh vi\u00EAn", "H\u1ECD v\u00E0 t\u00EAn", "L\u1EDBp", "\u0110i\u1EC3m"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(305);
		table.getColumnModel().getColumn(2).setPreferredWidth(256);
		table.getColumnModel().getColumn(3).setPreferredWidth(87);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
	}
	private void showStudent(){
	    List<Student> studentList = ConnectMysqlExample.getStudentList();
	    tableModel.setRowCount (0);
	    studentList.forEach((student) -> {
	        tableModel.addRow(new Object [] {
	                student.getMasv(),
	                student.getHoTen(),
	                student.getLop(),
	                student.getDiem()
	               });
	    });
	}
}
