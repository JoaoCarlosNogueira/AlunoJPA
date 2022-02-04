package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import vo.AlunoVO;

public class AlunoDAO extends DAO<AlunoVO> {

	public AlunoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
		
	}
	public AlunoVO buscarPorMatricula(int matricula) throws PersistenciaException {
		
		AlunoVO alu = null;
		try {
		alu = this.entityManager.find(AlunoVO.class, matricula);	
		}  catch (Exception ex) {
			throw new PersistenciaException("Erro na seleção por matricula - " + ex.getMessage());
		}
		return alu;
	}
	
	@SuppressWarnings("Unchecked")
	public List<AlunoVO> buscarPorNome(String nome) throws PersistenciaException{
		List<AlunoVO> listaAluno = new ArrayList<AlunoVO>();

	try {
	Query query = this.entityManager
	.createQuery("Select a From AlunoVO a WHERE UPPER(a.nome) LIKE :pNome ORDER BY a.nome");
	query.setParameter("pNome, %" + nome.toUpperCase().trim() + "%", query);
	listaAluno = query.getResultList();
	} catch (Exception ex) {
		throw new PersistenciaException("Erro na selecão por nome -" + ex.getMessage());
	}
	return listaAluno;
		
	}
	

	public List<AlunoVO> exibirMedia(float nota1, float nota2, float nota3, float nota4, float media) throws PersistenciaException{
			List<AlunoVO> listamedia = new ArrayList<AlunoVO>();			
	 try {
		 Query query = this.entityManager
		 .createQuery("\r\n"
		 		+ "SELECT nota1,nota2,nota3,nota4(AVG(nota1,nota2,nota3,nota4) AS \"Nota_Media\"\r\n"
		 		+ "from AlunoVO\r\n"
		 		+ "GROUP BY nota1,nota2\r\n"
		 		+ "ORDER BY media \r\n");
		 listamedia = query.getResultList();

	 } catch (Exception ex) {
		 throw new PersistenciaException("Erro ao calcular a media " + ex.getMessage());
	 } return listamedia;
	} 
	
 }

