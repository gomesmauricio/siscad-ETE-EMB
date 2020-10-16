package Excecao;

public class ElementoNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID =  1L;
	
	public ElementoNaoEncontradoException() {
		
		super("Registro não Encontrado...");
		
	}

}
