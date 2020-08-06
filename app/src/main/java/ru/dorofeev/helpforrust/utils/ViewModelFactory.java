package ru.dorofeev.helpforrust.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintFragmentViewModel;
import ru.dorofeev.helpforrust.fragments.furnace.FurnaceFragmentViewModel;
import ru.dorofeev.helpforrust.fragments.info.InfoFragmentViewModel;
import ru.dorofeev.helpforrust.fragments.raidCalculator.RaidCalculatorFragmentViewModel;
import ru.dorofeev.helpforrust.repo.RepositoryImpl;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public ViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BlueprintFragmentViewModel.class)) {
            return (T) new BlueprintFragmentViewModel(application, new RepositoryImpl(application));
        }
        if (modelClass.isAssignableFrom(FurnaceFragmentViewModel.class)) {
            return (T) new FurnaceFragmentViewModel(application);
        }
        if (modelClass.isAssignableFrom(RaidCalculatorFragmentViewModel.class)) {
            return (T) new RaidCalculatorFragmentViewModel(application);
        }
        if (modelClass.isAssignableFrom(InfoFragmentViewModel.class)) {
            return (T) new InfoFragmentViewModel(application);
        }
        return null;
    }
}
