import java.util.Arrays;
import java.util.List;

class Table {
    private final Flag[][] tabela;
    private final List<Rota> rotas;

    public Table(List<Rota> rotas, int tamanhoColuna) {
//        Arrays.sort(rotas);
        this.rotas = rotas;
        this.tabela = new Flag[rotas.size()+1][tamanhoColuna+1];
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
            int primeiroIndex = rotas.get(i-1).getTotal();
            for (int r = 0; r < primeiroIndex; r++) {
                Flag res = tabela[i-1][r];
                if (res == Flag.VERDADEIRA)
                    res = Flag.REPETIDO;
                tabela[i][r] = res;
            }
            for (int j = primeiroIndex; j < this.tabela[0].length; j++) {
                if (this.tabela[i-1][j] == Flag.VERDADEIRA) {
                    this.tabela[i][j] = Flag.REPETIDO;
                } else if (this.tabela[i-1][j-primeiroIndex] == Flag.VERDADEIRA) {
                    this.tabela[i][j] = Flag.VERDADEIRA;
                } else {
                    this.tabela[i][j] = Flag.FALSA;
                }
            }
        }
        return this.tabela;
    }

}
