import java.util.Scanner;

public class CalculadoraBinarios{
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        char continuar;
        do{
            System.out.println("numero de bits (com bit de sinal): ");
            int quantidadeBits = entrada.nextInt();
            System.out.println("sinal seguido do primeiro numero:");
            String numero1 = entrada.next();
            System.out.println("sinal seguido do segundo numero:");
            String numero2 = entrada.next();
            System.out.println("Complemento de 1: "+ complementoDe1(numero1, quantidadeBits));
            System.out.println("Complemento de 2: "+ complementoDe2(numero1, quantidadeBits));
            System.out.println("--------------------------");
            System.out.println("Complemento de 1: "+ complementoDe1(numero2, quantidadeBits));
            System.out.println("Complemento de 2: "+ complementoDe2(numero2, quantidadeBits));
            System.out.println("--------------------------");
            System.out.println("Soma: "+ somaInteiros(numero1, numero2, quantidadeBits));
            System.out.println("Subtracao: "+ subtracaoInteiros(numero1, numero2, quantidadeBits));
            System.out.println("Complemento de 2 da Subtração: "+ complementoDe2(subtracaoInteiros(numero1, numero2, quantidadeBits), quantidadeBits));
            System.out.println("Complemento de 2 da Soma: "+ complementoDe2(somaInteiros(numero1, numero2, quantidadeBits), quantidadeBits));
            System.out.println("Continuar ? (s/n)");
            continuar = entrada.next().charAt(0);
        }while(continuar == 's');
    }

    //Método que realiza a subtração de dois dados números binários inteiros
    public static String subtracaoInteiros(String numero1, String numero2, int quantidadeBits) {
        //Verifica se deu overflow
        if(verificaOverflow(numero1, numero2, quantidadeBits)){
            return "overflow!";
        }
        //String que armazenará o resultado da subtracao, como usamos concatenação o resultado ficará invertido
        String resultadoSubtracaoInvertido = "";
        //Boolean que armazenará uma representação se houve ou não transporte
        boolean transporte = false;
        //Números ainda não preenchidos com 0
        String numero1NaoTratado = numero1;
        String numero2NaoTratado = numero2;
        // completamos com zeros a esquerda os bits
        numero1 = preencherZeros(numero1NaoTratado, quantidadeBits);
        numero2 = preencherZeros(numero2NaoTratado, quantidadeBits);
		//For que percorre os dois números e realiza a subtração
        for(int contador = (quantidadeBits - 2); contador >= 0; contador--){
            //Caso o complemento de 1 seja 0, a soma seja 1 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '1' && transporte == false){
                resultadoSubtracaoInvertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 1 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '1' && transporte == true){
                resultadoSubtracaoInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 0 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '0' && transporte == false){
                resultadoSubtracaoInvertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 0 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '0' && transporte == true){
                resultadoSubtracaoInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 1 e não há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '1' && transporte == false){
                resultadoSubtracaoInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 1 e há transporte, o resultado é 1 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '1' && transporte == true){
                resultadoSubtracaoInvertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 0 e não há transporte, o resultado é 0 e não há transporte na próxima operação
            }else if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '0' && transporte == false){
                resultadoSubtracaoInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 0 e há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '0' && transporte == true){
                resultadoSubtracaoInvertido += "1";
                transporte = true;
            }
        }
        // String que armazenará o resultado da soma
        String resultadoSubtracao = "";
        //For que desinverte a String
        for(int contador = (resultadoSubtracaoInvertido.length() - 1); contador >= 0; contador--){
            resultadoSubtracao += resultadoSubtracaoInvertido.charAt(contador);
        }
        
        // Se apenas o primeiro número for negativo e ele for maior que o segundo, o resultado será negativo, então o bit de sinal é 1
        if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '+'){
            //uma subtração com um número positivo dá um número negativo, retornando uma soma de negativos
            numero2NaoTratado = numero2NaoTratado.replace('+', '-');
            resultadoSubtracao = somaInteiros(numero1NaoTratado, numero2NaoTratado, quantidadeBits);
        // Se apenas o segundo número for negativo e ele for maior que o primeiro, o resultado será negativo, então o bit de sinal é 1
        }else if(numero1NaoTratado.charAt(0) == '+' && numero2NaoTratado.charAt(0) == '-'){
            //uma subtração com um número negativo dá um número positivo, retornando uma soma
            numero2NaoTratado = numero2NaoTratado.replace('-', '+');
            resultadoSubtracao = somaInteiros(numero1NaoTratado, numero2NaoTratado, quantidadeBits); 
        // Se os dois números forem negativos, efetuamos a soma o mantemos o sinal de negativo
        }else if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '-'){
            //uma subtração com os dois número negativos dá um número positivo, retornando uma soma
            numero2NaoTratado = numero2NaoTratado.replace('-', '+');
            resultadoSubtracao = somaInteiros(numero1NaoTratado, numero2NaoTratado, quantidadeBits);
        // Compara qual o sinal do maior, o sinal do maior permanece no resultado
        }else if(numero1NaoTratado.charAt(0) == '+' && numero2NaoTratado.charAt(0) == '+' && verificaMaior(numero1NaoTratado, numero2NaoTratado)){
            resultadoSubtracao = "0" + resultadoSubtracao;
        }else if(numero1NaoTratado.charAt(0) == '+' && numero2NaoTratado.charAt(0) == '+' && verificaMaior(numero1NaoTratado, numero2NaoTratado)){
            resultadoSubtracao = "1" + resultadoSubtracao;
        }

        //Retorna o resultado da soma
		return resultadoSubtracao;
    }

    //Método que realiza a soma de dois dados números binários inteiros
    public static String somaInteiros(String numero1, String numero2, int quantidadeBits) {
        //Verifica se deu overflow
        if(verificaOverflow(numero1, numero2, quantidadeBits)){
            return "overflow!";
        }
        //String que armazenará o resultado da soma, como usamos concatenação o resultado ficará invertido
        String resultadoSomaInvertido = "";
        //Boolean que armazenará uma representação se houve ou não transporte
        boolean transporte = false;
        //Números ainda não preenchidos com 0
        String numero1NaoTratado = numero1;
        String numero2NaoTratado = numero2;
        //Se o primeiro número for negativo, usamos o complemento de 2 dele para representa-lo na soma
        if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '+'){
            numero1 = complementoDe2(numero1NaoTratado, quantidadeBits);
        }
        //Se o segundo número for negativo, usamos o complemento de 2 dele para representa-lo na soma
        if(numero2NaoTratado.charAt(0) == '-' && numero1NaoTratado.charAt(0) == '+'){
            numero2 = complementoDe2(numero2NaoTratado, quantidadeBits);
        }
        //Se o primeiro número for positivo, apenas completamos com zeros a esquerda os bits
        if(numero1NaoTratado.charAt(0) == '+'){
            numero1 = preencherZeros(numero1NaoTratado, quantidadeBits);
        }
        //Se o segundo número for positivo, apenas completamos com zeros a esquerda os bits
        if(numero2NaoTratado.charAt(0) == '+'){
            numero2 = preencherZeros(numero2NaoTratado, quantidadeBits);
        }
        //Se os dois números forem negativos, devemos fazer a soma deles em complemento de 2
        if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '-'){
            numero1 = complementoDe2(numero1NaoTratado, quantidadeBits);
            numero2 = complementoDe2(numero2NaoTratado, quantidadeBits);
        }

		//For que percorre os dois números e realiza a soma
        for(int contador = (quantidadeBits - 2); contador >= 0; contador--){
            //Caso o complemento de 1 seja 0, a soma seja 1 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '1' && transporte == false){
                resultadoSomaInvertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 1 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '1' && transporte == true){
                resultadoSomaInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 0 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '0' && transporte == false){
                resultadoSomaInvertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 0 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '0' && transporte == true){
                resultadoSomaInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 1 e não há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '1' && transporte == false){
                resultadoSomaInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 1 e há transporte, o resultado é 1 e há transporte na próxima operação
            }else if(numero1.charAt(contador) == '1' && numero2.charAt(contador) == '1' && transporte == true){
                resultadoSomaInvertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 0 e não há transporte, o resultado é 0 e não há transporte na próxima operação
            }else if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '0' && transporte == false){
                resultadoSomaInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 0 e há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numero1.charAt(contador) == '0' && numero2.charAt(contador) == '0' && transporte == true){
                resultadoSomaInvertido += "1";
                transporte = false;
            }
        }
        // String que armazenará o resultado da soma
        String resultadoSoma = "";
        //For que desinverte a String
        for(int contador = (resultadoSomaInvertido.length() - 1); contador >= 0; contador--){
            resultadoSoma += resultadoSomaInvertido.charAt(contador);
        }
        
        // Se apenas o primeiro número for negativo e ele for maior que o segundo, o resultado será negativo, então o bit de sinal é 1
        if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '+' && verificaMaior(numero1NaoTratado, numero2NaoTratado)){
            resultadoSoma = "1" + resultadoSoma;
        // Se apenas o segundo número for negativo e ele for maior que o primeiro, o resultado será negativo, então o bit de sinal é 1
        }else if(numero1NaoTratado.charAt(0) == '+' && numero2NaoTratado.charAt(0) == '-' && verificaMaior(numero2NaoTratado, numero1NaoTratado)){
            resultadoSoma = "1" + resultadoSoma;
        // Se os dois números forem negativos, efetuamos a soma o mantemos o sinal de negativo
        }else if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '-'){
            resultadoSoma = "1" + resultadoSoma;
        // Os outros casos são positivos, então o bit de sinal é 0
        }else{
            resultadoSoma = "0" + resultadoSoma;
        }

        //Retorna o resultado da soma
		return resultadoSoma;
    }
    
    //Método que realiza o complemento de 2 de um dado número binário
    public static String complementoDe2(String numero, int quantidadeBits){
        //String que representa o número 1 na hora de somar o número para fazer o complemento de 2
        String somaCom1 = "";
        //String que armazenará o resultado do complemento de 2, como usamos concatenação o resultado ficará invertido
        String resultadoComplementoDe2Invertido = "";
        //Boolean que armazenará uma representação se houve ou não transporte
        boolean transporte = false;
        //Armazena o Complemento de 1 do número
        String complementoDe1 = complementoDe1(numero, quantidadeBits);
        //For que preenche com 0 as esquerda para completar o número de Bits (Desconsidera o sinal e a última posição)
        for(int contador = 0; contador < (quantidadeBits - 2); contador++){
            somaCom1 += "0";
        }
        //Coloca o número 1 no final da String
        somaCom1 += "1";
        //For que realiza a soma do complemento de 1 com o número 1 (Desconsidera o sinal)
        for(int contador = (quantidadeBits - 2); contador >= 0; contador--){
            //Caso o complemento de 1 seja 0, a soma seja 1 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            if(complementoDe1.charAt(contador) == '0' && somaCom1.charAt(contador) == '1' && transporte == false){
                resultadoComplementoDe2Invertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 1 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '0' && somaCom1.charAt(contador) == '1' && transporte == true){
                resultadoComplementoDe2Invertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 0 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '1' && somaCom1.charAt(contador) == '0' && transporte == false){
                resultadoComplementoDe2Invertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 0 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '1' && somaCom1.charAt(contador) == '0' && transporte == true){
                resultadoComplementoDe2Invertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 1 e não há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '1' && somaCom1.charAt(contador) == '1' && transporte == false){
                resultadoComplementoDe2Invertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 1 e há transporte, o resultado é 1 e há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '1' && somaCom1.charAt(contador) == '1' && transporte == true){
                resultadoComplementoDe2Invertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 0 e não há transporte, o resultado é 0 e não há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '0' && somaCom1.charAt(contador) == '0' && transporte == false){
                resultadoComplementoDe2Invertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 0 e há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(complementoDe1.charAt(contador) == '0' && somaCom1.charAt(contador) == '0' && transporte == true){
                resultadoComplementoDe2Invertido += "1";
                transporte = false;
            }
        }
        // String que armazenará o resultado do complemento de 2
        String resultadoComplementoDe2 = "";
        //For que desinverte a String
        for(int contador = (resultadoComplementoDe2Invertido.length() - 1); contador >= 0; contador--){
            resultadoComplementoDe2 += resultadoComplementoDe2Invertido.charAt(contador);
        }
        //Retorna o resultado do complemento de 2
        return resultadoComplementoDe2;
    }

    //Método que realiza o complemento de 1 de um dado número binário
    public static String complementoDe1(String numero, int quantidadeBits){
        //String que armazenará o resultado do complemento de 1
        String resultadoComplementoDe1 = "";
        //Preenche os zeros a esquerda do número
        numero = preencherZeros(numero, quantidadeBits);
        //For que percorre o número dado, começa da posição 1 para desconsiderar o sinal
        for(int contador = 0; contador < numero.length(); contador++){
            //Se o número na posição for igual a 0, inverte o valor para 1
            if(numero.charAt(contador) == '0'){
                resultadoComplementoDe1 += "1";
            //Se o número na posição for igual a 1, inverte o valor para 0
            }else if(numero.charAt(contador) == '1'){
                resultadoComplementoDe1 += "0";
            }
        }
        //Retorna o resultado do complemento de 1
        return resultadoComplementoDe1;
    }

    //Método que preenche com zeros a esquerda até completar o número de bits e retorna o número preenchido
    public static String preencherZeros(String numero, int quantidadeBits){
        //String que armazenará os zeros a esquerda
        String numeroPreenchido = "";
        //For que preenche com 0 as esquerda para completar o número de Bits
        for(int contador = 0; contador < (quantidadeBits - numero.length()); contador++){
            numeroPreenchido += "0";
        }
        //For que preenche o restante da String com os números reais
        for(int contador = 1; contador < numero.length(); contador++){
            numeroPreenchido += numero.charAt(contador); 
        }
        //Retorna a String preenchida com os zeros a esquerda
        return numeroPreenchido;
    }

    //Método que verifica se um número binário é maior que outro
    public static boolean verificaMaior(String numero1, String numero2){   
        // Variáveis Auxiliares, que armazenam os expoentes e os valores 
        int somadorNumero1 = 0;
        int somadorNumero2 = 0;
        int expoenteNumero1 = 0;
        int expoenteNumero2 = 0;
        //For que calcula em decimal o primeiro número binário
        for(int contador = (numero1.length() - 1); contador > 0; contador--){
            if(numero1.charAt(contador) == '1'){
                somadorNumero1 += Math.pow(2, expoenteNumero1);
            }
            expoenteNumero1++;
        }
        //For que calcula em decimal o segundo número binário
        for(int contador = (numero2.length() - 1); contador > 0; contador--){
            if(numero2.charAt(contador) == '1'){
                somadorNumero2 += Math.pow(2, expoenteNumero2);
            }
            expoenteNumero2++;
        }
        // Retorna true se o número 1 for maior que o número 2 e false caso não seja
        return somadorNumero1 > somadorNumero2;
    }

    //Método que verifica se ocorreu overflow
    public static boolean verificaOverflow(String numero1, String numero2, int quantidadeBits){   
        // Variáveis Auxiliares, que armazenam os expoentes e os valores 
        int somadorNumero1 = 0;
        int somadorNumero2 = 0;
        int expoenteNumero1 = 0;
        int expoenteNumero2 = 0;
        //For que calcula em decimal o primeiro número binário
        for(int contador = (numero1.length() - 1); contador > 0; contador--){
            if(numero1.charAt(contador) == '1'){
                somadorNumero1 += Math.pow(2, expoenteNumero1);
            }
            expoenteNumero1++;
        }
        //For que calcula em decimal o segundo número binário
        for(int contador = (numero2.length() - 1); contador > 0; contador--){
            if(numero2.charAt(contador) == '1'){
                somadorNumero2 += Math.pow(2, expoenteNumero2);
            }
            expoenteNumero2++;
        }
        //Se a soma dos números ultrapassar o limite do valor máximo dos bits, retorna true, dando overflow
        return (somadorNumero1 + somadorNumero2) > Math.pow(2, (quantidadeBits - 1));
    }
}