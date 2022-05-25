package comp;

import java.io.FileReader;
import java.util.*;

import exceptions.UnexpectedCharacterException;
import exceptions.UnexpectedOperatorException;

public class RPNStacker {
	private static ArrayList<Token> tokens = new ArrayList<>();


	public void scan(FileReader file) throws UnexpectedCharacterException {
		Scanner in =  new Scanner(file);

		String op;
		int result = 0;

		Token token;

		while (in.hasNext()) {
			if (in.hasNextInt()) {

				token = new Token(TokenType.NUM, in.nextLine());
				this.tokens.add(token);
			} else {
				op = in.nextLine();
				if (op.equals("+")) { //Soma
					token = new Token(TokenType.PLUS, op);
					tokens.add(token);
				} else if (op.equals("-")) { //Sub
					token = new Token(TokenType.MINUS, op);
					tokens.add(token);
				} else if (op.equals("*")) { //Mult
					token = new Token(TokenType.STAR, op);
					tokens.add(token);
				} else if (op.equals("/")) { //Div
					token = new Token(TokenType.SLASH, op);
					tokens.add(token);
				} else {
					UnexpectedCharacterException error = new UnexpectedCharacterException(op);
					throw error;
				}
			}
		}

	}

	public int calc() throws Exception {
		Stack stck = new Stack();
		String aux;
		int num1;
		int num2;
		int x;
		for(Token token : this.tokens) {

			switch (token.type){
			case MINUS:
				aux = stck.desempilha();
				num1 = Integer.parseInt(aux);
				aux = stck.desempilha();
				num2 = Integer.parseInt(aux);
				x = num2 - num1;
				aux = Integer.toString(x);
				stck.empilha(aux);
				break;
			case PLUS:
				aux = stck.desempilha();
				num1 = Integer.parseInt(aux);
				aux = stck.desempilha();
				num2 = Integer.parseInt(aux);
				x = num2 + num1;
				aux = Integer.toString(x);
				stck.empilha(aux);
				break;
			case STAR:
				aux = stck.desempilha();
				num1 = Integer.parseInt(aux);
				aux = stck.desempilha();
				num2 = Integer.parseInt(aux);
				x = num2*num1;
				aux = Integer.toString(x);
				stck.empilha(aux);
				break;
			case SLASH:
				aux = stck.desempilha();
				num1 = Integer.parseInt(aux);
				aux = stck.desempilha();
				num2 = Integer.parseInt(aux);
				x = num2/num1;
				aux = Integer.toString(x);
				stck.empilha(aux);
				break;
			case EOF:

			case NUM:
				stck.empilha(token.lexeme);
				break;
			default: {
				UnexpectedOperatorException error = new UnexpectedOperatorException(token.lexeme);
				throw error;
			}
			}
		}
		aux = stck.desempilha();
		x = Integer.parseInt(aux);
		return x;
	}
}


class Stack {
	private static class Celula {
		String ex;
		Celula prox;
	}
	private Celula topo;
	private int tam;

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

class Token {

	public final TokenType type; // token type
	public final String lexeme; // token value

	public Token (TokenType type, String value) {
		this.type = type;
		this.lexeme = value;
	}

	@Override
	public String toString() {
		return "Token [type=" + this.type + ", lexeme=" + this.lexeme + "]";
	}
}

enum TokenType {

	// Literals.
	NUM,

	// Single-character tokens for operations.
	MINUS, PLUS, SLASH, STAR,

	EOF

}