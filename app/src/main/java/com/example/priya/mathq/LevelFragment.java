package com.example.priya.mathq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LevelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static  ImageView imageView;
    static  Button o1,o2,o3,o4;

    // TODO: Rename and change types of parameters
    private Question mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LevelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LevelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LevelFragment newInstance(Question param1, String param2) {
        LevelFragment fragment = new LevelFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Question) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myFragmentView = inflater.inflate(R.layout.fragment_level, container, false);

        ImageView maps = (ImageView) myFragmentView.findViewById(R.id.imageView3);


        Bitmap bitmap = decodeFromBase64ToBitmap(mParam1.getqImgStr());
        maps.setImageBitmap(bitmap);

        final Button o1 = (Button) myFragmentView.findViewById(R.id.op1);
        final Button o2 = (Button) myFragmentView.findViewById(R.id.op2);
        final Button o3 = (Button) myFragmentView.findViewById(R.id.op3);
        final Button o4 = (Button) myFragmentView.findViewById(R.id.op4);

        o1.setText(mParam1.getO1());
        o2.setText(mParam1.getO2());
        o3.setText(mParam1.getO3());
        o4.setText(mParam1.getO4());

        o1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((QuestionActivity)getActivity()).nextQuestion("o1");

            }
        });

        o2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((QuestionActivity)getActivity()).nextQuestion("o2");

            }
        });
        o3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((QuestionActivity)getActivity()).nextQuestion("o3");

            }
        });
        o4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((QuestionActivity)getActivity()).nextQuestion("o4");

            }
        });



        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private Bitmap decodeFromBase64ToBitmap(String encodedImage) {

        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);

        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;

    }
}
