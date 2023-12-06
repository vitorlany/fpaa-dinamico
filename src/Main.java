import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(3, 10, 1);
        List<Rota> rotas = rotasGeradas.stream().map(Rota::new).toList();
        Table table = new Table(rotas, 200);
        Flag[][] flags = table.gerarTabela();
        System.out.println(rotas);

        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                System.out.print(flags[i][j] + " ");
            }
            System.out.println();
        }
    }
}
