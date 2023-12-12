import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(250, 10, 1);
        final int caminhoes = 3;

        List<List<DtoResposta>> respostas = new ArrayList<>();

        for (int[] rotasGerada : rotasGeradas) {
            respostas.add(getRotas(caminhoes, rotasGerada));
        }

        respostas.forEach(conjunto -> {
            int[] totaisRotas = conjunto.stream()
                    .mapToInt(resCaminhoes -> resCaminhoes.rota().total())
                    .toArray();

            double mediaTempo = conjunto.stream()
                    .mapToLong(DtoResposta::timmer)
                    .average()
                    .orElse(-1);

            double desvioPadrao = Utils.calcularDesvioPadrao(totaisRotas);
            int amplitude = Utils.calcularAmplitude(totaisRotas);

            System.out.println("Totais de rota: " + Arrays.toString(totaisRotas));
            System.out.println("Desvio Padrão: " + desvioPadrao);
            System.out.println("Amplitude: " + amplitude);
            System.out.println("Media tempo: " + mediaTempo);
            System.out.println();
        });
    }

    private static List<DtoResposta> getRotas(int caminhoes, int[] array) {
        List<Integer> set = new ArrayList<>(Arrays.stream(array)
                .boxed()
                .toList());
        List<DtoResposta> respostas = new ArrayList<>();
        int sum = (int) Math.ceil(set.stream().mapToDouble(a -> a).sum()/caminhoes);

        for (int i = 0; i < caminhoes; i++) {
            String label = "Caminhão " + (i + 1);

            long timmer = System.currentTimeMillis();
            boolean[][] subsetSum = GFG.subsetSum(set, sum);
            Rota rota = GFG.getResults(set, subsetSum);
            timmer = System.currentTimeMillis() - timmer;

            respostas.add(new DtoResposta(label, rota, timmer));

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