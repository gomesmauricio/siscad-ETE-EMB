package model;

import java.util.List;

import Excecao.BancoVazioException;
import Excecao.ElementoNaoEncontradoException;
import Excecao.RegistroAlteradoException;
import Excecao.RegistroCadastradoComSucessoException;
import Excecao.RegistroExcluidoException;

/**
 * Classe que cria a padroniza��o das assinaturas dos m�todos.
 * @author Jos� Mauricio
 *
 */

public interface InterfacePessoa {
	
	/**
	 * Metodo cujo objetivo � inserir o registro de uma pessoa no banco de dados.
	 * @parm pessoa Parametro de entrada um objeto pessoa.
	 * @throws RegitroCadastradoComSucessoException excessao levantada
	 * caso consiga inserir no banco dedados.
	 * 
	 */
	
	public void inserir(Pessoa pessoa) throws RegistroCadastradoComSucessoException;
	
	/**
	 * Metodo cujo ojetivo � alterar o cadastro de uma pessoa no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws RegistroAlteradoException excessao levantada
	 * caso consiga alterar no banco de dados.
	 */
	
	public void alterar(Pessoa pessoa) throws RegistroAlteradoException;
	
	/**
	 * Metodo cujo ojetivo � excluir o cadastro de uma pessoa no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws RegistroExcluidoException excessao levantada
	 * caso consiga excluir do banco de dados.
	 */	
	
	public void excluir(Pessoa pessoa) throws RegistroExcluidoException;
	
	/**
	 * Metodo cujo ojetivo � listar todas as pessoas cadastradas no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws BancoVazioException excessao levantada
	 * caso n�o encontre cadastro no banco de dados.
	 */
	
	public List<Pessoa> listar() throws BancoVazioException;
	
	/**
	 * Metodo cujo ojetivo � listar todas as pessoas com cadastro ativo no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws ancoVazioException excessao levantada
	 * caso n�o encontre cadastro ativo no banco de dados.
	 */
	
	public List<Pessoa> relatorioPessoaAtiva() throws BancoVazioException;
	
	
	/**
	 * Metodo cujo ojetivo � consultar o registro de uma pessoas ativa ou inativa no banco de dados.
	 * @param cod
	 * @throws ElementoNaoEncontradoException excessao levantada
	 * caso n�o encontre o codigo passado como paramentro no banco de dados.
	 */
	public Pessoa consultar(int cod) throws ElementoNaoEncontradoException;
	
	/**
	 * Metodo cujo ojetivo � listar todas as pessoas cadastradas no banco de dados.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws ancoVazioException excessao levantada
	 * caso n�o encontre cadastro ativo no banco de dados.
	 */
	
	public List<Pessoa> relatorioGeral() throws BancoVazioException;
	
	/**
	 * Metodo cujo ojetivo � listar todas as pessoas que fazem aniversario no respectivo mes.
	 * @param pessoa Parametro de entrada um ojeto Pessoa.
	 * @throws ancoVazioException excessao levantada
	 * caso n�o encontre cadastro ativo no banco de dados.
	 */
	

	public List<Pessoa> relatorioPessoaAniversario() throws BancoVazioException;
	
}
