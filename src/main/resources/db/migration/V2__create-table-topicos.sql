create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fechaCreacion datetime not null,
    status tinyint not null,
    autor bigint not null,
    curso varchar(100) not null,
    primary key(id),

    constraint fk_topicos_autor_id foreign key(autor) references usuarios(id)
);