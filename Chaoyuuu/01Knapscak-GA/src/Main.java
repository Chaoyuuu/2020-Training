import java.util.*;

public class Main {
    private static List<Individual> population = new ArrayList<>();
    private static final int POPULATION_SIZE = 10;

    public static void main(String[] args) {
        initPopulation();

        for (int i = 0; i < 1000; i++) {
            if (i % 100 == 0) {
                printPopulation();
            }
            mate(population.get(0), population.get(1));
        }
        System.out.println("----end----");
        printPopulation();

    }

    public static void initPopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Individual individual = new Individual();
            individual.init();
            population.add(individual);
        }
        sortPopulation();
    }

    public static void sortPopulation() {
        Collections.sort(population);
        Collections.reverse(population);
    }

    public static void printPopulation() {
        for (Individual individual : population) {
            System.out.println(individual + " " + individual.getScore());
        }
        System.out.println("--------");
    }

    public static void mate(Individual parent1, Individual parent2) {
        int[] chromosome1 = parent1.getChromosome().clone();
        int[] chromosome2 = parent2.getChromosome().clone();

        Random random = new Random();
        int crossoverPoint = random.nextInt(Individual.ITEM_SIZE);
        for (int i = 0; i < crossoverPoint; i++) {
            int gene = chromosome1[i];
            chromosome1[i] = chromosome2[i];
            chromosome2[i] = gene;
        }

        newIndividuals(chromosome1);
        newIndividuals(chromosome2);
    }

    public static void newIndividuals(int[] chromosome) {
        Individual offSpring = new Individual();
        offSpring.setChromosome(chromosome);
        offSpring.mutate();
        addGeneration(offSpring);
    }

    public static void addGeneration(Individual chromosome) {
        population.add(chromosome);
        sortPopulation();
        removeMinScoreChromosome();
    }

    public static void removeMinScoreChromosome() {
        int index = population.size() - 1;
        Individual eviction = population.get(index);
        population.remove(eviction);
    }
}

