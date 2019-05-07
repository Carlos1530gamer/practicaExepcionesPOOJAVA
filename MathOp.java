import java.util.Scanner;
import java.util.InputMismatchException;

class MathOp{
	public static void main(String[] args) {
		chicharronera(args);
	}

	private static void chicharronera(String[] args){
		double varA, varB, varC;
		boolean exit = false;
		do{
			try{
				//cast numbers of argumernts
				
				println("Dame la variable a: ");
				varA = new Scanner(System.in).nextDouble();
				if (varA == 0){
					throw new ArithmeticException();
				}
				println("Dame la variable b: ");
				varB = new Scanner(System.in).nextDouble();
				println("Dame la variable c: ");
				varC = new Scanner(System.in).nextDouble();

				double drisc = Math.pow(varB, 2.0) - (4 * varA * varC);

				if(drisc >=  0){
					double firstX, secondX;

					firstX = ((-varB) + Math.sqrt(drisc)) / 2*(varA);
					secondX = ((-varB) - Math.sqrt(drisc)) / 2*(varA);

					println("El resultado de x1 = \"" + firstX + "\" y x2 = \"" + secondX + "\"");
					exit = true;
				}else{
					double ima = drisc * (-1);
					println("El resultado de x1 = \"" + (-varB)/(2*varA) + " + " + Math.sqrt(ima)/(2*varA) + "i\" y x2 = \"" + (-varB)/(2 * varA) + " - " + Math.sqrt(ima)/(2*varA) + "i\"");
					exit = true;
				}

				}
			catch(InputMismatchException exception){
				println("Ingrese bien los valores");
			}
			catch(ArithmeticException exception){
				println("No se puede dividir entre cero :'v");
			}

		}while(!exit);
	}

	private static void print(Object arg){
		System.out.print(arg);
	}

	private static void println(Object arg){
		System.out.println(arg);
	}
}