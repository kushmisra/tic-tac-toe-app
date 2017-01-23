package panda.tictactoe;

import android.icu.util.TimeUnit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    boolean active=true;
    int activeplayer = 0;
    int[] gamesituation={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void click(View view){

        ImageView iv=(ImageView) view;
        Log.i("hello", "m here before index ");
        int index=Integer.parseInt(iv.getTag().toString());
        Log.i("tag", iv.getTag().toString());
        if(gamesituation[index]==2 && active) {


            gamesituation[index]=activeplayer;
            iv.setTranslationX(1000f);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (activeplayer==0) {
                iv.setImageResource(R.drawable.android);

            } else {
                iv.setImageResource(R.drawable.linux);
            }
            iv.animate().translationXBy(-1000f).setDuration(1000);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            iv.animate().rotationBy(360).setDuration(1000);

            activeplayer= activeplayer^1;

            for(int[] temp :winningpositions){

                if (gamesituation[temp[0]]==gamesituation[temp[1]] && gamesituation[temp[1]]==gamesituation[temp[2]] && gamesituation[temp[0]]!=2 ){

                    String message;
                    if(gamesituation[temp[0]]==0){
                        message="android";
                    }else{
                        message="linux";
                    }

                    Toast.makeText(MainActivity.this, message+"won", Toast.LENGTH_LONG).show();

                    TextView tv=(TextView)findViewById(R.id.textView);
                    tv.setText(message+"won");
                    active=false;
                    LinearLayout ll=(LinearLayout)findViewById(R.id.linear);
                    ll.setVisibility(View.VISIBLE);

                }

            }


        }else{
            int done=1;
            for(int i=0;i<9;i++){
                if(gamesituation[i]==2){
                    done=0;
                }
            }

            if(done==1){
                active=false;
                Toast.makeText(MainActivity.this, "draw", Toast.LENGTH_LONG).show();
                TextView tv=(TextView)findViewById(R.id.textView);
                tv.setText("draw");
                LinearLayout ll=(LinearLayout)findViewById(R.id.linear);
                ll.setVisibility(View.VISIBLE);
            }

        }
    }


    public void reset(View view){
        LinearLayout ll=(LinearLayout)findViewById(R.id.linear);
        ll.setVisibility(View.INVISIBLE);
        activeplayer=0;
        Log.i("hello", "reset: ");
        for(int i=0;i<9;i++){
            gamesituation[i]=2;

        }
        GridLayout gl=(GridLayout)findViewById(R.id.grid);

        for(int i=0;i<gl.getChildCount();i++){
            ImageView iv=(ImageView)gl.getChildAt(i);
            iv.setImageDrawable(null);
        }
        active=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
