use db_euripedes;

WITH soma_media AS (
    SELECT 
        id_aluno, 
        nome_aluno, 
        participacao,
        exercicio,
        trabalho,
        prova,
        ( coalesce(participacao, 0) + coalesce(exercicio, 0) + coalesce(trabalho, 0) + coalesce(prova, 0)) AS total
    FROM 
        tb_usabilidade_design 
)

SELECT 
    id_aluno,
    nome_aluno,
    participacao,
    exercicio,
    trabalho,
    prova,
    (total / 4) AS media
FROM 
    soma_media;
    
select * from tb_usabilidade_design;    


# Atualizando Exercícios
update tb_interfaces
set exercicio = 1;

# Atulizando participação 10
update tb_interfaces
set participacao = 10
where 
	id_aluno in (1,5,6,8,9,14,18,24,28);

# Atualizando participação 8   
update tb_interfaces
set participacao = 8
where 
	id_aluno in (4,13,20,21,26,31,35,37);
    
# Atualizando participacao 7
update tb_interfaces
set participacao = 7
where 
	id_aluno in (7,10,15,17,22,27,29,30,33,34,36);
    
# Atualizando participacao 6
update tb_interfaces
set participacao = 6
where 
	id_aluno in (2,3,11,12,14,16,19,23,25,32); 

###########################    

# Atulizando participação 10
update tb_interfaces
set exercicio = exercicio + 10
where 
	id_aluno in (14);

# Atualizando exercicio 8   
update tb_interfaces
set exercicio = exercicio + 8
where 
	id_aluno in (24);
    
# Atualizando participacao 7
update tb_interfaces
set exercicio = exercicio + 7
where 
	id_aluno in (?);
    
# Atualizando participacao 6
update tb_interfaces
set exercicio = exercicio + 6
where 
	id_aluno in (26); 
    
#######################    
    
# Descrição
update tb_interfaces
set descricao = "Colou na prova (chat-gpt)"
where id_aluno = 23;
    
# PROVA
update tb_interfaces
set Prova = 0
where 
	id_aluno in (25);

# Trabalho
update tb_interfaces
set TRABALHO = 1
where 
	id_aluno in (25);
 
select * from tb_interfaces
where prova >= 0;