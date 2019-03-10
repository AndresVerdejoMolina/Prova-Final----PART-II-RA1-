package com.example.jcmilena.jdainventario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class SearchFragment extends Fragment {

    EditText fabricante, modelo, mac, aula;
    ImageButton fabricante_b , modelo_b, mac_b, aula_b;

    private OnSearchFragmentListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        fabricante = view.findViewById(R.id.searchFabricanteEditText);
        modelo = view.findViewById(R.id.searchModeloEditText);
        mac = view.findViewById(R.id.searchMACEditText);
        aula = view.findViewById(R.id.searchAulaEditText);

        fabricante_b = view.findViewById(R.id.fabricanteImageButton);
        modelo_b = view.findViewById(R.id.modeloImageButton);
        mac_b = view.findViewById(R.id.MACImageButton);
        aula_b = view.findViewById(R.id.aulaImageButton);

        fabricante_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.searchSQLite("fabricante", fabricante.getText().toString());
            }
        });

        modelo_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.searchSQLite("modelo", modelo.getText().toString());
            }
        });

        mac_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.searchSQLite("mac", mac.getText().toString());
            }
        });

        aula_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.searchSQLite("aula",aula.getText().toString());
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchFragmentListener) {
            mListener = (OnSearchFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSearchFragmentListener {
        // TODO: Update argument type and name
        void searchSQLite(String columna, String valor);
    }
}
