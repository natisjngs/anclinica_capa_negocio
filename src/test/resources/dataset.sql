use proyecto;

INSERT INTO Cuenta (correo, password) values('juanperez@email.com', '123');
INSERT INTO Cuenta (correo, password) values('mariarodriguez@email.com', '234');
INSERT INTO Cuenta (correo, password) values('carlossanchez@email.com', '123');
INSERT INTO Cuenta (correo, password) values('lauralopez@email.com', '234');
INSERT INTO Cuenta (correo, password) values('anatorres@email.com', '234');

INSERT INTO Cuenta (correo, password) values('pac_luisgarcia@email.com', '123');
INSERT INTO Cuenta (correo, password) values('pac_lauramartinez@email.com', '234');
INSERT INTO Cuenta (correo, password) values('pac_miguellopez@email.com', '123');
INSERT INTO Cuenta (correo, password) values('pac_sofiarodriguez@email.com', '234');
INSERT INTO Cuenta (correo, password) values('pac_anagarcia@email.com', '234');

INSERT INTO Cuenta (correo, password) values('admin_1@email.com', 'root123');
INSERT INTO Cuenta (correo, password) values('admin_2@email.com', 'root234');
INSERT INTO Cuenta (correo, password) values('admin_3@email.com', 'root123');
INSERT INTO Cuenta (correo, password) values('admin_4@email.com', 'root234');
INSERT INTO Cuenta (correo, password) values('admin_5@email.com', 'root234');

INSERT INTO Medico (codigo, cedula, nombre, telefono, url_foto, hora_inicio, hora_fin, especialidad, ciudad, estado_usuario) VALUES
    (1, '1234567890', 'Dr. Juan Perez', '123-456-7890', 'url_foto1.jpg', '08:00:00', '16:00:00', 1, 1, 1);
INSERT INTO Medico (codigo, cedula, nombre, telefono, url_foto, hora_inicio, hora_fin, especialidad, ciudad, estado_usuario) VALUES
    (2, '2345678901', 'Dra. Maria Rodriguez', '234-567-8901', 'url_foto2.jpg', '08:00:00', '16:00:00', 2, 2, 1);
INSERT INTO Medico (codigo, cedula, nombre, telefono, url_foto, hora_inicio, hora_fin, especialidad, ciudad, estado_usuario) VALUES
    (3, '3456789012', 'Dr. Carlos Sanchez', '345-678-9012', 'url_foto3.jpg', '08:00:00', '16:00:00', 3, 3, 1);
INSERT INTO Medico (codigo, cedula, nombre, telefono, url_foto, hora_inicio, hora_fin, especialidad, ciudad, estado_usuario) VALUES
    (4, '164958', 'Dra. Laura', '320-756-1649', 'url_foto4.jpg', '08:00:00', '16:00:00', 3, 3, 1);
INSERT INTO Medico (codigo, cedula, nombre, telefono, url_foto, hora_inicio, hora_fin, especialidad, ciudad, estado_usuario) VALUES
    (5, '1667598', 'Dra. Ana', '345-678-9012', 'url_foto3.jpg', '08:00:00', '16:00:00', 2, 2, 1);

INSERT INTO Horario (codigo, dia_cita, hora_inicio, hora_fin, medico_codigo) VALUES
    (1, '2023-10-18', '2023-10-18 08:00:00', '2023-10-18 12:00:00', 1);
INSERT INTO Horario (codigo, dia_cita, hora_inicio, hora_fin, medico_codigo) VALUES
    (2, '2023-10-19', '2023-10-19 09:30:00', '2023-10-19 13:30:00', 2);
INSERT INTO Horario (codigo, dia_cita, hora_inicio, hora_fin, medico_codigo) VALUES
    (3, '2023-10-20', '2023-10-20 14:00:00', '2023-10-20 18:00:00', 3);
INSERT INTO Horario (codigo, dia_cita, hora_inicio, hora_fin, medico_codigo) VALUES
    (4, '2023-10-21', '2023-10-21 10:00:00', '2023-10-21 14:00:00', 4);
INSERT INTO Horario (codigo, dia_cita, hora_inicio, hora_fin, medico_codigo) VALUES
    (5, '2023-10-22', '2023-10-22 13:30:00', '2023-10-22 17:30:00', 5);

INSERT INTO dia_libre (dia_libre, medico_codigo) VALUES
    ('2023-10-18', 1);
INSERT INTO dia_libre (dia_libre, medico_codigo) VALUES
    ('2023-10-19', 2);
INSERT INTO dia_libre (dia_libre, medico_codigo) VALUES
    ('2023-10-20', 3);
INSERT INTO dia_libre (dia_libre, medico_codigo) VALUES
    ('2023-10-21', 4);
INSERT INTO dia_libre (dia_libre, medico_codigo) VALUES
    ('2023-10-22', 5);

