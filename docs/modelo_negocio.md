# Modelo_Negocio.md

# CodeSnippet Manager

## 1. Descripción del proyecto

CodeSnippet Manager es una aplicación de escritorio desarrollada en Java cuyo objetivo es permitir a los usuarios almacenar, organizar, consultar y reutilizar fragmentos de código fuente.

La aplicación funciona como un repositorio personal donde cada usuario puede guardar snippets de diferentes lenguajes de programación y clasificarlos mediante categorías y etiquetas para facilitar su búsqueda posterior.

Este proyecto está orientado especialmente a estudiantes y desarrolladores que desean disponer de una biblioteca organizada de soluciones, ejemplos y fragmentos de código reutilizables.

---

# 2. Problema que resuelve

Durante el aprendizaje de programación es habitual generar numerosos fragmentos de código útiles que terminan dispersos en distintos proyectos, documentos o archivos de texto.

Esta situación provoca:

* Pérdida de código reutilizable.
* Dificultad para encontrar ejemplos ya realizados.
* Duplicación de trabajo.
* Falta de organización del conocimiento técnico.

CodeSnippet Manager centraliza toda esta información en una única aplicación, permitiendo almacenar y localizar rápidamente cualquier fragmento de código.

---

# 3. Usuarios del sistema

El sistema está pensado para:

### Estudiantes

Alumnos de programación que desean guardar ejercicios, algoritmos o ejemplos para futuras consultas.

### Desarrolladores

Programadores que necesitan almacenar soluciones reutilizables para distintos proyectos.

### Profesores

Docentes que desean disponer de ejemplos organizados para utilizarlos en clase.

---

# 4. Funcionalidades principales

La aplicación permite:

* Crear snippets de código.
* Modificar snippets existentes.
* Eliminar snippets.
* Consultar todos los snippets almacenados.
* Buscar snippets por título.
* Asociar un lenguaje de programación a cada fragmento.
* Asociar una categoría a cada fragmento.
* Asociar múltiples etiquetas a cada fragmento.
* Gestionar usuarios.
* Gestionar categorías.
* Gestionar lenguajes de programación.
* Gestionar etiquetas.

---

# 5. Información almacenada

El sistema almacena la siguiente información:

### Usuarios

* Nombre
* Correo electrónico
* Curso académico

### Lenguajes

* Nombre del lenguaje

### Categorías

* Nombre de la categoría

### Etiquetas

* Nombre de la etiqueta

### Snippets

* Título
* Descripción
* Código fuente
* Fecha de creación
* Fecha de modificación
* Usuario propietario
* Lenguaje asociado
* Categoría asociada
* Etiquetas asociadas

---

# 6. Reglas de negocio

Las principales reglas de funcionamiento son:

* Todo snippet debe tener un título.
* Todo snippet debe tener una descripción.
* Todo snippet debe contener código fuente.
* Todo snippet debe estar asociado a un usuario.
* Todo snippet debe estar asociado a un lenguaje.
* Todo snippet debe estar asociado a una categoría.
* Un usuario puede tener varios snippets.
* Un lenguaje puede estar asociado a varios snippets.
* Una categoría puede estar asociada a varios snippets.
* Un snippet puede tener varias etiquetas.
* Una etiqueta puede estar asociada a varios snippets.
* No pueden existir dos lenguajes con el mismo nombre.
* No pueden existir dos categorías con el mismo nombre.
* El correo electrónico de un usuario debe ser único.

---

# 7. Beneficios del sistema

La utilización de CodeSnippet Manager aporta las siguientes ventajas:

* Mejor organización del código.
* Mayor reutilización de soluciones existentes.
* Reducción del tiempo de búsqueda.
* Centralización de conocimientos técnicos.
* Mayor productividad para estudiantes y desarrolladores.
* Aprendizaje más eficiente mediante la reutilización de ejemplos.

---

# 8. Alcance del proyecto

La primera versión del proyecto incluye:

* Aplicación de escritorio desarrollada con Java Swing.
* Persistencia de datos mediante Hibernate.
* Base de datos MySQL.
* Gestión de snippets, usuarios, categorías, lenguajes y etiquetas.
* Operaciones CRUD completas sobre todas las entidades.
* Relación muchos a muchos entre snippets y etiquetas.

No se contempla en esta versión:

* Aplicación web.
* Gestión de usuarios con autenticación.
* Gestión de permisos.
* Compartición de snippets entre usuarios.
* Almacenamiento en la nube.
* Exportación de snippets.

---

# 9. Tecnologías utilizadas

* Java 21
* Java Swing
* Hibernate ORM
* Jakarta Persistence (JPA)
* MySQL
* Maven
* Git
* GitHub

---

# 10. Conclusión

CodeSnippet Manager es una herramienta diseñada para facilitar la gestión y reutilización de fragmentos de código. Gracias a su sistema de clasificación mediante usuarios, lenguajes, categorías y etiquetas, permite mantener organizado el conocimiento técnico generado durante el aprendizaje y desarrollo de aplicaciones.

La utilización de Hibernate simplifica la persistencia de datos, mientras que Java Swing proporciona una interfaz gráfica sencilla para realizar las operaciones de gestión de forma intuitiva.
