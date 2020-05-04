public class FuncoesAuxiliares {
    
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