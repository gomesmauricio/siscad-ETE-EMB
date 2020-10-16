package Excecao;

public class BancoVazioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** 
	 * Construtor da classe.
	 * Não recebe parametros.
	 * Mensagem de alerta para o usuario.
	 * Exceção caso não exista o registro procurado no banco de dados.
	 **/
	
	public BancoVazioException() {
		
		super("Nem um registro cadastrado na base de dados");
		
	}
	
}
