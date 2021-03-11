package ViewModels.Factory.ConcreteFactories;

import ViewModels.Factory.IViewModelFactory;
import ViewModels.IViewModel;
import ViewModels.MainViewModel;
import lombok.Getter;

public class MainViewModelFactory implements IViewModelFactory {

    @Getter
    String name="Side";

    @Override
    public IViewModel createViewModel() {
        return new MainViewModel();
    }
}
