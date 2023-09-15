package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import softocardmaker.softoappa.softobook.com.databinding.ActivityPrivacypolicyBinding;

public class privacypolicy extends AppCompatActivity {

    ActivityPrivacypolicyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPrivacypolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.webview.loadUrl("file:///android_asset/privacypolicy.html");
    }
}