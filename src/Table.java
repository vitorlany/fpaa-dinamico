import java.util.Arrays;

class Table {
    private Flag[][] tabela;
    private int[] valores;

    public Table(int[] numeros, int tamanhoColuna) {
        Arrays.sort(numeros);
        valores = numeros;
        tabela = new Flag[numeros.length][tamanhoColuna];
        preencherDefault();
    }

    private void preencherDefault() {
        Arrays.fill(this.tabela[0], Flag.FALSA);
        this.tabela[0][0] = Flag.VERDADEIRA;

        for (int i = 1; i < this.tabela.length; i++) {
            this.tabela[i][0] = Flag.REPETIDO;
        }
    }

    public Flag[][] executar() {
        
        return this.tabela;
    }
}
