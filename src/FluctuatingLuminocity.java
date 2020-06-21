public class FluctuatingLuminocity {
    private int luminocity;
    private int maxLuminocity;
    private int minLuminocity;

    public FluctuatingLuminocity(int luminocity) {
        this.luminocity = luminocity;
        maxLuminocity = (int) (luminocity + (0.05/100)*luminocity);
        minLuminocity = (int) (luminocity - (0.05/100)*luminocity);
    }

    public int getLuminocity() {
        return luminocity;
    }

    public int getMaxLuminocity() {
        return maxLuminocity;
    }

    public int getMinLuminocity() {
        return minLuminocity;
    }
}
