package ru.job4j;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TicTacViewModel extends AndroidViewModel {

    private Bot bot;
    private Logic logic;
    private MutableLiveData<Logic> logicLiveData = new MutableLiveData<>();

    public TicTacViewModel(@NonNull Application application) {
        super(application);
        bot = Bot.getInstance();
        logic = Logic.getInstance(bot);
        initLogicLiveData();
    }

    public void initLogicLiveData(){
        logicLiveData.postValue(logic);
    }

    public LiveData<Logic> getLogic(){
        return logicLiveData;
    }

    public void updateField(int index){
        logic.updateField(index);
        logicLiveData.postValue(logic);
    }

    public void switchBot(){
        logic.switchBot();
        logicLiveData.postValue(logic);
    }
}
