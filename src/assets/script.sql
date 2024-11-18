CREATE TABLE FormContact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    problematica VARCHAR(255) NOT NULL,
    imagenes LONGBLOB
);