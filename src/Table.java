import java.util.Arrays;

class Table {
    private Flag[][] tabela;
    private int[] valores;

    public Table(int[] numeros, int tamanhoColuna) {
        int tamanhoLinha = numeros.length;
        valores = numeros;
        tabela = new Flag[tamanhoLinha][tamanhoColuna];
        preencherDefault();
        System.out.println(Arrays.asList(tabela[1]));
    }

    private void preencherDefault() {
        Arrays.fill(this.tabela[0], Flag.FALSA);
        this.tabela[0][0] = Flag.VERDADEIRA;

        for (int i = 1; i < this.tabela.length; i++) {
            this.tabela[i][0] = Flag.REPETIDO;
        }
    }
}
