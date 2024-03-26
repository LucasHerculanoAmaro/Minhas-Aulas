#Está linha cria um banco novo
create database db_escola;
#selecionando o banco que eu vou usar
use db_escola;
#Criar uma tabela
CREATE TABLE Alunos (
	id_aluno CHAR(20),  
	nome CHAR(30),  	
    login CHAR(20),  	
    idade INTEGER,  	
    média REAL )
#seleciona todos os campos da tabela
select * from alunos;
#Inseri informações em um banco de dados
INSERT INTO Alunos (id_aluno, nome, login, idade, média)
	 	VALUES (50000, "Carlito", "carlito@3ta.com", 17, 3.3);
       
INSERT INTO Alunos (id_aluno, nome, login, idade, média)
	VALUES (53688, "goiaba", "goiaba@3ta.com", 25, 3.2);
select * from alunos;    
#Deletauma informação da tabela    
DELETE FROM Alunos WHERE id_aluno = 50000;
select * from alunos;
#Atualiza uma informação na tabela
UPDATE Alunos
SET	idade = idade - 1, média = média + 1 WHERE id_aluno = 53688;
select * from alunos;








    
