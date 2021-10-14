# GuessNumber
Este proyecto consiste en un juego muy sencillo, en el cual debes adivinar un numero secreto creado por la aplicacion. 
Para realizar este proyecto he creado 3 Actividades:
	* ConfigActivity
	*  PlayActivity
	* EndPlayActivity

<h3> ConfigActivity</h3>
En esta activity encontramos dos EditText para escrbir nuestro nombre y el numero de intentos.
Nuestro nombre sera guardado en la clase Application que he implementado para guardar ciertas variables.
El numero de intentos son las veces que podremos intentar adivinar el numero en la siguiente activity.

Al final encontramos un boton que al pulsarlo nos redirige a la actividad PlayActivity (con Intent). Si no has introducido bien las variables de los EditText no te dejara avanzar.

<h2></h2>
<h2></h2>

<h4> PlayActivity</h4>
Aqui hemos implementado el juego con todos sus emtodos para comprobar si acierto el numero secreto asi como si me quedan intentos etc...

En la Activity encontramos 2 botones: Uno para comprobar el numero y otro para volver a intentarlo.
Cuando pulsemos el primero si hemos acertado nos iremos directamente a EndPlayActivity como ganadores del juego. Si no hemos acertado nos mostrara una pista por la pantalla (mayor o menor) y para reintentarlo hay que darle al otro boton.

<h4> ConfigActivity</h4>
En esta activity simplemente mostramos los resultados del juego, si has ganado, perdido y segun en que caso mostrara los intentos usados o cual era el numero secreto que buscabamos.

<h2> </h2>
<h2> </h2>
<h2> Aspectos Didacticos</h2>
Realizando esta activity he reforzado conceptos basicos de android y me estoy familiarizando poco a poco con el IDE.
Cada vez es mas facil para mi usar Intents, manejar las vistas de las actividades, modificar valores...

Uno de los aspectos mas interesantes que he aprendido realizando el proyecto es la creacion de un segundo archivo .xml de strings pero en otro idioma, para poder seleccionar el idioma que queramos en cualquier momento. Esto hace que debas guardar TODOS los strings en el archivo de values strings, sino habra textos que se traduzcan y otros que no.

Otro aspecto que me ha parecido util y no conocia anteriormente son los metodos para evitar que el usuario introduzca lo que quiera por el EditText y solo pueda introducir lo que tu deseas (vease problemas con Integers y cadenas...).
Para solucionar esto he utilizado el siguiente metodo:
```
private boolean checkTvClickBtnCheckNumber() {
        if (!TextUtils.isEmpty(binding.edAttemp.getText())) {
            if (TextUtils.isDigitsOnly(binding.edAttemp.getText()) && (Integer.parseInt(binding.edAttemp.getText().toString())) <= 100 ) {
                return true;
            } else {
                showMessage(getText(R.string.tvErrorMessagePlayAttempInteger).toString());
            }
        }
        return false;
    }
```
Este metodo en la actividad PlayActivity comprueba que el texto introducido cumple con mis requisitos. Ademas imlpementa el metodo showMessage:
```
    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
```
Este metodo me parecio curioso tambien, ya que no conocia la clase Toast y es bastante util para estas situaciones. 

<h2></h2>
<h2></h2>

