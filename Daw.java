import java.util.ArrayList;
import java.util.List;

public class Daw {
    private List<Sample> samples = new ArrayList<>();
    private int currentSample = 0;

    public void CreateSample(Sample sample)
    {
        if (findSample(sample.getName(), null, true) != true) {
            samples.add(sample);
            System.out.println("Сэмпл " + sample.getName() + " успешно созданн!");
        }
        else{
            System.out.println("Сэмпл с названием " + sample.getName() + " уже существует");
        }
    }

    public void playSample(String typeOfSample)
    {
        if ("KICK".equals(typeOfSample)) samples.get(currentSample).playFallbackSound();
        else if ("SNARE".equals(typeOfSample)) samples.get(currentSample).playFallbackSound();
        else samples.get(currentSample).playFallbackSound();
    }

    public void increaseHatTail() {
            ((Hat)samples.get(currentSample)).increaseTail();
    }
    
    public void decreaseHatTail() {
            ((Hat)samples.get(currentSample)).decreaseTail();
    }
    
    public void toggleHatClosed() {
            ((Hat)samples.get(currentSample)).toggleClosed();
    }
    
    public void applyEffect(String typeOfSample) {
        if ("KICK".equals(typeOfSample)) ((Kick)samples.get(currentSample)).FX();
        else if ("SNARE".equals(typeOfSample)) ((Snare)samples.get(currentSample)).FX();
        else ((Hat)samples.get(currentSample)).FX();
    }
    
    public void increaseKickBass() {
            ((Kick)samples.get(currentSample)).increaseBass();
    }
    
    public void decreaseKickBass() {
            ((Kick)samples.get(currentSample)).decreaseBass();
    }
    
    public void increaseSnarePunch() {
            ((Snare)samples.get(currentSample)).increasePunch();
    }
    
    public void decreaseSnarePunch() {
            ((Snare)samples.get(currentSample)).decreasePunch();
    }
    public void printInfo(String typeOfSample) {
        if ("KICK".equals(typeOfSample)) ((Kick)samples.get(currentSample)).printInfo();
        else if ("SNARE".equals(typeOfSample))((Snare)samples.get(currentSample)).printInfo();
        else ((Hat)samples.get(currentSample)).printInfo();
    }
    
    public void printAllInfo() {
        System.out.println("\n=== ВСЕ СЭМПЛЫ ===");
        for (int i = 0; i < samples.size(); i++) {
            samples.get(i).printInfo();
        }
        System.out.println("Общее количество сэмплов: " + samples.size());
    }
    public List<Sample> getSamples() {
        return new ArrayList<>(samples);
    }
    
    public int getSamplesCount() {
        return samples.size();
    }

    public Sample getSample(int index) {
        return samples.get(index);
    }
    public boolean findSample(String name, String typeOfSample, boolean delFalseFindTrue) {
        if ("KICK".equals(typeOfSample)) {
            for (int i = 0; i < samples.size(); i++) {
                if (samples.get(i).getName().equals(name) && samples.get(i).getType() == SampleType.KICK) {
                    if(delFalseFindTrue)
                    {
                        currentSample = i;
                        return true;
                    }
                    else{
                        samples.remove(i);
                        return true;
                    }
                }
            }
            return false;
        } else if ("HAT".equals(typeOfSample)) {
            for (int i = 0; i < samples.size(); i++) {
                if (samples.get(i).getName().equals(name) && samples.get(i).getType() == SampleType.HAT) {
                    if(delFalseFindTrue)
                    {
                        currentSample = i;
                        return true;
                    }
                    else{
                        samples.remove(i);
                        return true;
                    }
                }
            }
            return false;
        } else if ("SNARE".equals(typeOfSample)){
            for (int i = 0; i < samples.size(); i++) {
                if (samples.get(i).getName().equals(name) && samples.get(i).getType()== SampleType.SNARE) {
                    currentSample = i;
                    if(delFalseFindTrue)
                    {
                        currentSample = i;
                        return true;
                    }
                    else{
                        samples.remove(i);
                        return true;
                    }                
                }
            }
            return false;
        }
        else{
            for (int i = 0; i < samples.size(); i++) {
                if (samples.get(i).getName().equals(name)) {
                    currentSample = i;
                    if(delFalseFindTrue)
                    {
                        currentSample = i;
                        return true;
                    }
                    else{
                        samples.remove(i);
                        return true;
                    }                
                }
            }
            return false;
        }
    }
}