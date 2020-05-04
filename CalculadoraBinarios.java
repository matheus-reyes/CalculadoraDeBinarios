import java.util.Scanner;

public class CalculadoraBinarios{
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        char continuar, opcao, operacao;
        //While que repete até o usuário não querer mais continuar
        do{
            System.out.println("inteiro ou float? (i/f)");
            opcao = entrada.next().charAt(0);
            //Se o usuário escolher inteiros
            if(opcao == 'i'){
                //Pede as informações ao usuário
                System.out.println("numero de bits (com bit de sinal): ");
                int quantidadeBits = entrada.nextInt();
                System.out.println("sinal seguido do primeiro numero: (+, -)");
                String numero1 = entrada.next();
                System.out.println("sinal seguido do segundo numero: (+, -)");
                String numero2 = entrada.next();
                System.out.println("operacao: (+, -, *, /)");
                operacao = entrada.next().charAt(0);
                //Se a operação for soma, soma os números inteiros e exibe o resultado
                if(operacao == '+'){
                    System.out.println("Soma: "+ FuncoesInteiros.somaInteiros(numero1, numero2, quantidadeBits, "soma"));
                //Se a operação for subtração, subtrai os números inteiros e exibe o resultado
                }else if(operacao == '-'){
                    System.out.println("Subtracao: "+ FuncoesInteiros.subtracaoInteiros(numero1, numero2, quantidadeBits));
                //Se a operação for multiplicação, multiplica os números inteiros e exibe o resultado
                }else if(operacao == '*'){
                    System.out.println("Multiplicacao: "+ FuncoesInteiros.multiplicacaoInteiros(numero1, numero2, quantidadeBits));
                //Se a operação for divisão, divide os números inteiros e exibe o resultado
                }else if(operacao == '/'){

                }
            //Se o usuário escolher floats
            }else if(opcao == 'f'){
                //Pede as informações ao usuário
                System.out.println("sinal seguido da mantissa do primeiro numero: (+, -)");
                String mantissa1 = entrada.next();
                System.out.println("expoente do primeiro numero: ");
                String expoente1 = entrada.next();
                System.out.println("sinal seguido da mantissa do segundo numero: (+, -)");
                String mantissa2 = entrada.next();
                System.out.println("expoente do segundo numero: ");
                String expoente2 = entrada.next();
                System.out.println("operacao: (+, -, *, /)");
                operacao = entrada.next().charAt(0);
                //Se a operação for soma, soma os números inteiros e exibe o resultado
                if(operacao == '+'){
                    System.out.println("Soma: " + FuncoesFlutuantes.somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2));
                //Se a operação for subtração, subtrai os números inteiros e exibe o resultado
                }else if(operacao == '-'){
                    System.out.println("Subtracao: " + FuncoesFlutuantes.subtracaoFlutuantes(mantissa1, mantissa2, expoente1, expoente2));
                //Se a operação for multiplicação, multiplica os números inteiros e exibe o resultado
                }else if(operacao == '*'){
                    System.out.println("Multiplicacao: " + FuncoesFlutuantes.multiplicacaoFlutuantes(mantissa1, mantissa2, expoente1, expoente2));
                //Se a operação for divisão, divide os números inteiros e exibe o resultado
                }else if(operacao == '/'){

                }
            }
            //Se o usuário quer ou não continuar mais operações
            System.out.println("Continuar ? (s/n)");
            continuar = entrada.next().charAt(0);
        }while(continuar == 's');
    }
}