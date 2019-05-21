![N|Solid](https://media.licdn.com/dms/image/C560BAQE3S2bQRwZDEQ/company-logo_200_200/0?e=2159024400&v=beta&t=w6PtKOGk7vJg5uy_qoTOq7-Urh7oXF36DkrasNoeqNM)

# app-eureka 

Este proyecto es el encargado de registrar todos los microservicios para su posterior descubrimiento por el Gateway Zuul, además hace posible poder ver los microservicios que están registrados y que actualmente se encuentran en funcionamiento.

## Comenzando 🚀 

Este proyecto es un módulo de app-master-gateway, tiene si propio método Main con el servidor de aplicaciones Tomcat embebido para que pueda desplegar correctamente.

## Pre-requisitos 📋

Este proyecto ya tiene su propio servidor de aplicaciones por lo tanto no tiene configuraciones adicionales, se debe tener en cuenta que este proyecto debe ser el primero en desplegarse para que los microservicios puedan ser registrados con éxito.

## Información 📌

Eureka cuenta con un archivo .properties donde se encontrarán todas las configuraciones necesarias para su funcionamiento, el archivo se encuentra en src/main/resources. 
```sh
#nombre que se le da al servidor de eureka
spring.application.name=eureka-server

#puerto predeterminado 
server.port=8761

# eureka se registrara automanticamente el mismo, por lo tanto se deben deshabilitar estas opciones.
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```
Teniendo en cuenta las configuraciones anteriormente mostradas cuando se despliegue el proyecto de Eureka se deberá poder ver la interfaz grafica en la siguiente Url "http://localhost:8761/".