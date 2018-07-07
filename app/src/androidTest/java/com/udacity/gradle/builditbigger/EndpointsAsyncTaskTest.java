package com.udacity.gradle.builditbigger;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

import android.support.test.runner.AndroidJUnit4;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.OnResponseListener;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Checks Endpoints async task retrieves no empty string
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

  @Test
  public void checkResponseIsNotEmpty() {
    final CountDownLatch signal = new CountDownLatch(1);
    try {
      new EndpointsAsyncTask(new OnResponseListener() {
        @Override
        public void onFinished(String result) {
          assertNotNull(result);
          assertThat(result, not(isEmptyString()));
          signal.countDown();
        }
      }).execute();

      signal.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
