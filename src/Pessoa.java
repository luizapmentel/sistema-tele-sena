import java.util.Set;

public class Pessoa {

    private String nome;
    private TeleSena[] teleSenas;
    private int quantidadeTeleSenas;
    private double premiacaoRecebida;

    public Pessoa(String nome) {
        this.nome = nome;
        this.teleSenas = new TeleSena[15]; // Máximo de 15 TeleSenas por pessoa.
        this.quantidadeTeleSenas = 0; // Inicialmente, a pessoa não possui TeleSenas.
        this.premiacaoRecebida = 0.0; // Inicialmente, a premiação recebida é zero.
    }

    public String getNome() {
        return nome;
    }

    public TeleSena[] getTeleSenas() {
        return teleSenas;
    }

    public int getQuantidadeTeleSenas() {
        return quantidadeTeleSenas;
    }

    public double getPremiacaoRecebida() {
        return premiacaoRecebida;
    }

    public void comprarTeleSena(TeleSena teleSena) {
        if (quantidadeTeleSenas < 15) {
            teleSenas[quantidadeTeleSenas] = teleSena;
            quantidadeTeleSenas++;
        }
    }

    public void creditarPremio(double valor) {
        this.premiacaoRecebida += valor;
    }
    
    public boolean temGanhador(Set<Integer> sorteados) {
        for (int i = 0; i < quantidadeTeleSenas; i++) { 
            TeleSena ts = teleSenas[i]; 
            
            boolean ganhou1 = true;
            for (int n : ts.getPrimeiroConjunto()) {
                if (!sorteados.contains(n)) {
                    ganhou1 = false;
                    break;
                }
            }
            if (ganhou1) return true;

            boolean ganhou2 = true;
            for (int n : ts.getSegundoConjunto()) {
                if (!sorteados.contains(n)) {
                    ganhou2 = false;
                    break;
                }
            }
            if (ganhou2) return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Quantidade de TeleSenas: " + quantidadeTeleSenas + "\n" +
                "Premiação Recebida: R$" + String.format("%.2f", premiacaoRecebida);
    }
}
