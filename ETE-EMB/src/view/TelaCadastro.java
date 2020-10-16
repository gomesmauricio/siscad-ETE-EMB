package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Excecao.RegistroCadastradoComSucessoException;
import controller.PessoaController;
import model.Pessoa;
import util.Clear;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import model.Pessoa;
import model.Pessoa;
import model.InterfacePessoa;


public class TelaCadastro extends JDialog {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;

	String[] comboCargos = new String[] { "Selecione", "Administrador", "Analista de Sistemas", "Contador",
			"Desenvolvedor", "Eng. Civil" };

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setResizable(false);
		setTitle("SISCAD - M\u00F3dulo de Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 11, 46, 14);
		contentPane.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(20, 29, 481, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data de Cadastro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(527, 11, 127, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lbDataCadastro = new JLabel("New label");
		lbDataCadastro.setEnabled(false);
		lbDataCadastro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbDataCadastro.setBounds(537, 32, 92, 17);
		contentPane.add(lbDataCadastro);
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataUtil = new java.util.Date();
		lbDataCadastro.setText(formatar.format(dataUtil));

		JLabel lblNewLabel_3 = new JLabel("Endere\u00E7o");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 60, 65, 20);
		contentPane.add(lblNewLabel_3);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(20, 81, 627, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Data de Nascimento");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(440, 140, 127, 14);
		contentPane.add(lblNewLabel_4);

		JDateChooser dtNascimento = new JDateChooser();
		dtNascimento.setBounds(440, 158, 197, 20);
		contentPane.add(dtNascimento);

		JComboBox cbCargo = new JComboBox(comboCargos);
		cbCargo.setBounds(440, 234, 196, 20);
		contentPane.add(cbCargo);

		JLabel lblNewLabel_5 = new JLabel("Cargo");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(440, 213, 51, 20);
		contentPane.add(lblNewLabel_5);

		JRadioButton rbM = new JRadioButton("M");
		rbM.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbM.setBounds(29, 158, 37, 23);
		contentPane.add(rbM);

		JRadioButton rbF = new JRadioButton("F");
		rbF.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbF.setBounds(73, 158, 37, 23);
		contentPane.add(rbF);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rbM);
		bg.add(rbF);

		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Sexo");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewJgoodiesLabel.setBounds(33, 140, 92, 14);
		contentPane.add(lblNewJgoodiesLabel);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear.limpar(contentPane);
			}
		});
		btn_cancelar.setBounds(314, 297, 103, 29);
		contentPane.add(btn_cancelar);

		JCheckBox ckAtivo = new JCheckBox("Ativo");
		ckAtivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		ckAtivo.setBounds(20, 231, 97, 23);
		contentPane.add(ckAtivo);

		JButton btn_salvar = new JButton("Salvar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((!txtEndereco.getText().equals("") ) && (!txtNome.getText().equals(""))
						&& (cbCargo.getSelectedIndex() > 0) && ckAtivo.isSelected()
						&& (dtNascimento.getDate() != null)
						&& (rbF.isSelected() || rbM.isSelected()) ) {
					
					Pessoa pessoa = new Pessoa();
					PessoaController controller = new PessoaController();
					
					pessoa.setNome(txtNome.getText());
					pessoa.setEndereco(txtEndereco.getText());
					pessoa.setCargo(Integer.toString(cbCargo.getSelectedIndex()) );
					
					String dataCadastro = lbDataCadastro.getText();
					java.util.Date date = null;
					
					try {
						date = formatar.parse(dataCadastro);
					}catch(ParseException e1) {
						e1.printStackTrace();
						
					}
					
					java.sql.Date cadastro = new java.sql.Date(date.getTime());
					pessoa.setDataCadastro(cadastro);
					
					java.sql.Date nascimento = new java.sql.Date(dtNascimento.getDate().getTime());
					pessoa.setDataNascimento(nascimento);
					
					if(rbF.isSelected()) {
						pessoa.setSexo("F");
					}else {
						pessoa.setSexo("M");
					}
					
					if(ckAtivo.isSelected()) {
						pessoa.setAtivo(1);
					}else {
						pessoa.setAtivo(0);
					}
					
					try	{
						controller.inserir(pessoa);
					}catch(RegistroCadastradoComSucessoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Cadastro",
								JOptionPane.INFORMATION_MESSAGE);
						
						Clear.limpar(contentPane);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Necessário preencher todos os campos!","ATENÇÃO",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btn_salvar.setBounds(202, 297, 103, 29);
		contentPane.add(btn_salvar);
	}
}
