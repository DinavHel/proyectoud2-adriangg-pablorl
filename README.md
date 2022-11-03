# proyectoud1-adriangg-pablorl
## 1. Idea base del proyecto
Usaremos la API mhw-db del videojuego Monster Hunter World.
La documentación online de esta API se puede encontrar en https://docs.mhw-db.com/

La propuesta para la aplicación es una herramienta gráfica para *builds*,
que permita al usuario seleccionar un conjunto de piezas de armadura
y que vea las características que resultan de esa combinación.

La base de toda build de Monster Hunter son la armadura y el arma.
La API que hemos elegido nos deja hacer una consulta de los datos de todas
las armaduras del juego, como se ve en la captura:
![](./Capturas/PostmanArmor.png)
Por supuesto, la API también nos deja elegir consultar las armas, aunque al menos en la
primera versión de la app no las incluiremos.
![](./Capturas/PostmanWeapon.png)
Hay otros elementos que también son importantes en el juego, como las decoraciones (captura de abajo),
pero pospondremos su implementación debido al hecho de que los jugadores
novatos tardarán en familiarizarse con ellas.
![](./Capturas/PostmanDecorations.png)
## 2. Manual técnico para desarrolladores
La app está pensada para ser consumida directamente por el usuario final;
al no estar pensada para ser reaprovechada para otros proyectos de sotware, no incluiremos
un manual para desarrolladores.
## 3. Manual de usuario
Lanzar la aplicación hace aparecer la ventana que vemos abajo.
En ella vemos los cinco campos correspondientes a las cinco piezas de la armadura en Monster Hunter World.
![](./Capturas/Screenshot_20221014_201643.png)
Si clicamos en uno de los campos se despliega una lista con la lista de armaduras de ese tipo.
![](./Capturas/Screenshot_20221014_201820.png)
Conforme vayamos seleccionando las piezas que formarán nuestra *build* veremos aparecer,
en el cuadro de la derecha, las habilidades que nos confiere la armadura.
![](./Capturas/Screenshot_20221014_201845.png)
Algunas piezas tienen una habilidad con un nivel superior a 1. También es posible que, por ejemplo, una armadura
de tipo Helm y una de tipo Chest. En ese caso, el cuadro de la derecha reflejará la suma de los niveles
de las armaduras que formen nuestra *build*.
![](./Capturas/Screenshot_20221014_201904.png)
## 4. Reparto de las tareas entre ambos integrantes
* **Pablo Rodríguez Lomba**
    + Diseño de la interfaz gráfica
    + Codificación del lanzador de la aplicación.
    + Codificación del controlador correspondiente a la interfaz gráfica.
    + Codificación de la solicitud y almacenamiento de los datos solicitados a la API en forma de JSONArray.
    + Codificación de los eventos resultantes de la interacción del usuario con la interfaz gráfica.
    + Documentación del código Java.
* **Adrián González Gómez**
    + Redacción de la memoria del proyecto.
    + Inicio de la refactorización del proyecto para incluír el uso de Jackson (*proceso abortado*)
## 5. Extras realizados
No se codificaron extras, por lo que no aplica.

## 6. Propuestas de mejora
* Conversión de JSONArray a POJO mediante Jackson
* Considerar la inclusión de las armas del juego.
* Considerar la implementación de las decoraciones y los amuletos.
## 7. Conclusiones y opinión del trabajo realizado
En su versión actual, la app es un poco básica y, sin duda, los aficionados
de la saga priorizarán otras aplicaciones más completas (como algunas que permiten
seleccionar las habilidades deseadas y devuelven sugerencias de *builds*).

Respecto al proceso de desarrollo, una buena parte del tiempo fue invertido en consultar
la documentación de las tecnologías con las que los integrantes no
estaban familiarizados, como JavaFX o, sobre todo, Jackson y su clase Object Mapper.