package com.ducdungdam.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
  public final static String EXTRA_JOKE = "extra_joke";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_joke);

    Intent intent = getIntent();
    String joke= intent.getStringExtra(EXTRA_JOKE);

    ((TextView)findViewById(R.id.tv_joke)).setText(joke);
  }
}
