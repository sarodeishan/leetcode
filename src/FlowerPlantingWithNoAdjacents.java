package src;

import java.util.*;
import java.util.stream.Collectors;

public class FlowerPlantingWithNoAdjacents {

    public static void main(String[] args) {
        FlowerPlantingWithNoAdjacents test = new FlowerPlantingWithNoAdjacents();
        System.out.println("Answer:" + Arrays.toString(test.gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.gardenNoAdj(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}})));
        System.out.println();
    }

    static class Garden {
        public static final int[] FLOWER_TYPES = {1, 2, 3, 4};
        int flowerType;
        List<Garden> connectedGardens;

        public Garden() {
            flowerType = -1;
            connectedGardens = new ArrayList<Garden>();
        }

        public int getFlowerType() {
            return flowerType;
        }

        void setUniqueFlowers() {
            HashSet<Integer> takenByConnectedGarden = new HashSet();
            for (Garden garden : connectedGardens) {
                if (garden.flowerType != -1) {
                    takenByConnectedGarden.add(garden.flowerType);
                }
            }

            for (int flowerType : FLOWER_TYPES) {
                if (!takenByConnectedGarden.contains(flowerType)) {
                    this.flowerType = flowerType;
                    break;
                }
            }
        }
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
        Garden[] gardens = new Garden[n];
        for (int i = 0; i < n; i++) {
            gardens[i] = new Garden();
        }
        for (int[] path : paths) {
            int p1 = path[0] - 1;
            int p2 = path[1] - 1;
            Garden g1 = gardens[p1];
            Garden g2 = gardens[p2];
            g1.connectedGardens.add(g2);
            g2.connectedGardens.add(g1);
        }

        for (Garden garden : gardens) {
            garden.setUniqueFlowers();
        }
        return Arrays.stream(gardens).mapToInt(Garden::getFlowerType).toArray();
    }

}
