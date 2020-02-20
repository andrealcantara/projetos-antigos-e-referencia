drop database projetofaculdade;
create database projetofaculdade;
use projetofaculdade;

CREATE TABLE pessoa (
  matricula varchar(10) NOT NULL,
  nome varchar(500) DEFAULT NULL,
  endereco varchar(500) DEFAULT NULL,
  cpf int(11) DEFAULT NULL,
  dataNascimento date DEFAULT NULL,
  email varchar(30) DEFAULT NULL,
  CONSTRAINT PK_Pessoa PRIMARY KEY (matricula)
);

CREATE TABLE disciplina (
  codigo varchar(5) NOT NULL,
  nome varchar(50) DEFAULT NULL,
  ementa varchar(4000) DEFAULT NULL,
  CONSTRAINT PK_Disciplina PRIMARY KEY (codigo)
);
CREATE TABLE fone (
  id int(11) NOT NULL AUTO_INCREMENT,
  ddd int(11) DEFAULT NULL,
  numero int(11) DEFAULT NULL,
  matricula varchar(10) NOT NULL,
  CONSTRAINT PK_Fone PRIMARY KEY (id)
);

CREATE TABLE titulo (
  id int(11) NOT NULL AUTO_INCREMENT,
  nome int(11) NOT NULL,
  CONSTRAINT PK_Titulo PRIMARY KEY (id)
);

CREATE TABLE aluno (
  matricula varchar(10) NOT NULL,
  nomeMae varchar(500) NOT NULL,
  nomePai varchar(500) DEFAULT NULL,
  escolaAnterior varchar(500) DEFAULT NULL,
  CONSTRAINT PK_Aluno PRIMARY KEY (matricula),
  CONSTRAINT FK_Aluno_Pessoa FOREIGN KEY (matricula) REFERENCES pessoa (matricula)
);

CREATE TABLE fone_pessoa (
  matricula_pessoa varchar(10) NOT NULL,
  id_fone int(11) NOT NULL,
  CONSTRAINT PK_Fone_pessoa PRIMARY KEY (matricula_pessoa,id_fone),
  CONSTRAINT FK_Fone_Pessoa_Fone FOREIGN KEY (id_fone) REFERENCES fone (id),
  CONSTRAINT FK_Fone_Pessoa_Pessoa FOREIGN KEY (matricula_pessoa) REFERENCES pessoa (matricula)
);

CREATE TABLE turma (
  codigo varchar(5) NOT NULL,
  codigo_disciplina varchar(5) NOT NULL,
  ano int(11) DEFAULT NULL,
  semestre int(11) DEFAULT NULL,
  turno varchar(6) DEFAULT NULL,
  maximoAlunos int(11) DEFAULT NULL,
  CONSTRAINT PK_Turma PRIMARY KEY (codigo),
  CONSTRAINT FF_Turma_Disciplina FOREIGN KEY (codigo_disciplina) REFERENCES disciplina (codigo)
);



CREATE TABLE professor (
  matricula varchar(10) NOT NULL,
  id_titulo int(11) NOT NULL,
  salario float DEFAULT NULL,
  CONSTRAINT PK_Professor PRIMARY KEY (matricula),
  CONSTRAINT FK_Professor_Pessoa FOREIGN KEY (matricula) REFERENCES pessoa (matricula),
  CONSTRAINT FK_Professor_Titulo FOREIGN KEY (id_titulo) REFERENCES titulo (id)
);



CREATE TABLE aprender (
  matricula_aluno varchar(10) NOT NULL,
  codigo_turma varchar(5) NOT NULL,
  CONSTRAINT PK_Aprender PRIMARY KEY (matricula_aluno,codigo_turma),
  CONSTRAINT FK_aprender_aluno FOREIGN KEY (matricula_aluno) REFERENCES aluno (matricula),
  CONSTRAINT FK_aprender_turma FOREIGN KEY (codigo_turma) REFERENCES turma (codigo)
);


CREATE TABLE ensinar(
	matricula_professor varchar(10) NOT NULL,
	codigo_turma varchar(5) NOT NULL,
	CONSTRAINT PK_Ensinar PRIMARY KEY (matricula_professor,codigo_turma),
	CONSTRAINT FK_Ensinar_Professor FOREIGN KEY (matricula_professor) REFERENCES professor(matricula),
	CONSTRAINT FK_Ensinar_Turma FOREIGN KEY (codigo_turma) REFERENCES turma(codigo)
);

-- corrigindo a coluna nome da tabela titulo.
alter table titulo drop column nome;

alter table titulo add column nome varchar(20);