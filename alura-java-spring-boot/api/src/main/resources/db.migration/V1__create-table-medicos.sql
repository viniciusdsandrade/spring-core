create table tb_medicos
(

    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(100)    NOT NULL,
    email         VARCHAR(100)    NOT NULL UNIQUE,
    crm           VARCHAR(6)      NOT NULL UNIQUE,
    especialidade VARCHAR(100)    NOT NULL,
    logradouro    VARCHAR(100)    NOT NULL,
    bairro        VARCHAR(100)    NOT NULL,
    cep           VARCHAR(9)      NOT NULL,
    complemento   VARCHAR(100),
    numero        VARCHAR(20),
    uf            char(2)         NOT NULL,
    cidade        varchar(100)    NOT NULL,

    PRIMARY KEY (id)
);