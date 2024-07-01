package com.example.landingonboardcard;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
MotionLayout motionLayout;
TextView txtPageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.motionLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        motionLayout = findViewById(R.id.motionLayout);
        txtPageText = findViewById(R.id.txtPageText);
       /* ArrayList<MotionScene.Transition> transitions =  motionLayout.getDefinedTransitions();
        int[] list = motionLayout.getConstraintSetIds();
        for(int i=0;i<list.length;i++){
            ConstraintSet thatConstrain = motionLayout.getConstraintSet(list[i]);
            if(thatConstrain !=null){
                ConstraintSet.Constraint constraint =  thatConstrain.getConstraint(R.id.point);
                if(constraint !=null)
                    constraint.layout.topToTop = (i+1)*200;
            }
        }*/


        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
                System.out.println("onTransitionStarted");
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                //System.out.println("onTransitionChange");
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                System.out.println("onTransitionCompleted : ");
                ArrayList<MotionScene.Transition> transitions =  motionLayout.getDefinedTransitions();
                for(int i = 0;i<transitions.size();i++){
                    if(transitions.get(i).getEndConstraintSetId() == currentId){
                        if(i == 0){
                            txtPageText.setText("Sayfa 1");
                            break;
                        } else if (i ==1) {
                            txtPageText.setText("Sayfa 2");
                            break;
                        }else if(i==2){
                            txtPageText.setText("Sayfa 3");
                            break;
                        }else{
                            txtPageText.setText("Sayfa 0");
                            break;
                        }
                    }else {
                        txtPageText.setText("Sayfa 0");

                    }
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
                System.out.println("onTransitionTrigger");
            }
        });
    }
}