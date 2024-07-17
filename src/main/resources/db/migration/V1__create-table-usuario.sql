create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correoElectronico varchar(100) not null,
    contrasenia varchar(300) not null,
    perfil varchar(100) not null,
    primary key(id)
);