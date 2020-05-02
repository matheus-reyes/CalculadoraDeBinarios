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
                    System.out.println("Soma: "+ somaInteiros(numero1, numero2, quantidadeBits));
                //Se a operação for subtração, subtrai os números inteiros e exibe o resultado
                }else if(operacao == '-'){
                    System.out.println("Subtracao: "+ subtracaoInteiros(numero1, numero2, quantidadeBits));
                //Se a operação for multiplicação, multiplica os números inteiros e exibe o resultado
                }else if(operacao == '*'){
                    System.out.println("Multiplicacao: "+ multiplicacaoInteiros(numero1, numero2, quantidadeBits));
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
                    System.out.println("Soma: " + somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2));
                //Se a operação for subtração, subtrai os números inteiros e exibe o resultado
                }else if(operacao == '-'){
                    System.out.println("Subtracao: " + subtracaoFlutuantes(mantissa1, mantissa2, expoente1, expoente2));
                //Se a operação for multiplicação, multiplica os números inteiros e exibe o resultado
                }else if(operacao == '*'){

                //Se a operação for divisão, divide os números inteiros e exibe o resultado
                }else if(operacao == '/'){

                }
                System.out.println("Binario 1: " + converteBinarioFlutuante(mantissa1, expoente1));
                System.out.println("Binario 2: " + converteBinarioFlutuante(mantissa2, expoente2));
            }
            //Se o usuário quer ou não continuar mais operações
            System.out.println("Continuar ? (s/n)");
            continuar = entrada.next().charAt(0);
        }while(continuar == 's');
    }

    //Método que realiza a subtração de dois números binários flutuantes
    public static String subtracaoFlutuantes(String mantissa1, String mantissa2, String expoente1, String expoente2){
        //Converte os dois números movendo as vírgulas de acordo com o expoente
        String numero1Convertido = converteBinarioFlutuante(mantissa1, expoente1); 
        String numero2Convertido = converteBinarioFlutuante(mantissa2, expoente2); 
        //Armazena os sinais dos dois números
        String sinal1 = "" + numero1Convertido.charAt(0);
        String sinal2 = "" + numero2Convertido.charAt(0);
        //Remove os sinais dos dois números
        numero1Convertido = numero1Convertido.substring(1, numero1Convertido.length());
        numero2Convertido = numero2Convertido.substring(1, numero2Convertido.length());
        //Separa as partes inteiras e as partes decimais de cada número
        String partes1[] = numero1Convertido.split("[.]");
        String partes2[] = numero2Convertido.split("[.]");
        //Atribui as partes as variáveis corretas
        String inteiroNumero1 = partes1[0];
        String decimalNumero1 = partes1[1];
        String inteiroNumero2 = partes2[0];
        String decimalNumero2 = partes2[1];
        //Se o tamanho dos números inteiros forem diferentes, precisamos preencher com zeros a esquerda para ficarem padronizados
        if(inteiroNumero1.length() > inteiroNumero2.length()){
            String zeros = "";
            for(int contador = inteiroNumero2.length(); contador < inteiroNumero1.length(); contador++){
                zeros += "0";
            }
            inteiroNumero2 = zeros + inteiroNumero2;
        }else if(inteiroNumero2.length() > inteiroNumero1.length()){
            String zeros = "";
            for(int contador = inteiroNumero1.length(); contador < inteiroNumero2.length(); contador++){
                zeros += "0";
            }
            inteiroNumero1 = zeros + inteiroNumero1;
        }

        int posicao1 = 0;
        int posicao2 = 0;

        //Identifica o último 1 a direita para evitar zeros desnecessários
        for(int contador = decimalNumero1.length() - 1; contador >= 0; contador--){
            if(decimalNumero1.charAt(contador) == '1'){
                posicao1 = contador;
                break;
            }
        }
        //Identifica o último 1 a direita para evitar zeros desnecessários
        for(int contador = decimalNumero2.length() - 1; contador >= 0; contador--){
            if(decimalNumero2.charAt(contador) == '1'){
                posicao2 = contador;
                break;
            }
        }
        //Elimina os zeros desnecessários a direita do decimal
        decimalNumero1 = decimalNumero1.substring(0, posicao1 + 1);
        decimalNumero2 = decimalNumero2.substring(0, posicao2 + 1);

        //Se o tamanho dos números decimais forem diferentes, precisamos preencher com zeros a direita para ficarem padronizados
        if(decimalNumero1.length() > decimalNumero2.length()){
            //Identifica quantos zeros a direita precisamos ter
            String zeros = "";
            for(int contador = decimalNumero2.length(); contador < decimalNumero1.length(); contador++){
                zeros += "0";
            }
            //
            decimalNumero2 = decimalNumero2 + zeros;
        }else if(decimalNumero2.length() > decimalNumero1.length()){
            String zeros = "";
            for(int contador = decimalNumero1.length(); contador < decimalNumero2.length(); contador++){
                zeros += "0";
            }
            decimalNumero1 = decimalNumero1 + zeros;
        }

        //String que armazenará o resultado da soma, como usamos concatenação o resultado ficará invertido
        String resultadoSubtracaoInvertido = "";
        //Boolean que armazenará uma representação se houve ou não transporte
        boolean transporte = false;
        //String que armazenará a junção dos inteiros e decimais
        String numeroCompleto1 = inteiroNumero1 + decimalNumero1;
        String numeroCompleto2 = inteiroNumero2 + decimalNumero2;

        //For que percorre os dois números e realiza a subtração
        for(int contador = (numeroCompleto1.length() - 1); contador >= 0; contador--){
            //Caso o complemento de 1 seja 0, a soma seja 1 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '1' && transporte == false){
                resultadoSubtracaoInvertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 1 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '1' && transporte == true){
                resultadoSubtracaoInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 0 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '0' && transporte == false){
                resultadoSubtracaoInvertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 0 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '0' && transporte == true){
                resultadoSubtracaoInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 1 e não há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '1' && transporte == false){
                resultadoSubtracaoInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 1 e há transporte, o resultado é 1 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '1' && transporte == true){
                resultadoSubtracaoInvertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 0 e não há transporte, o resultado é 0 e não há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '0' && transporte == false){
                resultadoSubtracaoInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 0 e há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '0' && transporte == true){
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
        if(sinal1.equals("-") && sinal2.equals("+")){
            //uma subtração com um número positivo dá um número negativo, retornando uma soma de negativos
            mantissa2 = mantissa2.replace("+", "-");
            resultadoSubtracao = somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2);
        // Se apenas o segundo número for negativo e ele for maior que o primeiro, o resultado será negativo, então o bit de sinal é 1
        }else if(sinal1.equals("+") && sinal2.equals("-")){
            //uma subtração com um número negativo dá um número positivo, retornando uma soma
            mantissa2 = mantissa2.replace("-", "+");
            resultadoSubtracao = somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2); 
        // Se os dois números forem negativos, e o segundo número for maior, o sinal é positivo
        }else if(sinal1.equals("-") && sinal2.equals("-") && verificaMaiorFlutuante(inteiroNumero2, inteiroNumero1, decimalNumero2, decimalNumero1)){
            //uma subtração com os dois número negativos dá um número positivo, retornando uma soma
            mantissa2 = mantissa2.replace("-", "+");
            resultadoSubtracao = subtracaoFlutuantes(mantissa2, mantissa1, expoente2, expoente1);
        // Se os dois números forem negativos, e o primeiro número for maior, o sinal é negativo
        }else if(sinal1.equals("-") && sinal2.equals("-") && verificaMaiorFlutuante(inteiroNumero1, inteiroNumero2, decimalNumero1, decimalNumero2)){
            //uma subtração com os dois número negativos dá um número positivo, retornando uma soma
            mantissa2 = mantissa2.replace("-", "+");
            String subtracao = subtracaoFlutuantes(mantissa2, mantissa1, expoente2, expoente1).replace(".", "");
            int posicao = subtracao.indexOf(" ");
            subtracao = subtracao.substring(1, posicao);
            subtracao = complementoDe2(sinal1 + subtracao, subtracao.length() + 1);
            resultadoSubtracao = "1" + subtracao.charAt(0) + "." + subtracao.substring(1, subtracao.length()) + " * 2 ^ " + (subtracao.length() - decimalNumero1.length() - 1);
        // Compara qual o sinal do maior, o sinal do maior permanece no resultado
        }else if(sinal1.equals("+") && sinal2.equals("+") && verificaMaiorFlutuante(inteiroNumero1, inteiroNumero2, decimalNumero1, decimalNumero2)){
            resultadoSubtracao = "0" + resultadoSubtracao.charAt(0) + "." + resultadoSubtracao.substring(1, resultadoSubtracao.length()) + " * 2 ^ " + (resultadoSubtracao.length() - decimalNumero1.length() - 1);
        }else if(sinal1.equals("+") && sinal2.equals("+") && verificaMaiorFlutuante(inteiroNumero2, inteiroNumero1, decimalNumero2, decimalNumero1)){
            String subtracao = subtracaoFlutuantes(mantissa2, mantissa1, expoente2, expoente1).replace(".", "");
            int posicao = subtracao.indexOf(" ");
            subtracao = subtracao.substring(1, posicao);
            subtracao = complementoDe2(sinal1 + subtracao, subtracao.length() + 1);
            resultadoSubtracao = "1" + subtracao.charAt(0) + "." + subtracao.substring(1, subtracao.length()) + " * 2 ^ " + (subtracao.length() - decimalNumero1.length() - 1);
        }

        //Retorna o resultado da subtração
		return resultadoSubtracao;
    }
    
    //Método que realiza a soma de dois números binários flutuantes
    public static String somaFlutuantes(String mantissa1, String mantissa2, String expoente1, String expoente2){
        //Converte os dois números movendo as vírgulas de acordo com o expoente
        String numero1Convertido = converteBinarioFlutuante(mantissa1, expoente1); 
        String numero2Convertido = converteBinarioFlutuante(mantissa2, expoente2); 
        //Armazena os sinais dos dois números
        String sinal1 = "" + numero1Convertido.charAt(0);
        String sinal2 = "" + numero2Convertido.charAt(0);
        //Remove os sinais dos dois números
        numero1Convertido = numero1Convertido.substring(1, numero1Convertido.length());
        numero2Convertido = numero2Convertido.substring(1, numero2Convertido.length());
        //Separa as partes inteiras e as partes decimais de cada número
        String partes1[] = numero1Convertido.split("[.]");
        String partes2[] = numero2Convertido.split("[.]");
        //Atribui as partes as variáveis corretas
        String inteiroNumero1 = partes1[0];
        String decimalNumero1 = partes1[1];
        String inteiroNumero2 = partes2[0];
        String decimalNumero2 = partes2[1];
        //Se o tamanho dos números inteiros forem diferentes, precisamos preencher com zeros a esquerda para ficarem padronizados
        if(inteiroNumero1.length() > inteiroNumero2.length()){
            String zeros = "";
            for(int contador = inteiroNumero2.length(); contador < inteiroNumero1.length(); contador++){
                zeros += "0";
            }
            inteiroNumero2 = zeros + inteiroNumero2;
        }else if(inteiroNumero2.length() > inteiroNumero1.length()){
            String zeros = "";
            for(int contador = inteiroNumero1.length(); contador < inteiroNumero2.length(); contador++){
                zeros += "0";
            }
            inteiroNumero1 = zeros + inteiroNumero1;
        }

        int posicao1 = 0;
        int posicao2 = 0;

        //Identifica o último 1 a direita para evitar zeros desnecessários
        for(int contador = decimalNumero1.length() - 1; contador >= 0; contador--){
            if(decimalNumero1.charAt(contador) == '1'){
                posicao1 = contador;
                break;
            }
        }
        //Identifica o último 1 a direita para evitar zeros desnecessários
        for(int contador = decimalNumero2.length() - 1; contador >= 0; contador--){
            if(decimalNumero2.charAt(contador) == '1'){
                posicao2 = contador;
                break;
            }
        }
        //Elimina os zeros desnecessários a direita do decimal
        decimalNumero1 = decimalNumero1.substring(0, posicao1 + 1);
        decimalNumero2 = decimalNumero2.substring(0, posicao2 + 1);

        //Se o tamanho dos números decimais forem diferentes, precisamos preencher com zeros a direita para ficarem padronizados
        if(decimalNumero1.length() > decimalNumero2.length()){
            //Identifica quantos zeros a direita precisamos ter
            String zeros = "";
            for(int contador = decimalNumero2.length(); contador < decimalNumero1.length(); contador++){
                zeros += "0";
            }
            //
            decimalNumero2 = decimalNumero2 + zeros;
        }else if(decimalNumero2.length() > decimalNumero1.length()){
            String zeros = "";
            for(int contador = decimalNumero1.length(); contador < decimalNumero2.length(); contador++){
                zeros += "0";
            }
            decimalNumero1 = decimalNumero1 + zeros;
        }

        //String que armazenará o resultado da soma, como usamos concatenação o resultado ficará invertido
        String resultadoSomaInvertido = "";
        //Boolean que armazenará uma representação se houve ou não transporte
        boolean transporte = false;
        //String que armazenará a junção dos inteiros e decimais
        String numeroCompleto1 = "";
        String numeroCompleto2 = "";

        //Se o primeiro número for negativo, usamos o complemento de 2 dele para representa-lo na soma
        if(sinal1.equals("-") && sinal2.equals("+")){
            numeroCompleto1 = complementoDe2(sinal1 + inteiroNumero1 + decimalNumero1, inteiroNumero1.length() + decimalNumero1.length() + 1);
            numeroCompleto2 = inteiroNumero2 + decimalNumero2;
        }
        //Se o segundo número for negativo, usamos o complemento de 2 dele para representa-lo na soma
        if(sinal2.equals("-") && sinal1.equals("+")){
            numeroCompleto2 = complementoDe2(sinal2 + inteiroNumero2 + decimalNumero2, inteiroNumero2.length() + decimalNumero2.length() + 1);
            numeroCompleto1 = inteiroNumero1 + decimalNumero1;
        }
        //Se os dois números forem negativos, devemos fazer a soma deles em complemento de 2
        if(sinal1.equals("-") && sinal2.equals("-")){
            numeroCompleto1 = complementoDe2(sinal1 + inteiroNumero1 + decimalNumero1, inteiroNumero1.length() + decimalNumero1.length() + 1);
            numeroCompleto2 = complementoDe2(sinal2 + inteiroNumero2 + decimalNumero2, inteiroNumero2.length() + decimalNumero2.length() + 1);
        }
        //Se os dois números forem positivos, apenas juntamos os dois
        if(sinal1.equals("+") && sinal2.equals("+")){
            numeroCompleto1 = inteiroNumero1 + decimalNumero1;
            numeroCompleto2 = inteiroNumero2 + decimalNumero2;
        }

		//For que percorre os dois números e realiza a soma
        for(int contador = (numeroCompleto1.length() - 1); contador >= 0; contador--){
            //Caso o complemento de 1 seja 0, a soma seja 1 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '1' && transporte == false){
                resultadoSomaInvertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 1 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '1' && transporte == true){
                resultadoSomaInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 0 e não há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '0' && transporte == false){
                resultadoSomaInvertido += "1";
                transporte = false;
            //Caso o complemento de 1 seja 1, a soma seja 0 e há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '0' && transporte == true){
                resultadoSomaInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 1 e não há transporte, o resultado é 0 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '1' && transporte == false){
                resultadoSomaInvertido += "0";
                transporte = true;
            //Caso o complemento de 1 seja 1, a soma seja 1 e há transporte, o resultado é 1 e há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '1' && numeroCompleto2.charAt(contador) == '1' && transporte == true){
                resultadoSomaInvertido += "1";
                transporte = true;
            //Caso o complemento de 1 seja 0, a soma seja 0 e não há transporte, o resultado é 0 e não há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '0' && transporte == false){
                resultadoSomaInvertido += "0";
                transporte = false;
            //Caso o complemento de 1 seja 0, a soma seja 0 e há transporte, o resultado é 1 e não há transporte na próxima operação
            }else if(numeroCompleto1.charAt(contador) == '0' && numeroCompleto2.charAt(contador) == '0' && transporte == true){
                resultadoSomaInvertido += "1";
                transporte = false;
            }
            //Caso na última posição ainda houver transporte, um bit a mais é considerado
            if(contador == 0 && transporte == true){
                resultadoSomaInvertido += "1";
            }
        }
        // String que armazenará o resultado da soma
        String resultadoSoma = "";
        //For que desinverte a String
        for(int contador = (resultadoSomaInvertido.length() - 1); contador >= 0; contador--){
            resultadoSoma += resultadoSomaInvertido.charAt(contador);
        }
        //Verifica se existem zeros a esquerda, se existem devem ser desconsiderados
        if(resultadoSoma.charAt(0) != '1'){
            int contador = 1;
            int posicao = 0;
            while(true){
                if(resultadoSoma.charAt(contador) == '1'){
                    posicao = contador;
                    break;
                }
            }
            resultadoSoma = resultadoSoma.substring(posicao, resultadoSoma.length());
        }
        
        // Se apenas o primeiro número for negativo e ele for maior que o segundo, o resultado será negativo, então o bit de sinal é 1
        if(sinal1.equals("-") && sinal2.equals("+") && verificaMaiorFlutuante(inteiroNumero1, inteiroNumero2, decimalNumero1, decimalNumero2)){
            resultadoSoma = "1" + resultadoSoma.charAt(0) + "." + resultadoSoma.substring(1, resultadoSoma.length()) + " * 2 ^ " + (resultadoSoma.length() - decimalNumero1.length() - 1);
        // Se apenas o segundo número for negativo e ele for maior que o primeiro, o resultado será negativo, então o bit de sinal é 1
        }else if(sinal1.equals("+") && sinal2.equals("-") && verificaMaiorFlutuante(inteiroNumero2, inteiroNumero1, decimalNumero2, decimalNumero1)){
            resultadoSoma = "1" + resultadoSoma.charAt(0) + "." + resultadoSoma.substring(1, resultadoSoma.length()) + " * 2 ^ " + (resultadoSoma.length() - decimalNumero1.length() - 1);
        // Se os dois números forem negativos, efetuamos a soma o mantemos o sinal de negativo
        }else if(sinal1.equals("-") && sinal2.equals("-")){
            mantissa1 = mantissa1.replace("-", "+");
            mantissa2 = mantissa2.replace("-", "+");
            String soma = somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2).replace(".", "");
            int posicao = soma.indexOf(" ");
            soma = soma.substring(1, posicao);
            soma = complementoDe2(sinal1 + soma, soma.length() + 1);
            resultadoSoma = "1" + soma.charAt(0) + "." + soma.substring(1, soma.length()) + " * 2 ^ " + (soma.length() - decimalNumero1.length() - 1);
        // Se o primeiro número for negativo e menor que o segundo, é realizada a subtração
        }else if(sinal1.equals("-") && sinal2.equals("+") && verificaMaiorFlutuante(inteiroNumero2, inteiroNumero1, decimalNumero2, decimalNumero1)){
            resultadoSoma = "0" + subtracaoFlutuantes(mantissa1, mantissa2, expoente1, expoente2);
            // Os outros casos são positivos, então o bit de sinal é 0
        }else if(sinal1.equals("+") && sinal2.equals("-") && verificaMaiorFlutuante(inteiroNumero1, inteiroNumero2, decimalNumero1, decimalNumero2)){
            mantissa2 = mantissa2.replace("-", "+");
            resultadoSoma = subtracaoFlutuantes(mantissa1, mantissa2, expoente1, expoente2);
        }else{
            resultadoSoma = "0" + resultadoSoma.charAt(0) + "." + resultadoSoma.substring(1, resultadoSoma.length()) + " * 2 ^ " + (resultadoSoma.length() - decimalNumero1.length() - 1);
        }

        return resultadoSoma;
    }

    //Método que realiza a divisão de dois números binários inteiros
    public static String divisaoInteiros(String numero1, String numero2, int quantidadeBits){
        
        int contador = 0;
        int contador2 = 0;

        while(contador < numero1.length()){

            while(true){

                if(verificaMaior(numero1.substring(0, contador), numero2)){
                    
                }

                if(contador == numero1.length()){
                    break;
                }

                contador2++;
            }

            contador++;
        }

        return "";
    }

    //Método que realiza a multiplicação com algoritmo de booth de dois números binários inteiros
    public static String multiplicacaoInteiros(String numero1, String numero2, int quantidadeBits){
        //Tamanho dos números antes de serem alterados
        int tamanho1 = numero1.length(); 
        int tamanho2 = numero2.length();
        //Guarda os sinais dos números
        String sinal1 = "" + numero1.charAt(0);
        String sinal2 = "" + numero2.charAt(0);
        //Preencher com zeros a esquerda o menor número de bits
        if(numero1.length() < numero2.length()){
            numero1 = preencherZeros(numero1, numero2.length());
            numero2 = preencherZeros(numero2, numero2.length());
        }else if(numero2.length() < numero1.length()){
            numero2 = preencherZeros(numero2, numero1.length());
            numero1 = preencherZeros(numero1, numero1.length());
        }
        //Se os tamanhos forem iguais, apenas convertemos 
        if(tamanho1 == tamanho2){
            numero1 = converterBitSinal(numero1);
            numero2 = converterBitSinal(numero2);
        //Se os tamanhos forem diferentes, precisamos enviar o sinal junto com o número
        }else{
            numero1 = converterBitSinal(sinal1 + numero1);
            numero2 = converterBitSinal(sinal2 + numero2);
        }
        // Contador que controla o algoritmo de Booth
        int contador = numero1.length();
        // Variáveis utilizadas no Algoritmo de Booth
        String a = "";
        String q = "";
        String q2 = "";
        String m = "";
        String resultadoMultiplicacao = "";
        String sinala = "";
        String sinalm = "";
        //Repete a operação até o contador for igual a 0
        while(contador >= 0){
            //a primeira passagem pelo For apenas inicializa os valores
            if(contador == numero1.length()){
                //a variável a começa com todos os bits valendo 0
                for(int contador2 = 0; contador2 < numero1.length(); contador2++){
                    a += "0";
                }
                // a variável q começa com com o primeiro valor em binário
                q = numero1;
                // a variável q2 começa com 0
                q2 = "0";
                // a variável m começa com o segundo valor em binário
                m = numero2;
            }
            //quando o contador for igual a 0, encerramos o laço e temos o valor da multiplicação 
            if(contador == 0){
                resultadoMultiplicacao = a + q;
                break;
            }
            //Se o último bit de q for igual a 1 e o último bit de q2 for igual a 0
            if((q.charAt(q.length() - 1) == '1' && q2.charAt(q2.length() - 1) == '1') || (q.charAt(q.length() - 1) == '0' && q2.charAt(q2.length() - 1) == '0')){
                //Depois, o primeiro bit de a se duplica, e o último bit de a é transportado
                String ultimoBita = "" + a.charAt(a.length() - 1);
                a = a.charAt(0) + a.substring(0, a.length() - 1);
                //o último bit de a é transportado para q e armazenamos seu último bit
                String ultimoBitq = "" + q.charAt(q.length() - 1);
                q = ultimoBita + q.substring(0, q.length() - 1);
                //o último bit de q é transportado para q2
                q2 = ultimoBitq;
            //Se o último bit de q for igual a 1 e o último bit de q2 for igual a 0
            }else if(q.charAt(q.length() - 1) == '1' && q2.charAt(q2.length() - 1) == '0'){
                //recebe o sinal de a
                if(a.charAt(0) == '0'){
                    sinala = "+";
                }else if(a.charAt(0) == '1'){
                    sinala = "-";
                }
                //recebe o sinal de m
                if(m.charAt(0) == '0'){
                    sinalm = "+";
                }else if(m.charAt(0) == '1'){
                    sinalm = "-";
                }
                //Nesse caso, efetuamos a subtração de A e M e o resultado é recebido por A
                a = subtracaoInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), m.length() + 1);
                a = a.substring(a.length() - m.length(), a.length());
                //Depois, o primeiro bit de a se duplica, e o último bit de a é transportado
                String ultimoBita = "" + a.charAt(a.length() - 1);
                a = a.charAt(0) + a.substring(0, a.length() - 1);
                //o último bit de a é transportado para q e armazenamos seu último bit
                String ultimoBitq = "" + q.charAt(q.length() - 1);
                q = ultimoBita + q.substring(0, q.length() - 1);
                //o último bit de q é transportado para q2
                q2 = ultimoBitq;
            //Se o último bit de q for igual a 0 e o último bit de q2 for igual a 1
            }else if(q.charAt(q.length() - 1) == '0' && q2.charAt(q2.length() - 1) == '1'){
                //recebe o sinal de a
                if(a.charAt(0) == '0'){
                    sinala = "+";
                }else if(a.charAt(0) == '1'){
                    sinala = "-";
                }
                //recebe o sinal de m
                if(m.charAt(0) == '0'){
                    sinalm = "+";
                }else if(m.charAt(0) == '1'){
                    sinalm = "-";
                }
                //Nesse caso, efetuamos a soma de A e M e o resultado é recebido por A
                a = somaInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), m.length() + 1);
                a = a.substring(a.length() - m.length(), a.length());
                //Depois, o primeiro bit de a se duplica, e o último bit de a é transportado
                String ultimoBita = "" + a.charAt(a.length() - 1);
                a = a.charAt(0) + a.substring(0, a.length() - 1);
                //o último bit de a é transportado para q e armazenamos seu último bit
                String ultimoBitq = ""+ q.charAt(q.length() - 1);
                q = ultimoBita + q.substring(0, q.length() - 1);
                //o último bit de q é transportado para q2
                q2 = ultimoBitq;
            }
            //Depois desses passos, decrementamos o nosso contador
            contador--;
        }
        
        return resultadoMultiplicacao;
    }

    //Método que realiza a subtração de dois dados números binários inteiros
    public static String subtracaoInteiros(String numero1, String numero2, int quantidadeBits) {
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
        }else if(numero1NaoTratado.charAt(0) == '+' && numero2NaoTratado.charAt(0) == '+' && verificaMaior(numero2NaoTratado, numero1NaoTratado)){
            resultadoSubtracao = "1" + resultadoSubtracao;
        }

        //Retorna o resultado da soma
		return resultadoSubtracao;
    }

    //Método que realiza a soma de dois dados números binários inteiros
    public static String somaInteiros(String numero1, String numero2, int quantidadeBits) {
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
        // Se o primeiro número for negativo e menor que o segundo, é realizada a subtração
        }else if(numero1NaoTratado.charAt(0) == '-' && numero2NaoTratado.charAt(0) == '+' && verificaMaior(numero2NaoTratado, numero1NaoTratado)){
            resultadoSoma = "0" + subtracaoInteiros(numero2, numero1, quantidadeBits);
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

    //Método que verifica se um número binário flutuante é maior que outro
    public static boolean verificaMaiorFlutuante(String inteiro1, String inteiro2, String decimal1, String decimal2){
        // Variáveis Auxiliares, que armazenam os expoentes e os valores 
        int somadorInteiro1 = 0;
        int somadorInteiro2 = 0;
        int expoenteInteiro1 = 0;
        int expoenteInteiro2 = 0;
        int somadorDecimal1 = 0;
        int somadorDecimal2 = 0;
        int expoenteDecimal1 = -1;
        int expoenteDecimal2 = -1;
        //For que calcula em decimal o primeiro número binário
        for(int contador = (inteiro1.length() - 1); contador > 0; contador--){
            if(inteiro1.charAt(contador) == '1'){
                somadorInteiro1 += Math.pow(2, expoenteInteiro1);
            }
            expoenteInteiro1++;
        }
        //For que calcula em decimal o segundo número binário
        for(int contador = (inteiro2.length() - 1); contador > 0; contador--){
            if(inteiro2.charAt(contador) == '1'){
                somadorInteiro2 += Math.pow(2, expoenteInteiro2);
            }
            expoenteInteiro2++;
        }
        //For que calcula em decimal o primeiro número binário flutuante
        for(int contador = 0; contador < decimal1.length(); contador++){
            if(decimal1.charAt(contador) == '1'){
                somadorDecimal1 += Math.pow(2, expoenteDecimal1);
            }
            expoenteDecimal1--;
        }
        //For que calcula em decimal o segundo número binário flutuante
        for(int contador = 0; contador < decimal2.length(); contador++){
            if(decimal2.charAt(contador) == '1'){
                somadorDecimal2 += Math.pow(2, expoenteDecimal2);
            }
            expoenteDecimal2--;
        }
        //Variáveis que armazenarão o total
        double total1 = somadorInteiro1 + somadorDecimal1;
        double total2 = somadorInteiro2 + somadorDecimal2;
        // Retorna true se o número 1 for maior que o número 2 e false caso não seja
        return total1 > total2;
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

    //Método que converte para número binário com a mantissa e expoente
    public static String converteBinarioFlutuante(String mantissa, String expoente){
        // Variáveis Auxiliares, que armazenam os expoentes e os valores 
        int somadorNumero = 0;
        int expoenteNumero = 0;
        //For que calcula em decimal o número binário
        for(int contador = (expoente.length() - 1); contador >= 0; contador--){
            if(expoente.charAt(contador) == '1'){
                somadorNumero += Math.pow(2, expoenteNumero);
            }
            expoenteNumero++;
        }
        // Se o expoente for positivo
        if(expoente.charAt(0) == '+'){
            //String que representa os zeros a esquerda
            String zeros = "";
            //String que representa o sinal do número
            String sinal = "" + mantissa.charAt(0);
            //Remove o ponto original da mantissa
            String mantissaTratada = mantissa.replace(".", "");
            int tamanhoMantissa = mantissaTratada.length();
            //Preenche com 0 até completar 31 bits (ainda não considera o bit de sinal)
            for(int contador = tamanhoMantissa; contador < 33; contador++){
                    zeros += "0";
            }
            //String com as alterações feitas
            mantissaTratada = sinal + mantissaTratada.substring(1, mantissaTratada.length()) + zeros;
            //String com ponto
            String mantissaFinalizada = "";
            //Coloca o ponto no local certo
            for(int contador = 0; contador < mantissaTratada.length(); contador++){
                mantissaFinalizada += mantissaTratada.charAt(contador);
                if(contador == (somadorNumero + 1)){
                    mantissaFinalizada += ".";
                } 
            }
            return mantissaFinalizada;

        // Se o expoente for negativo
        }else if(expoente.charAt(0) == '-'){
            //String que representa os zeros a esquerda
            String zeros = "";
            //String que representa o sinal do número
            String sinal = "" + mantissa.charAt(0);
            //For que adiciona a quantidade de zeros a esquerda proporcional ao expoente
            for(int contador = 0; contador < somadorNumero; contador++){
                zeros += "0";
            }
            //Remove o ponto original da mantissa
            mantissa = mantissa.replace(".", "");
            //Retorna a String com as alterações feitas
            String mantissaTratada = sinal + zeros + "." + mantissa.substring(1, mantissa.length());
            //Preenche com 0 até completar 31 bits (ainda não considera o bit de sinal)
            for(int contador = mantissaTratada.length(); contador < 33; contador++){
                mantissaTratada += "0";
            }
            return mantissaTratada;
        }
        // Possível erro de digitação
        return "Digite + ou -";
    }

    //Método que converte o sinal em bit de sinal
    public static String converterBitSinal(String numero){
        //Se o sinal é +, o bit de sinal é 0
        if(numero.charAt(0) == '+'){
            return numero.replace("+", "0");
        //Se o sinal é -, o bit de sinal é 1
        }else if(numero.charAt(0) == '-'){
            return numero.replace("-", "1");
        }
        //Possível Erro de Digitação
        return "Digite + ou -";
    }
}