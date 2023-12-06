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
            for (int j = 1; j < this.tabela[0].length; j++) {
                //TODO: Validar lógica
                int atualMenosValor = j-1 + rotas.get(i-1).getTotal();
                if (this.tabela[i-1][j] == Flag.VERDADEIRA) {
                    this.tabela[i][j] = Flag.REPETIDO;
                } else if (atualMenosValor == j) {
                    this.tabela[i][j] = Flag.VERDADEIRA;
                } else {
                    this.tabela[i][j] = Flag.FALSA;
                }
            }
        }
        return this.tabela;
    }

}
