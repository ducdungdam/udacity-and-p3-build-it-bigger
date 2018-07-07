package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

  private static MyApi myApiService = null;
  private OnResponseListener onResponseListener;

  public EndpointsAsyncTask(OnResponseListener l) {
    onResponseListener = l;
  }

  @Override
  protected String doInBackground(Void... params) {
    //context = params[0];
    if (myApiService == null) {  // Only do this once
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          // options for running against local devappserver
          // - 10.0.2.2 is localhost's IP address in Android emulator
          // - turn off compression when running against local devappserver
          .setRootUrl("http://10.0.2.2:8080/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                throws IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
      // end options for devappserver

      myApiService = builder.build();
    }

    try {
      return myApiService.getJoke().execute().getJoke();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPostExecute(String result) {
    onResponseListener.onFinished(result);
  }

  public interface OnResponseListener {

    void onFinished(String result);
  }
}