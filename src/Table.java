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

    public Flag[][] gerarTabela() {
        for(int i = 1; i < this.tabela.length; i++) {
            for (int j = 1; j < this.tabela[0].length; j++) {
                //TODO: Corrigir essa logica
                Flag atualMenosValor = this.tabela[i - 1][j - (this.valores[i])];
                if (this.tabela[i-1][j] == Flag.VERDADEIRA) {
                    this.tabela[i][j] = Flag.REPETIDO;
                } else if (atualMenosValor == Flag.REPETIDO || atualMenosValor == Flag.VERDADEIRA) {
                    this.tabela[i][j] = Flag.VERDADEIRA;
                } else {
                    this.tabela[i][j] = Flag.FALSA;
                }
            }
        }
        return this.tabela;
    }
}
