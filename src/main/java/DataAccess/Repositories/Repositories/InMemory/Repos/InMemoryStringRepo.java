package DataAccess.Repositories.Repositories.InMemory.Repos;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InMemoryStringRepo {

    static InMemoryStringRepo inMemoryStringRepo;

    private InMemoryStringRepo() {
    }

    public StringProperty inputText= new SimpleStringProperty("");
    public StringProperty outputText= new SimpleStringProperty("");

    public static InMemoryStringRepo getInstance()
    {
        if(inMemoryStringRepo==null)
            inMemoryStringRepo=new InMemoryStringRepo();

        return inMemoryStringRepo;
    }


}
