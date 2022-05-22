# Sucursal-mas-cercana

### Features

##Sucursal.

   Esta version resuelve encontrar la hubicación de la sucursal mas cercana


- Documentado con Swagger y conectado con MySQL
- contiene una capa por cada estereotipo, mas la capa proyeccion en donde contiene una interfas de tipo Dto. tmabien contiene una capa para ejecutar y crear los recursos SQL, tales como la inserción de valores y la creación del SP.

- te testea a través de la capa del controlador. (primera vez usando este consepto sobre esta capa)
- las dependecias implementadas son:
######  Spring data JPA.Spring data JPA
######  Lombok
###### MySQL connector
###### MySQL connector
###### Spring Web
###### springdoc-openapi-ui / aplicacion sencilla sin configuración de swagger

- Se conecta a una base de datos llamada "*sucursal*"
###### spring.datasource.url=jdbc: mysql://localhost:3306/sucursal
- Se puede visualizar como opera interiormente
###### spring.jpa.show-sql= true
- Todo el contenido sera borrardo al detener la aplicacion
######  Hibernate: drop table if exists sucursal

-----------
######    GET/latitud/longitud

Este metodo mapea la entidad con una Interfáz de tipo Dto que solo es de lectura y que devuelve el horario y la dirección. 
Tambien este metodo llama un SP que devuelve una lista DESC, de la sucursal mas prox. con el objetivo de que mas adelante se pueda resolver con  el horario disponible de la segunda; en el caso de que el primero esté cerrado.
######    GET/buscar_por_id/id
Devuelve un registro conforme al id.
######  Post/crear_sucursal
Crea un nuevo registros en la base de datos
######  Put/modificar_sucursal/id
Modifica un registro existente en base al id
######  Delete/eliminar-por-id/id
elimina registro por id si existe.



