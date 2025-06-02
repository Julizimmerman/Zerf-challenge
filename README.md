# Zerf-challenge

Comentarios: 
- En el método cd, se toma como válido el path si arranca desde root o si arranca desde el directorio actual.
- La clase Node podria plantearse como abstracta y generar jerarquías obteniendo otras 3 clases: RootNode, DirectoryNode y FileNode. De esta manera se dividen los comportamientos de ciertas acciones.
- En el método de create se asume que el nombre recibido por parametro no tiene ningún tipo de error.
- En el método de resolvePath no se verifica si el ultimo nodo encontrado es un directorio o un archivo.
- En el método cd cuando se le pasa "..", si el actual es la raiz, no sucede nada.
- Al crear, si ese nombre ya existe tira error. Esto podria optimizarse consultando con el usuario qué prefiere hacer en ese caso, si reemplazarlo, cambiar el nombre o no crearlo. 
