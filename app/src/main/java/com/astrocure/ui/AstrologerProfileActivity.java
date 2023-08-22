package com.astrocure.ui;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.R;
import com.astrocure.adapters.ProfileReviewAdapter;
import com.astrocure.adapters.SimilarAstrologerListAdapter;
import com.astrocure.databinding.ActivityAstrologerProfileBinding;

public class AstrologerProfileActivity extends AppCompatActivity {
    ActivityAstrologerProfileBinding binding;
    public static final int MAX_LINES = 7;
    public static final String TWO_SPACES = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAstrologerProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

//        String myReallyLongText = "Bacon ipsum dolor amet porchetta venison ham fatback alcatra tri-tip, turducken strip steak sausage rump burgdoggen pork loin. Spare ribs filet mignon salami, strip steak ball tip shank frankfurter corned beef venison. Pig pork belly pork chop andouille. Porchetta pork belly ground round, filet mignon bresaola chuck swine shoulder leberkas jerky boudin. Landjaeger pork chop corned beef, tri-tip brisket rump pastrami flank.";

        binding.about.post(() -> {
            // Past the maximum number of lines we want to display.
            if (binding.about.getLineCount() > MAX_LINES) {
                int lastCharShown = binding.about.getLayout().getLineVisibleEnd(MAX_LINES - 1);

                binding.about.setMaxLines(MAX_LINES);

                String moreString = getApplicationContext().getString(R.string.more);
                String suffix = TWO_SPACES + moreString;

                // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
                String actionDisplayText = binding.about.getText().toString().substring(0, lastCharShown - suffix.length() - 3) + "..." + suffix;

                SpannableString truncatedSpannableString = new SpannableString(actionDisplayText);
                int startIndex = actionDisplayText.indexOf(moreString);
                truncatedSpannableString.setSpan(new ForegroundColorSpan(getApplicationContext().getColor(android.R.color.holo_blue_bright)), startIndex, startIndex + moreString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.about.setText(truncatedSpannableString);
            }
        });

        SimilarAstrologerListAdapter adapter = new SimilarAstrologerListAdapter(getApplicationContext());
        binding.astrologerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.astrologerList.setAdapter(adapter);

        ProfileReviewAdapter reviewAdapter = new ProfileReviewAdapter(getApplicationContext());
        binding.reviewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.reviewList.setAdapter(reviewAdapter);
    }
}