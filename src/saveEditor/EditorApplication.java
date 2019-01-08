package saveEditor;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EditorApplication {

	private JFrame frmPfesSaveEditor;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JLabel lblJunpei;
	private JTextField txtJlevel;
	private JTextField txtJexp;
	private JLabel lblAkihiko;
	private JTextField txtAlevel;
	private JTextField txtAexp;
	private JLabel lblMitsuru;
	private JTextField txtMlevel;
	private JTextField txtMexp;
	private JLabel lblFuuka;
	private JTextField txtFlevel;
	private JTextField txtFexp;
	private JLabel lblKen;
	private JTextField txtKlevel;
	private JTextField txtKexp;
	private JLabel lblAigis;
	private JTextField txtAilevel;
	private JTextField txtAiexp;
	private JLabel lblYukari;
	private JTextField txtYlevel;
	private JTextField txtYexp;
	private JLabel lblShinjiro;
	private JTextField txtSlevel;
	private JTextField txtSexp;
	private JLabel lblKoromaru;
	private JTextField txtKolevel;
	private JTextField txtKoexp;
	private JLabel lblMc;
	private JTextField txtMclevel;
	private JTextField txtMcexp;
	private JLabel lblAcademics;
	private JLabel lblCharm;
	private JLabel lblCourage;
	private JSpinner spinnerAca;
	private JSpinner spinnerCha;
	private JSpinner spinnerCou;
	private JTextField txtYen;
	private JTextField txtPlumes;
	private JLabel label;
	private JSpinner spinnerRev;
	private JCheckBox checkJult;
	private JCheckBox checkYult;
	private JCheckBox checkAult;
	private JCheckBox checkMult;
	private JCheckBox checkFult;
	private JCheckBox checkKult;
	private JCheckBox checkAiult;
	
	private FileReader fr;
	private SaveFile currentSave;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorApplication window = new EditorApplication();
					window.frmPfesSaveEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditorApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPfesSaveEditor = new JFrame();
		frmPfesSaveEditor.setTitle("P3:FES Save Editor");
		frmPfesSaveEditor.setBounds(100, 100, 574, 620);
		frmPfesSaveEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPfesSaveEditor.getContentPane().setLayout(null);
		
		JButton btnImport = new JButton("Import");
		btnImport.setToolTipText("Import save file.");
		btnImport.setBounds(343, 16, 93, 29);
		btnImport.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	FileDialog fd = new FileDialog(frmPfesSaveEditor, "Choose a file", FileDialog.LOAD);
				//fd.setDirectory("C:\\");
				fd.setFile("*.psu");
				fd.setVisible(true);
				String filename = fd.getFile();
				if (filename == null) {
					//System.out.println("You cancelled the choice");
				}
				else {
					//System.out.println("You chose " + filename);
					filename = fd.getDirectory() + fd.getFile();
					fr = new FileReader(filename);
					try {
						currentSave = new SaveFile(fr.readFile());
						repopFields();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}		  
		    }
		});
		frmPfesSaveEditor.getContentPane().add(btnImport);
			
		JButton btnExport = new JButton("Export");
		btnExport.setToolTipText("Export to save file.");
		btnExport.setBounds(442, 16, 93, 29);
		frmPfesSaveEditor.getContentPane().add(btnExport);
		
		// Player name
		txtFirstName = new JTextField();
		txtFirstName.setBounds(15, 46, 120, 26);
		frmPfesSaveEditor.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(179, 46, 120, 26);
		frmPfesSaveEditor.getContentPane().add(txtLastName);
		
		
		// Characters
		// MC
		lblMc = new JLabel("MC");
		lblMc.setBounds(15, 128, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblMc);
		
		txtMclevel = new JTextField();
		txtMclevel.setColumns(10);
		txtMclevel.setBounds(99, 125, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtMclevel);
		
		txtMcexp = new JTextField();
		txtMcexp.setColumns(10);
		txtMcexp.setBounds(162, 125, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtMcexp);
		
		// Junpei
		lblJunpei = new JLabel("Junpei");
		lblJunpei.setBounds(15, 167, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblJunpei);
		
		txtJlevel = new JTextField();
		txtJlevel.setColumns(10);
		txtJlevel.setBounds(99, 164, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtJlevel);
		
		txtJexp = new JTextField();
		txtJexp.setColumns(10);
		txtJexp.setBounds(162, 164, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtJexp);
		
		// Yukari
		lblYukari = new JLabel("Yukari");
		lblYukari.setBounds(15, 206, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblYukari);
		
		txtYlevel = new JTextField();
		txtYlevel.setBounds(99, 203, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtYlevel);
		txtYlevel.setColumns(10);
		
		txtYexp = new JTextField();
		txtYexp.setBounds(162, 203, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtYexp);
		txtYexp.setColumns(10);
		
		// Akihiko
		lblAkihiko = new JLabel("Akihiko");
		lblAkihiko.setBounds(15, 245, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblAkihiko);
		
		txtAlevel = new JTextField();
		txtAlevel.setColumns(10);
		txtAlevel.setBounds(99, 242, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtAlevel);
		
		txtAexp = new JTextField();
		txtAexp.setColumns(10);
		txtAexp.setBounds(162, 242, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtAexp);
		
		// Mitsuru
		lblMitsuru = new JLabel("Mitsuru");
		lblMitsuru.setBounds(15, 284, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblMitsuru);
		
		txtMlevel = new JTextField();
		txtMlevel.setColumns(10);
		txtMlevel.setBounds(99, 281, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtMlevel);
		
		txtMexp = new JTextField();
		txtMexp.setColumns(10);
		txtMexp.setBounds(162, 281, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtMexp);
		
		// Fuuka 
		lblFuuka = new JLabel("Fuuka");
		lblFuuka.setBounds(15, 323, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblFuuka);
		
		txtFlevel = new JTextField();
		txtFlevel.setColumns(10);
		txtFlevel.setBounds(99, 320, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtFlevel);
		
		txtFexp = new JTextField();
		txtFexp.setColumns(10);
		txtFexp.setBounds(162, 320, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtFexp);
		
		// Ken
		lblKen = new JLabel("Ken");
		lblKen.setBounds(15, 362, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblKen);
		
		txtKlevel = new JTextField();
		txtKlevel.setColumns(10);
		txtKlevel.setBounds(99, 359, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtKlevel);
		
		txtKexp = new JTextField();
		txtKexp.setColumns(10);
		txtKexp.setBounds(162, 359, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtKexp);
		
		// Aigis
		lblAigis = new JLabel("Aigis");
		lblAigis.setBounds(15, 401, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblAigis);
		
		txtAilevel = new JTextField();
		txtAilevel.setColumns(10);
		txtAilevel.setBounds(99, 398, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtAilevel);
		
		txtAiexp = new JTextField();
		txtAiexp.setColumns(10);
		txtAiexp.setBounds(162, 398, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtAiexp);
		
		// Koromaru
		lblKoromaru = new JLabel("Koromaru");
		lblKoromaru.setBounds(15, 484, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblKoromaru);
		
		txtKolevel = new JTextField();
		txtKolevel.setColumns(10);
		txtKolevel.setBounds(99, 481, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtKolevel);
		
		txtKoexp = new JTextField();
		txtKoexp.setColumns(10);
		txtKoexp.setBounds(162, 481, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtKoexp);
		
		// Shinjiro
		lblShinjiro = new JLabel("Shinjiro");
		lblShinjiro.setBounds(15, 440, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblShinjiro);
		
		txtSlevel = new JTextField();
		txtSlevel.setColumns(10);
		txtSlevel.setBounds(99, 437, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtSlevel);
		
		txtSexp = new JTextField();
		txtSexp.setColumns(10);
		txtSexp.setBounds(162, 437, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtSexp);
		
		
		// Social stats
		lblAcademics = new JLabel("Academics");
		lblAcademics.setBounds(343, 131, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblAcademics);
		
		spinnerAca = new JSpinner();
		spinnerAca.setBounds(487, 128, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinnerAca);
		
		lblCharm = new JLabel("Charm");
		lblCharm.setBounds(343, 170, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblCharm);
		
		spinnerCha = new JSpinner();
		spinnerCha.setBounds(487, 170, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinnerCha);
		
		lblCourage = new JLabel("Courage");
		lblCourage.setBounds(343, 209, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblCourage);
		
		spinnerCou = new JSpinner();
		spinnerCou.setBounds(487, 206, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinnerCou);
		
		

		
		
		JLabel lblYen = new JLabel("Yen");
		lblYen.setBounds(343, 283, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblYen);
		
		JLabel lblRevivalPlumes = new JLabel("Plumes");
		lblRevivalPlumes.setBounds(343, 322, 120, 20);
		frmPfesSaveEditor.getContentPane().add(lblRevivalPlumes);
		
		txtYen = new JTextField();
		txtYen.setBounds(454, 278, 81, 26);
		frmPfesSaveEditor.getContentPane().add(txtYen);
		txtYen.setColumns(10);
		
		txtPlumes = new JTextField();
		txtPlumes.setColumns(10);
		txtPlumes.setBounds(454, 317, 81, 26);
		frmPfesSaveEditor.getContentPane().add(txtPlumes);
		
		JButton btnMatchAll = new JButton("Set Party to MC");
		btnMatchAll.setToolTipText("Sets the level and exp of all party members to be that of the player.");
		btnMatchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMatchAll.setBounds(142, 519, 157, 29);
		frmPfesSaveEditor.getContentPane().add(btnMatchAll);
		
		JButton btnMaxAll = new JButton("Max All");
		btnMaxAll.setBounds(15, 519, 115, 29);
		frmPfesSaveEditor.getContentPane().add(btnMaxAll);
		
		JLabel lblRevivalFlags = new JLabel("Revival Flags (of 4)");
		lblRevivalFlags.setBounds(343, 401, 120, 20);
		frmPfesSaveEditor.getContentPane().add(lblRevivalFlags);
		
		spinnerRev = new JSpinner();
		spinnerRev.setBounds(487, 398, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinnerRev);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(99, 89, 48, 20);
		frmPfesSaveEditor.getContentPane().add(lblLevel);
		
		JLabel lblExp = new JLabel("Exp");
		lblExp.setBounds(162, 89, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblExp);
		
		JLabel lblUltPersona = new JLabel("Ult.");
		lblUltPersona.setBounds(272, 89, 35, 20);
		frmPfesSaveEditor.getContentPane().add(lblUltPersona);
		
		checkJult = new JCheckBox("");
		checkJult.setBounds(273, 163, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkJult);
		
		checkYult = new JCheckBox("");
		checkYult.setBounds(273, 202, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkYult);
		
		checkAult = new JCheckBox("");
		checkAult.setBounds(273, 241, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkAult);
		
		checkMult = new JCheckBox("");
		checkMult.setBounds(273, 280, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkMult);
		
		checkFult = new JCheckBox("");
		checkFult.setBounds(273, 319, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkFult);
		
		checkKult = new JCheckBox("");
		checkKult.setBounds(273, 358, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkKult);
		
		checkAiult = new JCheckBox("");
		checkAiult.setBounds(273, 397, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkAiult);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(15, 16, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(179, 16, 93, 20);
		frmPfesSaveEditor.getContentPane().add(lblLastName);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setToolTipText("Updates the save file object. Required before exporting.");
		btnUpdate.setBounds(343, 479, 192, 29);
		frmPfesSaveEditor.getContentPane().add(btnUpdate);
		
		label = new JLabel("(11/06, 11/11, 11/14, 11/22)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(343, 434, 192, 20);
		frmPfesSaveEditor.getContentPane().add(label);
		
		
	}
	
	private void repopFields() {
		HashMap<String, Integer> levelMap = currentSave.getLevelMap();
		HashMap<String, Integer> expMap = currentSave.getExpMap();
		HashMap<String, Boolean> ultFlagMap = currentSave.getUltFlagMap();
		
		txtFirstName.setText(currentSave.getPlayerFirstName());
		txtLastName.setText(currentSave.getPlayerLastName());
		
		txtMclevel.setText(Integer.toString(currentSave.getPlayerLevel()));
		txtMcexp.setText(Integer.toString(currentSave.getPlayerExp()));
		
		txtJlevel.setText(Integer.toString(levelMap.get("junpeiLevel")));
		txtYlevel.setText(Integer.toString(levelMap.get("yukariLevel")));
		txtAlevel.setText(Integer.toString(levelMap.get("akihikoLevel")));
		txtMlevel.setText(Integer.toString(levelMap.get("mitsuruLevel")));
		txtFlevel.setText(Integer.toString(levelMap.get("fuukaLevel"))); 
		txtAilevel.setText(Integer.toString(levelMap.get("aigisLevel")));
		txtKlevel.setText(Integer.toString(levelMap.get("kenLevel")));
		txtKolevel.setText(Integer.toString(levelMap.get("koromaruLevel")));
		txtSlevel.setText(Integer.toString(levelMap.get("shinjiroLevel"))); 
		
		txtJexp.setText(Integer.toString(expMap.get("junpeiExp")));
		txtYexp.setText(Integer.toString(expMap.get("yukariExp")));
		txtAexp.setText(Integer.toString(expMap.get("akihikoExp")));
		txtMexp.setText(Integer.toString(expMap.get("mitsuruExp")));
		txtFexp.setText(Integer.toString(expMap.get("fuukaExp"))); 
		txtAiexp.setText(Integer.toString(expMap.get("aigisExp")));
		txtKexp.setText(Integer.toString(expMap.get("kenExp")));
		txtKoexp.setText(Integer.toString(expMap.get("koromaruExp")));
		txtSexp.setText(Integer.toString(expMap.get("shinjiroExp"))); 
		
		checkJult.setSelected(ultFlagMap.get("junpei"));
		checkYult.setSelected(ultFlagMap.get("yukari"));
		checkAult.setSelected(ultFlagMap.get("akihiko"));
		checkMult.setSelected(ultFlagMap.get("mitsuru"));
		checkFult.setSelected(ultFlagMap.get("fuuka"));
		checkKult.setSelected(ultFlagMap.get("ken"));
		checkAiult.setSelected(ultFlagMap.get("aigis"));
		
		spinnerAca.setValue(currentSave.getAcademicsLevel());
		spinnerCha.setValue(currentSave.getCharmLevel());
		spinnerCou.setValue(currentSave.getCourageLevel());
		
		txtYen.setText(Integer.toString(currentSave.getYen()));
		txtPlumes.setText(Integer.toString(currentSave.getPlumes()));
		
		String flag = currentSave.getRevivalFlag();
		if (flag.equals("11")) {
			spinnerRev.setValue(1);
		} else if (flag.equals("33")) {
			spinnerRev.setValue(2);
		} else if (flag.equals("77")) {
			spinnerRev.setValue(3);
		} else if (flag.equals("ff")) {
			spinnerRev.setValue(4);
		} else {
			spinnerRev.setValue(8);
		}
	}
}
