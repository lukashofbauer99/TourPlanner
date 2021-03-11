package ViewModels.Factory.ConcreteFactories;

import ViewModels.Factory.IViewModelFactory;
import ViewModels.IViewModel;
import ViewModels.MainViewModel;
import lombok.Getter;

public class SideViewModelFactory implements IViewModelFactory {

    @Getter
    String name="Main";

    @Override
    public IViewModel createViewModel() {
        return new MainViewModel();
    }
}
