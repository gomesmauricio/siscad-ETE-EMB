package model;

import java.util.List;

import Excecao.BancoVazioException;
import Excecao.ElementoNaoEncontradoException;
import Excecao.RegistroAlteradoException;
import Excecao.RegistroCadastradoComSucessoException;
import Excecao.RegistroExcluidoException;

/**
 * Classe que cria a padronização das assinaturas dos métodos.
 * @author José Mauricio
 *
 */

public interface InterfacePessoa {
	
	/**
	 * Metodo cujo objetivo é inserir o registro de uma pessoa no banco de dados.
	 * @parm pessoa Parametro de entrada um objeto pessoa.
	 * @throws RegitroCadastradoComSucessoException excessao levantada
	 * caso consiga inserir no banco dedados.
	 * 
	 */
	
	public void inserir(Pessoa pessoa) throws RegistroCadastradoComSucessoException;
	
	/**
	 * Metodo cujo ojetivo é alterar o cadastro de uma pessoa no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws RegistroAlteradoException excessao levantada
	 * caso consiga alterar no banco de dados.
	 */
	
	public void alterar(Pessoa pessoa) throws RegistroAlteradoException;
	
	/**
	 * Metodo cujo ojetivo é excluir o cadastro de uma pessoa no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws RegistroExcluidoException excessao levantada
	 * caso consiga excluir do banco de dados.
	 */	
	
	public void excluir(Pessoa pessoa) throws RegistroExcluidoException;
	
	/**
	 * Metodo cujo ojetivo é listar todas as pessoas cadastradas no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws BancoVazioException excessao levantada
	 * caso não encontre cadastro no banco de dados.
	 */
	
	public List<Pessoa> listar() throws BancoVazioException;
	
	/**
	 * Metodo cujo ojetivo é listar todas as pessoas com cadastro ativo no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws ancoVazioException excessao levantada
	 * caso não encontre cadastro ativo no banco de dados.
	 */
	
	public List<Pessoa> relatorioPessoaAtiva() throws BancoVazioException;
	
	
	/**
	 * Metodo cujo ojetivo é consultar o registro de uma pessoas ativa ou inativa no banco de dados.
	 * @param cod
	 * @throws ElementoNaoEncontradoException excessao levantada
	 * caso não encontre o codigo passado como paramentro no banco de dados.
	 */
	public Pessoa consultar(int cod) throws ElementoNaoEncontradoException;
	
	/**
	 * Metodo cujo ojetivo é listar todas as pessoas cadastradas no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws ancoVazioException excessao levantada
	 * caso não encontre cadastro ativo no banco de dados.
	 */
	
	public List<Pessoa> relatorioGeral() throws BancoVazioException;
	
	/**
	 * Metodo cujo ojetivo é listar todas as pessoas que fazem aniversario no respectivo mes.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws ancoVazioException excessao levantada
	 * caso não encontre cadastro ativo no banco de dados.
	 */
	

	public List<Pessoa> relatorioPessoaAniversario() throws BancoVazioException;
	
}
