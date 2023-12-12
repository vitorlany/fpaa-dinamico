import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(25, 10, 1);
        final int caminhoes = 3;

        List<List<DtoResposta>> respostas = new ArrayList<>();

        for (int[] rotasGerada : rotasGeradas) {
            respostas.add(getRotas(caminhoes, rotasGerada));
        }

        System.out.println(respostas);
    }

    private static List<DtoResposta> getRotas(int caminhoes, int[] array) {
        List<Integer> set = new ArrayList<>(Arrays.stream(array)
                .boxed()
                .toList());
        List<DtoResposta> respostas = new ArrayList<>();
        int sum = (int) Math.ceil(set.stream().mapToDouble(a -> a).sum()/ caminhoes);

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