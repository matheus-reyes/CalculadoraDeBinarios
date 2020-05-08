
public class FuncoesInteiros {
    
    //Método que realiza a divisão de dois números binários inteiros
    public static String divisaoInteiros(String numero1, String numero2, int quantidadeBits){
        //Guarda os sinais dos números
        String sinal1 = "" + numero1.charAt(0);
        String sinal2 = "" + numero2.charAt(0);
        //Variáveis que representarão o divisor e o dividendo
        String divisor = "";
        String dividendo = "";

        //Substitui o sinal do dividendo pelo bit de sinal de acordo com cada situação
        if(numero1.charAt(0) == '+'){
            dividendo = numero1.replace('+', '0');
        }else if(numero1.charAt(0) == '-'){
            dividendo = numero1.replace('-', '1');
        }

        //Substitui o sinal do divisor pelo bit de sinal de acordo com cada situação
        if(numero2.charAt(0) == '+'){
            divisor = numero2.replace('+', '0');
        }else if(numero2.charAt(0) == '-'){
            divisor = numero2.replace('-', '1');
        }
        
        //Contador que fará o controle do número de loops na divisão
        int contador = 0;
        //Variáveis que serão utilizadas na divisão, a representará o resto e q o resultado da divisão
        String a = "";
        String q = "";
        String m = "";

        //Variáveis que receberão os sinais de a e m
        String sinala = "";
        String sinalm = "";

        while(contador < numero1.length()){

            //No primeiro loop, inicializamos as variáveis
            if(contador == 0){ 

                //a variável a começa com todos os bits valendo 0
                for(int contador2 = 0; contador2 < numero1.length(); contador2++){
                    a += "0";
                }
                //a variável q recebe o dividendo
                q = dividendo;
                //a variável m recebe o complemento de 2 do divisor, como a função de complemento de 2 espera o sinal, devemos passa-lo também
                m = FuncoesAuxiliares.complementoDe2(sinal2 + divisor, divisor.length() + 1);
            }

            //Armazena o primeiro bit de q, ele será transportado para a
            String primeiroBitq = "" + q.charAt(0);
            //Elimina o primeiro bit de q e coloca o bit 0 no final
            q = q.substring(1, q.length()) + "0";
            //Elimina o primeiro bit de a e coloca o primeiro bit de q no final
            a = a.substring(1, a.length()) + primeiroBitq;
            //Armazena o valor de a depois do deslocamento no caso de restauração
            String restauracaoa = a;

            //Como utilizaremos a função de soma e ela recebe o sinal como primeiro caracter, precisamos fazer a conversão para a 
            if(a.charAt(0) == '1'){
                sinala = "-";
            }else if(a.charAt(0) == '0'){
                sinala = "+";
            }

            //Como utilizaremos a função de soma e ela recebe o sinal como primeiro caracter, precisamos fazer a conversão para m
            if(m.charAt(0) == '1'){
                sinalm = "-";
            }else if(m.charAt(0) == '0'){
                sinalm = "+";
            }

            //realizamos a soma de a e m
            a = somaInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), a.length(), "divisao");

            //Se o primeiro bit de a for 1
            if(a.charAt(0) == '1'){
                //Restauramos o valor de a
                a = restauracaoa;
                //q recebe na sua última posição o bit 0
                q = q.substring(0, q.length() - 1) + "0";

            //Se o primeiro bit de a for 0
            }else if(a.charAt(0) == '0'){
                //q recebe na sua última posição o bit 1
                q = q.substring(0, q.length() - 1) + "1";
            }