INSERT INTO Paciente (codigo, cedula, nombre, fecha_nacimiento, telefono, url_foto, eps, tipo_sangre, ciudad, estado_usuario) VALUES
    (6, '16495785', 'Luis García', '1994-9-6', '432-109-8765', 'url_foto6.jpg', 1, 2, 1, 1);
INSERT INTO Paciente (codigo, cedula, nombre, fecha_nacimiento, telefono, url_foto, eps, tipo_sangre, ciudad, estado_usuario) VALUES
    (7, '16485792', 'Laura Martínez', '1986-6-9', '321-098-7654', 'url_foto7.jpg', 2, 1, 2, 1);
INSERT INTO Paciente (codigo, cedula, nombre, fecha_nacimiento, telefono, url_foto, eps, tipo_sangre, ciudad, estado_usuario) VALUES
    (8,  '16485934', 'Miguel López', '2001-7-12', '210-987-6543', 'url_foto8.jpg', 1, 3, 3, 1);
INSERT INTO Paciente (codigo, cedula, nombre, fecha_nacimiento, telefono, url_foto, eps, tipo_sangre, ciudad, estado_usuario) VALUES
    (9, '199784652', 'Sofía Rodríguez', '1999-8-11', '109-876-5432', 'url_foto9.jpg', 3, 1, 4, 1);
INSERT INTO Paciente (codigo, cedula, nombre, fecha_nacimiento, telefono, url_foto, eps, tipo_sangre, ciudad, estado_usuario) VALUES
    (10, '164978532', 'Ana García', '1997-9-10', '098-765-4321', 'url_foto10.jpg', 2, 3, 3, 1);

INSERT INTO administrador (codigo) VALUES
    (11), (12), (13), (14), (15);

INSERT INTO Cita (codigo, estado_cita, paciente_codigo, medico_codigo, fecha_creacion, fecha_cita, motivo) VALUES
      (1, 1, 6, 1, '2023-10-13', '2023-10-18', 'Consulta médica de seguimiento'),
      (2, 1, 7, 2, '2023-10-14', '2023-11-19', 'Control de medicación'),
      (3, 1, 8, 3, '2023-10-15', '2023-11-20', 'Dolor persistente'),
      (4, 1, 9, 4, '2023-10-16', '2023-11-21', 'Examen de sangre anual'),
      (5, 1, 10, 5, '2023-10-17', '2023-11-22', 'Consulta pediátrica');

INSERT INTO Atencion (cita_codigo, codigo, especializacion, fecha_atencion, diagnostico, notas_medicas, tratamiento) VALUES
       (1, 1, 1, '2023-10-18 08:00:00', 'Gripe común', 'Descanso y medicamentos', 'N/A'),
       (2, 2, 2, '2023-10-19 09:30:00', 'Fractura de brazo', 'Inmovilización y cirugía', 'Requiere seguimiento'),
       (3, 3, 1, '2023-10-20 14:00:00', 'Dolor de cabeza', 'Analgésicos y reposo', 'Mejorar la alimentación'),
       (4, 4, 2, '2023-10-21 10:00:00', 'Infección de garganta', 'Antibióticos y garganta caliente', 'Evitar el frío'),
       (5, 5, 1, '2023-10-22 13:30:00', 'Examen de rutina', 'N/A', 'Salud en general');

INSERT INTO PQRS (cita_codigo, codigo, estado_pqrs, fecha_creacion, tipo_pqrs, imagen, motivo) VALUES
    (1, 1, 1, '2023-10-18', 1, 'fotoRes1.jpg', 'Sugerencia de mejora en el servicio'),
    (2, 2, 1, '2023-10-19', 2, 'fotoRes2.jpg', 'Queja sobre el tiempo de espera'),
    (3, 3, 1, '2023-10-20', 1, 'fotoRes3.jpg', 'Solicitud de información sobre un tratamiento'),
    (4, 4, 1, '2023-10-21', 3, 'fotoRes4.jpg', 'Elogio al médico de atención'),
    (5, 5, 1, '2023-10-22', 3, 'fotoRes5.jpg', 'Otro asunto importante');

INSERT INTO Mensaje (codigo, cuenta_codigo, pqrs_codigo, fecha_creacion, texto) VALUES
    (1, 6, 1, '2023-10-18 10:00:00', 'Hola, ¿cómo te encuentras?'),
    (2, 7, 2, '2023-10-19 15:30:00', 'Necesito una receta para mis medicamentos.'),
    (3, 8, 3, '2023-10-20 09:15:00', '¿Puedo cambiar mi cita para el próximo viernes?'),
    (4, 9, 4, '2023-10-21 13:45:00', 'Los síntomas han empeorado.'),
    (5, 10, 5, '2023-10-22 17:00:00', 'Gracias por la atención de hoy.');
