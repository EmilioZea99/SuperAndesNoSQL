# SuperAndesNoSQL

Bienvenidos a la aplicación **SuperAndesNoSQL**.

Este proyecto implementa una aplicación que conecta con MongoDB y permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en las colecciones de **Productos**, **Proveedores**, **Sucursales**, **Bodegas**, **Ordenes de Compra**, y **Detalles de Orden**, además de consultas avanzadas utilizando **pipelines de agregación** para resolver problemas complejos.

## Tabla de Contenidos
1. [Requisitos Previos](#requisitos-previos)
2. [Instalación](#instalación)
3. [Configuración](#configuración)
4. [Ejecución](#ejecución)
5. [Implementación de Consultas CRUD](#implementación-de-consultas-crud)
6. [Consultas Avanzadas](#consultas-avanzadas)
7. [Tags del Proyecto](#tags-del-proyecto)

---

## Requisitos Previos

Antes de instalar la aplicación, asegúrate de tener instalados los siguientes componentes:

1. **Java 17**: La aplicación está desarrollada en Java, por lo que necesitas tener instalado JDK 17.
2. **Maven**: Utilizado para la gestión de dependencias y la construcción del proyecto.
3. **MongoDB**: La base de datos NoSQL utilizada por la aplicación.
4. **Postman**: Para realizar pruebas de las operaciones CRUD y consultas avanzadas.

---

## Instalación

Sigue estos pasos para instalar la aplicación:

1. **Clonar el repositorio**:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    cd demo
    ```

2. **Construir el proyecto con Maven**:
    ```sh
    ./mvnw clean install
    ```

---

## Configuración

La conexión a MongoDB se define en el archivo `application.properties`, ubicado en la carpeta `src/main/resources` de la aplicación. Ahí se debe colocar el **connection string** para conectar con el cluster de MongoDB.

1. **Editar el archivo `application.properties`**:
    ```properties
    spring.data.mongodb.uri=mongodb+srv://<USUARIO>:<CONTRASEÑA>@<CLUSTER>.mongodb.net/<BASE_DE_DATOS>
    spring.data.mongodb.database=<BASE_DE_DATOS>
    ```

2. **Recomendación**:
    Para que la aplicación funcione sin problemas desde cualquier ubicación, se recomienda **permitir el acceso desde todas las direcciones IP** al cluster de MongoDB.

    **Pasos para habilitar el acceso a todas las IPs**:
    1. Ir a **MongoDB Atlas**.
    2. Acceder a tu **cluster**.
    3. En la sección de **Network Access**, hacer clic en "Add IP Address".
    4. Seleccionar la opción "**Allow access from anywhere**" (0.0.0.0/0).
    5. Guardar los cambios.

---

## Ejecución

Para ejecutar la aplicación, ve a:
demo>src>main>java>DemoApplication.java y corre este ultimo archivo
