import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

interface printMethods {//metodos poara escribir menos al momento de mandar mensajes en pantalla
	default void print(Object argumento){
		System.out.print(argumento);
	}

	default void println(Object argumento){
		System.out.println(argumento);
	}
}

class EmpleadoSalarioExeption extends Exception {
	@Override
	public String getLocalizedMessage() {
		return "Error al asignar el salario del empleado";
	}
}

class Empleado implements printMethods{ //inplementando la interfaz en la clase padre de todas todos los hijos pueder acceder a ella
	String clave;
	LiderDeProyecto liderDeProyecto = null; //Esta nos servira para poder saber el salario de todos y lo podemos ver como que
									        //todo empleado debe tener un lider para trabajar en este caso al crear al empleado
											//no tiene ningun lider de proyecto
	double salario;
	String nombre;
	String puesto;

	public void registrarEntrada(){
		println("Estoy llegando a mi trabajo...");
	}

	public void trabajar(){
		println("Estoy trabajando...");
	}

	public void registrarSalida(){
		println("Hasta pronto. Ya me voy a casa...");
	}

	public void modificarSalario(Double nuevoSalario) throws EmpleadoSalarioExeption{
		if(nuevoSalario != 0){
			if(!(this instanceof LiderDeProyecto)){
				if(this.liderDeProyecto != null){
					if(nuevoSalario <= liderDeProyecto.salario){
						this.salario = nuevoSalario;
					}else{
						println("Ningun empleado pude tener un salario mayor al del lider de proyecto");
						throw new EmpleadoSalarioExeption();
					}
				}else{
					println("Error no el empleado no tiene lider de proyecto asignele uno cuanto antes");
					throw new EmpleadoSalarioExeption();
				}
			}else{ //este solo puede ser un lider de proyecto
				this.salario = nuevoSalario;
			}
		}else{
			println("error ningun empleado puede tener de sueldo 0");
			throw new EmpleadoSalarioExeption();
		}
	}
}

class LiderDeProyecto extends Empleado{
	Hashtable<String, Empleado> equipoDeTrabajo;

	public LiderDeProyecto(String clave, String nombre, double salario, Hashtable<String, Empleado> equipo){
		this.puesto = "Lider de Proyecto";
		this.nombre = nombre;
		this.clave = clave;
		this.salario = salario;
		this.equipoDeTrabajo = equipo;

		//registrar el lider para cada nuevo trabajador

		equipo.forEach((llave, empleado) -> { //funcion lamda(anonima) que permite iterar de una manera mas facil
			empleado.liderDeProyecto = this;
		});

	}

	public void asignarTarea(Empleado empleado){
		this.trabajar();
		println("Asignando actividad a " + empleado.nombre);
		if(empleado instanceof DesarrolladorJava){
			DesarrolladorJava desarrolladorJava = (DesarrolladorJava) empleado;
			desarrolladorJava.trabajoTerminado = false;
		} 
	}

	public void convocarReunion(){
		println("Convocando reunion de trabajo...");
	}
}

class DesarrolladorJava extends Empleado{
	int horasSinDormir;
	boolean trabajoTerminado;

	public DesarrolladorJava(String nombre, String clave){
		this.nombre = nombre;
		this.clave = clave;
		this.horasSinDormir = 0;
	}

	public void trabajar(){
		println("Transformando café en energía...");
		super.trabajar();
		this.horasSinDormir ++;

	}

	public void dormir(){
		if(this.trabajoTerminado) println("Al fin voy a dormir!!!");
		else this.trabajar();
	}
}

class Diseniador extends Empleado{
	public Diseniador(String clave){
		this.clave = clave;
	}

	public void trabajar(){
		println("Diseñando una interfaz gráfica");
	}

	@Override
	public void modificarSalario(Double nuevoSalario) throws EmpleadoSalarioExeption {
		if(this.liderDeProyecto != null){
			Enumeration<String> enumeration = this.liderDeProyecto.equipoDeTrabajo.keys();

			while (enumeration.hasMoreElements()){
				String llave = enumeration.nextElement();
				Empleado empleado = this.liderDeProyecto.equipoDeTrabajo.get(llave);

				if(empleado instanceof DesarrolladorJava){
					if(nuevoSalario < empleado.salario){
						println("Ningun diseñador puede cobrar menos que un desarollador");
						throw new EmpleadoSalarioExeption();
					}else{
						super.modificarSalario(nuevoSalario);
					}
				}
			}
		}else{
			println("Error el Empleado no tiene lider de proyecto");
			throw new EmpleadoSalarioExeption();
		}
	}
}

class Tester extends Empleado{
	public Tester(String clave){
		this.clave = clave;
	}
	public void trabajar(boolean faseTerminada){
		if(faseTerminada){
			println("Haciendo pruebas...");
		}else println("Planificando las pruebas...");
	}
}

class Proyecto{
	public static void main(String[] args) {
		try {
			Hashtable equipo = new Hashtable();

			DesarrolladorJava hugo = new DesarrolladorJava("Hugo", "devj01");
			//hugo.modificarSalario(100.00);
			DesarrolladorJava paco = new DesarrolladorJava("Paco", "devj02");
			DesarrolladorJava luis = new DesarrolladorJava("Luis", "devj03");

			Diseniador daisy = new Diseniador("des01");
			//daisy.modificarSalario(200.00);
			Diseniador minnie = new Diseniador("des02");

			daisy.nombre = "daisy";

			Tester donald = new Tester("test01");
			//donald.modificarSalario(300.00);
			Tester mickey = new Tester("test02");
			Tester goofy = new Tester("test03");

			equipo.put(hugo.clave, hugo);
			equipo.put(paco.clave, paco);
			equipo.put(luis.clave, luis);
			equipo.put(daisy.clave, daisy);
			equipo.put(minnie.clave, daisy);
			equipo.put(donald.clave, donald);
			equipo.put(mickey.clave, mickey);
			equipo.put(goofy.clave, goofy);

			Diseniador diseniador = (Diseniador) equipo.get(daisy.clave);

			System.out.println("El jefe de proyeto de " + diseniador.nombre + " es: " + diseniador.liderDeProyecto);

			LiderDeProyecto pedro = new LiderDeProyecto("p123", "Pedro", 15000.00, equipo);

			System.out.println("El jefe de proyeto de " + diseniador.nombre + " es: " + diseniador.liderDeProyecto.nombre);

			diseniador.modificarSalario(100.0);

			hugo.modificarSalario(1000.0);

			System.out.println(diseniador.salario);

			diseniador.modificarSalario(10.0);

			System.out.println(diseniador.salario);
		}catch (EmpleadoSalarioExeption exeption){
			System.out.println("Error con el salario");
		}
	}
}

