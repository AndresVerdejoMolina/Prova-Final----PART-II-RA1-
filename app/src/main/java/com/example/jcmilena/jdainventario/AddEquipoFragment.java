package com.example.jcmilena.jdainventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEquipoFragment extends Fragment {

    EditText fabricante,modelo,mac,aula;
    Button add_equipo;


    private OnAddEquipoListener mListener;

    public AddEquipoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_equipo, container, false);

        fabricante = view.findViewById(R.id.fabricanteEditText);
        modelo = view.findViewById(R.id.modeloEditText);
        mac = view.findViewById(R.id.MACEditText);
        aula = view.findViewById(R.id.aulaEditText);
        add_equipo = view.findViewById(R.id.addButton);

        add_equipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EquipoInformatico equipoInformatico = new EquipoInformatico(fabricante.getText().toString(),
                        modelo.getText().toString(), mac.getText().toString(),
                        aula.getText().toString());

                mListener.writeSQLite(equipoInformatico);

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddEquipoListener) {
            mListener = (OnAddEquipoListener) context;
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
    public interface OnAddEquipoListener {
        // TODO: Update argument type and name
        void writeSQLite(EquipoInformatico equipo);
    }
}
