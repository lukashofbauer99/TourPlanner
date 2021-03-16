package ViewModels.Factory;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ViewModelFactoryManager {

    List<IViewModelFactory> registeredFactories= new ArrayList<>();

    public ViewModelFactoryManager(){
    }

    public void registerFactories() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        File directoryPath = new File("src/main/java/ViewModels/Factory/ConcreteFactories");
        FilenameFilter textFilefilter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".java");
        };
        String[] filesList = directoryPath.list(textFilefilter);
        assert filesList != null;
        for(String fileName : filesList) {
            Class cardClass = Class.forName("ViewModels.Factory.ConcreteFactories."+fileName.substring(0,fileName.length()-5));
            registeredFactories.add((IViewModelFactory) ((Class<?>) cardClass).getDeclaredConstructor().newInstance());
        }

    }

    //TODO: Scan classes in Namespace instead of Files in directory
    public IViewModelFactory getFactory(String groupName) {
        File directoryPath = new File("src/main/java/ViewModels/Factory/ConcreteFactories");
        FilenameFilter textFilefilter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".java")&&lowercaseName.startsWith(groupName.toLowerCase());
        };
        String[] filesList = directoryPath.list(textFilefilter);
        assert filesList != null;
        for(String fileName : filesList) {
            Class factoryClass;
            String ClassName = fileName.substring(0, fileName.length() - 5);
            try {
                factoryClass = Class.forName("ViewModels.Factory.ConcreteFactories."+ ClassName);
                return (IViewModelFactory) ((Class<?>) factoryClass).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                log.error("No ViewModelFactory for Class ViewModels.Factory.ConcreteFactories."+ ClassName);
            } catch (InstantiationException e) {
                log.error("No fitting Constructor for Class ViewModels.Factory.ConcreteFactories."+ ClassName);
            } catch (InvocationTargetException e) {
                log.error("Constructor of ViewModels.Factory.ConcreteFactories."+ ClassName + "throws an exception that is not handled");
            } catch (IllegalAccessException e) {
                log.error(ClassName + "has no Method called newInstance()");
            } catch (NoSuchMethodException e) {
                log.error(ClassName + "has no Method called getDeclaredConstructor()");
            }
        }
        return null;
    }

}
