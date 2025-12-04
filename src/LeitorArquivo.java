import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LeitorArquivo {

    public static List<String> lerNomes(String caminho) {
        List<String> nomes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(caminho))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (!linha.isEmpty()) {
                    nomes.add(linha);
                }
            }
        } catch (Exception e) {
            System.out.println("Aviso: Arquivo 'nomes.txt' não encontrado. Utilizando lista padrão de 20 nomes...");
    
            String[] padrao = { // Lista padrão de nomes caso o arquivo não seja encontrado.
                "Ana", "Bruno", "Carlos", "Daniela", "Eduardo",
                "Fernanda", "Gabriel", "Helena", "Igor", "Julia",
                "Kleber", "Laura", "Marcos", "Natália", "Otávio",
                "Paula", "Rafael", "Sabrina", "Thiago", "Vanessa"
            };
            nomes.addAll(Arrays.asList(padrao));
        }
        return nomes;
    }
}
