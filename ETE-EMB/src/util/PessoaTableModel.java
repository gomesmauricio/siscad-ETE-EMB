package util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Pessoa;

public class PessoaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Pessoa> listPessoa;
	String[] colunas = { "Código", "Nome", "Sexo", "Endereço", "Cargo", "Dt.Nascimento", "Status" };

	String[] cargos = new String[] { "Selecione", "Administrador", "Analista de Sistemas", "Contador", "Desenvolvedor",
			"Eng. Civil" };

	public PessoaTableModel(List<Pessoa> listPessoa) {
		this.listPessoa = listPessoa;
	}

	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.listPessoa.size();
	}

	public String getColumnName(int column) {
		return this.colunas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pessoa pessoa = listPessoa.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return pessoa.getId();
		case 1:
			return pessoa.getNome();
		case 2:
			return pessoa.getSexo();
		case 3:
			return pessoa.getEndereco();
		case 4:
			if (pessoa.getCargo().equals("1")) {
				//return pessoa.getCargo();
				return cargos[1];
			}else if (pessoa.getCargo().equals("2")) {
				return cargos[2];
			}else if (pessoa.getCargo().equals("3")) {
				return cargos[3];
			}else if (pessoa.getCargo().equals("4")) {
				return cargos[4];
			}else if(pessoa.getCargo().equals("5")) {
				return cargos[5];
			}else if (pessoa.getCargo().equals(6)){
				return cargos[6];
			}
		case 5:
			return pessoa.getDataNascimento();
		case 6:
		//	return pessoa.getAtivo();
			if(pessoa.getAtivo() == 0) {
				return "Inativo";
			}else {
				return "Ativo";
			}
		default: return null;
		}
		
	}
	
	public Pessoa getCadastro(int rowIndex) {
		Pessoa pessoa = listPessoa.get(rowIndex);
		return pessoa;
	     }
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
}
