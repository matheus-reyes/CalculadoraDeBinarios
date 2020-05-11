public class FuncoesFlutuantes {
    
    //Método que realiza a multiplicação de dois números binários flutuantes
    public static String multiplicacaoFlutuantes(String mantissa1, String mantissa2, String expoente1, String expoente2){

        //Converte os dois números movendo as vírgulas de acordo com o expoente
        String numero1Convertido = FuncoesAuxiliares.converteBinarioFlutuante(mantissa1, expoente1); 
        String numero2Convertido = FuncoesAuxiliares.converteBinarioFlutuante(mantissa2, expoente2); 
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

        //Capturam a posição do último 1 a direita para eliminar os zeros restantes
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
            decimalNumero2 = decimalNumero2 + zeros;

        }else if(decimalNumero2.length() > decimalNumero1.length()){
            String zeros = "";
            for(int contador = decimalNumero1.length(); contador < decimalNumero2.length(); contador++){
                zeros += "0";
            }
            decimalNumero1 = decimalNumero1 + zeros;
        }

        //Variáveis que armazenarão o número completo, a parte inteira junto com a parte decimal
        String numero1Completo = inteiroNumero1 + decimalNumero1;
        String numero2Completo = inteiroNumero2 + decimalNumero2;

        //A multiplicação com flutuantes pode ser feita da mesma forma que a de inteiros, mas precisamos ter cuidado com os decimais

        //Armazena o resultado da multiplicação dos números, como se fossem números inteiros
        String multiplicacao = FuncoesInteiros.multiplicacaoInteiros(sinal1 + numero1Completo, sinal2 + numero2Completo, (numero1Completo.length() + numero2Completo.length() + 2));
        
        //Se o expoente for maior que 128, retorna Overflow
        if(((multiplicacao.length() - decimalNumero1.length()) - 2) > 127){
            return "Overflow!";
        }

        //Como no caso de números pequenos o expoente é 0, usamos o tamanho do resultado para comparação
        //caso o tamanho for maior que 127, retorna Underflow!
        if(multiplicacao.length() > 127){
            return "Underflow!";
        }
        
        //Retorna o resultado da multiplicação formatado, colocando o expoente da maneira correta
        return multiplicacao.substring(0,2) + "." + multiplicacao.substring(2, multiplicacao.length()) + " * 2 ^ " + ((multiplicacao.length() - (decimalNumero1.length() * 2)) - 2);
    }
    
    //Método que realiza a subtração de dois números binários flutuantes
    public static String subtracaoFlutuantes(String mantissa1, String mantissa2, String expoente1, String expoente2){
        //Uma subtração é uma soma com complemento de 2, então apenas alteramos os sinais conforme a necessidade e realizamos a soma

        //No caso de apenas o segundo número for negativo, ele torna-se positivo, realizando uma soma comum
        if(mantissa1.charAt(0) == '+' && mantissa2.charAt(0) == '-'){
            mantissa2 = mantissa2.replace('-', '+');
            return somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2);

        //No caso de apenas o segundo número for positivo, ele torna-se negativo, virando uma soma de negativos
        }else if(mantissa1.charAt(0) == '-' && mantissa2.charAt(0) == '+'){
            mantissa2 = mantissa2.replace('+', '-');
            return somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2);
        
        // No caso dos dois serem positivos, é trocada o sinal do segundo número, realizando uma subtração comum
        }else if(mantissa1.charAt(0) == '+' && mantissa2.charAt(0) == '+'){
            mantissa2 = mantissa2.replace('+', '-');
            return somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2);
        
        // No caso dos dois serem negativos, o segundo número torna-se positivo
        }else if(mantissa1.charAt(0) == '-' && mantissa2.charAt(0) == '-'){
            mantissa2 = mantissa2.replace('-', '+');
            return somaFlutuantes(mantissa1, mantissa2, expoente1, expoente2);
        }

        //Se nenhuma opção anterior for acionada, o usuário não digitou um sinal correto
        return "Possivel erro de digitacao";
    }
    
    //Método que realiza a soma de dois números binários flutuantes
    public static String somaFlutuantes(String mantissa1, String mantissa2, String expoente1, String expoente2){
        //Converte os dois números movendo as vírgulas de acordo com o expoente
        String numero1Convertido = FuncoesAuxiliares.converteBinarioFlutuante(mantissa1, expoente1); 
        String numero2Convertido = FuncoesAuxiliares.converteBinarioFlutuante(mantissa2, expoente2); 
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

        //Capturam a posição do último 1 a direita para eliminar os zeros restantes
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
            numeroCompleto1 = "1" + FuncoesAuxiliares.complementoDe2(sinal1 + inteiroNumero1 + decimalNumero1, inteiroNumero1.length() + decimalNumero1.length() + 1);
            numeroCompleto2 = "0" + inteiroNumero2 + decimalNumero2;
        }
        //Se o segundo número for negativo, usamos o complemento de 2 dele para representa-lo na soma
        if(sinal2.equals("-") && sinal1.equals("+")){
            numeroCompleto2 = "1" + FuncoesAuxiliares.complementoDe2(sinal2 + inteiroNumero2 + decimalNumero2, inteiroNumero2.length() + decimalNumero2.length() + 1);
            numeroCompleto1 = "0" + inteiroNumero1 + decimalNumero1;
        }
        //Se os dois números forem negativos, devemos fazer a soma deles em complemento de 2
        if(sinal1.equals("-") && sinal2.equals("-")){
            numeroCompleto1 = "1" + FuncoesAuxiliares.complementoDe2(sinal1 + inteiroNumero1 + decimalNumero1, inteiroNumero1.length() + decimalNumero1.length() + 1);
            numeroCompleto2 = "1" + FuncoesAuxiliares.complementoDe2(sinal2 + inteiroNumero2 + decimalNumero2, inteiroNumero2.length() + decimalNumero2.length() + 1);
        }
        //Se os dois números forem positivos, apenas juntamos os dois
        if(sinal1.equals("+") && sinal2.equals("+")){
            numeroCompleto1 = "0" + inteiroNumero1 + decimalNumero1;
            numeroCompleto2 = "0" + inteiroNumero2 + decimalNumero2;
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
        }

        // No caso de Overflow, precisamos alocar um bit a mais na soma

        //Caso os dois forem positivos, o bit de sinal é 0
        if(numeroCompleto1.charAt(0) == '0' && numeroCompleto2.charAt(0) == '0' && resultadoSomaInvertido.charAt(resultadoSomaInvertido.length() - 1) == '1'){
            resultadoSomaInvertido += "0";
                    
        //Caso ambos os sinais sejam negativos, o bit de sinal será 1
        }else if(numeroCompleto1.charAt(0) == '1' && numeroCompleto2.charAt(0) == '1' && resultadoSomaInvertido.charAt(resultadoSomaInvertido.length() - 1) == '0'){
            resultadoSomaInvertido += "1";
        }
     
        // String que armazenará o resultado da soma
        String resultadoSoma = "";
        //For que desinverte a String
        for(int contador = (resultadoSomaInvertido.length() - 1); contador >= 0; contador--){
            resultadoSoma += resultadoSomaInvertido.charAt(contador);
        }

        //Se o expoente for maior que 128, retorna Overflow
        if(((resultadoSoma.length() - decimalNumero1.length()) - 2) > 127){
            return "Overflow!";
        }

        //Como no caso de números pequenos o expoente é 0, usamos o tamanho do resultado para comparação
        //caso o tamanho for maior que 127, retorna Underflow!
        if(resultadoSoma.length() > 127){
            return "Underflow!";
        }

        //Retorna o resultado da soma, com bit de sinal e representação em ponto flutuante
        return resultadoSoma.substring(0, 2) + "." + resultadoSoma.substring(2, resultadoSoma.length()) + " * 2 ^ " + ((resultadoSoma.length() - decimalNumero1.length()) - 2);
    }

}