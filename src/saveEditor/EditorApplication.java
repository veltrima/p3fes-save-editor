package saveEditor;

import java.awt.EventQueue;

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
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EditorApplication {

	private JFrame frmPfesSaveEditor;
	private JTextField txtJlevel;
	private JTextField txtJexp;
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
	private JTextField txtAlevel_1;
	private JTextField txtAexp_1;
	private JLabel label_5;
	private JTextField textField_10;
	private JTextField textField_11;
	private JLabel lblShinjiro;
	private JTextField txtSlevel;
	private JTextField txtAexp_2;
	private JLabel lblMc;
	private JTextField txtMclevel;
	private JTextField txtMcexp;
	private JTextField textField;
	private JLabel lblCharm;
	private JLabel lblCharm_1;
	private JSpinner spinner_1;
	private JLabel lblCourage;
	private JSpinner spinner_2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel label;

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
		frmPfesSaveEditor.setBounds(100, 100, 574, 580);
		frmPfesSaveEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPfesSaveEditor.getContentPane().setLayout(null);
		
		JButton btnImport = new JButton("Import");
		btnImport.setToolTipText("Import save file.");
		btnImport.setBounds(343, 16, 93, 29);
		frmPfesSaveEditor.getContentPane().add(btnImport);
		
		JButton btnExport = new JButton("Export");
		btnExport.setToolTipText("Export to save file.");
		btnExport.setBounds(442, 16, 93, 29);
		frmPfesSaveEditor.getContentPane().add(btnExport);
		
		JLabel lblJunpei = new JLabel("Yukari");
		lblJunpei.setBounds(15, 206, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblJunpei);
		
		txtJlevel = new JTextField();
		txtJlevel.setText("yLevel");
		txtJlevel.setBounds(99, 203, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtJlevel);
		txtJlevel.setColumns(10);
		
		txtJexp = new JTextField();
		txtJexp.setText("yExp");
		txtJexp.setBounds(162, 203, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtJexp);
		txtJexp.setColumns(10);
		
		JLabel lblAkihiko = new JLabel("Akihiko");
		lblAkihiko.setBounds(15, 245, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblAkihiko);
		
		txtAlevel = new JTextField();
		txtAlevel.setText("aLevel");
		txtAlevel.setColumns(10);
		txtAlevel.setBounds(99, 242, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtAlevel);
		
		txtAexp = new JTextField();
		txtAexp.setText("aExp");
		txtAexp.setColumns(10);
		txtAexp.setBounds(162, 242, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtAexp);
		
		lblMitsuru = new JLabel("Mitsuru");
		lblMitsuru.setBounds(15, 284, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblMitsuru);
		
		txtMlevel = new JTextField();
		txtMlevel.setText("mLevel");
		txtMlevel.setColumns(10);
		txtMlevel.setBounds(99, 281, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtMlevel);
		
		txtMexp = new JTextField();
		txtMexp.setText("mExp");
		txtMexp.setColumns(10);
		txtMexp.setBounds(162, 281, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtMexp);
		
		lblFuuka = new JLabel("Fuuka");
		lblFuuka.setBounds(15, 323, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblFuuka);
		
		txtFlevel = new JTextField();
		txtFlevel.setText("fLevel");
		txtFlevel.setColumns(10);
		txtFlevel.setBounds(99, 320, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtFlevel);
		
		txtFexp = new JTextField();
		txtFexp.setText("fExp");
		txtFexp.setColumns(10);
		txtFexp.setBounds(162, 320, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtFexp);
		
		lblKen = new JLabel("Ken");
		lblKen.setBounds(15, 362, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblKen);
		
		txtKlevel = new JTextField();
		txtKlevel.setText("kLevel");
		txtKlevel.setColumns(10);
		txtKlevel.setBounds(99, 359, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtKlevel);
		
		txtKexp = new JTextField();
		txtKexp.setText("kExp");
		txtKexp.setColumns(10);
		txtKexp.setBounds(162, 359, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtKexp);
		
		lblAigis = new JLabel("Aigis");
		lblAigis.setBounds(15, 401, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblAigis);
		
		txtAlevel_1 = new JTextField();
		txtAlevel_1.setText("aiLevel");
		txtAlevel_1.setColumns(10);
		txtAlevel_1.setBounds(99, 398, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtAlevel_1);
		
		txtAexp_1 = new JTextField();
		txtAexp_1.setText("aiExp");
		txtAexp_1.setColumns(10);
		txtAexp_1.setBounds(162, 398, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtAexp_1);
		
		label_5 = new JLabel("Junpei");
		label_5.setBounds(15, 167, 69, 20);
		frmPfesSaveEditor.getContentPane().add(label_5);
		
		textField_10 = new JTextField();
		textField_10.setText("jLevel");
		textField_10.setColumns(10);
		textField_10.setBounds(99, 164, 48, 26);
		frmPfesSaveEditor.getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("jExp");
		textField_11.setColumns(10);
		textField_11.setBounds(162, 164, 100, 26);
		frmPfesSaveEditor.getContentPane().add(textField_11);
		
		lblShinjiro = new JLabel("Shinjiro");
		lblShinjiro.setBounds(15, 440, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblShinjiro);
		
		txtSlevel = new JTextField();
		txtSlevel.setText("sLevel");
		txtSlevel.setColumns(10);
		txtSlevel.setBounds(99, 437, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtSlevel);
		
		txtAexp_2 = new JTextField();
		txtAexp_2.setText("sExp");
		txtAexp_2.setColumns(10);
		txtAexp_2.setBounds(162, 437, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtAexp_2);
		
		lblMc = new JLabel("MC");
		lblMc.setBounds(15, 128, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblMc);
		
		txtMclevel = new JTextField();
		txtMclevel.setText("mcLevel");
		txtMclevel.setColumns(10);
		txtMclevel.setBounds(99, 125, 48, 26);
		frmPfesSaveEditor.getContentPane().add(txtMclevel);
		
		txtMcexp = new JTextField();
		txtMcexp.setText("mcExp");
		txtMcexp.setColumns(10);
		txtMcexp.setBounds(162, 125, 100, 26);
		frmPfesSaveEditor.getContentPane().add(txtMcexp);
		
		textField = new JTextField();
		textField.setBounds(15, 46, 120, 26);
		frmPfesSaveEditor.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblCharm = new JLabel("Academics");
		lblCharm.setBounds(343, 131, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblCharm);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(487, 128, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinner);
		
		lblCharm_1 = new JLabel("Charm");
		lblCharm_1.setBounds(343, 170, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblCharm_1);
		
		spinner_1 = new JSpinner();
		spinner_1.setBounds(487, 170, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinner_1);
		
		lblCourage = new JLabel("Courage");
		lblCourage.setBounds(343, 209, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblCourage);
		
		spinner_2 = new JSpinner();
		spinner_2.setBounds(487, 206, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinner_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 46, 120, 26);
		frmPfesSaveEditor.getContentPane().add(textField_1);
		
		JLabel lblYen = new JLabel("Yen");
		lblYen.setBounds(343, 283, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblYen);
		
		JLabel lblRevivalPlumes = new JLabel("Plumes");
		lblRevivalPlumes.setBounds(343, 322, 120, 20);
		frmPfesSaveEditor.getContentPane().add(lblRevivalPlumes);
		
		textField_2 = new JTextField();
		textField_2.setBounds(454, 278, 81, 26);
		frmPfesSaveEditor.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(454, 317, 81, 26);
		frmPfesSaveEditor.getContentPane().add(textField_3);
		
		JButton btnMatchAll = new JButton("Set Party to MC");
		btnMatchAll.setToolTipText("Sets the level and exp of all party members to be that of the player.");
		btnMatchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMatchAll.setBounds(142, 479, 157, 29);
		frmPfesSaveEditor.getContentPane().add(btnMatchAll);
		
		JButton btnMaxAll = new JButton("Max All");
		btnMaxAll.setBounds(15, 479, 115, 29);
		frmPfesSaveEditor.getContentPane().add(btnMaxAll);
		
		JLabel lblRevivalFlags = new JLabel("Revival Flags");
		lblRevivalFlags.setBounds(343, 401, 100, 20);
		frmPfesSaveEditor.getContentPane().add(lblRevivalFlags);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(487, 398, 48, 26);
		frmPfesSaveEditor.getContentPane().add(spinner_3);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(99, 89, 48, 20);
		frmPfesSaveEditor.getContentPane().add(lblLevel);
		
		JLabel lblExp = new JLabel("Exp");
		lblExp.setBounds(162, 89, 69, 20);
		frmPfesSaveEditor.getContentPane().add(lblExp);
		
		JLabel lblUltPersona = new JLabel("Ult.");
		lblUltPersona.setBounds(272, 89, 35, 20);
		frmPfesSaveEditor.getContentPane().add(lblUltPersona);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setBounds(273, 163, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setBounds(273, 202, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setBounds(273, 241, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setBounds(273, 280, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setBounds(273, 319, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.setBounds(273, 358, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_6);
		
		JCheckBox checkBox_7 = new JCheckBox("");
		checkBox_7.setBounds(273, 397, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_7);
		
		JCheckBox checkBox_8 = new JCheckBox("");
		checkBox_8.setBounds(273, 434, 26, 29);
		frmPfesSaveEditor.getContentPane().add(checkBox_8);
		
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
}
