CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    contrasena VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE perfiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE usuarios_perfiles (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);

CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255),
    categoria VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255),
    mensaje TEXT,
    fecha_creacion DATETIME,
    estatus VARCHAR(20),
    usuario_id BIGINT,
    curso_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
