package test.yifan.cn.myviewdraghelpertest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testBtn= (Button) findViewById(R.id.testBtn);
        Button testBtn1= (Button) findViewById(R.id.testBtn1);
       /* Button dragBtn= (Button) findViewById(R.id.dragBtn);*/

       testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"我是按钮1，我被点击了",Toast.LENGTH_LONG).show();
            }
        });
        testBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"我是按钮2，我被点击了",Toast.LENGTH_LONG).show();
            }
        });
        /* dragBtn.setOnTouchListener(new View.OnTouchListener() {
            int lastX = 0,lastY = 0,mdx,mdy,startX,startY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int ev=event.getAction();

                DisplayMetrics dm=getResources().getDisplayMetrics();

                switch (ev){
                    case MotionEvent.ACTION_DOWN:
                        lastX= (int) event.getRawX();
                        lastY= (int) event.getRawY();
                        startX= (int) event.getRawX();
                        startY= (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx= (int) (event.getRawX()-lastX);
                        int dy= (int) (event.getRawY()-lastY);
                        int l=v.getLeft()+dx;
                        int t=v.getTop()+dy;
                        int r=v.getRight()+dx;
                        int b=v.getBottom()+dy;

                        if(l<0){
                            l=0;
                            r=l+v.getWidth();
                        }

                        if(t<0){
                            t=0;
                            b=t+v.getHeight();
                        }

                        if(r>dm.widthPixels){
                            r=dm.widthPixels;
                            l=r-v.getWidth();
                        }

                        if(b>dm.heightPixels){
                            b=dm.heightPixels;
                            t=b-v.getHeight();
                        }


                        v.layout(l,t,r,b);
                        lastX= (int) event.getRawX();
                        lastY= (int) event.getRawY();
                        v.postInvalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        mdx=Math.abs(lastX-startX);
                        mdx=Math.abs(lastY-startY);
                        break;
                }

               // Toast.makeText(MainActivity.this,mdx+"|"+mdy, Toast.LENGTH_SHORT).show();
                if(mdx>20||mdy>20){
                    return true;
                }
                return false;
            }
        });
        dragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"我点击了按钮",Toast.LENGTH_LONG).show();
            }
        });*/
    }


}
