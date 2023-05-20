ALTER TABLE topicos
ADD CONSTRAINT curso_topico
FOREIGN KEY (id_curso) REFERENCES cursos (id);