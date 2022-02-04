package execucao;

import negocio.AlunoNegocio;
import negocio.NegocioException;
import vo.AlunoVO;
import vo.EnumSexo;
import vo.EnumUF;

import java.util.List;

import javax.swing.JOptionPane;

public class Principal {

	private static AlunoNegocio alunoNegocio;

	@SuppressWarnings("Incomplete-switch")
	public static void main(String[] args) {
		try {
			alunoNegocio = new AlunoNegocio();
			
		} catch (NegocioException ex) {
			System.out.println("Camada de negocio e persistencia não iniciada " + ex.getMessage());
		} 
		if(alunoNegocio != null) {
			EnumMenu opcao =EnumMenu.SAIR;
		 do { 
			 try {
				 opcao = exibirMenu();
				 switch (opcao) {
				 case INCLUIR_ALUNO:
					 incluirAluno();
					 break;
				 case ALTERAR_ALUNO:
					  alterarAluno();
					  break; 
				 case EXCLUIR_ALUNO:
					  excluirAluno();
					  
				 case PESQ_MATRICULA:
					  pesquisarPorMatricula();
					  break;
				 case PESQ_NOME:
					 pesquisarPorNome();
				 } 				 
				 
			 } catch (NegocioException ex) {
				 System.out.println("Operação não realizada corretamente - " + ex.getMessage());
			 }	 
		 } while (opcao!=EnumMenu.SAIR);
		}
		System.exit(0);
	}

	/**
	 * Inclui um novo aluno na base de dados
	 * 
	 * @throws NegocioException
	 */

	private static void incluirAluno() throws NegocioException {
		AlunoVO alunoTemp = lerDados();
		alunoNegocio.inserir(alunoTemp);
	}

