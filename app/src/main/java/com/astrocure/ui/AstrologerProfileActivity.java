package com.astrocure.ui;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

        String myReallyLongText = "If you want the 'View More' part of your Text to be clickable (but not the entire TextView), utilize ClickableSpan as outlined here in this StackOverflow for How to set the part of the text view is clickable. I would caution you to be aware of the UX implications of this, as normally you truncate your text because you have a lot of it and you don't have much space, so your font size is already probably small.";

        aboutTextPost(myReallyLongText);
        binding.about.setOnClickListener(v -> {
            if (binding.about.getLineCount() == MAX_LINES + 1) {
                binding.about.setText(myReallyLongText);
            } else if (binding.about.getLineCount() > MAX_LINES) {
                aboutTextPost(myReallyLongText);
            }
        });

        SimilarAstrologerListAdapter adapter = new SimilarAstrologerListAdapter(getApplicationContext());
        binding.astrologerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.astrologerList.setAdapter(adapter);

        ProfileReviewAdapter reviewAdapter = new ProfileReviewAdapter(getApplicationContext());
        binding.reviewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.reviewList.setAdapter(reviewAdapter);

//        binding.chat.setText("Chat\n" + Html.fromHtml(setTextSize("Currently Offline", (int) binding.chat.getTextSize() - 4)));
    }

    private void aboutTextPost(String text) {

        binding.about.post(() -> {
            if (binding.about.getLineCount() > MAX_LINES) {
                int lastCharShown = binding.about.getLayout().getLineVisibleEnd(MAX_LINES - 1);

//                binding.about.setMaxLines(MAX_LINES);

                String moreString = getApplicationContext().getString(R.string.more);
                String suffix = TWO_SPACES + moreString;

                // 3 is a "magic number" but it's just basically the length of the ellipsis we're going to insert
                String actionDisplayText = text.substring(0, lastCharShown - suffix.length() - 3) + "..." + suffix;

                SpannableString truncatedSpannableString = new SpannableString(actionDisplayText);
                int startIndex = actionDisplayText.indexOf(moreString);
                truncatedSpannableString.setSpan(new ForegroundColorSpan(getApplicationContext().getColor(android.R.color.holo_blue_bright)), startIndex, startIndex + moreString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.about.setText(truncatedSpannableString);

                binding.call.text.setText("Call");
                binding.call.icon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_profile_calling));


            }
        });
    }
}