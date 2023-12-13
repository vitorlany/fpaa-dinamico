import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final int caminhoes = 3;
        final int tamConjunto = 10;

//        List<int[]> rotasGeradas = List.of(
//                new int[]{40,36,38,29,32,28,31,35,31,30,32,30,29,39,35,38,39,35,32,38,32,33,29,33,29,39,28},
//                new int[]{32,51,32,43,42,30,42,51,43,51,29,25,27,32,29,55,43,29,32,44,55,29,53,30,24,27});
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(220 , tamConjunto, 1);

        List<List<DtoResposta>> respostas = new ArrayList<>();

        for (int[] rotasGerada : rotasGeradas) {
            respostas.add(getRotas(caminhoes, rotasGerada));
        }

        int numConjunto = 1;
        double tempoTotal = 0.0;
        double desvioPadraoTotal = 0.0;
        int amplitudeTotal = 0;
        // Imprimir Relatório
        for (List<DtoResposta> conjunto : respostas) {
            int[] totaisRotas = conjunto.stream()
                    .mapToInt(resCaminhoes -> resCaminhoes.rota().total())
                    .toArray();

            double tempo = conjunto.stream()
                    .mapToDouble(DtoResposta::timmer)
                    .sum();

            double desvioPadrao = Utils.calcularDesvioPadrao(totaisRotas);
            int amplitude = Utils.calcularAmplitude(totaisRotas);

            tempoTotal += tempo;
            desvioPadraoTotal += desvioPadrao;
            amplitudeTotal += amplitude;

            System.out.println("Conjunto (" + (numConjunto++) + ")");
            System.out.println("Totais de rota: " + Arrays.toString(totaisRotas));
            System.out.println("Desvio Padrão: " + desvioPadrao);
            System.out.println("Amplitude: " + amplitude);
            System.out.println("Tempo (ms): " + tempo);
            System.out.println();
        }
        System.out.println("===================================");
        System.out.println();
        System.out.println("[Total] Desvio Padrão: " + desvioPadraoTotal/tamConjunto);
        System.out.println("[Total] Amplitude: " + amplitudeTotal/tamConjunto);
        System.out.println("[Total] Media tempo (ms): " + tempoTotal/tamConjunto);
    }

    private static List<DtoResposta> getRotas(int caminhoes, int[] array) {
        List<Integer> set = new ArrayList<>(Arrays.stream(array)
                .boxed()
                .toList());
        List<DtoResposta> respostas = new ArrayList<>();
        int sum = (int) Math.ceil(set.stream().mapToDouble(a -> a).sum()/caminhoes);

        for (int i = 0; i < caminhoes; i++) {
            String label = "Caminhão " + (i + 1);

            double timmer = System.nanoTime();
            boolean[][] subsetSum = GFG.subsetSum(set, sum);
            Rota rota = GFG.getResults(set, subsetSum);
            timmer = System.nanoTime() - timmer;

            respostas.add(new DtoResposta(label, rota, timmer/1000000));

            // Garantimos que removemos apenas 1
            for (int r: rota.rotas()) {
                set.remove(set.indexOf(r));
            }

            // Garante margem de erro que não foi suprida
            sum += sum - rota.total();
        }
        return respostas;
    }
}