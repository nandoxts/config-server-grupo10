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

## Perfil opcional `git` (configuración desde GitHub)

Además del modo nativo, el servidor puede leer los **mismos archivos** desde este repositorio de GitHub (carpeta `edusmart-config/`, rama `main`). Está en `application-git.properties`:

```properties
spring.cloud.config.server.git.uri=https://github.com/nandoxts/config-server-grupo10
spring.cloud.config.server.git.search-paths=edusmart-config
spring.cloud.config.server.git.default-label=main
```

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=git
# o: java -jar target/config-server-grupo10-0.0.1-SNAPSHOT.jar --spring.profiles.active=git
```

`http://localhost:8888/matricula-service/lima` muestra entonces como fuente `https://github.com/nandoxts/config-server-grupo10/edusmart-config/matricula-service-lima.yml`. matricula-service no cambia nada.

> La demo de recarga en caliente se hace con el perfil **native** (editando `C:/edusmart-config/`), tal como pide el enunciado. Con el perfil `git` el cambio se hace con un commit al repositorio y luego `POST /actuator/refresh`.

## Ejecutar

```bash
./mvnw spring-boot:run      # se levanta PRIMERO: matricula-service lo necesita al iniciar
```

El consumo (`@RefreshScope`, `/actuator/refresh`) está en [matricula-service-grupo10](https://github.com/nandoxts/matricula-service-grupo10#readme).
