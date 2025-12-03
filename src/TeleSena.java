import java.util.Arrays;

public class TeleSena {

    public static final double VALOR_VENDA = 10.0;
	private int[] primeiroConjunto;
	private int[] segundoConjunto;
	
	public TeleSena() {
	    this.primeiroConjunto = gerarConjunto();
	    this.segundoConjunto = gerarConjunto();
	}
	
	public static double getValorVenda() {
	    return VALOR_VENDA;
	}
	
	public int[] getPrimeiroConjunto() {
	    return primeiroConjunto;
	}
	
	public int[] getSegundoConjunto() {
	    return segundoConjunto;
	}
	
	private int[] gerarConjunto() {
	    int[] numeros = new int[25]; // Array para armazenar 25 números únicos.
	    boolean[] usados = new boolean[61]; // Array auxiliar para controlar repetição, índice de 1 a 60.
	
	    int cont = 0;
	    while(cont < 25) {
	        int n = (int)(Math.random() * 60 + 1); // Gera um número inteiro aleatório entre 1 e 60 (inclusive).
	        if(!usados[n]) { // Verifica se o número já foi usado.
	            usados[n] = true; // Marca o número como usado.
	            numeros[cont] = n; // Adiciona o número ao conjunto.
	            cont++; // Incrementa o contador.
	        }
	    }
        // Organiza os números em ordem crescente.
	    Arrays.sort(numeros);
	    return numeros;
	}
	
	@Override
	public String toString() {
	    return "Conjunto 1: " + java.util.Arrays.toString(primeiroConjunto) +
	           "\nConjunto 2: " + java.util.Arrays.toString(segundoConjunto);
	}
}
