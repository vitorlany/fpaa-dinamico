public class Utils {
    public static double calcularDesvioPadrao(int[] array) {
        // Passo 1: Calcular a média
        double soma = 0;
        for (int valor : array) {
            soma += valor;
        }
        double media = soma / array.length;

        // Passo 2: Calcular a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = 0;
        for (int valor : array) {
            double diferenca = valor - media;
            somaQuadradosDiferencas += diferenca * diferenca;
        }

        // Passo 3: Calcular a variância
        double variancia = somaQuadradosDiferencas / array.length;

        // Passo 4: Calcular o desvio padrão (raiz quadrada da variância)
        double desvioPadrao = Math.sqrt(variancia);

        return desvioPadrao;
    }

    public static int calcularAmplitude(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("O array não pode estar vazio.");
        }

        // Encontrar o menor e o maior valor no array
        int menorValor = array[0];
        int maiorValor = array[0];

        for (int valor : array) {
            if (valor < menorValor) {
                menorValor = valor;
            } else if (valor > maiorValor) {
                maiorValor = valor;
            }
        }

        // Calcular a amplitude
        int amplitude = maiorValor - menorValor;

        return amplitude;
    }
}
