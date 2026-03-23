package Model;
public class Snare extends Sample implements applyEf{
    private int resonance; 
    private int punch; 
    
    public Snare(String name, int lengthMs, double volume, 
                 int lowFrequency, int highFrequency, int resonance, int punch) {
        super(name, lengthMs, volume, lowFrequency, highFrequency, SampleType.SNARE);
        this.resonance = Math.max(0, Math.min(100, resonance));
        this.punch = Math.max(0, Math.min(100, punch));
    }
    
    public void increasePunch() {
        if (punch < 100) {
            punch += 10;
            System.out.println("Панч увеличен до: " + punch + "%");
        }
    }
    
    public void decreasePunch() {
        if (punch > 0) {
            punch -= 10;
            System.out.println("Панч уменьшен до: " + punch + "%");
        }
    }
    
    
    public void FX() {
        System.out.println("Применен эффект реверберации для Snare");
    }
    
    @Override
    protected void playFallbackSound() {
        System.out.println("CRACK! (Snare звук)");
        if(punch > 50){
            playSound("Sounds/XLNT-QFB - Snare 09 - C#.wav");
        }
        else playSound("Sounds/XLNT-QFB - Snare 31 - F.wav");
    }
    
    @Override
    protected String getSoundPath() {
        if(punch > 50){
            return "Sounds/XLNT-QFB - Snare 09 - C#.wav";
        }
        else return "Sounds/XLNT-QFB - Snare 31 - F.wav";
    }
    
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Резонанс: " + resonance + "%");
        System.out.println("Панч: " + punch + "%");
    }
    
    public int getResonance() { return resonance; }
    public int getPunch() { return punch; }
}