package lz77;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class fram1 {

	private JFrame frmLz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fram1 window = new fram1();
					window.frmLz.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public fram1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLz = new JFrame();
		frmLz.setTitle("lz77 ");
		frmLz.setBounds(100, 100, 661, 418);
		frmLz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLz.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select where is the compressed file OR the file to be decompress :");
		lblNewLabel.setBounds(12, 12, 508, 26);
		frmLz.getContentPane().add(lblNewLabel);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 39, 476, 32);
		frmLz.getContentPane().add(textPane);
		
		final JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(12, 145, 476, 32);
		frmLz.getContentPane().add(textPane_1);
		
		JButton btnNewButton = new JButton("Compress");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					compress tmp = new compress();
					if(textPane.getText().toString().isEmpty() ||textPane_1.getText().toString().isEmpty() ){
						JOptionPane.showMessageDialog(null, "Please enter the data needed");

					}else tmp.proccess(textPane.getText(),textPane_1.getText() );
					
					tmp.showtags();
			}
		});
		btnNewButton.setBounds(24, 286, 131, 67);
		frmLz.getContentPane().add(btnNewButton);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decompress tmp = new decompress();
				if(textPane.getText().toString().isEmpty() ||textPane_1.getText().toString().isEmpty() ){
					JOptionPane.showMessageDialog(null, "Please enter the data needed");

				}else tmp.proccess(textPane.getText(),textPane_1.getText() );
			}
		});
		btnDecompress.setBounds(504, 286, 131, 67);
		frmLz.getContentPane().add(btnDecompress);
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser(new File("/home/"));				
				file.setDialogTitle("select the file to be compressed OR to be decompressed");
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				//FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
				//file.setFileFilter(filter);
				//file.addChoosableFileFilter(filter);
				int ret = file.showDialog(null, "select");
				if(ret == JFileChooser.APPROVE_OPTION){
					File selectedfile = file.getSelectedFile();
					String path = selectedfile.getAbsolutePath();
					textPane.setText(path);
					
				}
			}
		});
		btnNewButton_1.setBounds(518, 46, 117, 25);
		frmLz.getContentPane().add(btnNewButton_1);

		JLabel lblSelectWhereIs = new JLabel("Select where is the file to be Save :");
		lblSelectWhereIs.setBounds(12, 118, 508, 26);
		frmLz.getContentPane().add(lblSelectWhereIs);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser(new File("/home/"));				
				file.setDialogTitle("select where to save it :");
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				int ret = file.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION ){
					File selectedfile = file.getSelectedFile();
					String path = selectedfile.getAbsolutePath();
					textPane_1.setText(path);
					
				}
				
			}
		});
		btnBrowse.setBounds(518, 152, 117, 25);
		frmLz.getContentPane().add(btnBrowse);
	}
}
