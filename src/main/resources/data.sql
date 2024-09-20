INSERT INTO Usuario(dni, email, password, creditos, nombre, apellido, rol, activo)
VALUES(12345678, 'test@unlam.edu.ar', 'test', 100, 'Juan', 'Perez', 'ADMIN', true);

-- Insertar datos en la tabla Parada
INSERT INTO Parada (nombre) VALUES
('Parada A'),
('Parada B'),
('Parada C'),
('Parada D'),
('Parada E'),
('Parada F'),
('Parada G');

-- Insertar datos en la tabla Recorrido
INSERT INTO Recorrido (descripcion, destino, duracion, ramal, salida, tipoRecorrido, tipoViaje, titulo, visibilidad) VALUES
('Descripción del recorrido 1', 'Destino 1', '30 minutos', 'Ramal 1', 'Salida 1', 'Charter', 'Ida', 'Recorrido 1', 'PUBLICO'),
('Descripción del recorrido 2', 'Destino 2', '45 minutos', 'Ramal 2', 'Salida 2', 'Regular', 'Ida y vuelta', 'Recorrido 2', 'PRIVADO'),
('Descripción del recorrido 3', 'Destino 3', '1 hora', 'Ramal 3', 'Salida 3', 'Charter', 'Ida', 'Recorrido 3', 'PUBLICO'),
('Descripción del recorrido 4', 'Destino 4', '1 hora 15 minutos', 'Ramal 4', 'Salida 4', 'Regular', 'Ida y vuelta', 'Recorrido 4', 'PRIVADO'),
('Descripción del recorrido 5', 'Destino 5', '2 horas', 'Ramal 5', 'Salida 5', 'Charter', 'Ida', 'Recorrido 5', 'PUBLICO'),
('Descripción del recorrido 6', 'Destino 6', '50 minutos', 'Ramal 6', 'Salida 6', 'Regular', 'Ida y vuelta', 'Recorrido 6', 'PRIVADO'),
('Descripción del recorrido 7', 'Destino 7', '1 hora 30 minutos', 'Ramal 7', 'Salida 7', 'Charter', 'Ida', 'Recorrido 7', 'PUBLICO');
