package persistencia;

public class PersistenciaException  extends Exception{ //Especialização da classe Exception para captura e tratamento de exceções ocorridas na camada de persistência.
	

	private static final long serialVersionUID = 1L; // identificador usado para comparar versões das classes 
													//garantindo que a mesma classe que foi usada durante a serialização seja carregada durante a desserialização.
	
	public PersistenciaException() {
		super("Erro ocorrido na manipulacão do banco de dados");
	} 
	
	public PersistenciaException(String msg) {
		super(msg);
	}
}
 
// Especialização da classe Exception para captura e tratamento de exceções ocorridas na camada de persistencia 