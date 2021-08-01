package com.example.utstubes_if2_10118078_stevendanesswaralay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {
    TextView txt_nama,txt_email,txt_phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        TextView txt_nama = (TextView)view.findViewById(R.id.txt_nama);
        TextView txt_email = (TextView)view.findViewById(R.id.txt_email);
        TextView txt_phone = (TextView)view.findViewById(R.id.txt_phone);

        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                txt_nama.setText(value.getString("fName"));
                txt_email.setText(value.getString("femail"));
                txt_phone.setText(value.getString("ftelephone"));
            }
        });

        return view;
    }



    public void keluar(View view) {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), login.class);
        startActivity(intent);
    }

}
