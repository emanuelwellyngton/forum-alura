CREATE TABLE topicos (

  id INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL UNIQUE,
  mensagem TEXT NOT NULL,
  data_criacao DATE NOT NULL,
  id_autor INT NOT NULL,
  id_curso INT NOT NULL,
  
  PRIMARY KEY (id)
  
  );