package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Excecao.BancoVazioException;
import Excecao.ElementoNaoEncontradoException;
import Excecao.RegistroAlteradoException;
import Excecao.RegistroCadastradoComSucessoException;
import Excecao.RegistroExcluidoException;
import conexao.Conexao;
import view.TelaCadastro;

public class PessoaDAO {

	private Connection connection = null;
	private PreparedStatement stm = null;

	public void inserir(Pessoa pessoa) throws RegistroCadastradoComSucessoException {

		String sql = "INSERT INTO pessoa (nome, sexo, endereco, cargo, "
				+ "ativo, dataNascimento, dataCadastro) VALUES (?,?,?,?,?,?,?);";

		try {
			this.connection = new Conexao().getConnection();
			this.stm = this.connection.prepareStatement(sql);
			this.stm.setString(1, pessoa.getNome());
			this.stm.setString(2, pessoa.getSexo());
			this.stm.setString(3, pessoa.getEndereco());
			this.stm.setString(4, pessoa.getCargo());
			this.stm.setInt(5, pessoa.getAtivo());
			this.stm.setDate(6, (Date) pessoa.getDataNascimento());
			this.stm.setDate(7, (Date) pessoa.getDataCadastro());
			this.stm.execute();
			this.stm.close();

			throw new RegistroCadastradoComSucessoException();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	public void alterar(Pessoa pessoa) throws RegistroAlteradoException {

		String sql = "UPDATE pessoa SET nome=?, endereco=?, ativo=?, sexo=?, cargo=? WHERE id=?";

		try {
			this.connection = new Conexao().getConnection();
			this.stm = this.connection.prepareStatement(sql);
			this.stm.setString(1, pessoa.getNome());
			this.stm.setString(2, pessoa.getEndereco());
			this.stm.setInt(3, pessoa.getAtivo());
			this.stm.setString(4, pessoa.getSexo());
			this.stm.setString(5, pessoa.getCargo());
			this.stm.setInt(6, pessoa.getId());
			this.stm.execute();
			this.stm.close();

			throw new RegistroAlteradoException();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	public void excluir(Pessoa pessoa) throws RegistroExcluidoException {

		String sql = "DELETE FROM pessoa WHERE id=?";

		try {
			this.connection = new Conexao().getConnection();
			this.stm = this.connection.prepareStatement(sql);
			this.stm.setInt(1, pessoa.getId());
			this.stm.execute();
			this.stm.close();

			throw new RegistroExcluidoException();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	public Pessoa consultar(int id) throws ElementoNaoEncontradoException {
		Pessoa pessoa = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM pessoa WHERE id=?";

		try {

			this.connection = new Conexao().getConnection();
			stm = this.connection.prepareStatement(sql);
			this.stm.setInt(1, id);

			rs = this.stm.executeQuery();

			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setCargo(rs.getString("cargo"));
				pessoa.setAtivo(rs.getInt("ativo"));
				pessoa.setDataNascimento(rs.getDate("dataNascimento"));
				pessoa.setDataCadastro(rs.getDate("dataCadastro"));
			}

			this.stm.close();
			rs.close();

			if (pessoa == null) {
				throw new ElementoNaoEncontradoException();
			} else {
				return pessoa;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {

			try {
				this.connection.close();
			} catch (SQLException e) {

				throw new RuntimeException(e);
			}

		}
	}

	public List<Pessoa> listar() throws BancoVazioException {
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		Pessoa pessoa = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM pessoa";

		try {
			this.connection = new Conexao().getConnection();
			stm = this.connection.prepareStatement(sql);
			rs = this.stm.executeQuery();

			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setCargo(rs.getString("cargo"));
				pessoa.setAtivo(rs.getInt("ativo"));
				pessoa.setDataNascimento(rs.getDate("dataNascimento"));

				listPessoa.add(pessoa);
			}

			this.stm.close();
			rs.close();

			if (listPessoa.isEmpty()) {
				throw new BancoVazioException();
			} else {
				return listPessoa;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	public List relatorioPessoaAtiva() throws BancoVazioException {
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		Pessoa pessoa = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM pessoa";

		try {
			this.connection = new Conexao().getConnection();
			stm = this.connection.prepareStatement(sql);
			rs = this.stm.executeQuery();

			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setCargo(rs.getString("cargo"));
				pessoa.setAtivo(rs.getInt("ativo"));
				pessoa.setDataNascimento(rs.getDate("dataNascimento"));

				if (pessoa.getAtivo() == 1) {
					listPessoa.add(pessoa);
				}
			}

			this.stm.close();
			rs.close();

			if (listPessoa.isEmpty()) {
				throw new BancoVazioException();
			} else {
				return listPessoa;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public List relatorioGeral() throws BancoVazioException {

		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		Pessoa pessoa = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM pessoa";

		try {
			this.connection = new Conexao().getConnection();
			stm = this.connection.prepareStatement(sql);
			rs = this.stm.executeQuery();

			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setCargo(rs.getString("cargo"));
				pessoa.setAtivo(rs.getInt("ativo"));
				pessoa.setDataNascimento(rs.getDate("dataNascimento"));
				pessoa.setDataCadastro(rs.getDate("dataCadastro"));

				listPessoa.add(pessoa);
			}

			this.stm.close();
			rs.close();

			if (listPessoa.isEmpty()) {
				throw new BancoVazioException();
			} else {
				return listPessoa;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	public List relatorioPessoaAniversario() throws BancoVazioException {

		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		Pessoa pessoa = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM pessoa";

		try {
			this.connection = new Conexao().getConnection();
			stm = this.connection.prepareStatement(sql);
			rs = this.stm.executeQuery();

			TelaCadastro tc = new TelaCadastro();
			String aniversario;

			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setCargo(rs.getString("cargo"));
				pessoa.setAtivo(rs.getInt("ativo"));
				pessoa.setDataNascimento(rs.getDate("dataNascimento"));

				SimpleDateFormat formatar = new SimpleDateFormat("MM");
				java.util.Date dataUtil = new java.util.Date();
				String niver = formatar.format(dataUtil);

				DateFormat dateFormat = new SimpleDateFormat("MM");
				String dataFormatada = dateFormat.format(pessoa.getDataNascimento());

				if (dataFormatada == niver) {
					listPessoa.add(pessoa);
				}

			}

			this.stm.close();
			rs.close();

			if (listPessoa.isEmpty()) {
				throw new BancoVazioException();
			} else {
				return listPessoa;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