	/**
	 * Permite a alteracao dos dados de um aluno por meio da matrícula fornecida.
	 * 
	 * @throws NegocioException
	 */
	private static void alterarAluno() throws NegocioException {
		int matricula = 0;
		try {
			matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Forneça a matricula do Aluno",
					"Leitura de dados", JOptionPane.QUESTION_MESSAGE));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Digitação incossistente -" + ex.getMessage());
		}
		AlunoVO alunoVO = alunoNegocio.pesquisaMatricula(matricula);
		if (alunoVO != null) {
			AlunoVO alunoTemp = lerDados(alunoVO);
			alunoNegocio.alterar(alunoTemp);
		} else {
			JOptionPane.showMessageDialog(null, "Aluno não Localizado");
		}
	 }
		

	

	/**
	 * Exclui um aluno por meio de uma matricula fornecida.
	 * 
	 * @throws NegocioException
	 */
	private static void excluirAluno() throws NegocioException {
		int matricula = 0;
		try {
			matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Forneça a matricula do Aluno",
					"Leitura de Dados", JOptionPane.QUESTION_MESSAGE));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Digitação incossistente -" + ex.getMessage());
		}
	
	
	AlunoVO alunoVO = alunoNegocio.pesquisaMatricula(matricula);
	if (alunoVO != null) {
		AlunoVO alunoTemp = lerDados(alunoVO);
		alunoNegocio.excluir(alunoTemp);
	} else {
		JOptionPane.showMessageDialog(null, "Aluno não Localizado");
	}
 }
	
	

	/**
	 * Pesquisa um aluno por meio da matricula;
	 * 
	 * @throws NegocioException
	 */
     private static void pesquisarPorMatricula() throws NegocioException{
    	 int matricula = 0;
    	 try {
    		 matricula = Integer.parseInt(JOptionPane.showInputDialog(null,"Forneça a matricula do aluno","Leitura de dados",JOptionPane.QUESTION_MESSAGE));
    	 } catch (Exception ex) {
    		   JOptionPane.showMessageDialog(null,"Digitação incossistente -" + ex.getMessage());	
    	 }
     }

	/**
	 * Le um nome ou part de um nome de um aluno e busca no banco de dados alunos
	 * que possuem esse nome, ou que iniciam com a parte do nome fornecida.Caso não
	 * seja fornecido nenhum valor de entrada sera retornado os 10 primeiros alunos
	 * ordenados pelo nome.
	 * 
	 * @throws NegocioException
	 */
	private static void pesquisarPorNome() throws NegocioException {
		String nome = JOptionPane.showInputDialog(null, "Forneça o nome do Aluno", "Leitura de Dados",
				JOptionPane.QUESTION_MESSAGE);
		if (nome != null) {
			List<AlunoVO> listaAlunoVO = alunoNegocio.pesquisaParteNome(nome);

			if (listaAlunoVO.size() > 0) {
				for (AlunoVO alunoVO : listaAlunoVO) {
					mostrarDados(alunoVO);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Aluno não localizado");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Nome não localizado");
		}
	}
	/**
	 * Exibe no console da aplicação os dados dos alunos recebidos pelo parametro
	 * alunoVO
	 * @param alunoVO
	 */ 
	
	private static void mostrarDados(AlunoVO alunoVO) {
		if(alunoVO != null) {
		System.out.println("Matricula: " + alunoVO.getMatricula());
		System.out.println("Nome: " + alunoVO.getNome());
		System.out.println("Nome da mãe: " + alunoVO.getNomeMae());
		System.out.println("Nome do pai:  "+ alunoVO.getNomePai());
		System.out.println("Sexo: " + alunoVO.getSexo());
		if(alunoVO.getEndereco() != null) {
			System.out.println("Logradouro: " +alunoVO.getEndereco().getLogradouro());
			System.out.println("Numero: " + alunoVO.getEndereco().getNumero());
			System.out.println("Bairro: " + alunoVO.getEndereco().getBairro());
		}
	 }
	}
	/**
	 * Le os dados de um aluno exibindo os dados atuais recebidos pelo parametro
	 * alunoTemp. Na alteração permite que os dados atuais do aluno sejam visualizados. Na inclusão são exibidos
	 * os dados inicializados no AlunoVO
	 * @param alunoTemp 
	 * @return 
	 */
	private static AlunoVO lerDados(AlunoVO alunoTemp) {
	String nome,nomeMae,nomePai,logradouro,bairro,cidade;
	int numero;
	EnumSexo sexo;
	EnumUF uf;
	try {
	nome = JOptionPane.showInputDialog("Forneça o nome do aluno", alunoTemp.getNome().trim());
	alunoTemp.setNome(nome);
	nomeMae= JOptionPane.showInputDialog("Forneça o nome da mãe do aluno",alunoTemp.getNomeMae().trim());
	alunoTemp.setNomeMae(nomeMae);
	nomePai= JOptionPane.showInputDialog("Forneça o nome do pai do aluno",alunoTemp.getNomePai().trim());
	alunoTemp.setNomePai(nomePai);
	sexo=(EnumSexo)JOptionPane.showInputDialog(null," Escolha uma Opção", " Leitura de Dados ",JOptionPane.QUESTION_MESSAGE,null,EnumSexo.values(),alunoTemp.getSexo());
	alunoTemp.setSexo(sexo);
	logradouro= JOptionPane.showInputDialog("Forneça o logradouro do endereço",alunoTemp.getEndereco().getLogradouro().trim());
	alunoTemp.getEndereco().setLogradouro(logradouro);
	numero = Integer.parseInt(JOptionPane.showInputDialog("Forneça o numero no endereço", alunoTemp.getEndereco().getNumero()));
	alunoTemp.getEndereco().setNumero(numero);
	
	bairro = JOptionPane.showInputDialog("Forneça o bairro no endereço",alunoTemp.getEndereco().getBairro().trim());
	alunoTemp.getEndereco().setBairro(bairro);
	
	cidade = JOptionPane.showInputDialog("Forneça a cidade no endereço",alunoTemp.getEndereco().getCidade().trim());
	alunoTemp.getEndereco().setCidade(cidade);
	
	uf = (EnumUF) JOptionPane.showInputDialog(null,"Escolha uma opção","Leitura de dados",JOptionPane.QUESTION_MESSAGE,null,EnumUF.values(),alunoTemp.getEndereco().getUf());
	alunoTemp.getEndereco().setUf(uf);
	
	} catch(Exception ex) {
     System.out.println("Digitação Inconsistente -" + ex.getMessage());		
	}
	return alunoTemp;
	}
	/**
	 * Cria uma nova instancia de AlunoVO e chama o método lerdados(AlunoVO alunoVO)
	 * @return
	 */
	private static AlunoVO lerDados() {
		AlunoVO alunoTemp = new AlunoVO();
		return lerDados(alunoTemp);
	
	}
	/**
	 * Exibe as opções por meio de uma tela de dialogo
	 * @retrun
	 */
	 private static EnumMenu exibirMenu() {
	 EnumMenu opcao;
	 opcao = (EnumMenu) JOptionPane.showInputDialog(null,"Escolha uma opção","Menu",JOptionPane.QUESTION_MESSAGE,null,EnumMenu.values(),EnumMenu.values()[0]);
	 if(opcao == null) {
		 JOptionPane.showMessageDialog(null,"Nenhuma Opção Escolhida");
		 opcao=EnumMenu.SAIR;
	 }
	 return opcao;
	 }
}
