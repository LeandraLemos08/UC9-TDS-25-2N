CREATE TABLE aluno (

	id SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	turma VARCHAR(20) NOT NULL,
	email VARCHAR(20) UNIQUE
)

select * from aluno