# SecureAuthSystem 🛡️ *Backend API para Gestión de Usuarios y Autenticación*

## Descripción

**SecureAuthSystem** es una API backend diseñada para la gestión de usuarios y autenticación con roles, utilizando **arquitectura hexagonal** y **Spring Security JWT**. Este sistema maneja la separación entre los **datos personales** de los usuarios (como nombre, apellidos, etc.) y las **credenciales de acceso** (como nombre de usuario, contraseña, etc.). La aplicación proporciona un **CRUD completo** para la gestión de **Personas** y **Usuarios**, permitiendo crear, actualizar, eliminar y listar ambos tipos de entidades de forma segura.

Con la implementación de **JWT**, se asegura una autenticación sin estado, optimizando el acceso a recursos. Además, se incluyen **pruebas unitarias** con **JUnit** y **Mockito** para garantizar el correcto funcionamiento de la lógica de seguridad y acceso.

---

## Funcionalidades

- **Gestión de Personas**: Crear, actualizar, eliminar y listar personas con datos personales.
- **Gestión de Usuarios**: Crear, actualizar, eliminar y listar usuarios asociados a personas con autenticación segura.
- **Autenticación JWT**: Implementación de tokens JWT para autenticación sin estado.
- **Seguridad**: Uso de **Spring Security** para proteger los endpoints.
- **Pruebas Unitarias**: Uso de **JUnit** y **Mockito** para garantizar la calidad del código y el correcto funcionamiento del sistema.

---

## Tecnologías

- **Spring Boot**: Framework para construir aplicaciones Java.
- **Spring Security**: Framework para manejar la autenticación y autorización.
- **JWT (JSON Web Tokens)**: Técnica de autenticación sin estado.
- **JPA (Java Persistence API)**: Para la gestión de bases de datos y operaciones CRUD.
- **JUnit y Mockito**: Para pruebas unitarias y simulaciones de objetos.

---

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tu_usuario/SecureAuthSystem.git
