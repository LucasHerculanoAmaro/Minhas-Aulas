################################################################# 
# Não esqueça de alterar o nome para o nome que você deseja 	#
# Use uma nomeclatura padrão e especifica:			#
#	Inicie com "DB_" para nomear um banco de dados;		#
#	Inicie com "TB_" para nomear uma tabela.		#
#################################################################

# Está linha cria um banco novo [não esqueça de alterar o nome para o nome que você deseja
create database db_escola;

# Selecione o banco que acabou de criar
use db_escola;

# Para criar uma tabela use este exemplo
CREATE TABLE tb_alunos (
	id_aluno 	INTEGER,  
	nome 		CHAR(30),  	
    	login 		CHAR(20),  	
    	idade 		INTEGER,  	
    	media 		REAL 
);

# Para selecionar a tabela criada, use o script abaixo [Não haverá dados na tabela se você não inseriu]
select * from tb_alunos;

#Inseri informações em um banco de dados
INSERT INTO tb_alunos (id_aluno, nome, login, idade, média)
	 	VALUES (50000, "Carlito", "carlito@3ta.com", 17, 3.3);
       
INSERT INTO tb_alunos (id_aluno, nome, login, idade, média)
	VALUES (53688, "goiaba", "goiaba@3ta.com", 25, 3.2);

select * from alunos;

#Deletauma informação da tabela    
DELETE FROM tb_alunos WHERE id_aluno = 50000;

select * from tb_alunos;

#Atualiza uma informação na tabela
UPDATE tb_alunos
SET	idade = idade - 1, média = média + 1 WHERE id_aluno = 53688;
select * from tb_alunos;








    
