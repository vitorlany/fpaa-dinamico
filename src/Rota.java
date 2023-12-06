import java.util.Arrays;

public class Rota {
    private int[] rota;
    private int total;

    public Rota(int[] rota) {
        this.rota = rota;
        this.total = Arrays.stream(rota).sum();
    }

    public int[] getRota() {
        return rota;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Rota{" +
                "rota=" + Arrays.toString(rota) +
                ", total=" + total +
                '}';
    }
}
