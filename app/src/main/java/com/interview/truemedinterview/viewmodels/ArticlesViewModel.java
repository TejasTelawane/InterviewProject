package com.interview.truemedinterview.viewmodels;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.interview.truemedinterview.models.ArticlesModel;
import com.interview.truemedinterview.repository.PostAPIRepo;

public class ArticlesViewModel extends ViewModel {

    private MutableLiveData<ArticlesModel> mutableLiveData;
    private MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public ArticlesViewModel(){
    }

    // Live Data for article list.
    public LiveData<ArticlesModel> getArticles() {
        showProgress.setValue(true);
        if(mutableLiveData==null){
            mutableLiveData = new MutableLiveData<>();
        }

        PostAPIRepo.getInstance().CallArticlesAPI(new PostAPIRepo.OnArticlesAPIResponseListener() {
            @Override
            public void onSuccess(ArticlesModel model) {
                // Added just to show Progressbar
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress.setValue(false);
                        mutableLiveData.setValue(model);
                    }
                }, 2000);
            }

            @Override
            public void onFailure() {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }

    // to show progress dialog while Network calls
    public LiveData<Boolean> showProgress() {
        return showProgress;
    }

}
