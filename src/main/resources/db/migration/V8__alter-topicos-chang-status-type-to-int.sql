UPDATE topicos SET status='0';

ALTER TABLE topicos 
CHANGE COLUMN status status INT NOT NULL;