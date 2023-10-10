-- Cuidado que cada SGDB tiene su forma de crear tablas y datos
DROP TABLE IF EXISTS Temperaturas;
CREATE TABLE Temperaturas (
      id INT AUTO_INCREMENT PRIMARY KEY,
      localidad VARCHAR(255),
      provincia VARCHAR(255),
      tempMax DOUBLE,
      hsTempMax TIME,
      tempMin DOUBLE,
      hsTempMin TIME,
      precipitacion DOUBLE,
      dia DATE
);