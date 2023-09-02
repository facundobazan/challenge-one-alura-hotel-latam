# Hotel Alura

![Badge en Desarrollo](https://img.shields.io/badge/STATUS-EN%20DESARROLLO-green) ![JDK17](https://img.shields.io/badge/JAVA-OPEN--JDK%2017-red)

### Desafío UNO | Java | Back-end |

<p align="center" >
     <img width="300" height="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

## Indice

- [Hotel Alura](#hotel-alura)
  - [Índice](#indice)
  - [Descripción del proyecto](#descripcion-del-proyecto)
  - [Estado del proyecto](#estado-del-proyecto)
  - [Características de la aplicación y demostración](#caracteristicas-de-la-aplicacion-y-demostracion)
  - [Acceso al proyecto](#acceso-al-proyecto)
  - [Tecnologías utilizadas](#tecnologias-utilizadas)
  - [Personas Contribuyentes](#personas-contribuyentes)
  - [Autores - Desarrolladores del Proyecto](#autores---desarrolladores-del-proyecto)
  - [Licencia](#licencia)
  - [Conclusión](#conclusion)

---

## Descripcion del proyecto

El proyecto Hotel Alura es una aplicación de gestión de reservas y huéspedes desarrollada en Java Swing con JDK 17. Permite a los usuarios realizar las siguientes acciones:

- **Login de usuario**: Los usuarios pueden iniciar sesión con el usuario y la contraseña `admin`. También tienen la opción de registrarse utilizando una tabla de usuarios que almacena nombres de usuario y contraseñas encriptadas con `Argon2`.

- **Gestión de Reservas**: Los usuarios pueden realizar reservas proporcionando fechas de entrada y salida, y seleccionar entre tres formas de pago: `efectivo`, `tarjeta` de crédito y tarjeta de `débito`.

- **Edición de Reservas**: Los usuarios tienen la capacidad de editar las fechas de las `reservas` existentes.

- **Listado de Huéspedes y Reservas**: El sistema muestra un listado de huéspedes y reservas, permitiendo la búsqueda y eliminación de ambos desde una interfaz de tabla.

## Estado del proyecto

Este proyecto se encuentra en desarrollo activo. Se están realizando mejoras continuas y se agregan nuevas características regularmente.

## Caracteristicas de la aplicacion y demostracion

- `Inicio de sesión` de usuario.
- `Registro de usuarios` con `encriptación` Argon2.
- `Reservas` de habitaciones con fechas de entrada y salida.
- Tres `opciones de pago`: efectivo, tarjeta de crédito y tarjeta de débito.
- `Edición` de fechas de `reserva`.
- `Listado de huéspedes y reservas` con funciones de `búsqueda` y `eliminación`.

---

### Presentacion de la UI

#### Menu principal

<p align="center">
  <img src="./images/menu_principal.png" alt="Vista de menu principal">
</p>

#### Registro de usuario

<p align="center">
  <img src="./images/registro_usuario.png" alt="Vista de registro de usuario">
</p>

#### Login | Inicio de sesion

<p align="center">
  <img src="./images/login.png" alt="Vista inicio de sesion">
</p>

#### Menu de usuario

<p align="center">
  <img src="./images/menu_usuario.png" alt="Vista de menu de usuario">
</p>

#### Sistema de reservas

<p align="center">
  <img src="images/sistema_reserva.png" alt="Vista del sistema de reservas">
</p>

#### Registro de huesped

<p align="center">
  <img src="./images/registro_huesped.png" alt="Vista de registro de huesped">
</p>

#### Sistema de busqueda - Reservas

<p align="center">
  <img src="./images/busqueda_reserva.png" alt="Vista de Sistema de busqueda de reservas">
</p>

#### Edicion de reserva

<p align="center">
  <img src="./images/edicion_reserva.png" alt="Vista de edicion de reserva">
</p>

#### Sistema de busqueda - Huespedes

<p align="center">
  <img src="./images/busqueda_huesped.png" alt="Vista de Sistema de busqueda de huespedes">
</p>

#### Edicion de huesped

<p align="center">
  <img src="./images/edicion_huesped.png" alt="Vista de edicion de huesped">
</p>

## Acceso al proyecto

El proyecto está alojado en [GitHub](https://github.com/facundobazan/challenge-one-alura-hotel-latam).

## Tecnologias utilizadas

El proyecto utiliza las siguientes tecnologías y herramientas:

- `Java Swing`
- `hibernate`
- `Argon2`
- `jakarta`
- `jcalendar`

## Desarrolladores del proyecto

- [Facundo Bazán](https://github.com/facundobazan)

### Proyecto Base

| [<img src="https://avatars.githubusercontent.com/u/37356058?v=4" width=115><br><sub>Camila Fernanda Alves</sub>](https://github.com/camilafernanda) |  [<img src="https://avatars.githubusercontent.com/u/71970858?v=4" width=115><br><sub>Ellen Pimentel</sub>]([https://github.com/guilhermeonrails](https://github.com/ellenpimentel)) |  [<img src="https://avatars.githubusercontent.com/u/91544872?v=4" width=115><br><sub>Génesys Rondón</sub>](https://github.com/genesysaluralatam) |
| :---: | :---: | :---: |

## Licencia

Este proyecto está bajo la `Licencia MIT`

La Licencia MIT es una licencia de código abierto que permite a otros utilizar, modificar y distribuir este software en proyectos privados o comerciales. Los contribuyentes son bienvenidos y se alienta la colaboración. Para obtener más detalles, consulta el archivo [Licencia](LICENSE).

## Conclusion

Hotel Alura es un proyecto en desarrollo que ofrece una solución de gestión de reservas de habitaciones y huéspedes. Está diseñado en Java Swing con JDK 17 y utiliza encriptación Argon2 para garantizar la seguridad de las contraseñas de los usuarios. Siéntete libre de explorar el código fuente en el repositorio de GitHub y contribuir al desarrollo del proyecto. ¡Esperamos que este proyecto te sea útil!