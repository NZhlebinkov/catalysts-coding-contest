public class GroupedLight {
    int luminocitySize;
    int timeLength;

    public GroupedLight(int luminocitySize, int timeLength) {
        this.luminocitySize = luminocitySize;
        this.timeLength = timeLength;
    }

    public GroupedLight(int luminocitySize) {
        this.luminocitySize = luminocitySize;
        timeLength = 1;
    }

}
