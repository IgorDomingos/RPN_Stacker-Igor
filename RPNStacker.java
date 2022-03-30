package comp;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class RPNStacker {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			Scanner in = new Scanner(new FileInputStream("input.in")); //para Ler o arquivo de entrada
			PrintStream out = new PrintStream (new FileOutputStream("output.out"));
			Stack stck = new Stack();
			String op;
			int num1;
			int num2;
			int result = 0;
			String aux;
			while (in.hasNext()) {
				op = in.next();
				if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) { //Desempilha dois
					aux = stck.desempilha();
					num1 = Integer.parseInt(aux);
					aux = stck.desempilha();
					num2 = Integer.parseInt(aux);
					if (op.equals("+")) { //Soma
						result = num2 + num1;
						aux = Integer.toString(result);
						stck.empilha(aux);
					} else if (op.equals("-")) { //Sub
						result = num2 - num1;
						aux = Integer.toString(result);
						stck.empilha(aux);
					} else if (op.equals("*")) { //Mult
						result = num2 * num1;
						aux = Integer.toString(result);
						stck.empilha(aux);
					} else if (op.equals("/")) { //Div
						result = num2 / num1;
						aux = Integer.toString(result);
						stck.empilha(aux);
					}
				} else { // Empilha
					stck.empilha(op);
				}
			}
			aux = stck.desempilha();
			result = Integer.parseInt(aux);
			out.println(result); //GERA UM ARQUIVO DE SAÍDA
			System.out.println(result); //IMPRIME O RESULTADO
		} catch (FileNotFoundException e){
			System.out.println("Arquivo não encontrado");
		}
	}
}

class Stack {
	private static class Celula {
		String ex;
		Celula prox;
	}
	private Celula topo;
	private int tam;
	// Operações
	public Stack () { 
		// Cria uma Pilha vazia
		this.topo = null; 
		this.tam = 0;
	}
	public void empilha (String x) {
		Celula aux = this.topo;
		this.topo = new Celula () ;
		this.topo.ex = x;
		this.topo.prox = aux;
		this.tam++;
	}
	public String desempilha () throws Exception {
		if (this .vazia ())
			throw new Exception ( "Erro : A pilha esta vazia" );
		String ex = this.topo.ex;
		this.topo = this.topo.prox;
		this.tam--;
		return ex;
	}
	public boolean vazia ( ) {
		return (this.topo == null);
	}
	public int tamanho ( ) {
		return this.tam;
	}
}
