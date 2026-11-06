# Sistema de Gestión de Vacantes

## Descripción
Aplicación de escritorio desarrollada en Java que permite administrar ofertas laborales mediante operaciones CRUD (crear, consultar, actualizar y eliminar), utilizando una base de datos MySQL.

## Tecnologías utilizadas
- Java JDK 17
- Maven
- MySQL
- JDBC
- Git y GitHub

## Requisitos
- Tener instalado Java JDK 17 o superior.
- Tener instalado MySQL Server.
- Tener instalado Maven.

## Base de datos
Crear una base de datos llamada sistema_vacantes y una tabla llamada vacantes con los campos utilizados por el proyecto.

## Configuración de conexión
La aplicación utiliza las variables de entorno:
- DB_USER
- DB_PASSWORD

Estas variables deben configurarse con las credenciales del usuario de MySQL.

## Ejecución
Desde la carpeta del proyecto, ejecutar:

mvn clean compile

Luego ejecutar:

mvn exec:java '-Dexec.mainClass=com.sena.vacantes.Main'

## Funcionalidades
- Registrar vacantes.
- Consultar vacantes.
- Actualizar información de vacantes.
- Eliminar vacantes.

## Autora
Proyecto académico desarrollado para el programa de Análisis y Desarrollo de Software del SENA.
