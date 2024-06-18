## Conversor de Moneda

Este es un programa Java simple que utiliza la API de ExchangeRate-API para convertir entre diferentes monedas. El programa permite al usuario seleccionar entre varias opciones de conversión y registra un historial de conversiones en un archivo JSON local.

### Funcionalidades

- **Conversión de Moneda**: El programa puede convertir entre las siguientes monedas:
  - Dólar a Peso Argentino
  - Peso Argentino a Dólar
  - Dólar a Real Brasileño
  - Real Brasileño a Dólar
  - Dólar a Peso Colombiano
  - Peso Colombiano a Dólar

- **Registro de Historial**: Cada conversión realizada se guarda en un archivo `conversiones.json` en formato JSON, que contiene detalles como la cantidad convertida, las monedas involucradas y la hora de la conversión.

### Requisitos

Para ejecutar este programa, necesitas tener instalado:
- Java Development Kit (JDK) 8 o superior
- Acceso a Internet para consultar la API de ExchangeRate-API

### Instrucciones de Uso

1. Clona este repositorio o descarga el archivo `Main.java`.
2. Compila el código Java usando `javac Main.java`.
3. Ejecuta el programa usando `java Main`.
4. Sigue las instrucciones en pantalla para realizar conversiones de moneda.

### Configuración del Proyecto

Este proyecto utiliza:
- **Gson**: Para el manejo de objetos JSON.
- **HttpClient**: Para realizar solicitudes HTTP a la API de ExchangeRate-API.

### Ejemplo de Uso

```java
// Conversión de Dólar a Peso Argentino
Ingrese la cantidad de USD que desea convertir a ARS:
100
100.0000000000 USD equivale a 7682.4000000000 ARS

// Historial guardado en `conversiones.json`
[
  "(100.0000000000 USD equivale a 7682.4000000000 ARS , La hora de la conversión:14:30:00.123456789)"
]
