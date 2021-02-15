import java.util.Arrays;
import java.util.Random;

public class Individual implements Comparable<Individual> {

    public static final int ITEM_SIZE = 10;
    private static final int MAX_WEIGHT = 101;
    private static final int MUTATE_PROB = 30;
    private int[] chromosome = new int[ITEM_SIZE];
    private final Random random = new Random();
    private final int[] weight = {85, 26, 48, 21, 22, 95, 43, 45, 55, 52};
    private final int[] value = {79, 32,47, 18, 26, 85, 33, 40, 45, 59};
    private int score = 0;

    public void init() {
//        while (score == 0) {
            initChromosome();
            this.score = countFitnessScore();
//        }
    }

    public void initChromosome() {
        for (int i = 0; i < ITEM_SIZE; i++) {
            chromosome[i] = random.nextInt(2);
        }
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public void mutate() {
        for (int i = 0; i< 2; i++) {
            int randomPoint = random.nextInt(ITEM_SIZE);
            if (MUTATE_PROB > random.nextInt(100)) {
                if (chromosome[randomPoint] == 1) {
                    chromosome[randomPoint] = 0;
                } else {
                    chromosome[randomPoint] = 1;
                }
            }
        }
        this.score = countFitnessScore();
    }

    public int countFitnessScore() {
        int score = 0;
        int totalWeight = 0;
        for (int i = 0; i < ITEM_SIZE; i++) {
            if (chromosome[i] == 1) {
                score += value[i];
                totalWeight += weight[i];
            }
        }

        if (totalWeight > MAX_WEIGHT) {
            score = 0;
        }

        return score;
    }

    public int getScore() {
        return score;
    }


    @Override
    public int compareTo(Individual individual) {
        return this.getScore() - individual.getScore();
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome);
    }

}
