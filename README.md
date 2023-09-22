# servlet-music-app
Esta es una aplicación web hecha mediante servlets que tiene por temática un CRUD pero con música. Lo que tiene de especial es que te guarda los archivos mp3 y te deja una interfaz donde puedes reproducir tu música.

# Tecnologías aplicadas

Para este proyecto lo que he realizado es un controlador principal del que dota a todos los controladores hijos de los métodos necesarios para el correcto funcionamiento de la aplicación, de modo que el código es relativamente más límpio. Por otro lado, para gestionar la base de datos se ha hecho con un acceso de datos genérico, el cual devuelve List<Map<String, Object>>. Esta gestión de la base de datos es general, es decir, que vale para cualquier procedimiento almacenado de ésta (también tiene formas para construir consultas normales), ello lo hace a través de esta instrucción para consultar:

    this.db.procedure("[procedimiento]", bindearparametros, null, true); 

Para hacer todoos los demás elementos del CRUD:

    this.db.procedure("[procedimiento]", bindearparametros, null, true);    

# ¿Cómo lo hago funcionar?

La idea sería obtener un servidor Tomcat, el cual habría que configurar para importarle el proyecto entero como un archivo .war y de ahí correrlo en el servidor. Esto se puede hacer desde eclipse o desde cualquier otro IDE de Java.
