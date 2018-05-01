package com.twentyfourkapps.geograph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;

import com.twentyfourkapps.geograph.billing.IabHelper;
import com.twentyfourkapps.geograph.billing.IabResult;
import com.twentyfourkapps.geograph.billing.Inventory;
import com.twentyfourkapps.geograph.billing.Purchase;

import java.util.HashSet;
import java.util.Set;


public class MyBilling {
    // Debug tag, for logging
    static final String TAG = "GatePuzzle";

    static final String SKU_REMOVE_ADS = "remove.ads_1";
    private Set<MyEventClassListener> listeners = new HashSet<MyEventClassListener>();
    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 10111;

    Activity activity;

    // The helper object
    IabHelper mHelper;

    String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr18++CYw/tmyi3C34emM/gfDWGkcNnG3SzuURbvUSbOHz5svmtvw0MnBNJnuEPcpjsxYe/fAdi3r2r/vjMGnaZ9xfyKPgxos17ooJSnqPGz2q2huUeQdBO5ENE+RkGpvF5Jziq1FSen3B9WVHSgx6amRvKxXdw2+336v+eC2JuS8GWkhW2SWP9J5Qtza7i3bUwIogRSZEVxeXPQ7qVFUx8MPDTSwQW/n+CMnKPHkSUMbpcOkls8xg/p57mTKtpcek71GMsASqjGHPSmaDao8q+YsOywqP12kMFNmnZSVLk1pSs3SyzO7J2F5PcvEapAjAPjHMZG1WBBq7lX6y1zaFwIDAQAB";
    int isAdsDisabled = 0;
    String payload = "ANY_PAYLOAD_STRING";

    public MyBilling(Activity launcher) {
        this.activity = launcher;
    }

    public void onCreate() {

        // Create the helper, passing it our context and the public key to
        // verify signatures with
        Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(activity, base64EncodedPublicKey);

        Constants.isAdsDisabled = 99; //Set undefined state

        // enable debug logging (for a production application, you should set
        // this to false).
        mHelper.enableDebugLogging(false);

        // Start setup. This is asynchronous and the specified listener
        // will be called once setup completes.
        Log.d(TAG, "Starting setup.");
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    // complain("Problem setting up in-app billing: " + result);
                    return;
                }

                // Have we been disposed off in the meantime? If so, quit.
                if (mHelper == null)
                    return;

                // IAB is fully set up. Now, let's get an inventory of stuff we
                // own.
                Log.d(TAG, "Setup successful. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void addListener(MyEventClassListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MyEventClassListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (MyEventClassListener listener : listeners) {
            listener.somethingHappened();
        }
    }



/*    // Listener that's called when we finish consume the items
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase, IabResult result) {
                    if (result.isSuccess()) {
                        // provision the in-app purchase to the user
                        // (for example, credit 50 gold coins to player's character)
                    }
                    else {
                        // handle error
                    }
                }
            };*/

    // Listener that's called when we finish querying the items and
    // subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null)
                return;

            // Is it a failure?
            if (result.isFailure()) {
                // complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");

            /*
             * Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().
             */

            // Do we have the premium upgrade?
            Purchase removeAdsPurchase = inventory.getPurchase(SKU_REMOVE_ADS);
            //Constants.isAdsDisabled = (removeAdsPurchase != null && verifyDeveloperPayload(removeAdsPurchase));
            if(removeAdsPurchase != null && verifyDeveloperPayload(removeAdsPurchase)){
                Constants.isAdsDisabled = 1;
            }else{
                Constants.isAdsDisabled =0;
            }

/*            try {
                mHelper.consumeAsync(inventory.getPurchase(SKU_REMOVE_ADS),
                        mConsumeFinishedListener);
            } catch (Exception e) {
                e.printStackTrace();
            }*/



            // setWaitScreen(false);
            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    // User clicked the "Remove Ads" button.
    public void purchaseRemoveAds() {

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                try {
                    mHelper.launchPurchaseFlow(activity, SKU_REMOVE_ADS,
                            RC_REQUEST, mPurchaseFinishedListener, payload);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + ","
                + data);
        if (mHelper == null)
            return true;

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            return false;
        } else {

            Log.d(TAG, "onActivityResult handled by IABUtil.");

            return true;
        }

    }

    /** Verifies the developer payload of a purchase. */
    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        /*
         * TODO: verify that the developer payload of the purchase is correct.
         * It will be the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase
         * and verifying it here might seem like a good approach, but this will
         * fail in the case where the user purchases an item on one device and
         * then uses your app on a different device, because on the other device
         * you will not have access to the random string you originally
         * generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different
         * between them, so that one user's purchase can't be replayed to
         * another user.
         *
         * 2. The payload must be such that you can verify it even when the app
         * wasn't the one who initiated the purchase flow (so that items
         * purchased by the user on one device work on other devices owned by
         * the user).
         *
         * Using your own server to store and verify developer payloads across
         * app installations is recommended.
         */
        return true;
    }

    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: "
                    + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null)
                return;

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");
                return;
            }

            Log.d(TAG, "Purchase successful.");

            if (purchase.getSku().equals(SKU_REMOVE_ADS)) {
                // bought the premium upgrade!
                removeAds();

            }
        }
    };

    private void removeAds() {
        isAdsDisabled = 1;
    }

    // We're being destroyed. It's important to dispose of the helper here!

    public void onDestroy() {

        // very important:
        Log.d(TAG, "Destroying helper.");
        if (mHelper != null) {
            try {
                mHelper.dispose();
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }
            mHelper = null;
        }
    }

    void complain(String message) {
        Log.e(TAG, "**** TrivialDrive Error: " + message);
        alert("Error: " + message);
    }

    void alert(final String message) {
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                AlertDialog.Builder bld = new AlertDialog.Builder(activity);
                bld.setMessage(message);
                bld.setNeutralButton("OK", null);
                Log.d(TAG, "Showing alert dialog: " + message);
                bld.create().show();
            }
        });
    }

}