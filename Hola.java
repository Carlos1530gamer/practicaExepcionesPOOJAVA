public class Hola{
	public static void main(String[] args) {
		//No lo modifique ya que no arroja ninguna exepcion por el for each
		print("!Hola");
		for(String argumento: args){
			print(" " + argumento);
		}
		println("!");
	}

	private static void println(Object argumento){
		System.out.println(argumento);
	}

	private static void print(Object argumento){
		System.out.print(argumento);
	}
}