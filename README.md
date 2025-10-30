# TP-Final-programacion-2
## Condiciones generales:
El trabajo deberá realizarse en grupos de entre 2 y 4 integrantes.
La defensa del trabajo será individual (cada uno de los integrantes del grupo deberá tener conocimiento de cómo funciona todo el código, y ser capaz de explicar las decisiones de diseño tomadas).
Cada exposición durará máximo 30 minutos y consta de una explicación del sistema y una pregunta a cada integrante del grupo. La exposición se tendrá en cuenta para la evaluación final del trabajo práctico.
Deberán implementarse todos los conceptos aprendidos durante el desarrollo de las clases del cuatrimestre siendo de carácter obligatorio los siguientes conceptos:
Realización y entrega de un Diagrama UML en el cual quede diseñado la estructura del sistema. 
Utilización de los 4 pilares de la POO (Herencia, Polimorfismo, Abstracción y Encapsulamiento). 
Creación de un mínimo de 6 clases en su sistema (Sin contar la clase Main, interfaces ni excepciones). 
Implementar al menos una Colección y un Mapa. 
Crear mínimamente 1 Clase Abstracta, 1 interfaz y 1 enumeración para implementar. 
Aplicación del concepto de manejo de errores con al menos 2 clases de excepción personalizadas. 
Creación y uso de una Clase Genérica. 
Manejo de Archivos de texto o binarios o JSON para la persistencia de datos. 
Utilización de la herramienta GIT/GITHUB para el versionado de código y el trabajo colaborativo.


Todos los integrantes del grupo deben hacer commits de sus cambios a medida que van subiendo funcionalidad o van arreglando código. No puede haber un solo commit con toda la funcionalidad o que un solo integrante haya subido todo el código. Todos deben de participar.
El código fuente debe estar correctamente documentado/comentado.

## Consigna:
Aplicación sugerida: Sistema de administración de un hotel.
El sistema deberá organizar un hotel, administrando sus habitaciones y los pasajeros/clientes del hotel. El sistema deberá permitir realizar reservas de habitaciones, hacer el check-in y check-out de los pasajeros, listar las habitaciones actualmente ocupadas y los datos de los ocupantes, las habitaciones disponibles, y las habitaciones que no estén disponibles por algún motivo (limpieza, reparación, desinfección, etc. Detallar el motivo).
Deberá informar si es posible ocupar una habitación en un período determinado (consultando la ocupación y las reservas). Para los pasajeros se pide un informe que incluya nombre, DNI, origen, domicilio de origen. Opcionalmente se puede dar información sobre la historia del pasajero en el hotel (detalles de los períodos en


 los que estuvo alojado, la habitación que ocupó, etc). Para la realización correcta del trabajo se recomienda la visita a uno o más hoteles y hablar con el dueño,
conserje, personal de recepción, etc., para recopilar información sobre el funcionamiento de un sistema de hoteles, y saber que necesitan o necesitarán ellos del sistema. 
En función de esas entrevistas, podrían modificarse las definiciones previstas a continuación
- Check-in: Es el proceso de registro de un pasajero en el hotel. Se realiza cuando el pasajero llega al hotel para tomar posesión de la habitación.
- Check-out: Es cuando el pasajero deja la habitación.
- Pasajero: Es la persona que ocupa físicamente la habitación.
- Ocupación: La ocupación es cuando el pasajero está pagando por la habitación.
Normalmente comienza cuando el pasajero toma posesión de la misma, durante el
check-in, y termina cuando el pasajero abandona la misma en el check-out.
- Reserva: Una reserva consiste en un período de tiempo en el que la habitación será ocupada por un pasajero. Una habitación reservada no puede ser ocupada, salvo por el pasajero que la reservó, a no ser que se cancele la reserva.


Tipos de usuario del sistema: se prevén por lo menos 2 tipos de usuario
- Administrador: Es el encargado de las funciones administrativas del sistema. Dentro de sus funciones está la realización del backup de la información, la creación de otros usuarios, la asignación de permisos a usuarios, etc.
- Conserje o Recepcionista: Es la persona que atiende a los pasajeros, realiza los check-ins y check-outs, las reservas, etc. Debe poder conocer el estado de cada habitación en todo momento, y tener acceso a la carga de datos de los pasajeros del sistema.

- Pasajero(opcional): Es la persona que ocupa la habitación. Podrían preverse accesos al sistema de los pasajeros para solicitar reservas de habitaciones, o para realizar consumos en las habitaciones ya ocupadas.
