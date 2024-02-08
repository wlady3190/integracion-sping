# Usa la imagen base de MySQL
FROM mysql:latest

# Establece la contraseña de root de MySQL (cámbiala según sea necesario)
ENV MYSQL_ROOT_PASSWORD=12345

# Expone el puerto 3306 para permitir conexiones externas a MySQL (opcional)
EXPOSE 3306

# Define un volumen para persistir los datos de MySQL
VOLUME /var/lib/mysql
