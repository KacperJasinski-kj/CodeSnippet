# Especificaciones técnicas

## Estructura del proyecto

La aplicación está organizada en distintos paquetes para separar mejor las responsabilidades y facilitar el mantenimiento del código.

La estructura principal es la siguiente:

```text
com.codesnippet
│
├── config
├── dao
├── exception
├── model
├── view
└── Main.java
```

### Explicación de los paquetes

#### model

Aquí se encuentran las entidades principales del proyecto:

* User
* Snippet
* Language
* Category
* Tag

Estas clases representan las tablas de la base de datos y sus relaciones.

---

#### dao

Contiene las clases encargadas de realizar las operaciones de acceso a datos mediante Hibernate.

Entre las operaciones disponibles se encuentran:

* Guardar registros
* Buscar registros
* Modificar registros
* Eliminar registros

Además, todos los DAO heredan de una clase genérica llamada `GenericDAO`, que permite reutilizar las operaciones CRUD comunes.

---

#### view

Contiene toda la interfaz gráfica desarrollada con Java Swing.

Dentro de este paquete se encuentran:

* Ventanas principales
* Paneles de gestión
* Formularios de entrada de datos
* Tablas de visualización

Clases principales:

* MainWindow
* SnippetPanel
* UserPanel
* LanguagePanel
* CategoryPanel
* TagPanel
* SnippetDialog

---

#### config

Contiene la configuración relacionada con Hibernate y la creación de conexiones mediante `EntityManager`.

Clase principal:

* HibernateUtil

---

#### exception

Contiene excepciones personalizadas utilizadas para controlar errores de validación y ejecución.

---

# Tecnologías utilizadas

Las tecnologías utilizadas en el proyecto son:

| Tecnología | Uso                     |
| ---------- | ----------------------- |
| Java       | Lenguaje principal      |
| Maven      | Gestión del proyecto    |
| Hibernate  | Persistencia de datos   |
| JPA        | Mapeo objeto-relacional |
| MySQL      | Base de datos           |
| Java Swing | Interfaz gráfica        |
| GitHub     | Control de versiones    |

---

# Configuración de Hibernate

Hibernate se utiliza para conectar la aplicación Java con la base de datos MySQL.

La configuración se encuentra en:

```text
src/main/resources/META-INF/persistence.xml
```

En este archivo se configuran:

* Usuario de MySQL
* Contraseña
* URL de conexión
* Driver JDBC
* Proveedor de Hibernate
* Configuración de persistencia

Ejemplo:

```xml
<property name="jakarta.persistence.jdbc.url"
value="jdbc:mysql://localhost:3306/codesnippet_db"/>
```

La gestión de conexiones se realiza mediante la clase `HibernateUtil`.

---

# Base de datos

La base de datos utilizada se denomina:

```text
codesnippet_db
```

Las tablas principales son:

* users
* snippets
* languages
* categories
* tags
* snippet_tags

Relaciones principales:

* Un usuario puede tener varios snippets.
* Un lenguaje puede estar asociado a varios snippets.
* Una categoría puede contener varios snippets.
* Un snippet puede tener varias etiquetas.
* Una etiqueta puede estar asociada a varios snippets.

La relación muchos a muchos entre snippets y etiquetas se implementa mediante la tabla intermedia:

```text
snippet_tags
```

---

# Arquitectura utilizada

La aplicación utiliza una arquitectura simplificada basada en capas:

```text
Vista
↓
DAO
↓
Hibernate
↓
MySQL
```

Los paneles de la interfaz gráfica interactúan directamente con los DAO para realizar operaciones CRUD sobre la base de datos.

---

# Patrón MVC

La aplicación sigue parcialmente el patrón MVC.

## Modelo

Representa los datos de la aplicación y las entidades persistentes.

Ejemplos:

* User
* Snippet
* Language
* Category
* Tag

## Vista

Representa la interfaz gráfica desarrollada con Swing.

Ejemplos:

* MainWindow
* SnippetPanel
* UserPanel
* LanguagePanel
* CategoryPanel
* TagPanel
* SnippetDialog

## Control

El acceso a los datos se realiza directamente mediante los DAO, por lo que no existe una capa de control independiente en la versión actual del proyecto.

---

# Decisiones técnicas

## Uso de Hibernate

Se decidió utilizar Hibernate para simplificar la persistencia de datos y reducir la cantidad de consultas SQL escritas manualmente.

## Uso de Swing

Java Swing fue utilizado para desarrollar la interfaz gráfica de escritorio solicitada para el proyecto.

## Uso de Maven

Maven se utiliza para gestionar dependencias externas y organizar la estructura del proyecto.

## Uso de GenericDAO

Se implementó una clase genérica para centralizar las operaciones CRUD comunes y evitar duplicar código en cada DAO específico.

## Arquitectura modular

El proyecto se divide en paquetes independientes para facilitar el mantenimiento, la reutilización de código y futuras ampliaciones.
