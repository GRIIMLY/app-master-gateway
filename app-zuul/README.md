![N|Solid](https://media.licdn.com/dms/image/C560BAQE3S2bQRwZDEQ/company-logo_200_200/0?e=2159024400&v=beta&t=w6PtKOGk7vJg5uy_qoTOq7-Urh7oXF36DkrasNoeqNM)

# app-zuul 

Este proyecto es el encargado de servir como puerta de enlace es decir es el servidor de frontera que se encarga de recibir todas las peticiones y redirigirlas a los microservicios correspondientes, además se encarga de servir como filtro para registrar todas las peticiones y respuestas a la base de datos, validar el token  y de esta manera dar paso a la solicitud a los microservicios.

## Comenzando 🚀 

Este proyecto es un módulo de app-master-gateway, tiene si propio método Main con el servidor de aplicaciones Tomcat embebido para que pueda desplegar correctamente.

## Pre-requisitos 📋

Este proyecto ya tiene su propio servidor de aplicaciones por lo tanto no tiene configuraciones adicionales, se debe tener en cuenta que este proyecto debe ser el ultimo en desplegarse para que los microservicios puedan ser consumidos con éxito.

La configuración del Zuul es muy importante ya que nos permite enrutar las peticiones pero hay que tener en cuenta que este modulo depende mucho del Eureka por lo tanto para que este modulo funcione tiene que estar el Eureka funcionando con microservicios registrados

## Configuración 🔧

Zuul cuenta con un archivo .properties donde se encontrarán todas las configuraciones necesarias para su funcionamiento, el archivo se encuentra en src/main/resources. 
```sh
#nombre que se le da al servidor de eureka
# puerto en el que funcionará el Zuul
server.port=8083
# nombre que se le asignará a la aplicación
spring.application.name=zuul-server
# url del eureka ( la parte de "/eureka" se le deberá agregar adicional a la url ya
# que esta parte de la url es indispensable para que registre en el el servidor Eureka).
eureka.client.service-url.default-zone=http://localhost:8761/eureka


#Las configuraciones de a continuación sirven para poder enrutar las peticiones a los microservicios

#el valor de la siguiente confiuración es el nombre del microservicio al que se quiere enrutarle las peticiones
zuul.routes.rest-service.service-id= rest-service
# el valor de la siguiente configuración es  el path por el cual va recibir todas las peticiones para el microservicio anterior
zuul.routes.rest-service.path= /api/admin/**
# el valor de la siguiente configuración es que me puede enviar el header de la petición  recibida por el zuul a los microservicios
zuul.routes.rest-service.sensitive-headers = Cookie, Set-Cookie
# el valor de la siguiente configuración es que cada petición tendrá una pequeña pausa para poder procesar bien la solicitud
ribbon.ReadTimeout=10000
```
Teniendo en cuenta las configuraciones anteriormente mostradas cuando se despliegue el proyecto de Zuul se podrá acceder al microservicio al cual se le puso la configuración  de enrutamiento en este caso se podrá acceder de la siguiente manera: localhost:8083/api/admin/ y después de esto agrega el servicio rest al que le desea apuntar. ejemplo localhost:8083/api/admin/serviciouno.