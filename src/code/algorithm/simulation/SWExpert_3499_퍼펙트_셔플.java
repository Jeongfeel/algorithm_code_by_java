package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class SWExpert_3499_퍼펙트_셔플 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int testcase = Integer.parseInt(br.readLine());
 
        for (int i = 1; i <= testcase; i++) {
            int sizeOfDeck = Integer.parseInt(br.readLine());
 
            String card[] = br.readLine().split(" ");
            int firstDeckIndex = 0;
            int secondDeckIndex = card.length / 2;
            if (card.length % 2 == 1) {
                secondDeckIndex += 1;
            }
 
            System.out.print("#" + i + " ");
            for (int j = 0; j < sizeOfDeck; j++) {
                if (j % 2 == 0) {
                    System.out.print(card[firstDeckIndex++] + " ");
                } else if (j % 2 == 1) {
                    System.out.print(card[secondDeckIndex++] + " ");
                }
            }
 
            System.out.println();
        }
    }
}