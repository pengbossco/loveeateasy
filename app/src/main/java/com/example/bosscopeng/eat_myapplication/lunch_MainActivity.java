package com.example.bosscopeng.eat_myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
public class lunch_MainActivity extends AppCompatActivity {
    EditText editText_lunch_name,editText_lunch_description,editText_lunch_price;
    Button button_lunch_add,button_lunch_search,button_lunch_edit,button_lunch_delet,button_back;
    ListView ListView_lunch;
    SQLiteDatabase lunch_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch__main);



        editText_lunch_name=(EditText)findViewById(R.id.editText_lunch_name);
        editText_lunch_description=(EditText)findViewById(R.id.editText_lunch_description);
        editText_lunch_price=(EditText)findViewById(R.id.editText_lunch_price);

        button_lunch_add=(Button)findViewById(R.id.button_lunch_add);
        button_lunch_edit=(Button)findViewById(R.id. button_lunch_edit);
        button_lunch_search=(Button)findViewById(R.id. button_lunch_search);
        button_lunch_delet=(Button)findViewById(R.id.button_lunch_delet);
        button_back=(Button)findViewById(R.id.button_back);


        eat_db lunchdbhelper =new  eat_db(this);
        lunch_db =lunchdbhelper.getWritableDatabase();

        button_lunch_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newLunch();
            }
        });
        button_lunch_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                renewLunch();
            }
        });
        button_lunch_delet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deletLunch();
            }
        });
        button_lunch_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                queryLunch();

            }
        });


        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });







    }

    class LunchData{
        String lunch_name;
        String lunch_description;
        String lunch_price;
    }
    public class LunchAdapter extends BaseAdapter{
        private  LunchData[] lunchData;
        private int view;
        public LunchAdapter(LunchData[] lunchData,int view){
            this.lunchData=lunchData;
            this.view=view;
        }
        @Override
        public int getCount() {return lunchData.length;}
        @Override
        public LunchData getItem(int position){return lunchData[position];}
        @Override
        public long getItemId(int position){return 0;}
        @Override
        public View getView(int position , View rowView,ViewGroup parent){
            rowView=getLayoutInflater().inflate(view,parent,false);
            TextView textView_item_name=(TextView)rowView.findViewById(R.id.textView_item_name);
            TextView textView_item_description=(TextView)rowView.findViewById(R.id.textView_item_description);
            TextView textView_item_price=(TextView)rowView.findViewById(R.id.textView_item_price);

            textView_item_name.setText(lunchData[position].lunch_name);
            textView_item_description.setText(lunchData[position].lunch_description);
            textView_item_price.setText(lunchData[position].lunch_price);
            /*
            button_lunch_buy.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v){
                   buy();
               }
           });   */
            return rowView;
        }

    }
    public void newLunch(){
        if(editText_lunch_name.getText().toString().equals("") || editText_lunch_price.getText().toString().equals("")||editText_lunch_description.getText().toString().equals("")){
            Toast.makeText(this,"輸入資料不完全",Toast.LENGTH_SHORT).show();
        }else {
            String name=editText_lunch_name.getText().toString();
            String description=editText_lunch_description.getText().toString();
            double price=Double.parseDouble(editText_lunch_price.getText().toString());


            ContentValues cv=new ContentValues();
            cv.put("lunch_name",name);
            cv.put("lunch_description",description);
            cv.put("lunch_price",price);
            lunch_db.insert("lunchTable",null,cv);

            Toast.makeText(this,"新菜單:"+editText_lunch_name.getText().toString()+"價格:"+price,Toast.LENGTH_SHORT).show();

            editText_lunch_name.setText("");
            editText_lunch_price.setText("");
            editText_lunch_description.setText("");
        }


    }
    public void renewLunch(){
        if(editText_lunch_name.getText().toString().equals("") || editText_lunch_price.getText().toString().equals("")||editText_lunch_description.getText().toString().equals("")){
            Toast.makeText(this,"沒有輸入更新資料",Toast.LENGTH_SHORT).show();
        }else {

            String newname=editText_lunch_name.getText().toString();
            String newdescription=editText_lunch_description.getText().toString();
            double newprice=Double.parseDouble(editText_lunch_price.getText().toString());


            ContentValues cv=new ContentValues();

            cv.put("lunch_name",newname);
            cv.put("lunch_description",newdescription);
            cv.put("lunch_price",newprice);

            lunch_db.update("lunchTable",cv,"lunch_name="+"'"+editText_lunch_name.getText().toString()+"'",null);

            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();

            editText_lunch_name.setText("");
            editText_lunch_price.setText("");
            editText_lunch_description.setText("");
        }
    }
    public void deletLunch(){
        if(editText_lunch_name.getText().toString().equals("") ){
            Toast.makeText(this,"請輸入要刪除的菜單名稱",Toast.LENGTH_SHORT).show();
        }else {
            lunch_db.delete("lunchTable","lunch_name="+"'"+editText_lunch_name.getText().toString()+"'",null);
            Toast.makeText(this,"刪除成功",Toast.LENGTH_SHORT).show();

            editText_lunch_name.setText("");

        }
    }
    public void queryLunch(){
         /*
                 colum/商店名稱 / 商店地址/ 商店經緯度  / 商店連絡電話//

             db.execSQL("CREATE TABLE lunchTable(_id integer primary key autoincrement,"+"lunch_name text no null,"+"lunch_address  text no null,"+"lunch_address_x real no null,"+"lunch_address_y real no null,"+"lunch_address_xy Text no null,"+"lunch_phone_number integer no null)");
*/


        Cursor c;
        if(editText_lunch_name.getText().toString().equals(""))
            c=lunch_db.query("lunchTable",null,null,null,null,null,null,null);
        else
            c=lunch_db.query("lunchTable",null,"lunch_name LIKE"+"'%"+editText_lunch_name.getText().toString()+"%'",null,null,null,null);
        if(c.getCount()>0){
            c.moveToFirst();
            final LunchData[] lunchData=new LunchData[c.getCount()];

            for(int ii=0;ii<lunchData.length;ii++){
                lunchData[ii]=new LunchData();
            }
            for (int i=0;i<lunchData.length;i++){

                lunchData[i].lunch_name =c.getString(1);
                lunchData[i].lunch_description  =c.getString(2);
                lunchData[i].lunch_price=c.getString(3);

                c.moveToNext();
            }
            LunchAdapter lunchAdapter=new LunchAdapter(lunchData,R.layout.item_layout);
            ListView_lunch=(ListView)findViewById(R.id.ListView_lunch);

            ListView_lunch.setAdapter(lunchAdapter);

            Toast.makeText(this,"共有"+c.getCount()+"筆菜單",Toast.LENGTH_SHORT).show();

        }




    }



}

