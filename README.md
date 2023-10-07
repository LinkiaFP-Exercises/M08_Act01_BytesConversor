# M08_Act01_BytesConversor
Android App that allows the conversion between the different units that make up the byte measurement unit scale. The application must allow the user to convert (in both directions) one unit of measurement into another

Desarrollar una aplicación Android que permita la conversión entre las diferentes unidades que forman la escala de unidades de medida del byte. La aplicación debe permitir al usuario convertir (en los dos sentidos) una unidad de medida en otra. Indicaciones:
  Funcionalidad
    El usuario seleccionará la unidad de medida de origen y la unidad de medida a la cual se quiere hacer en cambio. Estas unidades se deben ofrecer al usuario utilizando un spinner.
    El valor por defecto del spinner tiene que estar vacío.
    El usuario introducirá la cantidad que quiere convertir. 
    Existirán dos posibles botones de conversión. Uno convertirá el primer tipo de conversión al segundo tipo de conversión. El segundo botón convertirá del segundo tipo de conversión al primero. El resultado de la conversión se mostrará al usuario en la propia pantalla.
    El resultado debe tener dos decimales. Si con dos decimales no se ve bien se ha de mostrar el formato x.xxE5 para valores grandes o x.xxE-5 para valores con muchos decimales
  Diseño. Por el orden en el que se indica. Para controlar los porcentajes que se indican se ha de usar un Layout de tipo Constraint con GuideLines
    La unidad origen tiene que ocupar el 20% de la pantalla en vertical
    La unidad destino tiene que ocupar el 20% de la pantalla en vertical
    El valor por convertir tiene que ocupar el 20% de la pantalla en vertical
    El resultado tiene que ocupar el 20% de la pantalla en vertical
    El resto de la pantalla debe tener una imagen
  Controles
    El valor por convertir ha de ser numérico sin decimales y no puede ser letras
    Ha de controlar que los valores de la unidad origen o destino no pueden estar vacíos
A modo de ayuda, aquí está la tabla de conversión entre las diferentes unidades de medida de la escala del byte.  

