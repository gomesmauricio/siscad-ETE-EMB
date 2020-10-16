package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Excecao.ElementoNaoEncontradoException;
import Excecao.RegistroAlteradoException;
import Excecao.RegistroExcluidoException;
import controller.PessoaController;
import model.Pessoa;
import util.Clear;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAlterar extends JDialog {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;

	String[] comboCargos = new String[] { "Selecione", "Administrador", "Analista de Sistemas", "Contador",
			"Desenvolvedor", "Eng. Civil" };

	/**
	 * Create the frame.
	 */
	public TelaAlterar() {
		setResizable(false);
		setTitle("SISCAD - M\u00F3dulo Altera\u00E7\u00E3o/Exclus\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(27, 13, 54, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(132, 14, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(132, 34, 462, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbID.setBounds(37, 34, 37, 17);
		contentPane.add(lbID);
		
		JLabel lblNewLabel_2 = new JLabel("Endere\u00E7o");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 75, 64, 17);
		contentPane.add(lblNewLabel_2);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(10, 93, 584, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Data de Nascimento");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(434, 155, 135, 20);
		contentPane.add(lblNewLabel_3);
		
		JDateChooser dtNascimento = new JDateChooser();
		dtNascimento.setBounds(434, 175, 160, 20);
		contentPane.add(dtNascimento);
		
		JComboBox cbCargo = new JComboBox(comboCargos);
		cbCargo.setBounds(434, 249, 160, 20);
		contentPane.add(cbCargo);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Cargo");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewJgoodiesLabel.setBounds(434, 228, 46, 17);
		contentPane.add(lblNewJgoodiesLabel);
		
		JRadioButton rbM = new JRadioButton("M");
		rbM.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbM.setBounds(10, 172, 37, 23);
		contentPane.add(rbM);
		
		JRadioButton rbF = new JRadioButton("F");
		rbF.setFont(new Font("Tahoma", Font.BOLD, 14));
		rbF.setBounds(55, 172, 37, 23);
		contentPane.add(rbF);
		
		ButtonGroup bgalt = new ButtonGroup();
		bgalt.add(rbM);
		bgalt.add(rbF);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Sexo");
		lblNewJgoodiesLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewJgoodiesLabel_1.setBounds(27, 156, 37, 19);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JCheckBox ckAtivo = new JCheckBox("Ativo");
		ckAtivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		ckAtivo.setBounds(10, 248, 97, 23);
		contentPane.add(ckAtivo);
		
		
		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(!txtEndereco.getText().equals("")) {
					
					Pessoa pessoa = new Pessoa();
					pessoa.setId(Integer.parseInt(lbID.getText() ) );
					pessoa.setEndereco(txtEndereco.getText());
					pessoa.setNome(txtNome.getText());
					pessoa.setCargo(Integer.toString(cbCargo.getSelectedIndex()));
					java.sql.Date nascimento = new 	java.sql.Date(dtNascimento.getDate().getTime());
					pessoa.setDataNascimento(nascimento);
					
					if (ckAtivo.isSelected()) {
						pessoa.setAtivo(1);
					}else {
						pessoa.setAtivo(0);
					}
					
					if(rbF.isSelected()) {
						pessoa.setSexo("F");
					}else {
						pessoa.setSexo("M");					
					}
					
						PessoaController controller = new PessoaController();
						
						try {
							controller.alterar(pessoa);
						}catch(RegistroAlteradoException e1){
							
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Alteração",
														JOptionPane.INFORMATION_MESSAGE);
							
							Clear.limpar(contentPane);
							
						}
				}else {
					JOptionPane.showMessageDialog(null, "Necessario preencher todos os Campos!",
													"Atenção", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		btAlterar.setBounds(110, 311, 97, 29);
		contentPane.add(btAlterar);
		
		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pessoa           pessoa      = new Pessoa();
				PessoaController controller  = new PessoaController();
				
				pessoa.setId(Integer.parseInt(lbID.getText() ));
				
				try {
					controller.excluir(pessoa);
				}catch(RegistroExcluidoException ex ){
					
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Exclusão",
							                     JOptionPane.INFORMATION_MESSAGE);
					Clear.limpar(contentPane);
					
				}
			}
		});
		btExcluir.setIcon(new ImageIcon("C:\\Users\\Jos\u00E9 Mauricio\\eclipse-workspace\\ETE-EMB\\img\\lixeira.png"));
		btExcluir.setBounds(217, 311, 97, 29);
		contentPane.add(btExcluir);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear.limpar(contentPane);
			}
		});
		btCancelar.setBounds(324, 311, 97, 29);
		contentPane.add(btCancelar);
		
		JButton btLocalizar = new JButton("");
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o Código", 
											"Localizar", JOptionPane.INFORMATION_MESSAGE));
				
				try {
					
					Pessoa pessoa			    = new Pessoa();
					PessoaController controller = new PessoaController();
					
					pessoa = controller.consultar(id);
					
				    
					lbID.setText(Integer.toString(pessoa.getId() ));
					txtNome.setText(pessoa.getNome());
					txtEndereco.setText(pessoa.getEndereco());
					cbCargo.setSelectedIndex(Integer.parseInt(pessoa.getCargo()));
					dtNascimento.setDate(pessoa.getDataNascimento());
					
					if(pessoa.getSexo().equals("F")) {
						rbF.setSelected(true);
					}else {
						rbM.setSelected(true);
					}
					
					if(pessoa.getAtivo() == 1) {
						ckAtivo.setSelected(true);
					}else {
						ckAtivo.setSelected(false);
					}
				}catch(ElementoNaoEncontradoException e2){
					JOptionPane.showMessageDialog(null, e2.getMessage(),"Alteração",
													JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		btLocalizar.setIcon(new ImageIcon("C:\\Users\\Jos\u00E9 Mauricio\\eclipse-workspace\\ETE-EMB\\img\\lupa.png"));
		btLocalizar.setBounds(431, 311, 64, 29);
		contentPane.add(btLocalizar);
		
		
	}
}
