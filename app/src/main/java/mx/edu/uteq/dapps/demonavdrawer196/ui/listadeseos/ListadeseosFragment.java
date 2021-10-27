package mx.edu.uteq.dapps.demonavdrawer196.ui.listadeseos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.edu.uteq.dapps.demonavdrawer196.R;
import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentListadeseosBinding;

public class ListadeseosFragment extends Fragment {

    private FragmentListadeseosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListadeseosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}