package com.example.utstubes_if2_10118078_stevendanesswaralay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fab_add;
    RecycleAdapter recycleAdapter;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<pendapatan> listpendapatan;
    RecyclerView rv_view;
    LoadingProgress loadingProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        fab_add = (FloatingActionButton)view.findViewById(R.id.fab_add);
        rv_view = (RecyclerView) view.findViewById(R.id.rv_view);

        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getContext());
        rv_view.setLayoutManager(mLayout);
        rv_view.setItemAnimator(new DefaultItemAnimator());


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputFragment inputFragment = new InputFragment("","","","Tambah");
                inputFragment.show(getFragmentManager(),"form");
            }
        });
        showData();
        return view;
    }
    private void  showData(){

        database.child("test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listpendapatan = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    pendapatan pendapat = item.getValue(pendapatan.class);
                    pendapat.setKey(item.getKey());
                    listpendapatan.add(pendapat);
                }
                recycleAdapter = new RecycleAdapter(listpendapatan,getActivity());
                rv_view.setAdapter(recycleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}
