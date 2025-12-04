import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ControleTeleSena {
    private Pessoa[] pessoas = new Pessoa[20];
    private int totalTeleSenasVendidas = 0;
    private Random random = new Random();

    public void iniciarSistema() {
        System.out.println("=== SORTEIO AUTOMÁTICO TELE SENA ===\n");
        pausar(1500);

        realizarVendas();
        pausar(2000);

        realizarSorteio();
    }
    
    public void realizarVendas() {
        System.out.println("INICIANDO VENDA DAS TELE SENAS...\n");
        pausar(1000);

        List<String> listaNomes = LeitorArquivo.lerNomes("nomes.txt"); // Carrega nomes do arquivo nomes.txt
        Collections.shuffle(listaNomes, random); // Embaralha para não repetir nomes nunca.

        for (int i = 0; i < 20; i++) { // 20 pessoas comprando Tele Senas.
            String nomeAtual = listaNomes.get(i); // Pega o nome da lista embaralhada.
            Pessoa p = new Pessoa(nomeAtual); // Cria a pessoa.
            pessoas[i] = p; // Adiciona a pessoa ao array de pessoas.

            int qtd = random.nextInt(15) + 1; // Sorteia quantas Tele Senas a pessoa vai comprar (1 a 15).
            if (totalTeleSenasVendidas + qtd > 300) { //Verifica se vai ultrapassar o limite de 300 Tele Senas, se sim, reduz a quantidade.
                qtd = 300 - totalTeleSenasVendidas;
            }

            System.out.printf("%s está comprando %d Tele Sena(s)...\n", nomeAtual, qtd);
            pausar(800);

            for (int j = 0; j < qtd && totalTeleSenasVendidas < 300; j++) {
                TeleSena ts = new TeleSena(); // Cria uma nova Tele Sena.
                p.comprarTeleSena(ts); // A pessoa compra a Tele Sena.
                totalTeleSenasVendidas++; // Incrementa o total de Tele Senas vendidas.
                System.out.printf("  Tele Sena %d comprada por %s.\n", j + 1, nomeAtual);
            }
            pausar(600);
        }

        double totalArrecadado = totalTeleSenasVendidas * TeleSena.VALOR_VENDA;
        System.out.printf("\nVENDAS ENCERRADAS!\n");
        System.out.printf("Tele Senas vendidas: %d\n", totalTeleSenasVendidas);
        System.out.printf("Valor total arrecadado: R$ %.2f\n\n", totalArrecadado);
        pausar(2500);
    }

    private void realizarSorteio() {
        System.out.println("INICIANDO O SORTEIO...\n");
        pausar(2000);

        List<Integer> ordemSorteio = new ArrayList<>(); // Lista para armazenar a ordem dos números sorteados.
        Set<Integer> numerosSorteados = new HashSet<>(); // Conjunto para evitar números repetidos.
        List<Pessoa> ganhadores = new ArrayList<>(); 

        int contador = 0;
        boolean temGanhador = false;

        // Sorteia números até que haja pelo menos um ganhador.
        while (!temGanhador) {

            int numero;
            while (true) {
                numero = random.nextInt(60) + 1; // Gera número entre 1 e 60.
                if (!numerosSorteados.contains(numero)) {
                    break; // Sai do loop se o número não foi sorteado antes.
                }
            }

            numerosSorteados.add(numero); // Adiciona o número ao conjunto de números sorteados.
            ordemSorteio.add(numero); // Adiciona o número à lista de ordem de sorteio.
            contador++;

            System.out.printf("%dº número sorteado: %02d\n", contador, numero);
            pausar(1500);

            if (contador >= 25) { // Após os 25 primeiros números, verifica se há ganhadores.
                for (Pessoa p : pessoas) { // Verifica cada pessoa.
                    if (p.temGanhador(numerosSorteados) && !ganhadores.contains(p)) { // Verifica se a pessoa é ganhadora e não está na lista ainda.
                        ganhadores.add(p); // Adiciona a pessoa à lista de ganhadores.
                    }
                }
                if (!ganhadores.isEmpty()) { // Se houver ganhadores, encerra o sorteio.
                    temGanhador = true;
                }
            }
        }
        exibirResultadoFinal(ordemSorteio, ganhadores);
    }

    private void exibirResultadoFinal(List<Integer> sorteados, List<Pessoa> ganhadores) {
        double totalArrecadado = totalTeleSenasVendidas * TeleSena.VALOR_VENDA;
        double premioTotal = totalArrecadado * 0.80;
        double premioIndividual = premioTotal / ganhadores.size();
        double lucroCasa = totalArrecadado * 0.20;

        for (Pessoa g : ganhadores) {
            g.creditarPremio(premioIndividual);
        }

        System.out.println("=================================================");
        System.out.println("\n           PARABÉNS AOS GANHADORES!\n");

        System.out.print("Números sorteados (" + sorteados.size() + "): ");

        for (int i = 0; i < sorteados.size(); i++) {
            System.out.printf("%02d", sorteados.get(i));
            if (i < sorteados.size() - 1) { // Não adiciona vírgula após o último número.
                System.out.print(", ");
            }
            if ((i + 1) % 5 == 0){
                System.out.println(); // Quebra de linha a cada 5 números.
            }
        }

        System.out.println("\n");

        System.out.println("Tele Senas vendidas: " + totalTeleSenasVendidas);
        System.out.println("Total arrecadado: R$ " + String.format("%.2f", totalArrecadado));
        System.out.println("Quantidade de ganhadores: " + ganhadores.size());

        System.out.println("\nGANHADORES:");
        for (Pessoa g : ganhadores) {
            System.out.println("-> " + g.getNome() + " - Prêmio: R$ " + String.format("%.2f", premioIndividual));
        }

        System.out.println("\nPrêmio total distribuído (80%): R$ " + String.format("%.2f", premioTotal));
        System.out.println("Lucro da casa (20%): R$ " + String.format("%.2f", lucroCasa));

        System.out.println("=================================================");
        System.out.println("           SORTEIO FINALIZADO!");
    }

    private void pausar(long millis) { 
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
