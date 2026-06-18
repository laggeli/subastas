# 🧾 Subastas online

![Subastas](./subasta.jpg)

Una casa de subastas en línea necesita desarrollar un sistema para gestionar subastas en tiempo real. El sistema debe permitir que los subastadores participen en la subasta de un producto, realizando ofertas que deben ser registradas y notificadas automáticamente a todos los participantes.


## 📌 Requerimientos

- **ProductoSubastado**: Clase que representa el producto en subasta. Debe mantener un registro de todas las ofertas recibidas y notificar automáticamente a los participantes cada vez que se recibe una nueva oferta.

- **Subastador**: Representa a los usuarios que participan en la subasta. Cada subastador debe ser notificado cuando se realiza una nueva oferta y debe poder realizar sus propias ofertas. El subastador conoce su nombre de usuario y la última oferta recibida.

- **Oferta**: Representa una oferta realizada por un subastador, conteniendo información sobre el subastador y el valor ofertado.

> 🔔 Importante: la solución debe notificar automáticamente a los participantes sobre nuevas ofertas, **sin que estos tengan que consultar activamente** el estado del producto subastado.

> ➕ Cuando un subastador crea una nueva oferta (la cual se pasará como argumento al método `agregarOferta` del producto subastado), el valor debe ser exactamente **$10 más** que la última oferta que haya recibido ese subastador.

> ❌ Si el producto subastado recibe una oferta de un subastador que **no está participando** en la subasta, debe lanzar una excepción de negocio no chequeada, con el mensaje:  
> **"El subastador no participa en la subasta".**

## ✅ Pruebas a implementar

El sistema debe incluir pruebas unitarias que verifiquen el correcto funcionamiento del sistema.

### 🛠 Configuración inicial

- Crear un `ProductoSubastado` y tres subastadores con los siguientes nombres de usuario: `"gonzager"`, `"diazdan"` y `"martomau"`.
- Antes de cada test:
  - Reiniciar el producto subastado, limpiando las listas de subastadores y ofertas.
  - Reiniciar la última oferta de cada subastador.
- Registrar a `"gonzager"` y `"martomau"` como participantes del producto subastado.

### 📚 Escenario 1

1. El subastador `"martomau"` realiza la primera oferta.
2. El subastador `"gonzager"` realiza una contraoferta.
3. El subastador `"martomau"` realiza otra oferta.

### 🧪 Tests

- **Test 1**  
  Dado el escenario 1, se verifica que los subastadores `"gonzager"` y `"martomau"` reciben correctamente la última oferta realizada.

- **Test 2**  
  Dado el escenario 1, se verifica que la última oferta registrada pertenece efectivamente al subastador `"martomau"`.

- **Test 3**  
  Dado el escenario 1, se verifica que el valor de la última oferta sea el correcto (`$30`), demostrando el incremento progresivo.

- **Test 4**  
  Dado el escenario 1, se verifica que la cantidad de ofertas registradas sea 3.

- **Test 5**  
  Dado el escenario 1, al intentar agregar una nueva oferta de `"diazdan"` (que no está participando), se verifica que se lanza la excepción correspondiente.

## 💡 Bonus:

Objetivo del bonus: Extender el comportamiento de los subastadores permitiendo que cada uno implemente una estrategia personalizada al momento de ofertar, para reforzar el uso de polimorfismo y facilitar la incorporación de nuevos tipos de subastadores sin modificar código existente.

.Las estrategias pueden ser:

- Subastador Arriesgado: sube siempre $10.

- Subastador con Límite: solo oferta si el nuevo valor no supera un umbral definido.

- Subastador Único: solo puede ofertar una vez.

Para esto, los subastadores deberán delegar el comportamiento de crear ofertas a una estrategia configurada al momento de su creación.
