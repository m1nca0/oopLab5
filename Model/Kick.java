package Model;
public class Kick extends Sample implements applyEf {
    private int bassLevel;
    
    public Kick(String name, int lengthMs, double volume, 
                int lowFrequency, int highFrequency, int bassLevel) {
        super(name, lengthMs, volume, lowFrequency, highFrequency, SampleType.KICK);
        
        this.bassLevel = bassLevel;
    }
    
    public void increaseBass() {
        if (bassLevel < 100) {
            bassLevel += 10;
            System.out.println("Бас увеличен до: " + bassLevel + "%");
        }
        else System.out.println("Уровень басса на максимуме " + bassLevel + "%");
    }
    
    public void decreaseBass() {
        if (bassLevel > 0) {
            bassLevel -= 10;
            System.out.println("Бас уменьшен до: " + bassLevel + "%");
        }
        else System.out.println("Уровень басса на минимуме " + bassLevel + "%");
    }    
    
    @Override
    protected void playFallbackSound() {
        System.out.println("BOOM! (Kick звук)");
        if(bassLevel > 50){
            playSound("Sounds/XLNT-QFB - Kick 05 - C#.wav");
        }
        else playSound("Sounds/XLNT-QFB - Kick 17 - F.wav");
    }
    
    @Override
    protected String getSoundPath() {
        if(bassLevel > 50){
            return "Sounds/XLNT-QFB - Kick 05 - C#.wav";
        }
        else return "Sounds/XLNT-QFB - Kick 17 - F.wav";
    }
    
    @Override
    public void FX() {
        System.out.println("Применен эффект компрессии для Kick");
    }
    
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Уровень басса: " + bassLevel + "%");
    }
    
    public int getBassLevel() { return bassLevel; }
}