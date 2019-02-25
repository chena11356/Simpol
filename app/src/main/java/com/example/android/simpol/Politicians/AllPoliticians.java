package com.example.android.simpol.Politicians;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.simpol.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

//GO ALL THE WAY TO THE BOTTOM TO A CLASS CALLED getWebsite

public class AllPoliticians extends Fragment {
    private OnFragmentInteractionListener mListener;
    TextView helloBlankFragment;

    public AllPoliticians() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllPoliticians newInstance() {
        AllPoliticians fragment = new AllPoliticians();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_politicians, container, false);
        helloBlankFragment = view.findViewById(R.id.hello_blank_fragment);
        new getWebsite().execute();
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //do stuff in the doInBackground method
    private class getWebsite extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            StringBuilder builder = new StringBuilder();

            try{
                Document doc = Jsoup.connect("https://www.congress.gov/search?q=%7B%22source%22%3A%22members%22%2C%22congress%22%3A%22116%22%7D").get();
                builder.append("Connected successfully to doc");
                helloBlankFragment.setText(builder.toString());
            } catch (IOException error){
                builder.append("Error: ").append(error.getMessage()).append("\n");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }
}
