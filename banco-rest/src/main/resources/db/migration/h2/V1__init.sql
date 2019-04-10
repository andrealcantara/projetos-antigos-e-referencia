create table transacao (
     id bigint auto_increment not null,
     descricao varchar(255) not null,
     tipo_transacao integer not null,
     vlr_transacao DECIMAL(20, 2) not null,
     dt_transacao timestamp not null,
     is_ativo integer not null,
     primary key (id)
 );