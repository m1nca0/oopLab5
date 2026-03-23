package Model;
public class Hat extends Sample implements applyEf{
    private int tailLength; 
    private boolean closed;
    
    public Hat(String name, int lengthMs, double volume, 
               int lowFrequency, int highFrequency, int tailLength, boolean closed) {
        super(name, lengthMs, volume, lowFrequency, highFrequency, SampleType.HAT);
        this.tailLength = tailLength;
        this.closed = closed;
    }
    
    public void increaseTail() {
        if (tailLength < 100) {
            tailLength += 10;
            System.out.println("Хвост увеличен до: " + tailLength + "%");
        }
    }
    
    public void decreaseTail() {
        if (tailLength > 0) {
            tailLength -= 10;
            System.out.println("Хвост уменьшен до: " + tailLength + "%");
        }
    }
    
    public void toggleClosed() {
        closed = !closed;
        System.out.println("Hi-Hat теперь: " + (closed ? "закрыт" : "открыт"));
    }
    
    @Override
    public void FX() {
        System.out.println("Применен эффект затухания для Hi-Hat");
    }
    
    @Override
    protected void playFallbackSound() {
        System.out.println("TSHH! (Hi-Hat звук)");
        if(closed == true){
            playSound("Sounds/XLNT-QFB - Closed Hihat 01.wav");
        }
        else playSound("Sounds/XLNT-QFB - Open Hihat 01.wav");
    }
    
    @Override
    protected String getSoundPath() {
        if(closed == true){
            return "Sounds/XLNT-QFB - Closed Hihat 01.wav";
        }
        else return "Sounds/XLNT-QFB - Open Hihat 01.wav";
    }
    
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Длина хвоста: " + tailLength + "%");
        System.out.println("Состояние: " + (closed ? "Закрыт" : "Открыт"));
    }
    
    public int getTailLength() { return tailLength; }
    public boolean isClosed() { return closed; }
}