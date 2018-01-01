package com.example.bosscopeng.eat_myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.ViewGroup;
import android.widget.AdapterView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class breakfast_MainActivity extends AppCompatActivity {
    EditText editText_breakfast_name,editText_breakfast_description,editText_breakfast_price;
    Button button_breakfast_add,button_breakfast_search,button_breakfast_edit,button_breakfast_delet,button_back;
    ListView ListView_breakfast;
    SQLiteDatabase breakfast_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast__main);



        editText_breakfast_name=(EditText)findViewById(R.id.editText_breakfast_name);
        editText_breakfast_description=(EditText)findViewById(R.id.editText_breakfast_description);
        editText_breakfast_price=(EditText)findViewById(R.id.editText_breakfast_price);

        button_breakfast_add=(Button)findViewById(R.id.button_breakfast_add);
        button_breakfast_edit=(Button)findViewById(R.id. button_breakfast_edit);
        button_breakfast_search=(Button)findViewById(R.id. button_breakfast_search);
        button_breakfast_delet=(Button)findViewById(R.id.button_breakfast_delet);
        button_back=(Button)findViewById(R.id.button_back);


        eat_db breakfastdbhelper =new  eat_db(this);
        breakfast_db =breakfastdbhelper.getWritableDatabase();

        button_breakfast_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newBreakfast();
            }
        });
        button_breakfast_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                renewBreakfast();
            }
        });
        button_breakfast_delet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deletBreakfast();
            }
        });
        button_breakfast_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                queryBreakfast();

            }
        });


        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });







    }

    class BreakfastData{
        String breakfast_name;
        String breakfast_description;
        String breakfast_price;
    }
    public class BreakfastAdapter extends BaseAdapter{
        private  BreakfastData[] breakfastData;
        private int view;
        public BreakfastAdapter(BreakfastData[] breakfastData,int view){
            this.breakfastData=breakfastData;
            this.view=view;
        }
        @Override
        public int getCount() {return breakfastData.length;}
        @Override
        public BreakfastData getItem(int position){return breakfastData[position];}
        @Override
        public long getItemId(int position){return 0;}
        @Override
        public View getView(int position , View rowView,ViewGroup parent){
            rowView=getLayoutInflater().inflate(view,parent,false);
            TextView textView_item_name=(TextView)rowView.findViewById(R.id.textView_item_name);
            TextView textView_item_description=(TextView)rowView.findViewById(R.id.textView_item_description);
            TextView textView_item_price=(TextView)rowView.findViewById(R.id.textView_item_price);

            textView_item_name.setText(breakfastData[position].breakfast_name);
            textView_item_description.setText(breakfastData[position].breakfast_description);
            textView_item_price.setText(breakfastData[position].breakfast_price);
            /*
            button_breakfast_buy.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v){
                   buy();
               }
           });   */
            return rowView;
        }

    }
    public void newBreakfast(){
        if(editText_breakfast_name.getText().toString().equals("") || editText_breakfast_price.getText().toString().equals("")||editText_breakfast_description.getText().toString().equals("")){
            Toast.makeText(this,"輸入資料不完全",Toast.LENGTH_SHORT).show();
        }else {
            String name=editText_breakfast_name.getText().toString();
            String description=editText_breakfast_description.getText().toString();
            double price=Double.parseDouble(editText_breakfast_price.getText().toString());


            ContentValues cv=new ContentValues();
            cv.put("breakfast_name",name);
            cv.put("breakfast_description",description);
            cv.put("breakfast_price",price);
            breakfast_db.insert("breakfastTable",null,cv);

            Toast.makeText(this,"新菜單:"+editText_breakfast_name.getText().toString()+"價格:"+price,Toast.LENGTH_SHORT).show();

            editText_breakfast_name.setText("");
            editText_breakfast_price.setText("");
            editText_breakfast_description.setText("");
        }


    }
    public void renewBreakfast(){
        if(editText_breakfast_name.getText().toString().equals("") || editText_breakfast_price.getText().toString().equals("")||editText_breakfast_description.getText().toString().equals("")){
            Toast.makeText(this,"沒有輸入更新資料",Toast.LENGTH_SHORT).show();
        }else {

            String newname=editText_breakfast_name.getText().toString();
            String newdescription=editText_breakfast_description.getText().toString();
            double newprice=Double.parseDouble(editText_breakfast_price.getText().toString());


            ContentValues cv=new ContentValues();

            cv.put("breakfast_name",newname);
            cv.put("breakfast_description",newdescription);
            cv.put("breakfast_price",newprice);

            breakfast_db.update("breakfastTable",cv,"breakfast_name="+"'"+editText_breakfast_name.getText().toString()+"'",null);

            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();

            editText_breakfast_name.setText("");
            editText_breakfast_price.setText("");
            editText_breakfast_description.setText("");
        }
    }
    public void deletBreakfast(){
        if(editText_breakfast_name.getText().toString().equals("") ){
            Toast.makeText(this,"請輸入要刪除的菜單名稱",Toast.LENGTH_SHORT).show();
        }else {
            breakfast_db.delete("breakfastTable","breakfast_name="+"'"+editText_breakfast_name.getText().toString()+"'",null);
            Toast.makeText(this,"刪除成功",Toast.LENGTH_SHORT).show();

            editText_breakfast_name.setText("");

        }
    }
    public void queryBreakfast(){
         /*
                 colum/商店名稱 / 商店地址/ 商店經緯度  / 商店連絡電話//

             db.execSQL("CREATE TABLE breakfastTable(_id integer primary key autoincrement,"+"breakfast_name text no null,"+"breakfast_address  text no null,"+"breakfast_address_x real no null,"+"breakfast_address_y real no null,"+"breakfast_address_xy Text no null,"+"breakfast_phone_number integer no null)");
*/


        Cursor c;
        if(editText_breakfast_name.getText().toString().equals(""))
            c=breakfast_db.query("breakfastTable",null,null,null,null,null,null,null);
        else
            c=breakfast_db.query("breakfastTable",null,"breakfast_name LIKE"+"'%"+editText_breakfast_name.getText().toString()+"%'",null,null,null,null);
        if(c.getCount()>0){
            c.moveToFirst();
            final BreakfastData[] breakfastData=new BreakfastData[c.getCount()];

            for(int ii=0;ii<breakfastData.length;ii++){
                breakfastData[ii]=new BreakfastData();
            }
            for (int i=0;i<breakfastData.length;i++){

                breakfastData[i].breakfast_name =c.getString(1);
                breakfastData[i].breakfast_description  =c.getString(2);
                breakfastData[i].breakfast_price=c.getString(3);

                c.moveToNext();
            }
            BreakfastAdapter breakfastAdapter=new BreakfastAdapter(breakfastData,R.layout.item_layout);
            ListView_breakfast=(ListView)findViewById(R.id.ListView_breakfast);

            ListView_breakfast.setAdapter(breakfastAdapter);

            Toast.makeText(this,"共有"+c.getCount()+"筆菜單",Toast.LENGTH_SHORT).show();

        }




    }



}
