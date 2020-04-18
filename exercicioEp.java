import java.util.Scanner;

public class exercicioEp {

	/*
	Criar uma calculadora que faça as quatro operações com números binários inteiros
	em complemento de 2 e em ponto flutuante (IEEE 754 32 bits). A multiplição de inteiros 
	DEVE ser feita usando o algoritmo de Booth!
	
	Primeiramente a calculadora pergunta se o usuário quer fazer uma operação com inteiros
    ou ponto flutuante (ou sair do programa).

    Para os números inteiros, o programa deve pedir que o usuário entre:
    - a quantidade de bits da operação desejada pelo usuário (incluindo o bit de sinal)
    - a operação (soma, subtração, multiplicação ou divisão)
    - o sinal (+ ou -) e o valor em binário do primeiro número (sem usar complemento de 2)
    - o sinal (+ ou -) e o valor em binário do segundo número (sem usar complemento de 2)

    E deve mostrar como saída o resultado da operação (inclusive se deu overflow), retornando
    para a opção de operação com inteiro, ponto flutuante ou sair.

    para os números de ponto flutuante:
    - a operação
    - o sinal (+ ou -), a mantissa  e o expoente em binário (mas sem o bias ou excesso)
    - a mesma coisa para o segundo número

    E a saída deve ser a resposta (inclusive se for overflow ou underflow), retornando para a opção 
    de operação com inteiro, ponto flutuante ou sair.
    */
	
	// Colocar métodos para as operações

	// Opera��o de soma com inteiros
	public static boolean [] somaInt (boolean [] arranjo1, boolean [] arranjo2) {
		boolean [] resultadoSoma;
		resultadoSoma = new boolean [arranjo1.length];
		for (int i = 0; i< arranjo1.length ; i++) {
		resultadoSoma[i] = (boolean) arranjo1[i] + arranjo2[i];
					i=i+1;
		}
		return resultadoSoma;
	}
	/*teteu a opera��o de subtra��o acho que � pra fazer com complemento de 2 n�? 
	 * dai acho que teria que colocar um if mais pra frente e n�o fazer um novo m�todo
	 * eu sou realmente muito ruim em programa��o mas to tentando, paci�ncia com essa jovem
	 * aprendiz, por favor.
	 */
	// Opera��o de subtra��o com inteiros n sei se deixo
	public static boolean [] subInt (boolean [] arranjo1, boolean [] arranjo2) {		
		boolean [] resultadoSub;
		resultadoSub = new boolean [arranjo1.length];
		for (int i = 0; i<arranjo1.length ; i++) {
			resultadoSub[i] = arranjo1 [i] - arranjo2 [i];
			i=i+1;
		}
		return resultadoSub;
		}
    // Opera��o de subtra��o com inteiros, n�o entendi a multiplica��o por booth ainda
	public static boolean [] multInt (boolean[]arranjo1,boolean[]arranjo2) {
		boolean [] resultadoMult;
		resultadoMult = new boolean [arranjo1.length];
		for(int i=0; i<arranjo1.length ; i++) {
			
		}
		// opera��o de multiplica��o aqui
		//provavelmente ta errado mas s� quis rascunhar
		return resultadoMult;
	}

	public static boolean [] divInt (boolean []arranjo1, boolean []arranjo2){
	boolean [] resultadoDiv;
	resultadoDiv = new boolean [arranjo1.length];
	// inserir a opera��o aqui, em cima ta errado mas s� quis preencher	
	return resultadoDiv;
	}
	
	
	
	//fim dos m�todos
	public static void main (String [] args) {
		// escolher o tipo de n�mero ou sair do programa
		System.out.println ("Digite 1 para n�mero inteiro, 2 para n�mero de ponto flutuante ou 3 para sair do programa.");
		Scanner scan3 = new Scanner (System.in);	
		int resp = scan3.nextInt();
		while (resp < 1 || resp > 3) {
			System.out.println ("Digite 1 para n�mero inteiro, 2 para n�mero de ponto flutuante ou 3 para sair do programa.");
				resp = scan3.nextInt();
		}
		// definir a quantidade de bits se a resposta for inteiro
		if (resp == 1) {
			System.out.println ("Digite quantos bits desejados para a opera��o.");
			int bits;
			Scanner scan4 = new Scanner (System.in);
			bits = scan4.nextInt();  
			if (resp == 2) {
		    	bits = 32;
		    	
		    }
	    // selecionar a opera��o 
			System.out.println ("Digite 1 para adi��o, 2 para subtra��o, 3 para multiplica��o e 4 para divis�o");
			Scanner scan5 = new Scanner (System.in);
			int op = scan5.nextInt ();
			
		    switch  (op) {
		    case 1: 
		    	// inserir o m�todo de soma 
		    	break;
		    case 2:
		    	//inserir o m�todo de subtra��o 
		    	break;
		    case 3: 
		    	//inserir m�todo de subtra��o
		        break; 
		    case 4:
		    	//inserir m�todo de divis�o
		        break;
		    }
		 
		    
			}
		
			
		}
		// inserir o primeiro n�mero 
	    // n�o sei o que colocar no tamanho
	    // se for inteiro
	if(resp == 1) {
				boolean [] arranjo1 = new boolean[bits]; {
			System.out.println ("Insira o primeiro n�mero.");
		for (int i = 0 ; i < arranjo1.length; i++) {	
			Scanner scan = new Scanner (System.in);
			arranjo1 [i] = scan.nextBoolean();
			i=i+1;
			// inserir o segundo n�mero
			boolean [] arranjo2 = new boolean [arranjo1.length];
			System.out.println("Insira o segundo n�mero.");
			for (int j = 0; j <arranjo2.length; j++) {	
				Scanner scan2 = new Scanner (System.in);
				arranjo2 [i] = scan2.nextBoolean();
	}
		}
				}
		// N�o sei se � necess�rio mas t� ai
		//se for ponto flutuante
	    }else {
		boolean [] arranjo1 = new boolean[32]; {
	     System.out.println ("Insira o primeiro n�mero, com o sinal, mantissa e expoente");
           for (int i = 0 ; i < arranjo1.length; i++) {	
	       Scanner scan = new Scanner (System.in);
	        arranjo1 [i] = scan.nextBoolean();
	               i=i+1;
				// inserir o segundo n�mero
		boolean [] arranjo2 = new boolean [32];
		System.out.println("Insira o segundo n�mero com o sinal, matissa e expoente");
		for (int j = 0; j <32 ; j ++) {	
			Scanner scan2 = new Scanner (System.in);
			arranjo2 [i] = scan2.nextBoolean();
		}
	}
		  }
		}
	}
}
	


