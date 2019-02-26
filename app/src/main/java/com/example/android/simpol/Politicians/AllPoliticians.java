package com.example.android.simpol.Politicians;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.simpol.R;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

//GO ALL THE WAY TO THE BOTTOM TO A CLASS CALLED getWebsite

public class AllPoliticians extends Fragment {
    private OnFragmentInteractionListener mListener;
    TextView helloBlankFragment;
    FirebaseFirestore db;
    ArrayList<Politician> politicianArrayList;

    public AllPoliticians() {
        // Required empty public constructor
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
        politicianArrayList = new ArrayList<Politician>();
        db = FirebaseFirestore.getInstance();
        initializePoliticians();
        return view;
    }

    public void initializePoliticians(){
        InputStream is = getResources().openRawResource(R.raw.search_results_feb_25);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                //set splitter
                String[] tokens = line.split(",");

                //read the data
                Politician politician = new Politician();
                politician.setName(tokens[0]);
                politician.setCongressURL(tokens[1]);
                politician.setState(tokens[2]);
                politician.setDistrict(Integer.parseInt(tokens[3]));
                politician.setParty(tokens[4]);
                politician.setTerms(tokens[5]);
                politicianArrayList.add(politician);

                Log.d("lllll" ,"Just Created " +politician);

            }
        } catch (IOException e1) {
            Log.e("lllll", "Error" + line, e1);
            e1.printStackTrace();
        }
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
        StringBuilder builder = new StringBuilder();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Document doc = Jsoup.connect("https://www.congress.gov/search?q=%7B%22source%22%3A%22members%22%2C%22congress%22%3A%22116%22%7D").get();
                builder.append("Connected successfully to doc");
            } catch (IOException error){
                builder.append("Error: ").append(error.getMessage()).append("\n");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            helloBlankFragment.setText(builder.toString());
        }
    }

    private class Politician {
        private String name;
        private String congressURL;
        private String state;
        private int district;
        private String party;
        private String terms;
        public String getName(){
            return name;
        }
        public void setName(String nameNew){
            name = nameNew;
        }
        public String getCongressURL(){
            return congressURL;
        }
        public void setCongressURL(String URLNew){
            congressURL = URLNew;
        }
        public String getState(){
            return state;
        }
        public void setState(String stateNew){
            state = stateNew;
        }
        public int getDistrict(){
            return district;
        }
        public void setDistrict(int districtNew){
            district = districtNew;
        }
        public String getParty(){
            return party;
        }
        public void setParty(String partyNew){
            party = partyNew;
        }
        public String getTerms(){
            return terms;
        }
        public void setTerms(String termsNew){
            terms = termsNew;
        }
    }

    /*
    //do stuff in the doInBackground method
    private class databaseTest extends AsyncTask<Void, Void, Void>{
        StringBuilder builder = new StringBuilder();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Document doc = Jsoup.connect("https://www.congress.gov/search?q=%7B%22source%22%3A%22members%22%2C%22congress%22%3A%22116%22%7D").get();
                builder.append("Connected successfully to doc");
            } catch (IOException error){
                builder.append("Error: ").append(error.getMessage()).append("\n");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            helloBlankFragment.setText(builder.toString());
        }
    }
    */
}
