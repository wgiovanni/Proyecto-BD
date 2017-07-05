# Proyecto-BD
Proyecto de Bases de Datos :trollface:

### [Manual de usuario](https://pipriles.github.io/Proyecto-BD/)

Lo primero que aparece al abrir el programa es una ventana de login, en el cual el usuario es el mismo de postgres, este usuario pueder ser tanto administrador como trabajador pero programa se encargara de determinar que tipo de usuario es para el inicio de sesion adecuado.

El primer panel es la interfaz del **login**, para crear un usuario para acceder se debe crear desde postgres con un usuario que si tenga permisos para crear otros usuarios, y luego asignarle el rol al usuario, el cual puede ser administrador o trabajador.

### Interfaz de administrador

Al iniciar sesion con un usuario administrador podemos hacer operaciones de insercion y eliminacion.
Si seleccionamos la opcion de **Insertar** podemos escoger que tipo de elemento vamos a agregar a la base de datos, para luego agregar la informacion acerca de este elemento.
Por otro lado, si seleccionamos la opcion de **Eliminar** podremos de igual forma seleccionar el tipo de elemento a borrar, para luego identificar el objeto con su clave.

### Interfaz de trabajador

Si llegaste hasta aqui podras darte cuenta que el administrador no puede consultar datos pero si puede operar encima de la base de datos, bueno, los trabajadores si pueden realizar operaciones de consulta, aunque en nuestra interfaz tenemos solo tres tipos que son los que nos piden.

Como podemos ver tenemos tres tipos de consultas:
  - La primera consulta muestra el producto mas costoso que se produce en la Chocolatera Cimarrón, sus distintas etapas de produccion y que trabajadores estan vinculados en cada etapa.
  - La segunda consulta muestra los productos vendidos en el mes de mayo de 2016 y las ganancias de cada Tienda Cacao Venezolano clasificados por estado y ordenados por la clave del producto en forma ascendente y por la ganancia de forma descendente.
  - Por ultimo, la tercera consulta muestra el producto que ha ganado mayor cantidad de premios y toda la informacion del evento y de los premios otorgados.

Basta con precionar el boton correspondiente de cada consulta para tener los resultados.

Para ejecutar:
``` sh
java -cp .:lib/driver.jar home
```

- Cosas por hacer:
  - [X] Conexion a la base de datos no por query
  - [X] Hacer conexion por usuario
  - [X] Retocar la interfaz, y verificar que funciona bien
  - [X] Llenar la base de datos
  - [ ] Limpiar el codigo haciendolo mas modular
  - [ ] Validacion de los tipos de datos cuando se insertan
  - [X] Manual de usuario
