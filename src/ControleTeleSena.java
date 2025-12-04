import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ControleTeleSena {
    private Pessoa[] pessoas = new Pessoa[20];
    private int totalTeleSenasVendidas = 0;
    private Random random = new Random();

    public void iniciarSistema() {
        System.out.println("=== SORTEIO AUTOMÁTICO TELE SENA ===\n");
        pausar(1500);

        realizarVendas();
        pausar(2000);
    }
    
    public void realizarVendas() {
        System.out.println("INICIANDO VENDA DAS TELE SENAS...\n");
        pausar(1000);

        List<String> listaNomes = new ArrayList<>(Arrays.asList(
            "Ana", "Bruno", "Carla", "Daniel", "Eduardo", "Fernanda", "Gabriel", "Helena",
            "Igor", "Juliana", "Kleber", "Larissa", "Marcos", "Natália", "Otávio", "Patrícia",
            "Quintino", "Rafaela", "Sérgio", "Tatiana"
        ));
        Collections.shuffle(listaNomes); // Embaralha a lista de nomes.

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

    private void pausar(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
