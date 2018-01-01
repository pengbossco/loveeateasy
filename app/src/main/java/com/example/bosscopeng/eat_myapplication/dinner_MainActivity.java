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
public class dinner_MainActivity extends AppCompatActivity {
    EditText editText_dinner_name,editText_dinner_description,editText_dinner_price;
    Button button_dinner_add,button_dinner_search,button_dinner_edit,button_dinner_delet,button_back;
    ListView ListView_dinner;
    SQLiteDatabase dinner_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner__main);



        editText_dinner_name=(EditText)findViewById(R.id.editText_dinner_name);
        editText_dinner_description=(EditText)findViewById(R.id.editText_dinner_description);
        editText_dinner_price=(EditText)findViewById(R.id.editText_dinner_price);

        button_dinner_add=(Button)findViewById(R.id.button_dinner_add);
        button_dinner_edit=(Button)findViewById(R.id. button_dinner_edit);
        button_dinner_search=(Button)findViewById(R.id. button_dinner_search);
        button_dinner_delet=(Button)findViewById(R.id.button_dinner_delet);
        button_back=(Button)findViewById(R.id.button_back);


        eat_db dinnerdbhelper =new  eat_db(this);
        dinner_db =dinnerdbhelper.getWritableDatabase();

        button_dinner_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newDinner();
            }
        });
        button_dinner_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                renewDinner();
            }
        });
        button_dinner_delet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deletDinner();
            }
        });
        button_dinner_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                queryDinner();

            }
        });


        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });







    }

    class DinnerData{
        String dinner_name;
        String dinner_description;
        String dinner_price;
    }
    public class DinnerAdapter extends BaseAdapter{
        private  DinnerData[] dinnerData;
        private int view;
        public DinnerAdapter(DinnerData[] dinnerData,int view){
            this.dinnerData=dinnerData;
            this.view=view;
        }
        @Override
        public int getCount() {return dinnerData.length;}
        @Override
        public DinnerData getItem(int position){return dinnerData[position];}
        @Override
        public long getItemId(int position){return 0;}
        @Override
        public View getView(int position , View rowView,ViewGroup parent){
            rowView=getLayoutInflater().inflate(view,parent,false);
            TextView textView_item_name=(TextView)rowView.findViewById(R.id.textView_item_name);
            TextView textView_item_description=(TextView)rowView.findViewById(R.id.textView_item_description);
            TextView textView_item_price=(TextView)rowView.findViewById(R.id.textView_item_price);

            textView_item_name.setText(dinnerData[position].dinner_name);
            textView_item_description.setText(dinnerData[position].dinner_description);
            textView_item_price.setText(dinnerData[position].dinner_price);
            /*
            button_dinner_buy.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v){
                   buy();
               }
           });   */
            return rowView;
        }

    }
    public void newDinner(){
        if(editText_dinner_name.getText().toString().equals("") || editText_dinner_price.getText().toString().equals("")||editText_dinner_description.getText().toString().equals("")){
            Toast.makeText(this,"輸入資料不完全",Toast.LENGTH_SHORT).show();
        }else {
            String name=editText_dinner_name.getText().toString();
            String description=editText_dinner_description.getText().toString();
            double price=Double.parseDouble(editText_dinner_price.getText().toString());


            ContentValues cv=new ContentValues();
            cv.put("dinner_name",name);
            cv.put("dinner_description",description);
            cv.put("dinner_price",price);
            dinner_db.insert("dinnerTable",null,cv);

            Toast.makeText(this,"新菜單:"+editText_dinner_name.getText().toString()+"價格:"+price,Toast.LENGTH_SHORT).show();

            editText_dinner_name.setText("");
            editText_dinner_price.setText("");
            editText_dinner_description.setText("");
        }


    }
    public void renewDinner(){
        if(editText_dinner_name.getText().toString().equals("") || editText_dinner_price.getText().toString().equals("")||editText_dinner_description.getText().toString().equals("")){
            Toast.makeText(this,"沒有輸入更新資料",Toast.LENGTH_SHORT).show();
        }else {

            String newname=editText_dinner_name.getText().toString();
            String newdescription=editText_dinner_description.getText().toString();
            double newprice=Double.parseDouble(editText_dinner_price.getText().toString());


            ContentValues cv=new ContentValues();

            cv.put("dinner_name",newname);
            cv.put("dinner_description",newdescription);
            cv.put("dinner_price",newprice);

            dinner_db.update("dinnerTable",cv,"dinner_name="+"'"+editText_dinner_name.getText().toString()+"'",null);

            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();

            editText_dinner_name.setText("");
            editText_dinner_price.setText("");
            editText_dinner_description.setText("");
        }
    }
    public void deletDinner(){
        if(editText_dinner_name.getText().toString().equals("") ){
            Toast.makeText(this,"請輸入要刪除的菜單名稱",Toast.LENGTH_SHORT).show();
        }else {
            dinner_db.delete("dinnerTable","dinner_name="+"'"+editText_dinner_name.getText().toString()+"'",null);
            Toast.makeText(this,"刪除成功",Toast.LENGTH_SHORT).show();

            editText_dinner_name.setText("");

        }
    }
    public void queryDinner(){
         /*
                 colum/商店名稱 / 商店地址/ 商店經緯度  / 商店連絡電話//

             db.execSQL("CREATE TABLE dinnerTable(_id integer primary key autoincrement,"+"dinner_name text no null,"+"dinner_address  text no null,"+"dinner_address_x real no null,"+"dinner_address_y real no null,"+"dinner_address_xy Text no null,"+"dinner_phone_number integer no null)");
*/


        Cursor c;
        if(editText_dinner_name.getText().toString().equals(""))
            c=dinner_db.query("dinnerTable",null,null,null,null,null,null,null);
        else
            c=dinner_db.query("dinnerTable",null,"dinner_name LIKE"+"'%"+editText_dinner_name.getText().toString()+"%'",null,null,null,null);
        if(c.getCount()>0){
            c.moveToFirst();
            final DinnerData[] dinnerData=new DinnerData[c.getCount()];

            for(int ii=0;ii<dinnerData.length;ii++){
                dinnerData[ii]=new DinnerData();
            }
            for (int i=0;i<dinnerData.length;i++){

                dinnerData[i].dinner_name =c.getString(1);
                dinnerData[i].dinner_description  =c.getString(2);
                dinnerData[i].dinner_price=c.getString(3);

                c.moveToNext();
            }
            DinnerAdapter dinnerAdapter=new DinnerAdapter(dinnerData,R.layout.item_layout);
            ListView_dinner=(ListView)findViewById(R.id.ListView_dinner);

            ListView_dinner.setAdapter(dinnerAdapter);

            Toast.makeText(this,"共有"+c.getCount()+"筆菜單",Toast.LENGTH_SHORT).show();

        }




    }



}

