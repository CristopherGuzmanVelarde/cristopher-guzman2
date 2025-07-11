#Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#Etapa 2: Preparar archivos necesarios
FROM eclipse-temurin:17-jre-alpine AS prepare
RUN addgroup -g 1001 -S appgroup && adduser -u 1001 -S appuser -G appgroup
RUN mkdir -p /tmp && chmod 1777 /tmp


#Etapa 3: Runtime final con scratch
FROM scratch
COPY --from=prepare /opt/java/openjdk /opt/java/openjdk
COPY --from=prepare /lib /lib
COPY --from=prepare /usr/lib /usr/lib
COPY --from=prepare /etc/passwd /etc/passwd
COPY --from=prepare /etc/group /etc/group

COPY --from=prepare /tmp /tmp

COPY --from=build /app/target/*.jar /app/app.jar
USER 1001:1001
EXPOSE 8087
ENV PATH="/opt/java/openjdk/bin:${PATH}"
ENV LD_LIBRARY_PATH="/opt/java/openjdk/lib/server:/opt/java/openjdk/lib:/opt/java/openjdk/../lib"
ENTRYPOINT ["/lib/ld-musl-x86_64.so.1", "--library-path", "/opt/java/openjdk/lib/server:/opt/java/openjdk/lib:/opt/java/openjdk/../lib", "/opt/java/openjdk/bin/java", "-jar", "/app/app.jar"]