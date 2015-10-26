package com.example.fuxk.jisuanqi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class jisuanqi extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jisuanqi);
        TextView t1=(TextView)this.findViewById(R.id.textView);
        TextView t2=(TextView)this.findViewById(R.id.textView2);
        TextView t3=(TextView)this.findViewById(R.id.textView3);
        TextView t4=(TextView)this.findViewById(R.id.textView4);
        Button btn= (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jisuanqi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        EditText tt=(EditText) findViewById(R.id.editText);
        CheckBox man=(CheckBox) findViewById(R.id.checkBox);
        CheckBox woman=(CheckBox) findViewById(R.id.checkBox2);
        if(!tt.getText().toString().trim().equals("")){
            if(man.isChecked()||woman.isChecked()){
                double weight=0;
                weight=Double.parseDouble(tt.getText().toString());
                StringBuffer sb=new StringBuffer();
                sb.append("------------评估结果------------ \n");
                if(man.isChecked()){
                    sb.append("男性标准身高：");
                    double result=evaluateHeight(weight,"男");
                    sb.append((int)result+"(厘米) \n");
                }
                if(woman.isChecked()){
                    sb.append("女性标准身高：");
                    double result=evaluateHeight(weight,"女");
                    sb.append((int)result+"(厘米)");
                }
                String result=sb.toString();
                TextView resultTextView=(TextView)findViewById(R.id.result);
                resultTextView.setText(result);

            }else{
                showMessage("请选择性别！");
            }
        }else{
            showMessage("请输入体重！");
        }
    }

    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    private void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

}
