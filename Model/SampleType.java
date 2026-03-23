package Model;
public enum SampleType {
    KICK("Kick", "Ударный барабан"),
    SNARE("Snare", "Малый барабан"),
    HAT("Hi-Hat", "Тарелки");
    
    private final String displayName;
    private final String description;
    
    SampleType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
}
