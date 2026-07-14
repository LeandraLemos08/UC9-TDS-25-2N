 CREATE TABLE aluno (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) not null,
  turma VARCHAR(50) not null,
  email VARCHAR(50) not null
 )