import java.util.Scanner;

public class ConsumoKwh {
    public static final int MAX_CONSUMIDORES = 10;

    public static double precoKwh; 

    public static int[] codConsumidor = new int[10];
    public static int[] quantidadeConsumo = new int[10];
    public static String[] tipo = new String[10];
    public static int numConsumidores = 0;

    public static double[] consumoTotal = new double [MAX_CONSUMIDORES];
    public static int maiorConsumo;
    public static int menorConsumo;
    public static int totalResidencial = 0;
    public static int totalComercial = 0;
    public static int totalIndustrial = 0;
    public static double mediaGeral;


    // Main que chama as funções criadas
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        lerConsumidores(input);
        exibeConsumidores();
        exibirConsumo();

    }

    // Método que exibi o maior consumo, menor consumo, o consumo total por cada tipo e a média de Consumo
    public static void exibirConsumo(){
        //chamada dos métodos
        maiorConsumo();
        menorConsumo();
        totalConsumoTipo("residencial");
        totalConsumoTipo("comercial");
        totalConsumoTipo("industrial");
        calcMedia();

        // saída formatada
        System.out.printf("%15s %15s %15s\n", "Maior Consumo", "Menor Consumo", "Media Geral");
        System.out.println("-".repeat(65));

        System.out.printf("%10d %15d %15.2f\n", maiorConsumo, menorConsumo, mediaGeral);
        System.out.println("-".repeat(65));

        System.out.printf("Consumo por tipo: %15s %15s %15s\n", "Comercial", "Residencial", "Industrial");
        System.out.println("-".repeat(65));
        System.out.printf("Consumo:      %15d %15d %15d\n", totalComercial, totalResidencial, totalIndustrial);
    }

    // Método para ler os consumidores a partir da entrada de dados pelo usuário
    public static void lerConsumidores (Scanner teclado){
        int codigo;

        System.out.println("Informe o preço do KWH: ");
            precoKwh = Integer.parseInt(teclado.nextLine());

        System.out.println("Digite o codigo do consumidor (0 para sair):");
        codigo = Integer.parseInt(teclado.nextLine());

        // enquanto o usuário não digitar "0" para o código, o sistema vai continuar lendo as entradas
        while (codigo != 0) {
            codConsumidor[numConsumidores] = codigo;

            System.out.println("Digite a quantidade consumida em kwh:");
            quantidadeConsumo[numConsumidores] = Integer.parseInt(teclado.nextLine());

            consumoTotal[numConsumidores] = quantidadeConsumo[numConsumidores] * precoKwh;

            tipo[numConsumidores] = lerTipoConsumidor(teclado);

            numConsumidores++;

            System.out.println("Digite o codigo do consumidor (0 para sair):");
            codigo = Integer.parseInt(teclado.nextLine());

            //verifica se o mumero de consumidores inseridos não ultaprassou o limite definido 
            if(numConsumidores == MAX_CONSUMIDORES || codigo == 0){
                return;
            }
        }
    }

        // Método que exibe os dados principais dos consumidores
        public static void exibeConsumidores(){
            if (numConsumidores == 0){
                return;
            }
            System.out.printf("%10s %9s %15s %6s\n", "Cod.", "Consumo", "Tipo", "Total");
            System.out.println("-".repeat(45));

            for(int i = 0; i< numConsumidores; i++){
                System.out.printf("%10d %9d %15s %6.2f\n", codConsumidor[i], quantidadeConsumo[i], tipo[i], consumoTotal[i]);
            }
            System.out.println("-".repeat(45));
            System.out.println();
        }  

        // Método que checa a entrada do tipo do usuário e retorna o tipo que o usuário digitou
        public static String lerTipoConsumidor(Scanner input){
            String tipo;

            System.out.println("Digite o tipo do consumidor (Comercial, Residencial ou Industrial):");
            tipo = input.nextLine().toLowerCase();

            while(!tipo.equals("comercial") && !tipo.equals("residencial") && !tipo.equals("industrial")){
                System.out.println("Tipo inválido, insira novamente (Comercial, Residencial ou Industrial):");
                tipo = input.nextLine().toLowerCase();

                if(tipo.equals("comercial") || tipo.equals("residencial") || tipo.equals("industrial")){
                    break;
                }

            }
            
            return tipo;
        }
        
        // método para verificar qual foi o maior consumo
        public static void maiorConsumo(){
            maiorConsumo = 0;

            for (int i = 0; i < numConsumidores; i++){
                if (quantidadeConsumo[i] >  maiorConsumo)
                    maiorConsumo = quantidadeConsumo[i];
            }
        }

        // método para verificar qual foi o menor consumo
        public static void menorConsumo(){
            menorConsumo = 1000000000;

            for (int i = 0; i < numConsumidores; i++){
                if (quantidadeConsumo[i] <  menorConsumo)
                    menorConsumo = quantidadeConsumo[i];
            }
        }

        // método para verificar o consumo total
        public static void totalConsumoTipo(String tipoConsumidor){
            int valor = 0;

            String converter = tipoConsumidor.toLowerCase();

            // necessário para verificar qual o tipo armazenado e guardar na variavel valor

            for(int i=0; i < MAX_CONSUMIDORES; i++){
                if(converter.equalsIgnoreCase(tipo[i])){
                    valor += quantidadeConsumo[i];
                }
            }

        // necessário para verificar qual o tipo e atribuir a variavel valor
          switch (converter) {
            case "comercial":
                totalComercial = valor;
                break;
            case "residencial":
                totalResidencial = valor;     
            case "industrial":
                totalIndustrial = valor;
                break;
            default:
            System.out.println("Tipo inexistente");
                break;
          }
        }

        // método para calcular a media de consumo
        public static void calcMedia(){
            int total = 0;
            for (int i =0; i < MAX_CONSUMIDORES; i++){
                total += quantidadeConsumo[i];
            }
            mediaGeral = total / numConsumidores;

        }

    }