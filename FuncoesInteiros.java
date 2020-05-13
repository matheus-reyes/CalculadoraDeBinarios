
public class FuncoesInteiros {
    
    //Método que realiza a divisão de dois números binários inteiros
    public static String divisaoInteiros(String numero1, String numero2, int quantidadeBits){
        //Guarda os sinais dos números
        String sinal1 = "" + numero1.charAt(0);
        String sinal2 = "" + numero2.charAt(0);
        //Tamanhos dos dois números
        int tamanho1 = numero1.length();
        int tamanho2 = numero2.length();
        //Variáveis que representarão o divisor e o dividendo
        String divisor = "";
        String dividendo = "";
        //Variável que identifica se está sendo realizada divisão por 0
        boolean divisaopor0 = true;
        //For que percorre o divisor e verifica se há pelo menos um bit ligado para prosseguir com a divisão
        for(int contador = 0; contador < numero2.length(); contador++){
            if(numero2.charAt(contador) == '1'){
                divisaopor0 = false;
            }
        }
        //Se não for identificado nenhum bit ligado, é uma divisão por 0 que deve ser negada
        if(divisaopor0 == true){
            return "divisao por 0 detectada";
        }

        //A quantidade de Bits na divisão é a mesma que a do maior valor informado
        //Caso a quantidade de bits informada for menor que a do número com maior bit, o overflow é informado
        
        //Caso o segundo número for maior que o segundo, usamos ele como comparação
        if(tamanho1 < tamanho2){
            if(tamanho2 > quantidadeBits){
                return "Overflow";
            }
        
        //Caso o primeiro número for maior que o segundo, usamos ele como comparação
        }else if(tamanho2 < tamanho1){
            if(tamanho1 > quantidadeBits){
                return "Overflow";
            }
        
        //Caso os dois números forem iguais, podemos usar qualquer um dos números como comparação
        }else{
            if(tamanho1 > quantidadeBits){
                return "Overflow";
            }
        }

        //Precisamos deixar os dois números padronizados, com o mesmo número de bits, usamos a quantidade informada pelo usuário para isso
        numero1 = sinal1 + FuncoesAuxiliares.preencherZeros(numero1, quantidadeBits);
        numero2 = sinal2 + FuncoesAuxiliares.preencherZeros(numero2, quantidadeBits);

        //O Algoritmo parte de que os números são positivos, trataremos casos negativos mais a frente
        //Logo, não importando o sinal, substituimos o sinal pelo bit de sinal 0
        if(sinal1.equals("+")){
            dividendo = numero1.replace('+', '0');
        }else if(sinal1.equals("-")){
            dividendo = numero1.replace('-', '0');
        }

        //O Algoritmo parte de que os números são positivos, trataremos casos negativos mais a frente
        //Logo, não importando o sinal, substituimos o sinal pelo bit de sinal 0
        if(sinal2.equals("+")){
            divisor = numero2.replace('+', '0');
        }else if(sinal2.equals("-")){
            divisor = numero2.replace('-', '0');
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

        while(contador < quantidadeBits){

            //No primeiro loop, inicializamos as variáveis
            if(contador == 0){ 

                //a variável a começa com todos os bits valendo 0
                for(int contador2 = 0; contador2 < quantidadeBits; contador2++){
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

            //Se o retorno da soma for igual a Overflow, então o resultado da divisão também é overflow, precisamos de mais bits para a divisão
            if(a.equals("Overflow!")){
                return "Overflow!";
            }

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

        //Com o resultado positivo, iremos analisar os casos negativos, caso os números forem negativos
        //Segundo Stallings, temos a equação D = Q * V + R, onde D é o dividendo, Q o quociente, V o divisor e R o resto
        //Para podermos aplicar os números nas equações, precisamos converte-los para decimal
        int qinteiro = FuncoesAuxiliares.converteDecimal(q);
        int rinteiro = FuncoesAuxiliares.converteDecimal(a);
        int dinteiro = FuncoesAuxiliares.converteDecimal(dividendo);
        int vinteiro = FuncoesAuxiliares.converteDecimal(divisor);

        //Isolando a equação para termos o resto, temos que R = D - (Q * V), logo:
        rinteiro = dinteiro - (qinteiro * vinteiro);

        //Isolando a equação para termos o quociente, temos que Q = (D - R) / V, logo:
        qinteiro = (dinteiro - rinteiro) / vinteiro;

        //Caso o dividendo seja positivo e o divisor negativo, teremos que o quociente será negativo
        if(sinal1.equals("+") && sinal2.equals("-")){
            qinteiro = -1 * qinteiro;
        
        //Caso o dividendo seja negativo e o divisor positivo, teremos que o quociente e o resto serão negativos
        }else if(sinal1.equals("-") && sinal2.equals("+")){
            qinteiro = -1 * qinteiro;
            rinteiro = -1 * rinteiro;
        
        //Caso o dividendo seja negativo e o divisor negativo, teremos que o resto será negativo
        }else if(sinal1.equals("-") && sinal2.equals("-")){
            rinteiro = -1 * rinteiro;
        }

        //Temos o resto e o quociente em números decimais, então precisamos converte-los para binário, passando o valor absoluto (positivo)
        String resto = FuncoesAuxiliares.converteBinario(Math.abs(rinteiro), a.length());
        String resultadoDivisao = FuncoesAuxiliares.converteBinario(Math.abs(qinteiro), q.length());

        //Se o resto for negativo, precisamos tirar seu complemento de 2
        if(rinteiro < 0){
            //Como a função de complemento de 2 espera um sinal, precisamos passá-lo junto com o número em si
            resto = FuncoesAuxiliares.complementoDe2("-" + resto, resto.length() + 1);
        }

        //Se o quociente for negativo, precisamos tirar seu complemento de 2
        if(qinteiro < 0){
            //Como a função de complemento de 2 espera um sinal, precisamos passá-lo junto com o número em si
            resultadoDivisao = FuncoesAuxiliares.complementoDe2("-" + resultadoDivisao, resultadoDivisao.length() + 1);
        }

        //Exibe o resto da divisão
        System.out.println("Resto: " + resto);

        //Retorna o resultado da divisão
        return resultadoDivisao;
    }
    
    //Método que realiza a multiplicação com algoritmo de booth de dois números binários inteiros
    public static String multiplicacaoInteiros(String numero1, String numero2, int quantidadeBits){
        //Tamanho dos números antes de serem alterados
        int tamanho1 = numero1.length(); 
        int tamanho2 = numero2.length();
        //Guarda os sinais dos números
        String sinal1 = "" + numero1.charAt(0);
        String sinal2 = "" + numero2.charAt(0);

        //A quantidade de Bits retornada no algoritmo de booth é o dobro da quantidade de bits do maior número
        //Caso a quantidade de bits informada for menor que o dobro do maior número, retornamos Overflow
        
        //Caso o segundo número for maior que o segundo, usamos ele como comparação
        if(tamanho1 < tamanho2){
            if((tamanho2 * 2) > quantidadeBits){
                return "Overflow";
            }
        
        //Caso o primeiro número for maior que o segundo, usamos ele como comparação
        }else if(tamanho2 < tamanho1){
            if((tamanho1 * 2) > quantidadeBits){
                return "Overflow";
            }
        
        //Caso os dois números forem iguais, podemos usar qualquer um dos números como comparação
        }else{
            if((tamanho1 * 2) > quantidadeBits){
                return "Overflow";
            }
        }

        //O Algoritmo de Booth pressupõe que os dois valores multiplicados possuem o mesmo número de bits
        //Então, padronizamos os dois números de bits com a metade do valor informado
        numero1 = FuncoesAuxiliares.preencherZeros(numero1, (quantidadeBits / 2));
        numero2 = FuncoesAuxiliares.preencherZeros(numero2, (quantidadeBits / 2));

        //Precisamos converter os sinais dos dois números para bits de sinal, nesse momento consideramos 
        //Que os números são positivos, depois tiramos complemento de 2 caso necessário
        numero1 = FuncoesAuxiliares.converterBitSinal("+" + numero1);
        numero2 = FuncoesAuxiliares.converterBitSinal("+" + numero2);

        //Se um dos números for negativo, precisamos tirar o complemento de 2 deles
        if(sinal1.equals("-")){
            numero1 = FuncoesAuxiliares.complementoDe2(sinal1 + numero1, numero1.length() + 1);
        }

        if(sinal2.equals("-")){
            numero2 = FuncoesAuxiliares.complementoDe2(sinal2 + numero2, numero2.length() + 1);
        }
        
        // Contador que controla o algoritmo de Booth
        int contador = quantidadeBits / 2;
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
            if(contador == quantidadeBits / 2){
                //a variável a começa com todos os bits valendo 0
                for(int contador2 = 0; contador2 < quantidadeBits / 2; contador2++){
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
                a = subtracaoInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), (quantidadeBits / 2));

                //Se o retorno da subtração for igual a Overflow, então o resultado da multiplicação também é overflow, precisamos de mais bits para a multiplicação
                if(a.equals("Overflow!")){
                    return "Overflow!";
                }
                
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
                a = somaInteiros(sinala + a.substring(1, a.length()), sinalm + m.substring(1, m.length()), (quantidadeBits / 2), "multiplicacao");

                //Se o retorno da soma for igual a Overflow, então o resultado da multiplicação também é overflow, precisamos de mais bits para a multiplicação
                if(a.equals("Overflow!")){
                    return "Overflow!";
                }

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