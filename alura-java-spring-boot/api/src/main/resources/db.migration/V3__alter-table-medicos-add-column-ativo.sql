ALTER TABLE tb_medicos
    ADD ativo TINYINT UNSIGNED NOT NULL DEFAULT 1;
UPDATE tb_medicos
SET ativo =1;
