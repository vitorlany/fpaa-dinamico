import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(10, 1, 1);
        int caminhoes = 3;

        List<Integer> set = new ArrayList<>(Arrays.stream(rotasGeradas.get(0))
                .boxed()
                .toList());
        int sum = set.stream().mapToInt(a -> a).sum()/caminhoes;

        System.out.println("Initial: " + set);

        for (int i = 0; i < caminhoes; i++) {
            System.out.println("Caminhão " + (i+1));
            boolean[][] subsetSum = GFG.subsetSum(set, sum);
            Rota rota = GFG.getResults(set, subsetSum);
            System.out.println(rota.rotas());
            System.out.println(rota.total());

            set.removeAll(rota.rotas());
            sum += sum - rota.total();
            System.out.println("====================");
        }
    }
}