package Model;
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
                        System.out.println("УДАЛЕН");
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