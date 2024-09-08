package calculator;
import java.util.random.*;

/**
 * @author Kellam
 * Calculates things, hopefully quickly.
 */
public class Calc {

    /**
     * How many battles are simulated, ideally 1 billion.
     */
    private static int repCount = (int)(Math.pow(10, 9));
    
    private static RandomGenerator g = RandomGenerator.of("Xoroshiro128PlusPlus");
    
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        final long[] residues = new long[32];
        for(int i = 0; i < 32; i++) {
            residues[i] = 3;
            for(int j = 0; j < i; j++) {
                residues[i] *= 4;
            }
        }
        int cap = 0;
        for(int i = 0; i < repCount; i++) {
            int para = 0;
            long gen;
            for(int j = 0; j < 7; j++) {
                gen = g.nextLong();
                for(int h = 0; h < residues.length; h++) {
                    if((gen & residues[h]) == 0) {
                        para++;
                    }
                }
            }
            gen = g.nextLong();
            for(int j = 0; j < 7; j++) {
                if((gen & residues[j]) == 0) {
                    para++;
                }
            }
            cap = Math.max(cap, para);
        }
        final long end = System.currentTimeMillis();
        System.out.println("Max para number: " + cap);
        System.out.println("Total time used in milliseconds:" + (end - start));
    }

}
