# config-server-grupo10

> **EduSmart Campus – Microservicios con Spring Cloud** · Laboratorio T2 (Fila B) · Grupo 10
> Java 25 · Spring Boot 4.1.1 · Spring Cloud 2025.1.3 · Maven

**Servidor de configuración centralizada** en modo **nativo / FileSystem local** (Pregunta 2). Lee las propiedades de cada sede desde `C:/edusmart-config/`, sin depender de un Git remoto.

| Dato | Valor |
|---|---|
| Puerto | `8888` |
| Anotación | `@EnableConfigServer` |
| Dependencias | Config Server |
| Perfil | `native` |

```properties
spring.application.name=config-server-grupo10
server.port=8888
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=file:///C:/edusmart-config/
```

## Archivos de cada sede

La carpeta [`edusmart-config/`](edusmart-config/) de este repositorio contiene los archivos físicos. **Copiarlos a `C:/edusmart-config/`** antes de levantar el servidor:

```bash
mkdir C:\edusmart-config
copy edusmart-config\*.yml C:\edusmart-config\
```

| Archivo | Sede | Costo por crédito |
|---|---|---|
| `matricula-service-lima.yml` | Campus Lima Central | 120.00 |
| `matricula-service-arequipa.yml` | Campus Arequipa | 95.00 |

> El enunciado los nombra `.properties`, pero su contenido es YAML; se crean como `.yml` para que el Config Server los lea correctamente.

## Verificación

- http://localhost:8888/matricula-service/lima → propiedades de Lima
- http://localhost:8888/matricula-service/arequipa → propiedades de Arequipa

## Ejecutar

```bash
./mvnw spring-boot:run      # se levanta PRIMERO: matricula-service lo necesita al iniciar
```

El consumo (`@RefreshScope`, `/actuator/refresh`) está en [matricula-service-grupo10](https://github.com/nandoxts/matricula-service-grupo10#readme).
