import java.util.ArrayList;
import java.util.Iterator;

public class Star {
    private String name;
    private int[] lightCurveArray;
    private ArrayList<GroupedLight> groupedLights;
    private int numOfFlares;
    private ArrayList<Transit> transits;
    private int baseLuminosity;
    public static final double TRANSIT_MIN_DIMMING = 0.005;

    public Star(String name, int[] lightCurveArray) {
        this.name = name;
        this.lightCurveArray = lightCurveArray;
        groupedLights = new ArrayList<GroupedLight>();
        numOfFlares = 0;
        transits = new ArrayList<Transit>();
        baseLuminosity = lightCurveArray[0];
    }

    public void groupLight() {
        GroupedLight currentLightValue = new GroupedLight(lightCurveArray[0]);
        groupedLights.add(currentLightValue);
        for(int i = 1; i != lightCurveArray.length; i++) {
            if(currentLightValue.luminocitySize == lightCurveArray[i]) {
                currentLightValue.timeLength++;
            }
            else {
                currentLightValue = new GroupedLight(lightCurveArray[i]);
                groupedLights.add(currentLightValue);
            }
        }
    }

    public void determineEvents() {
        int timePassed = 0;
        int i = 0;
        while (i+1 < groupedLights.size()) { // Every even i is a non-event
            timePassed += groupedLights.get(i).timeLength;
            i++;
            if(groupedLights.get(i).luminocitySize > baseLuminosity) {
                numOfFlares++;
            }
            else if (groupedLights.get(i).luminocitySize <= baseLuminosity*(1-TRANSIT_MIN_DIMMING)
                    && groupedLights.get(i).timeLength >= 5) {
                transits.add(new Transit(groupedLights.get(i), timePassed));
            }
            timePassed += groupedLights.get(i).timeLength;
            i++;
        }
    }

    public boolean hasPlanetCandidate() {
        if(transits.size() < 3) {
            return false;
        }

        for(int fti = 0; fti < transits.size() - 2; fti++) { //first transit in sequence
            for(int sti = fti + 1; sti < transits.size() - 1; sti++) {
                for(int tti = sti + 1; tti < transits.size(); tti++) {
                    int betweenTime = transits.get(sti).getStartTime() - transits.get(fti).getStartTime();
                    if(betweenTime == transits.get(tti).getStartTime() - transits.get(sti).getStartTime()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



    public String getName() {
        return name;
    }

    public ArrayList<GroupedLight> getGroupedLights() {
        return groupedLights;
    }

    @Override
    public String toString() {
        String result = name;
        for(int i = 0; i != groupedLights.size(); i++) {
            result += " " + groupedLights.get(i).luminocitySize + " " + groupedLights.get(i).timeLength;
        }
        return result;
    }

    public String eventsString() {
        return name + " " + numOfFlares + " " + transits.size();
    }

}
