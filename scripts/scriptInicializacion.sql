INSERT INTO public.rol(
            id, descripcion, estado)
    VALUES ('ADMINISTRADOR', 'Administrador del Sistema', 'ACT');
INSERT INTO public.rol(
            id, descripcion, estado)
    VALUES ('DOCTOR', 'Profesor', 'ACT');
    INSERT INTO public.rol(
            id, descripcion, estado)
    VALUES ('PACIENTE', 'Coordinador', 'ACT');
    INSERT INTO public.rol(
            id, descripcion, estado)
    VALUES ('ASISTENTE', 'Alumno', 'ACT');

INSERT INTO public.usuario(
            identificacion, apellido, direccion, email, estado, nombre, password)
    VALUES ('1010101010', 'LOPEZ', '12 de octubre', 'calopezf@gmail.com', 'ACT', 'CRISTIAN', '1010101010');
INSERT INTO public.usuario_rol(
            rol_id, email)
    VALUES ('ADMINISTRADOR','calopezf@gmail.com');