# INICIALIZACIÓN DE CONTENEDORES

## Herramientas usadas
- Docker desktop
-  Dockstation 

## Generar imagen docker
Para poder generar las imagenes decker primero se debe configurar el **RUN/DEBUG configurations** en el IDE que se esté trabajando primero :
```
mvn clean install
```
segundo generar imagen docker:
```
mvn package -P generar-imagen-docker
```
Con el segundo codigo ejecutado, este generará la imagen y desplegará el nombre del mismo.
La imagen generada se debe copiar y pegar en la configuraciones de la carpeta  **infraestructura\local\docker\servicios\services or servers**
dependiendo si la imagen generada es de los servidor o servicios.

> **Nota:** De aquí en adelante se debe trabajar en la ruta : **infraestructura\local\docker\servicios**.

## Servidores

### subir servidores
Subir en el orden que esta a continuacíon, ya que el config-server es consumido por el discovery-server para registrar los servicios y el gateway es el punto de entrada para las solicitudes entrantes.
```
docker compose -f 0_common.yml -f 1_servers.yml up -d tata-config-server
```
```
docker compose -f 0_common.yml -f 1_servers.yml up -d tata-discovery-server
```
```
docker compose -f 0_common.yml -f 1_servers.yml up -d tata-gateway-server
```
### reiniciar servidores
```
docker compose -f 0_common.yml -f 1_servers.yml tata-config-server
```
```
docker compose -f 0_common.yml -f 1_servers.yml tata-discovery-server
```
```
docker compose -f 0_common.yml -f 1_servers.yml tata-gateway-server
```
### bajar servidores
```
docker compose -f 0_common.yml -f 1_servers.yml down
```

## Microservicios

### subir servicio
```
docker compose -f 0_common.yml -f 2_services.yml up -d tata-servicio
```
### reiniciar servicios
```
docker compose -f 0_common.yml -f 2_services.yml tata-servicio
```
### bajar servicio
```
docker compose -f 0_common.yml -f 2_services.yml down
```
## Habilitar base de datos
Sirve para otorgar privilegios a base de datos para que acepte conexiones externas.
```
GRANT ALL PRIVILEGES ON *.* TO 'user'@'user' IDENTIFIED BY 'root';

FLUSH PRIVILEGES;
```
 ## Habilitar Hosts
 En el caso de que se realice consultas mediante postman, el servidor gateway lance un error de que no reconoce el host al que quiere acceder, se debe realizar lo siguiente : 
 Ir a la ruta : 
 ```
 c:/windows/system32/etc
 ```
  y editar el archivo hosts con un editor en modo administrador
 y añadir lo siguiente al final del archivo :
  ```
 # Added by Docker Desktop
127.0.0.1 tata-test-config-server
127.0.0.1 PCHQUIT0408LA11.fj.local (host al que permites acceso)
127.0.0.1 tata-test-discovery-server
127.0.0.1 tata-test-gateway-server
127.0.0.1 test-tata-service 
  ```