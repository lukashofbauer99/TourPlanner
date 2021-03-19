package ViewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class MainViewModel implements IViewModel {
    private StringProperty inputText= new SimpleStringProperty();
    private StringProperty outputText= new SimpleStringProperty();

    public void updateOutputText()
    {
        outputText.setValue( "you searched for "+ inputText.getValue());
    }

    public void clearFields()
    {
        inputText.setValue("");
        outputText.setValue("");
    }

}
