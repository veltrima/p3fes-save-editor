package saveEditor;

/* TODO:
 * Test
 * Check what happens with blank name data.
 */

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Button;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.JProgressBar;

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
	
	private Color personaOrange = new java.awt.Color (255, 197, 74);
	private Color personaNavy = new java.awt.Color (0, 59, 88);
	private Color personaBlue = new java.awt.Color(36, 149, 255);
	private Color personaLightBlue = new java.awt.Color(107, 174, 255);
	private Color personaCyan = new java.awt.Color(99, 199, 249);
	private Color personaLightGrey = new java.awt.Color(186, 188, 185);
	private Color personaDarkGrey = new java.awt.Color(60, 60, 60);
	private JPanel playerFieldPanel;
	private JPanel socialStatsPanel;
	private JLabel label_1;
	private JLabel label_2;
	private JSpinner spinnerAca;
	private JLabel label_3;
	private JSpinner spinnerCha;
	private JSpinner spinnerCou;
	private JPanel itemPanel;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField txtYen;
	private JTextField txtPlumes;
	private JLabel lblItems;
	private JLabel lblFlags;
	private JPanel flagPanel;
	private JPanel underline;
	private JPanel panel;
	private JLabel lblForPs;
	private JLabel lblGithubcomveltrima;
	
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
		// TODO: Get this path sorted out
		frmPfesSaveEditor.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\matth\\git\\p3fes-save-editor\\resources\\editor-icon.png"));

		frmPfesSaveEditor.setBackground(Color.ORANGE);
		frmPfesSaveEditor.getContentPane().setBackground(personaBlue);
		frmPfesSaveEditor.setTitle("P3:FES Save Editor");
		frmPfesSaveEditor.setBounds(100, 100, 600, 769);
		frmPfesSaveEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPfesSaveEditor.getContentPane().setLayout(null);
		
		SpinnerModel acaModel = new SpinnerNumberModel(1, 1, 6, 1);
		SpinnerModel chaModel = new SpinnerNumberModel(1, 1, 6, 1);
		SpinnerModel couModel = new SpinnerNumberModel(1, 1, 6, 1);
		SpinnerModel revModel = new SpinnerNumberModel(0, 0, 4, 1);
		
		/*
		MaskFormatter nameFormat = createFormatter("********");
		nameFormat.setValidCharacters(" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		nameFormat.setPlaceholderCharacter(' ');
		MaskFormatter levelFormat = createFormatter("#**");
		levelFormat.setValidCharacters(" 0123456789");
		levelFormat.setPlaceholderCharacter(' ');
		MaskFormatter expFormat = createFormatter("#******");
		expFormat.setValidCharacters("0123456789");
		expFormat.setPlaceholderCharacter(' ');
		MaskFormatter yenFormat = createFormatter("#******");
		yenFormat.setValidCharacters("0123456789");
		yenFormat.setPlaceholderCharacter(' ');
		MaskFormatter plumesFormat = createFormatter("#**");
		plumesFormat.setValidCharacters("0123456789");
		plumesFormat.setPlaceholderCharacter(' '); */
		
		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnImport.setToolTipText("Import save file.");
		btnImport.setBounds(333, 43, 108, 30);
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
					fr = new FileReader(filename, fd.getDirectory());
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
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(personaNavy);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFirstName.setBounds(15, 22, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblFirstName);
		
		lblItems = new JLabel("Items");
		lblItems.setForeground(new Color(0, 59, 88));
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblItems.setBounds(333, 263, 126, 20);
		frmPfesSaveEditor.getContentPane().add(lblItems);
		
		lblFlags = new JLabel("Flags");
		lblFlags.setForeground(new Color(0, 59, 88));
		lblFlags.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFlags.setBounds(333, 395, 126, 20);
		frmPfesSaveEditor.getContentPane().add(lblFlags);
		frmPfesSaveEditor.getContentPane().add(btnImport);
			
		JButton btnExport = new JButton("Export");
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExport.setToolTipText("Export to save file.");
		btnExport.setBounds(452, 44, 108, 29);
		btnExport.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (currentSave != null && fr != null) {
		    		try {
		    			currentSave.writeToByteArray();
						fr.writeFile(currentSave.getRaw());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("nooooo");
						e1.printStackTrace();
					}
		    	}	  
		    }
		});
		frmPfesSaveEditor.getContentPane().add(btnExport);
		
		// Player name
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFirstName.setBounds(15, 46, 120, 26);
		frmPfesSaveEditor.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLastName.setColumns(10);
		txtLastName.setBounds(179, 46, 120, 26);
		frmPfesSaveEditor.getContentPane().add(txtLastName);
		
		JButton btnMatchAll = new JButton("Set Party to MC");
		btnMatchAll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMatchAll.setToolTipText("Sets the level and exp of all party members to be that of the player.");
		btnMatchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentSave != null) {
					currentSave.setToMC();
					repopFields();
				}	
			}
		});
		btnMatchAll.setBounds(120, 565, 192, 30);
		frmPfesSaveEditor.getContentPane().add(btnMatchAll);
		
		JButton btnMaxAll = new JButton("Max All");
		btnMaxAll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMaxAll.setBounds(120, 530, 192, 30);
		btnMaxAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentSave != null) {
					currentSave.setMax();
					repopFields();
				}
			}
		});
		frmPfesSaveEditor.getContentPane().add(btnMaxAll);
		
		flagPanel = new JPanel();
		flagPanel.setBorder(null);
		flagPanel.setLayout(null);
		flagPanel.setBackground(new Color(107, 174, 255));
		flagPanel.setBounds(337, 419, 223, 88);
		frmPfesSaveEditor.getContentPane().add(flagPanel);
		
		label = new JLabel("(11/06, 11/11, 11/14, 11/22)");
		label.setForeground(personaNavy);
		label.setBounds(15, 52, 192, 20);
		flagPanel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblRevivalFlags = new JLabel("Revival Flags");
		lblRevivalFlags.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRevivalFlags.setForeground(personaNavy);
		lblRevivalFlags.setBounds(15, 17, 142, 20);
		flagPanel.add(lblRevivalFlags);
		
		spinnerRev = new JSpinner(revModel);
		spinnerRev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerRev.setBounds(158, 16, 50, 25);
		flagPanel.add(spinnerRev);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setForeground(personaNavy);
		lblLevel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLevel.setBounds(110, 103, 48, 20);
		frmPfesSaveEditor.getContentPane().add(lblLevel);
		
		JLabel lblExp = new JLabel("Exp");
		lblExp.setForeground(personaNavy);
		lblExp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblExp.setBounds(170, 103, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblExp);
		
		JLabel lblUltPersona = new JLabel("Ult.");
		lblUltPersona.setForeground(personaNavy);
		lblUltPersona.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUltPersona.setBounds(280, 103, 35, 20);
		frmPfesSaveEditor.getContentPane().add(lblUltPersona);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(personaNavy);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLastName.setBounds(179, 22, 120, 20);
		frmPfesSaveEditor.getContentPane().add(lblLastName);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setToolTipText("Updates the save file object. Required before exporting.");
		btnUpdate.setBounds(337, 530, 223, 65);
		btnUpdate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (currentSave != null) {
		    		updateSaveFile();
		    		repopFields();
		    	}
		    }
		});
		frmPfesSaveEditor.getContentPane().add(btnUpdate);
		
		// TODO: Why more than 8 chars for name allowed?
		
		playerFieldPanel = new JPanel();
		playerFieldPanel.setBackground(personaNavy);
		playerFieldPanel.setBounds(110, 120, 212, 485);
		frmPfesSaveEditor.getContentPane().add(playerFieldPanel);
		playerFieldPanel.setLayout(null);
		
		txtMclevel = new JTextField();
		txtMclevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMclevel.setBounds(10, 10, 50, 25);
		playerFieldPanel.add(txtMclevel);
		txtMclevel.setColumns(10);
		
		txtMcexp = new JTextField();
		txtMcexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMcexp.setBounds(70, 10, 100, 25);
		playerFieldPanel.add(txtMcexp);
		txtMcexp.setColumns(10);
		
		txtJlevel = new JTextField();
		txtJlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtJlevel.setBounds(10, 50, 50, 25);
		playerFieldPanel.add(txtJlevel);
		txtJlevel.setColumns(10);
		
		txtJexp = new JTextField();
		txtJexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtJexp.setBounds(70, 50, 100, 25);
		playerFieldPanel.add(txtJexp);
		txtJexp.setColumns(10);
		
		checkJult = new JCheckBox("");
		checkJult.setBounds(180, 50, 25, 25);
		playerFieldPanel.add(checkJult);
		checkJult.setBackground(personaNavy);
		
		txtYlevel = new JTextField();
		txtYlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtYlevel.setBounds(10, 90, 50, 25);
		playerFieldPanel.add(txtYlevel);
		txtYlevel.setColumns(10);
		
		txtYexp = new JTextField();
		txtYexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtYexp.setBounds(70, 90, 100, 25);
		playerFieldPanel.add(txtYexp);
		txtYexp.setColumns(10);
		
		checkYult = new JCheckBox("");
		checkYult.setBounds(180, 90, 25, 25);
		playerFieldPanel.add(checkYult);
		checkYult.setBackground(personaNavy);
		
		txtAlevel = new JTextField();
		txtAlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAlevel.setBounds(10, 130, 50, 25);
		playerFieldPanel.add(txtAlevel);
		txtAlevel.setColumns(10);
		
		txtAexp = new JTextField();
		txtAexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAexp.setBounds(70, 130, 100, 25);
		playerFieldPanel.add(txtAexp);
		txtAexp.setColumns(10);
		
		checkAult = new JCheckBox("");
		checkAult.setBounds(180, 130, 25, 25);
		playerFieldPanel.add(checkAult);
		checkAult.setBackground(personaNavy);
		
		txtMlevel = new JTextField();
		txtMlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMlevel.setBounds(10, 170, 50, 25);
		playerFieldPanel.add(txtMlevel);
		txtMlevel.setColumns(10);
		
		txtMexp = new JTextField();
		txtMexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMexp.setBounds(70, 170, 100, 25);
		playerFieldPanel.add(txtMexp);
		txtMexp.setColumns(10);
		
		checkMult = new JCheckBox("");
		checkMult.setBounds(180, 170, 25, 25);
		playerFieldPanel.add(checkMult);
		checkMult.setBackground(personaNavy);
		
		txtFlevel = new JTextField();
		txtFlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFlevel.setBounds(10, 210, 50, 25);
		playerFieldPanel.add(txtFlevel);
		txtFlevel.setColumns(10);
		
		txtFexp = new JTextField();
		txtFexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFexp.setBounds(70, 210, 100, 25);
		playerFieldPanel.add(txtFexp);
		txtFexp.setColumns(10);
		
		checkFult = new JCheckBox("");
		checkFult.setForeground(new Color(0, 0, 0));
		checkFult.setBounds(180, 210, 25, 25);
		playerFieldPanel.add(checkFult);
		checkFult.setBackground(personaNavy);
		
		txtKlevel = new JTextField();
		txtKlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKlevel.setBounds(10, 250, 50, 25);
		playerFieldPanel.add(txtKlevel);
		txtKlevel.setColumns(10);
		
		txtKexp = new JTextField();
		txtKexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKexp.setBounds(70, 250, 100, 25);
		playerFieldPanel.add(txtKexp);
		txtKexp.setColumns(10);
		
		checkKult = new JCheckBox("");
		checkKult.setBounds(180, 250, 25, 25);
		playerFieldPanel.add(checkKult);
		checkKult.setBackground(personaNavy);
		
		txtAilevel = new JTextField();
		txtAilevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAilevel.setBounds(10, 290, 50, 25);
		playerFieldPanel.add(txtAilevel);
		txtAilevel.setColumns(10);
		
		txtAiexp = new JTextField();
		txtAiexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAiexp.setBounds(70, 290, 100, 25);
		playerFieldPanel.add(txtAiexp);
		txtAiexp.setColumns(10);
		
		checkAiult = new JCheckBox("");
		checkAiult.setBounds(180, 290, 25, 25);
		playerFieldPanel.add(checkAiult);
		checkAiult.setBackground(personaNavy);
		
		txtKolevel = new JTextField();
		txtKolevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKolevel.setBounds(10, 330, 50, 25);
		playerFieldPanel.add(txtKolevel);
		txtKolevel.setColumns(10);
		
		txtKoexp = new JTextField();
		txtKoexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKoexp.setBounds(70, 330, 100, 25);
		playerFieldPanel.add(txtKoexp);
		txtKoexp.setColumns(10);
		
		txtSlevel = new JTextField();
		txtSlevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSlevel.setBounds(10, 370, 50, 25);
		playerFieldPanel.add(txtSlevel);
		txtSlevel.setColumns(10);
		
		txtSexp = new JTextField();
		txtSexp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSexp.setBounds(70, 370, 100, 25);
		playerFieldPanel.add(txtSexp);
		txtSexp.setColumns(10);
		
		JPanel partyNamePanel = new JPanel();
		partyNamePanel.setBorder(null);
		partyNamePanel.setBackground(personaOrange);
		partyNamePanel.setBounds(0, 120, 110, 485);
		frmPfesSaveEditor.getContentPane().add(partyNamePanel);
		partyNamePanel.setLayout(null);
		
		
		// Characters
		// MC
		lblMc = new JLabel("MC");
		lblMc.setForeground(personaNavy);
		lblMc.setBounds(15, 15, 73, 20);
		partyNamePanel.add(lblMc);
		lblMc.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Junpei
		lblJunpei = new JLabel("Junpei");
		lblJunpei.setForeground(personaNavy);
		lblJunpei.setBounds(15, 55, 73, 20);
		partyNamePanel.add(lblJunpei);
		lblJunpei.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Yukari
		lblYukari = new JLabel("Yukari");
		lblYukari.setForeground(personaNavy);
		lblYukari.setBounds(15, 95, 73, 20);
		partyNamePanel.add(lblYukari);
		lblYukari.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Akihiko
		lblAkihiko = new JLabel("Akihiko");
		lblAkihiko.setForeground(personaNavy);
		lblAkihiko.setBounds(15, 135, 73, 20);
		partyNamePanel.add(lblAkihiko);
		lblAkihiko.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Mitsuru
		lblMitsuru = new JLabel("Mitsuru");
		lblMitsuru.setForeground(personaNavy);
		lblMitsuru.setBounds(15, 175, 73, 20);
		partyNamePanel.add(lblMitsuru);
		lblMitsuru.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Fuuka 
		lblFuuka = new JLabel("Fuuka");
		lblFuuka.setForeground(personaNavy);
		lblFuuka.setBounds(15, 215, 73, 20);
		partyNamePanel.add(lblFuuka);
		lblFuuka.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Ken
		lblKen = new JLabel("Ken");
		lblKen.setForeground(personaNavy);
		lblKen.setBounds(15, 255, 73, 20);
		partyNamePanel.add(lblKen);
		lblKen.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Aigis
		lblAigis = new JLabel("Aigis");
		lblAigis.setForeground(personaNavy);
		lblAigis.setBounds(15, 295, 73, 20);
		partyNamePanel.add(lblAigis);
		lblAigis.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Shinjiro
		lblShinjiro = new JLabel("Shinjiro");
		lblShinjiro.setForeground(personaNavy);
		lblShinjiro.setBounds(15, 375, 73, 20);
		partyNamePanel.add(lblShinjiro);
		lblShinjiro.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Koromaru
		lblKoromaru = new JLabel("Koromaru");
		lblKoromaru.setForeground(personaNavy);
		lblKoromaru.setBounds(15, 335, 90, 20);
		partyNamePanel.add(lblKoromaru);
		lblKoromaru.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		socialStatsPanel = new JPanel();
		socialStatsPanel.setBorder(null);
		socialStatsPanel.setBackground(personaLightBlue);
		socialStatsPanel.setBounds(337, 127, 223, 123);
		frmPfesSaveEditor.getContentPane().add(socialStatsPanel);
		socialStatsPanel.setLayout(null);
		
		label_1 = new JLabel("Academics");
		label_1.setForeground(personaNavy);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(15, 15, 99, 20);
		socialStatsPanel.add(label_1);
		
		spinnerAca = new JSpinner(acaModel);
		spinnerAca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerAca.setBounds(158, 15, 50, 25);
		socialStatsPanel.add(spinnerAca);
		
		label_2 = new JLabel("Charm");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setForeground(personaNavy);
		label_2.setBounds(15, 50, 59, 20);
		socialStatsPanel.add(label_2);
		
		spinnerCha = new JSpinner(chaModel);
		spinnerCha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCha.setBounds(158, 50, 50, 25);
		socialStatsPanel.add(spinnerCha);
		
		label_3 = new JLabel("Courage");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setForeground(personaNavy);
		label_3.setBounds(15, 85, 80, 20);
		socialStatsPanel.add(label_3);
		
		spinnerCou = new JSpinner(couModel);
		spinnerCou.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerCou.setBounds(158, 85, 50, 25);
		socialStatsPanel.add(spinnerCou);
		
		JLabel lblSocialStats = new JLabel("Social Stats");
		lblSocialStats.setForeground(personaNavy);
		lblSocialStats.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSocialStats.setBounds(333, 103, 126, 20);
		frmPfesSaveEditor.getContentPane().add(lblSocialStats);
		
		itemPanel = new JPanel();
		itemPanel.setBorder(null);
		itemPanel.setBackground(personaLightBlue);
		itemPanel.setBounds(337, 287, 223, 88);
		frmPfesSaveEditor.getContentPane().add(itemPanel);
		itemPanel.setLayout(null);
		
		label_4 = new JLabel("Yen");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setForeground(personaNavy);
		label_4.setBounds(15, 17, 27, 20);
		itemPanel.add(label_4);
		
		label_5 = new JLabel("Plumes");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setForeground(personaNavy);
		label_5.setBounds(15, 54, 51, 20);
		itemPanel.add(label_5);
		
		txtYen = new JTextField();
		txtYen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtYen.setBounds(108, 15, 100, 25);
		txtYen.setColumns(10);
		itemPanel.add(txtYen);
		
		txtPlumes = new JTextField();
		txtPlumes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlumes.setBounds(108, 50, 100, 25);
		txtPlumes.setColumns(10);
		itemPanel.add(txtPlumes);
		
		underline = new JPanel();
		underline.setBorder(null);
		underline.setBackground(personaCyan);
		underline.setBounds(15, 37, 120, 5);
		frmPfesSaveEditor.getContentPane().add(underline);
		underline.setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(99, 199, 249));
		panel.setBounds(179, 37, 120, 5);
		frmPfesSaveEditor.getContentPane().add(panel);
		
		JPanel creditPanel = new JPanel();
		creditPanel.setBorder(null);
		creditPanel.setLayout(null);
		creditPanel.setBackground(new Color(107, 174, 255));
		creditPanel.setBounds(15, 626, 545, 70);
		frmPfesSaveEditor.getContentPane().add(creditPanel);
		
		JLabel lblPersonaFes = new JLabel("Persona 3: FES Save Editor (PS2/PS3) by Matthew Taillefer Veltri");
		lblPersonaFes.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblPersonaFes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonaFes.setBounds(0, 5, 539, 20);
		creditPanel.add(lblPersonaFes);
		
		lblForPs = new JLabel("Released under GPL-3.0");
		lblForPs.setHorizontalAlignment(SwingConstants.CENTER);
		lblForPs.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblForPs.setBounds(0, 25, 539, 20);
		creditPanel.add(lblForPs);
		
		lblGithubcomveltrima = new JLabel("github.com/veltrima");
		lblGithubcomveltrima.setHorizontalAlignment(SwingConstants.CENTER);
		lblGithubcomveltrima.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblGithubcomveltrima.setBounds(0, 45, 539, 20);
		creditPanel.add(lblGithubcomveltrima);
		
		
	}
	
	private void repopFields() {
		HashMap<String, PartyMember> PartyMemberMap = currentSave.getPartyMemberMap();
		
		txtFirstName.setText(currentSave.getPlayerFirstName());
		txtLastName.setText(currentSave.getPlayerLastName());
		
		txtMclevel.setText(Integer.toString(currentSave.getPlayerLevel()));
		txtMcexp.setText(Integer.toString(currentSave.getPlayerExp()));
		
		txtJlevel.setText(Integer.toString(PartyMemberMap.get("junpei").getLevel()));
		txtYlevel.setText(Integer.toString(PartyMemberMap.get("yukari").getLevel()));
		txtAlevel.setText(Integer.toString(PartyMemberMap.get("akihiko").getLevel()));
		txtMlevel.setText(Integer.toString(PartyMemberMap.get("mitsuru").getLevel()));
		txtFlevel.setText(Integer.toString(PartyMemberMap.get("fuuka").getLevel())); 
		txtAilevel.setText(Integer.toString(PartyMemberMap.get("aigis").getLevel()));
		txtKlevel.setText(Integer.toString(PartyMemberMap.get("ken").getLevel()));
		txtKolevel.setText(Integer.toString(PartyMemberMap.get("koromaru").getLevel()));
		txtSlevel.setText(Integer.toString(PartyMemberMap.get("shinjiro").getLevel())); 
		
		txtJexp.setText(Integer.toString(PartyMemberMap.get("junpei").getExp()));
		txtYexp.setText(Integer.toString(PartyMemberMap.get("yukari").getExp()));
		txtAexp.setText(Integer.toString(PartyMemberMap.get("akihiko").getExp()));
		txtMexp.setText(Integer.toString(PartyMemberMap.get("mitsuru").getExp()));
		txtFexp.setText(Integer.toString(PartyMemberMap.get("fuuka").getExp())); 
		txtAiexp.setText(Integer.toString(PartyMemberMap.get("aigis").getExp()));
		txtKexp.setText(Integer.toString(PartyMemberMap.get("ken").getExp()));
		txtKoexp.setText(Integer.toString(PartyMemberMap.get("koromaru").getExp()));
		txtSexp.setText(Integer.toString(PartyMemberMap.get("shinjiro").getExp())); 
		
		checkJult.setSelected(PartyMemberMap.get("junpei").getHasUlt());
		checkYult.setSelected(PartyMemberMap.get("yukari").getHasUlt());
		checkAult.setSelected(PartyMemberMap.get("akihiko").getHasUlt());
		checkMult.setSelected(PartyMemberMap.get("mitsuru").getHasUlt());
		checkFult.setSelected(PartyMemberMap.get("fuuka").getHasUlt());
		checkKult.setSelected(PartyMemberMap.get("ken").getHasUlt());
		checkAiult.setSelected(PartyMemberMap.get("aigis").getHasUlt());
		
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
			spinnerRev.setValue(0);
		}
	}
	
	// Updates the save file with data from the form.
	private void updateSaveFile () {
		int currLevel, currExp;
		String currParty;
		
		currentSave.setPlayerFirstName(ensureLetter(txtFirstName.getText()));
		currentSave.setPlayerLastName(ensureLetter(txtLastName.getText()));
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtMclevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtMcexp.getText())));
		
		if (currentSave.getPlayerLevel() != currLevel) {
			currentSave.setPlayerLevel(currLevel);
			currentSave.setPlayerExp(currentSave.expForLevel(currLevel));
		} else if (currentSave.getPlayerExp() != currExp) {
			currentSave.setPlayerExp(currExp);
			currentSave.setPlayerLevel(currentSave.levelForExp(currExp));
		}
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtJlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtJexp.getText())));
		currParty = "junpei";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtYlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtYexp.getText())));
		currParty = "yukari";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtAlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtAexp.getText())));
		currParty = "akihiko";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtMlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtMexp.getText())));
		currParty = "mitsuru";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtKlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtKexp.getText())));
		currParty = "ken";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtKolevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtKoexp.getText())));
		currParty = "koromaru";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtAilevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtAiexp.getText())));
		currParty = "aigis";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtFlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtFexp.getText())));
		currParty = "fuuka";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currLevel = scrubLevel(Integer.valueOf(ensureInt(txtSlevel.getText())));
		currExp = scrubExp(Integer.valueOf(ensureInt(txtSexp.getText())));
		currParty = "shinjiro";
		updatePartyLevelAndExp(currParty, currLevel, currExp);
		
		currentSave.updateCharacterSkills();
		currentSave.updatePartyAttributes();
		
		currentSave.updateUltFlagMap("junpei", checkJult.isSelected());
		currentSave.updateUltFlagMap("yukari", checkYult.isSelected());
		currentSave.updateUltFlagMap("akihiko", checkAult.isSelected());
		currentSave.updateUltFlagMap("mitsuru", checkMult.isSelected());
		currentSave.updateUltFlagMap("fuuka", checkFult.isSelected());
		currentSave.updateUltFlagMap("ken", checkKult.isSelected());
		currentSave.updateUltFlagMap("aigis", checkAiult.isSelected());	
		currentSave.updatePersonaFlags();
			
		try {
			spinnerRev.commitEdit();
			spinnerAca.commitEdit();
			spinnerCha.commitEdit();
			spinnerCou.commitEdit();
		} catch ( java.text.ParseException e ) { }
		
		
		if ((Integer) spinnerAca.getValue() != currentSave.getAcademicsLevel()) {
			currentSave.setAcademicsLevel((Integer) spinnerAca.getValue());
		}
		if ((Integer) spinnerCha.getValue() != currentSave.getCharmLevel()) {
			currentSave.setCharmLevel((Integer) spinnerCha.getValue());
		}
		if ((Integer) spinnerCou.getValue() != currentSave.getCourageLevel()) {
			currentSave.setCourageLevel((Integer) spinnerCou.getValue());
		}

		int newYen = Integer.valueOf(ensureInt(txtYen.getText()));
		int newPlumes = Integer.valueOf(ensureInt(txtPlumes.getText()));
		
		if (newYen > 9999999) {
			newYen = 9999999;
		}
		
		if (newPlumes > 99) {
			newPlumes = 99;
		}
		
		currentSave.setYen(newYen);
		currentSave.setPlumes(newPlumes);
		
		if ((Integer) spinnerRev.getValue() == 0) {
			currentSave.setRevivalFlag("00");
		} else if ((Integer) spinnerRev.getValue() == 1) {
			currentSave.setRevivalFlag("11");
		} else if ((Integer) spinnerRev.getValue() == 2) {
			currentSave.setRevivalFlag("33");
		} else if ((Integer) spinnerRev.getValue() == 3) {
			currentSave.setRevivalFlag("77");
		} else if ((Integer) spinnerRev.getValue() == 4) {
			currentSave.setRevivalFlag("ff");
		}
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	// Ensures level is between 1 and 99
	private int scrubLevel (int level) {
		if (level < 1) {
			return 1;
		} else if (level > 99) {
			return 99;
		} else {
			return level;
		}
	}
	
	private int scrubExp (int exp) {
		int MAX_EXP = 1358428;
		if (exp < 0) {
			return 0;
		} else if (exp > MAX_EXP) {
			return MAX_EXP;
		} else {
			return exp;
		}
	}
	
	// Assumes scrubbed level and exp.
	private void updatePartyLevelAndExp (String name, int level, int exp) {
		if (currentSave != null) {
			HashMap<String, PartyMember> partyMemberMap = currentSave.getPartyMemberMap();
			
			int prevLevel = partyMemberMap.get(name).getLevel();
			int prevExp = partyMemberMap.get(name).getExp();
			
			if (currentSave != null) {
				if (prevLevel != level) {
					partyMemberMap.get(name).setLevel(level);
					partyMemberMap.get(name).setExp(currentSave.expForLevel(level));
				} else if (prevExp != exp) {
					currentSave.setExp(name, exp);
					partyMemberMap.get(name).setLevel(currentSave.levelForExp(exp));
				}
			}
		}
	}
	
	// Ensures s only includes letters.
	private String ensureLetter (String s) {
		return s.replaceAll("[^A-Za-z]+", "");
	}
	
	// Ensures s only includes digits.
	private String ensureInt(String s) {
		s = s.replaceAll("\\D+","");
		if (s.length() == 0) {
			s = "0";
		} else if (s.length() >= 9) {
			s = s.substring(0, 9);
		}
		return s;
		
	}
}
