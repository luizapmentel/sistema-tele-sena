public class Pessoa {

    private String nome;
    private TeleSena[] teleSenas;
    private int quantidadeTeleSenas;
    private double premiacaoRecebida;

    public Pessoa(String nome, TeleSena[] teleSenas, int quantidadeTeleSenas, double premiacaoRecebida) {
        this.nome = nome;
        this.teleSenas = new TeleSena[15];
        this.quantidadeTeleSenas = quantidadeTeleSenas;
        this.premiacaoRecebida = premiacaoRecebida;
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

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Quantidade de TeleSenas: " + quantidadeTeleSenas + "\n" +
                "Premiação Recebida: R$" + premiacaoRecebida;
    }
}
