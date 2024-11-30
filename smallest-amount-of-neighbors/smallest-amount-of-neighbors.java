
class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Integer[][] predMatrix = buildPredMatrix(edges, n);
        int[][] weightsMatrix = buildWeightsMatrix(edges, n);

        int[] counter = new int[n];

        for (int x = 0; x < n; x++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    int uxWeight = weightsMatrix[u][x];
                    int xvWeight = weightsMatrix[x][v];
                    int weight = sumWithoutOverflow(uxWeight, xvWeight);
                    if (weightsMatrix[u][v] > weight) {
                        weightsMatrix[u][v] = weight;
                        predMatrix[u][v] = predMatrix[x][v];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (weightsMatrix[i][j] <= distanceThreshold) {
                    counter[i]++;
                }
            }
        }

        int min = counter[0];
        int index = 0;

        for (int i = 1; i < n; i++) {
            if (counter[i] <= min) {
                min = counter[i];
                index = i;
            }
        }

        return index;
    }

    private int sumWithoutOverflow(int x, int y) {
        return x == Integer.MAX_VALUE || y == Integer.MAX_VALUE ? Integer.MAX_VALUE : x + y;
    }

    private Integer[][] buildPredMatrix(int[][] edges, int n) {
        Integer[][] predMatrix = new Integer[n][n];

        for (int[] edge : edges) {
            predMatrix[edge[0]][edge[1]] = edge[0];
            predMatrix[edge[1]][edge[0]] = edge[1];
        }

        return predMatrix;
    }

    private int[][] buildWeightsMatrix(int[][] edges, int n) {
        int[][] weightsMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weightsMatrix[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }

        for (int[] edge : edges) {
            weightsMatrix[edge[0]][edge[1]] = edge[2];
            weightsMatrix[edge[1]][edge[0]] = edge[2];
        }

        return weightsMatrix;
    }
}
