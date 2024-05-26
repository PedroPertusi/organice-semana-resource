CREATE TABLE semana
(
    id_semana character varying(36) NOT NULL,
    dia_inicio character varying(256) NOT NULL,
    dia_fim character varying(256) NOT NULL,
    semana_descricao character varying(256) NOT NULL,
    semana_id_usuario character varying(256) NOT NULL,

    CONSTRAINT semana_pkey PRIMARY KEY (id_semana)
);