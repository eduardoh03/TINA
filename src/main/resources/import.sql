INSERT INTO tb_categoria (name) VALUES ('Cabelo');
INSERT INTO tb_categoria (name) VALUES ('Maquiagem');

INSERT INTO tb_procedimento (name,created_At) VALUES ('Corte Feminino',NOW());
INSERT INTO tb_procedimento (name,created_At) VALUES ('Corte Masculino',NOW());
INSERT INTO tb_procedimento (name,created_At) VALUES ('Hidratação',NOW());
INSERT INTO tb_procedimento (name,created_At) VALUES ('Maquiagem Artistica',NOW());
INSERT INTO tb_procedimento (name,created_At) VALUES ('Maquiagem + Cílios',NOW());

INSERT INTO tb_procedimento_categoria (procedimento_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_procedimento_categoria (procedimento_id, categoria_id) VALUES (2, 1);
INSERT INTO tb_procedimento_categoria (procedimento_id, categoria_id) VALUES (3, 1);
INSERT INTO tb_procedimento_categoria (procedimento_id, categoria_id) VALUES (4, 2);
INSERT INTO tb_procedimento_categoria (procedimento_id, categoria_id) VALUES (5, 2);