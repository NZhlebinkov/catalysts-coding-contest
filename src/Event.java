import java.util.ArrayList;

public class Event {
    private String name;
    private FluctuatingLuminocity[] lightCurveArray;
    BaseLuminocity baseLuminocity;
    private String type;
    public static final double TRANSIT_MIN_DIMMING = 0.005;

    public Event(String name, int[] inputLightCurveArray) {
        this.name = name;
        baseLuminocity = new BaseLuminocity(inputLightCurveArray[0]);
        lightCurveArray = new FluctuatingLuminocity[inputLightCurveArray.length];
        for(int i = 0; i != lightCurveArray.length; i++) {
            lightCurveArray[i] = new FluctuatingLuminocity(inputLightCurveArray[i]);
        }
    }

    public void determineType() {
        for(int i = 1; i != lightCurveArray.length; i++) {
            if(lightCurveArray[i].getMinLuminocity() - 200 > lightCurveArray[i-1].getMaxLuminocity()) {
                type = "flare";
                return;
            }
            if(lightCurveArray[i-1].getMaxLuminocity() - lightCurveArray[i].getMinLuminocity() - 200 > lightCurveArray[i-1].getLuminocity()*TRANSIT_MIN_DIMMING) {
                if(isTransit(i)) {
                    type = "transit";
                }
                else {
                    type = "something";
                }
                return;
            }
        }
        type = "nothing";
    }

    public boolean isTransit(int index) {
        int timeLength = 1;
        BaseLuminocity transitBaseLuminocity = new BaseLuminocity(lightCurveArray[index].getLuminocity());
        for(index++; index != lightCurveArray.length; index++) {
            if(lightCurveArray[index].getMinLuminocity() - 200 > transitBaseLuminocity.getMaxLuminocity()) {
                return false;
            }
            else {
                timeLength++;
                if(timeLength == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
