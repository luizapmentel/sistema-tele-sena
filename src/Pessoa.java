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

    public boolean temGanhador(int[] sorteados) {
        java.util.HashSet<Integer> setSorteados = new java.util.HashSet<>(); // Converte o array de números sorteados para um HashSet (busca mais rápida)
        for (int n : sorteados) setSorteados.add(n); // Adiciona cada número sorteado ao HashSet.

        for (int i = 0; i < quantidadeTeleSenas; i++) {
            TeleSena ts = teleSenas[i]; // Obtém a TeleSena da pessoa.
            boolean acertouConj1 = true; // Assume que o conjunto 1 foi completamente acertado.
            boolean acertouConj2 = true; // Assume que o conjunto 2 foi completamente acertado.

            // Percorre todos os 25 números do conjunto 1 e verifica se todos estão nos números sorteados.
            for (int n : ts.getPrimeiroConjunto()) {
                if (!setSorteados.contains(n)) { 
                    acertouConj1 = false; 
                    break; 
                }
            }
            if (acertouConj1) return true; // Todos os 25 números do conjunto 1 foram sorteados, então há um ganhador.

            // Percorre todos os 25 números do conjunto 2 e verifica se todos estão nos números sorteados. 
            for (int n : ts.getSegundoConjunto()) {
                if (!setSorteados.contains(n)) { 
                    acertouConj2 = false; 
                    break; 
                }
            }
            if (acertouConj2) return true; // Todos os 25 números do conjunto 2 foram sorteados, então há um ganhador.
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
