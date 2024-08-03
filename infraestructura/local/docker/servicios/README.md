# INICIALIZACIÓN DE CONTENEDORES

1. INSCRIPCIÓN
   Para ejecutar individualmente cada microservicio se debe primero ejecutar el siguiente comando

```bash
cd /opt/evaluaciones/pro
sudo chmod +x init_servers.sh
sudo chmod +x init_microservicio.sh
```

Y con eso se ejecutarán los siguientes pasos necesarios para la inicialización del contenedor.

- Obtener el ID del contenedor en ejecución
- Detener el contenedor
- Eliminar el contenedor
- Obtener el ID de la imagen
- Eliminar la imagen
- Cambiar al directorio con los archivos de configuración
- Iniciar el contenedor

Se necesita enviar como parámetro de entrada dos cosas:

1. El nombre del servicio detallado en el archivo semop-servicios.yml
2. El nombre del tag de la imagen del contenedor almacenada en docker.

Inicialización de los contenedores:

## Servidores

```bash
   sudo ./init_servers.sh eva-config-server
sudo ./init_servers.sh eva-discovery-server
sudo ./init_servers.sh eva-gateway-server
```

## Microservicios

## Generar imagen docker 
mvn package -P generar-imagen-docker

-- bajar servidores
docker compose -f 0_common.yml -f 7_cj_eval-servers.yml down
-- bajar servicios
docker compose -f 0_common.yml -f 8_cj_eval-services.yml down
-- servidores
docker compose -f 0_common.yml -f 7_cj_eval-servers.yml up -d eva-config-server
docker compose -f 0_common.yml -f 7_cj_eval-servers.yml up -d eva-discovery-server
docker compose -f 0_common.yml -f 7_cj_eval-servers.yml up -d eva-gateway-server
-- Uno a uno
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d eva-ubicacion-servicio
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d eva-catalogo-service
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d sso-authorization-service
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d sso-adm-provider-service
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d eva-personal-service
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d eva-evaluacion-service
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up -d eva-proceso-service
-- TODOS
docker compose -f 0_common.yml -f 8_cj_eval-services.yml up 