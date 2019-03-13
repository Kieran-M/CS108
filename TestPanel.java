import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class TestPanel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPanel window = new TestPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @return 
	 */
	public TestPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox playSelection = new JComboBox();
		playSelection.setBounds(100, 50, 350, 30);
		frame.getContentPane().add(playSelection);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchButton.setBounds(600, 50, 100, 30);
		frame.getContentPane().add(searchButton);
		
		final JTextArea textArea = new JTextArea();
		textArea.setRows(10);
		textArea.setColumns(10);
		textArea.setEditable(false);
		textArea.setBounds(100, 100, 600, 400);
		frame.getContentPane().add(textArea);
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				fileChooser.setBounds(40, 128, 618, 397);
				
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fileChooser.getSelectedFile();
					textArea.append(file.getPath() + "opened");
				}
				
				//frame.getContentPane().add(fileChooser);
				
			}
		});
		browseButton.setBounds(485, 50, 100, 30);
		frame.getContentPane().add(browseButton);
		
	
	}
}
