package Model;
import javax.sound.sampled.*;
import java.io.File;

public abstract class Sample {
    protected String name;
    protected int lengthMs;
    protected double volume;
    protected int lowFrequency;
    protected int highFrequency;
    protected SampleType type;

    public Sample(String name, int lengthMs, double volume, 
                  int lowFrequency, int highFrequency, SampleType type) {
        this.name = name;
        this.lengthMs = lengthMs;
        this.volume = volume;
        this.lowFrequency = lowFrequency;
        this.highFrequency = highFrequency;
        this.type = type;
    }

    public void printInfo() {
        System.out.println("=== " + name + " ===");
        System.out.println("Тип: " + type.getDisplayName());
        System.out.println("Длина: " + lengthMs + " мс");
        System.out.println("Громкость: " + (int)(volume * 100) + "%");
        System.out.println("Частоты: " + lowFrequency + " - " + highFrequency + " Гц");
    }
    
    protected void playSound(String filePath) {
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filePath))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
        
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            
            clip.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected abstract void playFallbackSound();
    protected abstract String getSoundPath();
    
    public String getName() { return name; }
    public int getLengthMs() { return lengthMs; }
    public double getVolume() { return volume; }
    public int getLowFrequency() { return lowFrequency; }
    public int getHighFrequency() { return highFrequency; }
    public SampleType getType() { return type; }
}