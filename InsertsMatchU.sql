-- ============================================
-- INSERTS: hobbies
-- ============================================

INSERT INTO hobbies (nombre, categoria) VALUES
('Fútbol', 'Deportes'),
('Gimnasio', 'Deportes'),
('Lectura', 'Intelectual'),
('Videojuegos', 'Entretenimiento'),
('Música', 'Arte'),
('Cine', 'Entretenimiento'),
('Viajar', 'Lifestyle'),
('Programación', 'Tecnología');

-- ============================================
-- INSERTS: estudiantes
-- contraseña = 123456 
-- ============================================

INSERT INTO estudiantes 
(nombre, apellidos, correo, contrasena, carrera, semestre, bio, activo)
VALUES
('Juan', 'Pérez', 'juan@test.com',
'$2a$10$R4jHDB0gJBCjIwlqF2yHEe99ur2Qel806sBw5DkpnZCB9S5oN60ZC',
'Ingeniería en Software', 5, 'Me gusta programar y el gym', 1),

('Ana', 'López', 'ana@test.com',
'$2a$10$R4jHDB0gJBCjIwlqF2yHEe99ur2Qel806sBw5DkpnZCB9S5oN60ZC',
'Diseño Gráfico', 4, 'Amante del arte y la música', 1),

('Carlos', 'Ramírez', 'carlos@test.com',
'$2a$10$R4jHDB0gJBCjIwlqF2yHEe99ur2Qel806sBw5DkpnZCB9S5oN60ZC',
'Ingeniería Industrial', 6, 'Deportes y viajes', 1),

('Sofía', 'Martínez', 'sofia@test.com',
'$2a$10$R4jHDB0gJBCjIwlqF2yHEe99ur2Qel806sBw5DkpnZCB9S5oN60ZC',
'Psicología', 3, 'Lectura y cine', 1),

('Luis', 'Hernández', 'luis@test.com',
'$2a$10$R4jHDB0gJBCjIwlqF2yHEe99ur2Qel806sBw5DkpnZCB9S5oN60ZC',
'Ingeniería en Sistemas', 7, 'Videojuegos y programación', 1);

-- ============================================
-- INSERTS: estudiante_hobby
-- ============================================

INSERT INTO estudiante_hobby VALUES
(1,1),(1,2),(1,8),
(2,5),(2,6),
(3,1),(3,7),
(4,3),(4,6),
(5,4),(5,8);

-- ============================================
-- INSERTS: interacciones
-- ============================================

INSERT INTO interacciones (fecha_hora, id_emisor, id_receptor, tipo) VALUES
(NOW(), 1, 2, 'LIKE'),
(NOW(), 2, 1, 'LIKE'),
(NOW(), 1, 3, 'LIKE'),
(NOW(), 3, 1, 'SKIP'),
(NOW(), 1, 4, 'SKIP'),
(NOW(), 5, 1, 'LIKE');

-- ============================================
-- INSERTS: matches
-- ============================================

INSERT INTO matches (activo, fecha_match, id_estudiante1, id_estudiante2)
VALUES (1, NOW(), 1, 2);

-- ============================================
-- INSERTS: mensajes
-- ============================================

INSERT INTO mensajes (leido, fecha_envio, id_match, id_remitente, contenido)
VALUES
(0, NOW(), 1, 1, 'Hola Ana 👋'),
(0, NOW(), 1, 2, 'Hola Juan 😊'),
(0, NOW(), 1, 1, '¿Qué te gusta hacer?');