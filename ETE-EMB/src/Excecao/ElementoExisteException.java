package Excecao;

public class ElementoExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Construtor da classe.
	 * Não recebe parametros.
	 * Mensagem de alerta para o usuario.
	 * Exceção caso exista um registro ja existente n banco de dados.
	 **/
	
	public ElementoExisteException() {
		super("Registro já Cadastrado...");
		
	}

}
