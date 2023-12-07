// A Dynamic Programming solution for subset
// sum problem
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

import java.io.*;
import java.util.Arrays;
import java.util.List;

class GFG {

    // Returns true if there is a subset of
    // set[] with sum equal to given sum
    static boolean[][] isSubsetSum(int set[], int n, int sum)
    {
        // The value of subset[i][j] will be
        // true if there is a subset of
        // set[0..j-1] with sum equal to i
        boolean[][] subset = new boolean[sum + 1][n + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++)
            subset[0][i] = true;

        // If sum is not 0 and set is empty,
        // then answer is false
        for (int i = 1; i <= sum; i++)
            subset[i][0] = false;

        // Fill the subset table in bottom
        // up manner
        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                subset[i][j] = subset[i][j - 1];
                if (i >= set[j - 1])
                    subset[i][j]
                            = subset[i][j]
                            || subset[i - set[j - 1]][j - 1];
            }
        }

        return subset;
    }

    // Método para retornar os elementos do conjunto que se encaixam no subconjunto
    static void printElementsInSubset(int set[], boolean[][] subset) {
        int total = 0;
        int i = subset.length-1, j = set.length;
        for (int k = i; k > 0; k--) {
            if (!subset[k][j]) continue;
            System.out.println(k);
            i = k;
            break;
        }
        while (i > 0 && j > 0) {
            if (subset[i][j] != subset[i][j - 1]) {
                total += set[j - 1];
                System.out.println("Elemento no subconjunto: " + set[j - 1]);
                i = i - set[j - 1];
            }
            j--;
        }
        System.out.println("Total: " + total);
        //TODO: objeto de resposta contendo a lsita do conjunto e total
    }

    // Driver code
    public static void main(String args[])
    {
        List<int[]> rotasGeradas = GeradorDeProblemas.geracaoDeRotas(10, 1, 1);

        int set[] = rotasGeradas.get(0);
        System.out.println(Arrays.toString(set));
        int sum = 37; // será passado de cada caminhão
        int n = set.length;
        boolean[][] subsetSum = isSubsetSum(set, n, sum);
        printElementsInSubset(set, subsetSum);
    }
}

/* This code is contributed by Rajat Mishra */
