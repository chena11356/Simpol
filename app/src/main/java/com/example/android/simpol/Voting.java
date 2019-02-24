package com.example.android.simpol;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Voting extends Fragment {
    private TextView daysLeft;
    private TextView nextElection;
    private OnFragmentInteractionListener mListener;

    public Voting() {
        // Required empty public constructor
    }

    public static Voting newInstance() {
        Voting fragment = new Voting();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voting, container, false);
        daysLeft = view.findViewById(R.id.daysLeft);
        nextElection = view.findViewById(R.id.nextElection);
        getNextElection();
        return view;
    }

    private void getNextElection(){
        String[] labels = new String[] {" YEAR", " MONTH", " DAY"};
        int[] today = getDate();
        int[] election = new int[] {11, getDay(today[0]), today[0]};
        Period diff = Period.between(LocalDate.of(today[0], today[1], today[2]),
                LocalDate.of(election[0], election[1], election[2]));
        int[] left = new int[] {diff.getYears(), diff.getMonths(), diff.getDays()};

        String daysLeftText = "";
        for(int i = 0; i < labels.length; i++){
            daysLeftText += " " + left[i] + labels[i];
            if(left[i]>1){
                labels[i] += "S";
            }
        }

        daysLeft.setText(daysLeftText);
        nextElection.setText(election[1] + "/" + election[2] + "/" + election[0]);
    }

    private int getDay(int year) {
        int cYear = year + year%2;
        try{
            Document cDoc = Jsoup.connect("http://en.wikipedia.org/wiki/" + cYear + "_United_States_elections").get();
            String cText = cDoc.select("p:contains(Tuesday)").text();
            int cIndex = cText.indexOf("Tuesday, November") + 9;
            int cIndex2 = cIndex;
            if(cText.charAt(cIndex+1)!=','){
                cIndex2 = cIndex + 1;
            }
            return Integer.parseInt(cText.substring(cIndex, cIndex2));
        } catch (IOException error){
            return -1;
        }
    }

    private int[] getDate(){
        int[] date = new int[3];
        Calendar calender = Calendar.getInstance();
        date[0] = calender.get(Calendar.YEAR);
        date[1] = calender.get(Calendar.MONTH);
        date[2] = calender.get(Calendar.DATE);

        return date;
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
}
