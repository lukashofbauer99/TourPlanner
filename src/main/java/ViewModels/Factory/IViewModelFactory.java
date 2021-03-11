package ViewModels.Factory;

import ViewModels.IViewModel;

public interface IViewModelFactory {

    String getName();
    IViewModel createViewModel();


}
