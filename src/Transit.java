public class Transit {
    private int luminositySize;
    private int timeLength;
    private int startTime;

    public Transit(GroupedLight groupedLight, int startTime) {
        luminositySize = groupedLight.luminocitySize;
        timeLength = groupedLight.timeLength;
        this.startTime = startTime;
    }

    public int getLuminositySize() {
        return luminositySize;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public int getStartTime() {
        return startTime;
    }
}
