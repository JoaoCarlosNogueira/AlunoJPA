package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaEntityManager {

private static EntityManagerFactory emf;

 static {
 emf = Persistence.createEntityManagerFactory("UnidadeAcademicoJPA");
 }
 
 private FabricaEntityManager () {

 }

 public static EntityManager getEntityManager () {
 if ( emf == null ) {
 return null;
 } else {
	 return emf.createEntityManager ();
 	}
 }
} 

	// Classe responsável por criar e retornar uma instância do objeto EntityManager, usado para a manipulação das entidades de persistência. 
	// Também é responsável por criar o contexto de persistência a partir da unidade de persisttência, configurada no arquivo persistence.xml 