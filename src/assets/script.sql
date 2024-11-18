CREATE TABLE FormContact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    descripcion TEXT NOT NULL,
    problematica VARCHAR(255) NOT NULL
);
CREATE TABLE Form_Imagenes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    form_contact_id BIGINT NOT NULL,
    imagen LONGBLOB NOT NULL,
    FOREIGN KEY (form_contact_id) REFERENCES FormContact(id) ON DELETE CASCADE
);