package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Excecao.BancoVazioException;
import controller.PessoaController;
import model.Pessoa;
import util.PessoaTableModel;

public class TelaListar extends JDialog {

	private static final long serialVersion = 1L;

	private JPanel contentPane;
	private PessoaController controller = new PessoaController();
	private PessoaTableModel pessoaTableModel;
	private List<Pessoa> pessoa = null;
	private JTable table;


	
	public TelaListar() {
		setTitle("SISCAD - Módulo de Listagem");

		setBounds(320, 180, 792, 400);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 766, 265);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setBounds(0,0,434,228);
		contentPane.setBorder(new  EmptyBorder(5, 5, 5, 5));
		
		try {
			pessoa = controller.listar();
			pessoaTableModel = new PessoaTableModel(pessoa);
			table.setModel(pessoaTableModel);
		}catch (BancoVazioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Aviso", JOptionPane.WARNING_MESSAGE);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO", JOptionPane.ERROR_MESSAGE);
		}

		
		
	}

}
