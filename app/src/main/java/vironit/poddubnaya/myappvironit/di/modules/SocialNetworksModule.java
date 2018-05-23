package vironit.poddubnaya.myappvironit.di.modules;

import android.content.Context;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SocialNetworksModule {

    @Provides
    @Singleton
    GoogleSignInClient getGoogleSignInClient(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        return GoogleSignIn.getClient(context, gso);
    }

    @Provides
    @Singleton
    TwitterAuthClient getTwitterAuthClient() {
        return new TwitterAuthClient();
    }


    @Provides
    @Singleton
    CallbackManager getCallbackManager() {
        return CallbackManager.Factory.create();
    }

}
