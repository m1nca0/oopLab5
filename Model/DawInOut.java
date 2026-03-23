package Model;
import java.io.*;
import java.util.List;

public class DawInOut {
    public void saveProject(Daw daw, String filePath) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            List<Sample> samples = daw.getSamples();
            
            for (Sample sample : samples) {

                if (sample instanceof Kick) {
                    Kick k = (Kick) sample;
                    writer.println("KICK|" + k.getName() + "|" + 
                                  k.getLengthMs() + "|" + 
                                  k.getVolume() + "|" + 
                                  k.getLowFrequency() + "|" + 
                                  k.getHighFrequency() + "|" + 
                                  k.getBassLevel());
                }
                if (sample instanceof Snare) {
                    Snare s = (Snare) sample;
                    writer.println("SNARE|" + s.getName() + "|" + 
                                  s.getLengthMs() + "|" + 
                                  s.getVolume() + "|" + 
                                  s.getLowFrequency() + "|" + 
                                  s.getHighFrequency() + "|" + 
                                  s.getResonance() + "|" + 
                                  s.getPunch());
                }
                if (sample instanceof Hat) {
                    Hat h = (Hat) sample;
                    writer.println("HAT|" + h.getName() + "|" + 
                                  h.getLengthMs() + "|" + 
                                  h.getVolume() + "|" + 
                                  h.getLowFrequency() + "|" + 
                                  h.getHighFrequency() + "|" + 
                                  h.getTailLength() + "|" + 
                                  h.isClosed());
                }
            }
            System.out.println("Сохранено!");
            
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    public Daw loadProject(String filePath) {
        Daw daw = new Daw();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));){
            
            String line;
            
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");
                

                if (parts[0].equals("KICK")) {
    
                    String name = parts[1];
                    int length = Integer.parseInt(parts[2]);
                    double volume = Double.parseDouble(parts[3]);
                    int lowFreq = Integer.parseInt(parts[4]);
                    int highFreq = Integer.parseInt(parts[5]);
                    int bass = Integer.parseInt(parts[6]);

                    daw.CreateSample(new Kick(name, length, volume, lowFreq, highFreq, bass));
                }
                else if (parts[0].equals("SNARE")) {
    
                    String name = parts[1];
                    int length = Integer.parseInt(parts[2]);
                    double volume = Double.parseDouble(parts[3]);
                    int lowFreq = Integer.parseInt(parts[4]);
                    int highFreq = Integer.parseInt(parts[5]);
                    int resonance = Integer.parseInt(parts[6]);
                    int punch = Integer.parseInt(parts[7]);

                    daw.CreateSample(new Snare(name, length, volume, lowFreq, highFreq, resonance, punch));
                }
                else if (parts[0].equals("HAT")) {
    
                    String name = parts[1];
                    int length = Integer.parseInt(parts[2]);
                    double volume = Double.parseDouble(parts[3]);
                    int lowFreq = Integer.parseInt(parts[4]);
                    int highFreq = Integer.parseInt(parts[5]);
                    int tail = Integer.parseInt(parts[6]);
                    boolean closed = parts[7].equals("true");
                    
                    daw.CreateSample(new Hat(name, length, volume, lowFreq, highFreq, tail, closed));
                }
            }
            System.out.println("Загружено!");
            
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        
        return daw;
    }
}