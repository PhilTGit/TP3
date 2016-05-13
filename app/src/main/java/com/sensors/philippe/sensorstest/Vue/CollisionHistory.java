package com.sensors.philippe.sensorstest.Vue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sensors.philippe.sensorstest.Modele.Colision;
import com.sensors.philippe.sensorstest.R;

import static com.sensors.philippe.sensorstest.R.layout.list_view;

public class CollisionHistory extends AppCompatActivity {

    private static final String INTENT_KEY ="COLLISIONS" ;
    private Colision[] colisionsList;
//    FragmentManager fragmentManager = getFragmentManager();
//    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             colisionsList = (Colision[]) extras.get(INTENT_KEY);
        }
        String[] colisionsListFormated = new String[colisionsList.length];
        for(int i =0;i<colisionsList.length;i++){
            colisionsListFormated[i] = colisionsList[i].toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,list_view,colisionsListFormated);
        ListView listViewToHoldCollision = (ListView) findViewById(R.id.collision_list);
        if (listViewToHoldCollision != null) {
            listViewToHoldCollision.setAdapter(adapter);
        }

//        setContentView(R.layout.activity_collision_history);
//        LinearLayout layout = (LinearLayout)findViewById(R.id.linear);
//        if (colisionsList != null) {
//            for (int i=0;i<colisionsList.length;i++){
//                FrameLayout frame = new FrameLayout(this);
//                frame.setId(i);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                    frame.setLayoutMode(FrameLayout.LayoutParams.WRAP_CONTENT);
//                }
//                FragmentCollision fragmentCollision = new FragmentCollision();
//                fragmentCollision.setDefaultArguements(colisionsList[i].getColisionOwner(),
//                        colisionsList[i].getColisionDate(), (float) colisionsList[i].getColisionStrength(),
//                        colisionsList[i].isCallDone());
//                if (layout != null) {
//                    layout.addView(frame);
//                }
//                fragmentTransaction.add(i,fragmentCollision,"");
//            }
//        }
    }
}
