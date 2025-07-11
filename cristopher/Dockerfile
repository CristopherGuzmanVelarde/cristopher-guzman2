#Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#Etapa 2: Crear JRE mínimo y preparar dependencias
FROM eclipse-temurin:17-jdk-alpine AS prepare
WORKDIR /opt

# Crear un JRE mínimo con solo los módulos necesarios
RUN $JAVA_HOME/bin/jlink \
    --add-modules java.base,java.logging,java.desktop,java.management,java.naming,java.net.http,java.sql,java.xml,java.instrument,jdk.unsupported \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /opt/jre-minimal

# Crear usuario y grupo
RUN addgroup -g 1001 -S appgroup && adduser -u 1001 -S appuser -G appgroup

# Crear directorio temporal con permisos correctos para el usuario
RUN mkdir -p /tmp && chmod 1777 /tmp && chown 1001:1001 /tmp

# Identificar dependencias mínimas de librerías
RUN mkdir -p /minimal-libs && \
    cp /lib/ld-musl-x86_64.so.1 /minimal-libs/ && \
    cp -r /lib/x86_64-linux-gnu* /minimal-libs/ 2>/dev/null || true

#Etapa 3: Runtime final con scratch
FROM scratch

# Copiar solo el JRE mínimo
COPY --from=prepare /opt/jre-minimal /opt/jre-minimal

# Copiar solo las librerías esenciales
COPY --from=prepare /minimal-libs/* /lib/

# Copiar archivos de usuario
COPY --from=prepare /etc/passwd /etc/passwd
COPY --from=prepare /etc/group /etc/group

# Copiar directorio temporal con permisos correctos
COPY --from=prepare --chown=1001:1001 /tmp /tmp

# Copiar la aplicación
COPY --from=build /app/target/*.jar /app/app.jar

USER 1001:1001
EXPOSE 8087

ENV PATH="/opt/jre-minimal/bin:${PATH}"
ENV JAVA_HOME="/opt/jre-minimal"

ENTRYPOINT ["/opt/jre-minimal/bin/java", "-XX:+UseSerialGC", "-XX:MaxRAMPercentage=75", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
