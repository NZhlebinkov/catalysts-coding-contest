import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scName = new Scanner(System.in);

        int levelNum = scName.nextInt();
        int fileNum = scName.nextInt();
        File myFile = new File("src/level"+levelNum+"-"+fileNum+".in");
        Scanner sc = null;

        try {
            sc = new Scanner(myFile);
        }catch (FileNotFoundException e) {
            System.out.println("No file");
            System.exit(-1);
        }

        int numOfEvents = Integer.parseInt(sc.next());
        Event[] myEvents = new Event[numOfEvents];

        for(int i = 0; i != numOfEvents; i++) {

            String eventName = sc.next();
            int lightCurveArrayLength = Integer.parseInt(sc.next());
            int[] lightCurveArray = new int[lightCurveArrayLength];
            for(int j = 0; j != lightCurveArrayLength; j++) {
                lightCurveArray[j] = Integer.parseInt(sc.next());
            }
            myEvents[i] = new Event(eventName, lightCurveArray);
            myEvents[i].determineType();
        }

        String result = "";
        for(int i = 0; i < numOfEvents; i++) {
                result += myEvents[i].getName() + " " + myEvents[i].getType() + " ";
        }

        System.out.println(result);



    }
}