            contador++;
        }

        //a é o resto e q o resultado da soma
        System.out.println("Resto: "+a);
        return q;

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
            numero1 = FuncoesAuxiliares.preencherZeros(numero1, numero2.length());
            numero2 = FuncoesAuxiliares.preencherZeros(numero2, numero2.length());
        }else if(numero2.length() < numero1.length()){
            numero2 = FuncoesAuxiliares.preencherZeros(numero2, numero1.length());
            numero1 = FuncoesAuxiliares.preencherZeros(numero1, numero1.length());
        }
        //Se os tamanhos forem iguais, apenas convertemos 
        if(tamanho1 == tamanho2){
            numero1 = FuncoesAuxiliares.converterBitSinal(numero1);
            numero2 = FuncoesAuxiliares.converterBitSinal(numero2);
        //Se os tamanhos forem diferentes, precisamos enviar o sinal junto com o número
        }else{
            numero1 = FuncoesAuxiliares.converterBitSinal(sinal1 + numero1);
            numero2 = FuncoesAuxiliares.converterBitSinal(sinal2 + numero2);
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

            //Se o último bit de q for igual ao último bit de q2, então apenas ocorre o deslocamento
            if((q.charAt(q.length() - 1) == '1' && q2.charAt(q2.length() - 1) == '1') || (q.charAt(q.length() - 1) == '0' && q2.charAt(q2.length() - 1) == '0')){
                //armazena o último bit de a
                String ultimoBita = "" + a.charAt(a.length() - 1);
                //elimina o último bit de a
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
                a = subtracaoInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), m.length());
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
                a = somaInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), m.length(), "multiplicacao");
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
        //Uma subtração é uma soma com complemento de 2, então apenas alteramos os sinais conforme a necessidade e realizamos a soma
        
        //No caso de apenas o segundo número for negativo, ele torna-se positivo, realizando uma soma comum
        if(numero1.charAt(0) == '+' && numero2.charAt(0) == '-'){
            numero2 = numero2.replace('-', '+');
            return somaInteiros(numero1, numero2, quantidadeBits, "soma");

        //No caso de apenas o segundo número for positivo, ele torna-se negativo, virando uma soma de negativos
        }else if(numero1.charAt(0) == '-' && numero2.charAt(0) == '+'){
            numero2 = numero2.replace('+', '-');
            return somaInteiros(numero1, numero2, quantidadeBits, "soma");
        
        // No caso dos dois serem positivos, é trocada o sinal do segundo número, realizando uma subtração comum
        }else if(numero1.charAt(0) == '+' && numero2.charAt(0) == '+'){
            numero2 = numero2.replace('+', '-');
            return somaInteiros(numero1, numero2, quantidadeBits, "soma");
        
        // No caso dos dois serem negativos, o segundo número torna-se positivo
        }else if(numero1.charAt(0) == '-' && numero2.charAt(0) == '-'){
            numero2 = numero2.replace('-', '+');
            return somaInteiros(numero1, numero2, quantidadeBits, "soma");
        }

        //Se nenhuma opção anterior for acionada, o usuário não digitou um sinal correto
        return "Possivel erro de digitacao";
    }

    //Método que realiza a soma de dois dados números binários inteiros
    //O último parâmetro é de onde essa função foi chamada, porque no caso do algoritmo de booth a função terá outro
    //Comportamento, pois os números já vem com complemento de 2
    public static String somaInteiros(String numero1, String numero2, int quantidadeBits, String origem) {
        //String que armazenará o resultado da soma, como usamos concatenação o resultado ficará invertido
        String resultadoSomaInvertido = "";
        //Boolean que armazenará uma representação se houve ou não transporte
        boolean transporte = false;
        //Números ainda não preenchidos com 0
        String numero1NaoTratado = numero1;
        String numero2NaoTratado = numero2;
        //precisamos completar com zeros se o número for positivo, ou tirar complemento de 2 se o número for negativo
        //Se o primeiro número for negativo
        if(numero1NaoTratado.charAt(0) == '-' && origem.equals("soma")){
            numero1 = "1" + FuncoesAuxiliares.complementoDe2(numero1NaoTratado, quantidadeBits);
        //Se o primeiro número for positivo
        }else if(numero1NaoTratado.charAt(0) == '+' && origem.equals("soma")){
            numero1 = "0" + FuncoesAuxiliares.preencherZeros(numero1NaoTratado, quantidadeBits);
        }
        //Se o segundo número for negativo
        if(numero2NaoTratado.charAt(0) == '-' && origem.equals("soma")){
            numero2 = "1" + FuncoesAuxiliares.complementoDe2(numero2NaoTratado, quantidadeBits);
        //Se o segundo número for positivo
        }else if(numero2NaoTratado.charAt(0) == '+' && origem.equals("soma")){
            numero2 = "0" + FuncoesAuxiliares.preencherZeros(numero2NaoTratado, quantidadeBits);
        }

        //Se os valores vierem direto do algoritmo de booth, os valores já vão estar em complemento de 2
        //Então apenas mudamos o bit de sinal
        if((origem.equals("multiplicacao") || origem.equals("divisao")) && numero1NaoTratado.charAt(0) == '-'){
            numero1 = numero1NaoTratado.replace('-', '1');
        }
        if((origem.equals("multiplicacao") || origem.equals("divisao")) && numero1NaoTratado.charAt(0) == '+'){
            numero1 = numero1NaoTratado.replace('+', '0');
        }
        if((origem.equals("multiplicacao") || origem.equals("divisao")) && numero2NaoTratado.charAt(0) == '-'){
            numero2 = numero2NaoTratado.replace('-', '1');
        }
        if((origem.equals("multiplicacao") || origem.equals("divisao")) && numero2NaoTratado.charAt(0) == '+'){
            numero2 = numero2NaoTratado.replace('+', '0');
        }

		//For que percorre os dois números e realiza a soma
        for(int contador = (quantidadeBits - 1); contador >= 0; contador--){
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

        //Se a soma dos dois números da primeira posição, for diferente do primeiro número do resultado, é overflow!
        if(numero1.charAt(0) == numero2.charAt(0) && resultadoSomaInvertido.charAt(resultadoSomaInvertido.length() - 1) != numero1.charAt(0)){
            return "Overflow!";
        }

        // String que armazenará o resultado da soma
        String resultadoSoma = "";
        //For que desinverte a String
        for(int contador = (resultadoSomaInvertido.length() - 1); contador >= 0; contador--){
            resultadoSoma += resultadoSomaInvertido.charAt(contador);
        }

        //Retorna o resultado da soma
		return resultadoSoma;
    }
}