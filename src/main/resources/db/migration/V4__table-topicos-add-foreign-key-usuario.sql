ALTER TABLE topicos
ADD CONSTRAINT usuario_topico
FOREIGN KEY (id_autor) REFERENCES usuarios (id);