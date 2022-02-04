package vo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="aluno")

public class AlunoVO {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private int matricula;

@Column(nullable = false, length=50)
private String nome;


@Column(name="nomemae",nullable = false, length= 50)
private String nomeMae;

@Column(name = "nomepai",nullable = false, length =50)
private String nomePai; 

@Enumerated(EnumType.STRING)
private EnumSexo sexo;

@Embedded  //agrega propriedades e obtém um modelo de objetos
private EnderecoVO endereco;


public AlunoVO() {
	this.endereco = new EnderecoVO();
	this.matricula = 0;
	this.nome = "";
	this.nomePai = "";
	this.nomeMae = "";
	this.sexo = EnumSexo.FEMININO;
}


public AlunoVO(int matricula, String nome, EnumSexo sexo) {
	this () ;
	this.matricula = matricula;
	this.nome = "";
	this.sexo = sexo;
	}


public int getMatricula() {
	return matricula;
}


public void setMatricula(int matricula) {
	this.matricula = matricula;
}


public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}


public String getNomeMae() {
	return nomeMae;
}


public void setNomeMae(String nomeMae) {
	this.nomeMae = nomeMae;
}


public String getNomePai() {
	return nomePai;
}


public void setNomePai(String nomePai) {
	this.nomePai = nomePai;
}


public EnumSexo getSexo() {
	return sexo;
}


public void setSexo(EnumSexo sexo) {
	this.sexo = sexo;
}


public EnderecoVO getEndereco() {
	return endereco;
}


public void setEndereco(EnderecoVO endereco) {
	this.endereco = endereco;
}


@Override
public String toString() {
	return  matricula + " , " + nome + ", " + sexo + ", " + "reidente em: "  + endereco ;
	}	
}

