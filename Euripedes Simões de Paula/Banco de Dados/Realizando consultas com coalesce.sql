WITH soma_media AS (
    SELECT 
        id_aluno, 
        nome_aluno, 
        ( coalesce(participacao, 0) + coalesce(exercicio, 0) + coalesce(trabalho, 0) + coalesce(prova, 0)) AS total
    FROM 
        tb_usabilidade_design 
)

SELECT 
    id_aluno,
    nome_aluno,
    (total / 4) AS media
FROM 
    soma_media;

