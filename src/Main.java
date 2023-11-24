import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(new int[]{1, 2, 3}, 15);
        Flag[][] flags = table.gerarTabela();
        String tableStr = Arrays.deepToString(flags);
        System.out.println(tableStr);
    }
}
