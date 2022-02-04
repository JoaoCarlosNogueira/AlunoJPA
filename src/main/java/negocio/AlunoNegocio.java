package negocio;

import java.util.List;
import persistencia.AlunoDAO;
import persistencia.FabricaEntityManager;
import persistencia.PersistenciaException;
import vo.AlunoVO;

public class AlunoNegocio {
	private AlunoDAO alunoDAO; 
	public AlunoNegocio() throws NegocioException{
		try {
			this.alunoDAO = new AlunoDAO(FabricaEntityManager.getEntityManager());
		} catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia-" + ex.getMessage());
		}
	} 
	
	public void inserir(AlunoVO alunoVO) throws NegocioException{
		String mensagemErros = this.validarDados(alunoVO);
	
	
	if(!mensagemErros.isEmpty()) {
		throw new NegocioException(mensagemErros);
	} 
	try {
	alunoDAO.beginTransaction();
	alunoDAO.incluir(alunoVO);
	alunoDAO.commitTransaction();
	} catch(PersistenciaException ex) {
	 alunoDAO.rollbackTransaction();
	 throw new NegocioException("Erro ao incluir o aluno- " + ex.getMessage());
	}
 }
	public void alterar(AlunoVO alunoVO) throws NegocioException{
		String mensagemErros = this.validarDados(alunoVO);
	
	
	if(!mensagemErros.isEmpty()) {
		throw new NegocioException(mensagemErros);
	} 
	try {
	alunoDAO.beginTransaction();
	alunoDAO.alterar(alunoVO);
	alunoDAO.commitTransaction();
	} catch(PersistenciaException ex) {
	 alunoDAO.rollbackTransaction();
	 throw new NegocioException("Erro ao alterar o aluno- " + ex.getMessage());
	}
 }
	public void excluir(AlunoVO alunoVO) throws NegocioException{

	try {
	alunoDAO.beginTransaction();
	alunoDAO.excluir(alunoVO);
	alunoDAO.commitTransaction();
	} catch(PersistenciaException ex) {
	 alunoDAO.rollbackTransaction();
	 throw new NegocioException("Erro ao excluir o aluno- " + ex.getMessage());
	}
 } 
	
	public List<AlunoVO>pesquisaParteNome(String parteNome) throws NegocioException{
	try {
		return alunoDAO.buscarPorNome(parteNome);
	} catch (PersistenciaException ex) {
		throw new NegocioException("Erro ao pesquisar o aluno pelo none -" + ex.getMessage());
	 }
	}
	
	public AlunoVO pesquisaMatricula (int matricula) throws NegocioException{
		try{
			return alunoDAO.buscarPorMatricula(matricula);
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar o aluno pela matricula -" + ex.getMessage());
		}
	}
	
	public String validarDados(AlunoVO alunoVO) {
		String mensagemErros="";
		
		if (alunoVO.getNome() == null || alunoVO.getNome().length() == 0 ) {
		mensagemErros += "Nome do aluno não pode estar vazio";
			
		}
		if(alunoVO.getNomeMae() == null || alunoVO.getNomeMae().length() == 0 ) {
		mensagemErros += "\nNome da mae não pode estar vazio";
		}
		if(alunoVO.getNomePai() == null || alunoVO.getNomeMae().length() == 0 ) {
		mensagemErros += "\nNome do pai não pode estar vazio";
		} 
		
		if (alunoVO.getSexo() == null) {
		mensagemErros += "\nNome do pai não pode estar vazio";	
		} 
		
		if (alunoVO.getEndereco().getLogradouro() == null || alunoVO.getEndereco().getLogradouro().length() == 0 ) {
		 mensagemErros += " \nLogradouro nao pode ser vazio ";
	    }
		
		if (alunoVO.getEndereco().getNumero ( ) <= 0 ) {
			mensagemErros += " \nNumero deve ser maior que zero ";
			 } 
		
		if (alunoVO.getEndereco().getBairro() == null || alunoVO.getEndereco().getBairro().length() == 0 ) {
			 mensagemErros += " \nBairro não pode ser vazio ";
		    }
		
		if (alunoVO.getEndereco().getCidade() == null || alunoVO.getEndereco().getCidade().length() == 0 ) {
			 mensagemErros += " \nCidade não pode ser vazio ";
		    } 
		
		if (alunoVO.getEndereco().getUf() == null) {
			 mensagemErros += " \nUF nao pode ser vazio ";
		}
		return mensagemErros;	
	} 
}
