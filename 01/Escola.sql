CREATE TABLE professor (
  nome VARCHAR(80) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  telefone VARCHAR(15),
  disciplina VARCHAR(50)
)