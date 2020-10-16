package controller;

import java.util.List;

import Excecao.BancoVazioException;
import Excecao.ElementoNaoEncontradoException;
import Excecao.RegistroAlteradoException;
import Excecao.RegistroCadastradoComSucessoException;
import Excecao.RegistroExcluidoException;
import model.InterfacePessoa;
import model.Pessoa;
import model.PessoaDAO;

public class PessoaController implements InterfacePessoa {
	
	private PessoaDAO pessoaDAO;
	
	public PessoaController() {
		this.pessoaDAO = new PessoaDAO();
	}
	
	public void inserir(Pessoa pessoa) throws RegistroCadastradoComSucessoException{
		pessoaDAO.inserir(pessoa);
		
	}
	
	public void excluir(Pessoa pessoa) throws RegistroExcluidoException{
		pessoaDAO.excluir(pessoa);
	}
	
	public void alterar(Pessoa pessoa) throws RegistroAlteradoException{
		pessoaDAO.alterar(pessoa);
	}
	
	public List<Pessoa> listar() throws BancoVazioException{
		return pessoaDAO.listar();
	}
	
	public List<Pessoa> relatorioPessoaAtiva() throws BancoVazioException{
		return pessoaDAO.relatorioPessoaAtiva();
	}
	

	public Pessoa consultar(int cod) throws ElementoNaoEncontradoException {
		return pessoaDAO.consultar(cod);
	}
	
	public List<Pessoa> relatorioGeral() throws BancoVazioException{
		return pessoaDAO.relatorioGeral();
	}
	
	public List<Pessoa> relatorioPessoaAniversario() throws BancoVazioException{
		return pessoaDAO.relatorioPessoaAtiva();
	}

}
