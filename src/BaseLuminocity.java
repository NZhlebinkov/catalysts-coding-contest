public class BaseLuminocity extends FluctuatingLuminocity{
    int maxLuminocity;
    int minLuminocity;

    public BaseLuminocity(int luminocity) {
        super(luminocity);
        minLuminocity = (int)(luminocity*100/100.05);
        maxLuminocity = (int)(luminocity*100/99.95);
    }

    public int getLuminocity() {
        return super.getLuminocity();
    }
}
