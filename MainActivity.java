package com.example.liczby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.liczby.databinding.ActivityMainBinding;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    int option =-1;
    ArrayAdapter<String> adapter;
    ArrayList<String> wyniki;
    ArrayAdapter<String> adapterChoose;
    ArrayList<String> choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        choose = new ArrayList<String>();
        choose.add("wybierz dzialanie");
        choose.add("NWD");
        choose.add("NWW");
        choose.add("Potegowanie");
        adapterChoose = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, choose);

        binding.spinner.setAdapter(adapterChoose);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                oblicz(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "case", Toast.LENGTH_SHORT).show();
            }
        });
        //binding.spinner.setOnItemClickListener((adapterView, view1, i, l) -> oblicz(i));//not working cause not
    }

    private void oblicz(int option)
    {
        wyniki = new ArrayList<>();
        int liczba1;
        int liczba2;
        if(binding.input1.getText().toString().equals("")||binding.input2.getText().toString().equals(""))
        {
            wyniki.add("po prostu coś wpisz");

            wyswietl();
            return;
        }
        else
        {
            liczba1 = Integer.parseInt(binding.input1.getText().toString());
            liczba2 = Integer.parseInt(binding.input2.getText().toString());
        }
        option = binding.spinner.getSelectedItemPosition();
        switch (option)
        {
            case 1:
                wyniki.add(String.valueOf(NWD(liczba1,liczba2)));
                break;
            case 2:
                NWW(liczba1,liczba2);

                break;
            case 3:
                wyniki.add(String.valueOf(Potegowanie(liczba1,liczba2)));

                break;
            default:
                wyniki.add("błąd wyboru");

                break;
        }
        wyswietl();
    }

    private int Potegowanie(int a, int b)
    {
        if(b==0)
        {
            return 1;
        }
        if (b%2==1)
        {
            return a * (int)(Math.pow(Potegowanie(a,(b-1)/2),2));
        }
        return (int)Math.pow(Potegowanie(a,b/2),2);
    }

    private int NWW(int a, int b)
    {
        int wynik = (a*b)/NWD(a,b);
        wyniki.add(String.valueOf(wynik));
        return  wynik;
    }

    private int NWD(int a, int b)
    {
        if (a>b)
        {
            int temp=b;
            b=a;
            a = temp;
        }
        if (b==0)
        {
            wyniki.add(String.valueOf(a));
            return a;
        }
        while(b!=0)
        {
            int reszta = a%b;
            a=b;
            b=reszta;
        }
        return a;
    }

    private void wyswietl()
    {
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, wyniki);

        binding.list.setAdapter(adapter);
    }
}