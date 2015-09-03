package org.peace.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.peace.demo.R;

/**
 * Created by peacepassion on 15/8/23.
 */
public class SampleFragment extends Fragment {

    public static final String KEY_INDEX = "index";

    @InjectView(R.id.tv)
    TextView textView;

    public static SampleFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX, i);
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rv = inflater.inflate(R.layout.sample_frag, container, false);
        ButterKnife.inject(this, rv);
        textView.setText("SampleFragment#" + getArguments().getInt(KEY_INDEX));
        return rv;
    }

}
