package com.performancetweaker.app.utils;

import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

public class InterstialHelper {

    private static InterstitialAd fanInterstitial;
    private static com.google.android.gms.ads.InterstitialAd adMobInterstitial;
    private static InterstialHelper interstialHelper;

    private static String TAG = Constants.App_Tag;

    private InterstialHelper(Context context) {
        fanInterstitial = new InterstitialAd(context, Constants.FAN_INTERSTITIAL_ID);
        fanInterstitial.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                Log.i(TAG, "FAN Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                Log.i(TAG, "FAN Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Log.e(TAG, "FAN Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.i(TAG, "FAN: Interstitial ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.i(TAG, "FAN Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.i(TAG, "FAN Interstitial ad impression logged!");
            }
        });

        adMobInterstitial = new com.google.android.gms.ads.InterstitialAd(context);
        adMobInterstitial.setAdUnitId(Constants.ADMOB_INTERSTIAL_ID);
        adMobInterstitial.loadAd(new AdRequest.Builder().build());
        adMobInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "Admob: Interstitial ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e(TAG, "Admob Interstitial ad failed to load: " + errorCode);
            }

            @Override
            public void onAdOpened() {
                Log.i(TAG, "Admob Interstitial ad closed");
            }

            @Override
            public void onAdClicked() {
                Log.i(TAG, "Admob Interstitial ad closed");            }

            @Override
            public void onAdLeftApplication() {
                Log.i(TAG, "Admob Interstitial ad closed");
            }

            @Override
            public void onAdClosed() {
                Log.i(TAG, "Admob Interstitial ad closed ");
                adMobInterstitial.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public static InterstialHelper getInstance(Context ctx) {
        if (interstialHelper == null) {
            interstialHelper = new InterstialHelper(ctx);
        }
        return interstialHelper;
    }

    public void loadAd() {
        if (!fanInterstitial.isAdLoaded() || fanInterstitial.isAdInvalidated()) {
            fanInterstitial.loadAd();
        }
        if (!adMobInterstitial.isLoading() || !adMobInterstitial.isLoaded()) {
            adMobInterstitial.loadAd(new AdRequest.Builder().build());
        }
    }

    public void showAd() {
        if (fanInterstitial.isAdLoaded()) {
            fanInterstitial.show();
        } else if (adMobInterstitial.isLoaded()) {
            adMobInterstitial.show();
        }
    }

    public void destroyAd() {
        if (fanInterstitial != null) {
            fanInterstitial.destroy();
        }
    }
}