package com.example.jcmilena.jdainventario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class InventarioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView miRecycler;
    List<EquipoInformatico> equipos = new ArrayList<>();
    EquiposAdapter miAdapter;


    private OnInventarioFragmentListener mListener;

    public InventarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InventarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventarioFragment newInstance(String param1, String param2) {
        InventarioFragment fragment = new InventarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        equipos = mListener.getEquiposList();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventario, container, false);

        miRecycler = view.findViewById(R.id.inventarioRecycler);
        miRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        miRecycler.addItemDecoration(divider);
        miAdapter = new EquiposAdapter(equipos);
        miRecycler.setAdapter(miAdapter);

        return view;
    }


    public class EquipoViewHolder extends RecyclerView.ViewHolder{

        TextView fabricante, modelo, mac, aula;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);

            fabricante = itemView.findViewById(R.id.inventFabTextView);
            modelo = itemView.findViewById(R.id.invModeloTextView);
            mac = itemView.findViewById(R.id.invMACTextView);
            aula = itemView.findViewById(R.id.invAulaTextView);
        }
    }

    public class EquiposAdapter extends RecyclerView.Adapter<EquipoViewHolder>{

        List<EquipoInformatico> equiposInformaticos;

        public EquiposAdapter(List<EquipoInformatico> equiposInformaticos) {
            this.equiposInformaticos = equiposInformaticos;
        }

        @NonNull
        @Override
        public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemview = getLayoutInflater().inflate(R.layout.equipo_viewholder, viewGroup, false);
            return new EquipoViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull EquipoViewHolder equipoViewHolder, int i) {

            equipoViewHolder.fabricante.setText("Fabricante: "+equiposInformaticos.get(0).getFabricante());
            equipoViewHolder.modelo.setText("Modelo: "+equiposInformaticos.get(0).getModelo());
            equipoViewHolder.mac.setText("MAC: "+equiposInformaticos.get(0).getMAC());
            equipoViewHolder.aula.setText("Aula: "+equiposInformaticos.get(0).getAula());

        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInventarioFragmentListener) {
            mListener = (OnInventarioFragmentListener) context;
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
    public interface OnInventarioFragmentListener {
        // TODO: Update argument type and name
        List<EquipoInformatico> getEquiposList();
    }
}
