    # Especificaciones técnicas

## Estructura del proyecto

La aplicación está organizada en distintas carpetas para separar mejor el código y que sea más fácil de mantener.

La estructura principal es la siguiente:

```text
com.codesnippet
│
├── config
├── controller
├── dao
├── exception
├── model
├── service
├── view
└── Main.java
```

### Explicación de los paquetes

#### model
Aquí se encuentran las entidades principales del proyecto:

- User
- Snippet
- Language
- Category
- Tag

Estas clases representan las tablas de la base de datos.

---

#### dao
Contiene las clases encargadas de realizar operaciones CRUD con Hibernate.

Ejemplo:
- guardar
- eliminar
- modificar
- buscar

---

#### service
Aquí se encuentra la lógica de negocio y las validaciones de datos.

Por ejemplo:
- comprobar campos vacíos
- validar títulos
- controlar errores

---

#### controller
Los controladores conectan la interfaz gráfica con los servicios.

Reciben los eventos de los botones y llaman a los métodos necesarios.

---

#### view
Contiene toda la interfaz gráfica realizada con Java Swing.

Aquí se encuentran:
- ventanas
- paneles
- formularios
- tablas

---

#### config
Contiene la configuración de Hibernate y la conexión con la base de datos.

---

#### exception
Contiene excepciones personalizadas para controlar errores de validación.

---

# Tecnologías utilizadas

Las tecnologías utilizadas en el proyecto son:

| Tecnología | Uso |
|---|---|
| Java | Lenguaje principal |
| Maven | Gestión del proyecto |
| Hibernate | Persistencia de datos |
| JPA | Mapeo objeto-relacional |
| MySQL | Base de datos |
| Java Swing | Interfaz gráfica |
| GitHub | Control de versiones |

---

# Configuración de Hibernate

Hibernate se utiliza para conectar Java con la base de datos MySQL.

La configuración se encuentra en:

```text
resources/META-INF/persistence.xml
```

En este archivo se configura:
- usuario de MySQL
- contraseña
- URL de conexión
- driver JDBC
- dialecto de Hibernate

Ejemplo:

```xml
<property name="jakarta.persistence.jdbc.url"
value="jdbc:mysql://localhost:3306/codesnippet_db"/>
```

---

# Base de datos

La base de datos utilizada se llama:

```text
codesnippet_db
```

Las tablas principales son:
- users
- snippets
- languages
- categories
- tags
- snippet_tags

La tabla `snippet_tags` se usa para la relación muchos a muchos entre snippets y etiquetas.

---

# Arquitectura utilizada

La aplicación sigue una arquitectura en capas:

```text
Vista
↓
Controller
↓
Service
↓
DAO
↓
Hibernate
↓
MySQL
```

Esto permite separar mejor las responsabilidades de cada parte del proyecto.

---

# Patrón MVC

La aplicación utiliza el patrón MVC:

## Modelo
Representa los datos y entidades.

Ejemplo:
- Snippet
- User
- Tag

## Vista
Es la interfaz gráfica creada con Swing.

Ejemplo:
- MainWindow
- SnippetPanel

## Controlador
Conecta la vista con la lógica del programa.

Ejemplo:
- SnippetController

---

# Decisiones técnicas

## Uso de Hibernate
Se decidió usar Hibernate para facilitar la persistencia de datos y evitar escribir demasiadas consultas SQL manualmente.

## Uso de Swing
Swing fue utilizado porque es el framework solicitado para la interfaz gráfica.

## Uso de Maven
Maven se utilizó para gestionar las dependencias y organizar el proyecto.

## Arquitectura por capas
Se decidió separar el proyecto en capas para mantener el código más organizado y fácil de entender.