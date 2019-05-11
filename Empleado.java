import java.util.Hashtable;

class Empleado{
	String clave;
	double salario;
	String nombre;
	String puesto;

	public void registrarEntrada(){
		System.out.println("Estoy llegando a mi trabajo...");
	}

	public void trabajar(){
		System.out.println("Estoy trabajando...");
	}

	public void registrarSalida(){
		System.out.println("Hasta pronto. Ya me voy a casa...");
	}
}

class LiderDeProyecto extends Empleado{
	Hashtable<String, Empleado> equipoDeTrabajo;

	public LiderDeProyecto(String clave, String nombre, double salario, Hashtable<String, Empleado> equipo){
		this.puesto = "Lider de Proyecto";
		this.clave = clave;
		this.salario = salario;
		this.equipoDeTrabajo = equipo;
	}

	public void asignarTarea(Empleado e){
		this.trabajar();
		System.out.println("Asignando actividad a "+e.nombre);
		if(e instanceof DesarrolladorJava){
			DesarrolladorJava dj = (DesarrolladorJava)e;
			dj.trabajoTerminado = false;	
		} 
	}

	public void convocarReunion(){
		System.out.println("Convocando reunion de trabajo...");		
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
		System.out.println("Transformando café en energía...");
		super.trabajar();
		this.horasSinDormir ++;

	}

	public void dormir(){
		if(this.trabajoTerminado) System.out.println("Al fin voy a dormir!!!");
		else this.trabajar();
	}
}

class Diseniador extends Empleado{
	public Diseniador(String clave){
		this.clave = clave;
	}

	public void trabajar(){
		System.out.println("Diseñando una interfaz gráfica");
	}
}

class Tester extends Empleado{
	public Tester(String clave){
		this.clave = clave;
	}
	public void trabajar(boolean faseTerminada){
		if(faseTerminada){
			System.out.println("Haciendo pruebas...");
		}else System.out.println("Planificando las pruebas...");
	}
}

class Proyecto{
	public static void main(String[] args) {
		Hashtable equipo = new Hashtable();
		
		DesarrolladorJava hugo = new DesarrolladorJava("Hugo", "devj01");
		DesarrolladorJava paco = new DesarrolladorJava("Paco", "devj02");
		DesarrolladorJava luis = new DesarrolladorJava("Luis", "devj03");

		Diseniador daisy = new Diseniador("des01");
		Diseniador minnie = new Diseniador("des02");

		daisy.nombre = "daisy";

		Tester donald = new Tester("test01");
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

		Diseniador d = (Diseniador)equipo.get(daisy.clave);

		System.out.println("Hola, soy "+d.nombre);

		LiderDeProyecto pedro = new LiderDeProyecto("p123", "Pedro", 15000.00, equipo);

		pedro.asignarTarea((Empleado)equipo.get(luis.clave));		

		luis.trabajar();
		luis.dormir();
	}
}

