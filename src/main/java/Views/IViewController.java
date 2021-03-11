package Views;

import ViewModels.IViewModel;

public interface IViewController {

    IViewModel getViewModel();
    void setViewModel(IViewModel viewModel);
}
