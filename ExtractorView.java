import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.HashMap;

public class ExtractorView {

	private JFrame frame;
	private File filepath;
	private String file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtractorView window = new ExtractorView();
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
	public ExtractorView() {
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
		
		final JTextArea textArea = new JTextArea();
		textArea.setRows(10);
		textArea.setColumns(10);
		textArea.setEditable(false);
		textArea.setBounds(100, 100, 600, 400);
		frame.getContentPane().add(textArea);
		
		HashMap<Integer, String> shakespeare = new HashMap<Integer,String>();
		shakespeare.put(0,"OTH.txt" );
		JComboBox playSelection = new JComboBox();
		playSelection.addItem(shakespeare.get(0));
		playSelection.setBounds(100, 50, 350, 30);
		frame.getContentPane().add(playSelection);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playSelection.getSelectedIndex() != 0) {
				extractor extractor = new extractor();
				textArea.append("\r\n");
				textArea.append(extractor.runExtractor(file));
				textArea.append(extractor.runExtractor(shakespeare.get(playSelection.getSelectedIndex())));
				}
			}
		});
		searchButton.setBounds(600, 50, 100, 30);
		frame.getContentPane().add(searchButton);
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				fileChooser.setBounds(40, 128, 618, 397);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					filepath = fileChooser.getSelectedFile();
					file = filepath.getPath().trim();
					textArea.append(file + "opened");
				}
			}
		});
		browseButton.setBounds(485, 50, 100, 30);
		frame.getContentPane().add(browseButton);
		
	
	}
}