package com.example.bosscopeng.eat_myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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


public class EAT_MainActivity extends AppCompatActivity {
    EditText editText_breakfast_price_min,editText_breakfast_price_Max,editText_lunch_price_min,editText_lunch_price_Max,editText_dinner_price_min,editText_dinner_price_Max;
    Button button_breakfast_List_edit,button_lunch_List_edit,button_dinner_List_edit,button_random;
    ListView ListView_breakfast_random,ListView_lunch_random,ListView_dinner_random;
    TextView textView_cost;
    SQLiteDatabase breakfast_db,lunch_db,dinner_db;
    double pricesum=0,breakfastprice=0,lunchprice=0,dinnerprice=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat__main);

        editText_breakfast_price_min=(EditText)findViewById(R.id.editText_breakfast_price_min);
        editText_breakfast_price_Max=(EditText)findViewById(R.id.editText_breakfast_price_Max);
        editText_lunch_price_min=(EditText)findViewById(R.id.editText_lunch_price_min);
        editText_lunch_price_Max=(EditText)findViewById(R.id.editText_lunch_price_Max);
        editText_dinner_price_min=(EditText)findViewById(R.id.editText_dinner_price_min);
        editText_dinner_price_Max=(EditText)findViewById(R.id.editText_dinner_price_Max);

        button_breakfast_List_edit=(Button)findViewById(R.id.button_breakfast_List_edit);
        button_lunch_List_edit=(Button)findViewById(R.id.button_lunch_List_edit);
        button_dinner_List_edit=(Button)findViewById(R.id.button_dinner_List_edit);
        button_random=(Button)findViewById(R.id.button_random);

        textView_cost=(TextView)findViewById(R.id.textView_cost);

        eat_db breakfastdbhelper =new  eat_db(this);
        breakfast_db =breakfastdbhelper.getWritableDatabase();
        eat_db lunchdbhelper =new  eat_db(this);
        lunch_db =lunchdbhelper.getWritableDatabase();
        eat_db dinnerdbhelper =new  eat_db(this);
        dinner_db =dinnerdbhelper.getWritableDatabase();


        button_breakfast_List_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent();
                intent.setClass(EAT_MainActivity.this,breakfast_MainActivity.class);
                startActivityForResult(intent,888);
            }
        });
        button_lunch_List_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent();
                intent.setClass(EAT_MainActivity.this,lunch_MainActivity.class);
                startActivityForResult(intent,888);
            }
        });
        button_dinner_List_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent();
                intent.setClass(EAT_MainActivity.this,dinner_MainActivity.class);
                startActivityForResult(intent,888);
            }
        });

        button_random.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                eat_random();
            }
        });
    }
     class BreakfastData{
        String breakfast_name;
        String breakfast_description;
        String breakfast_price;
    }
    class RandomBreakfastData{
        String breakfast_name;
        String breakfast_description;
        String breakfast_price;
    }
    class LunchData{
        String lunch_name;
        String lunch_description;
        String lunch_price;
    }
    class RandomLunchData{
        String lunch_name;
        String lunch_description;
        String lunch_price;
    }
    class DinnerData{
        String dinner_name;
        String dinner_description;
        String dinner_price;
    }
    class RandomDinnerData{
        String dinner_name;
        String dinner_description;
        String dinner_price;
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



    public void eat_random(){

        Cursor c;

        if(editText_breakfast_price_min.getText().toString().equals("")){
            if(editText_breakfast_price_Max.getText().toString().equals("")){c=breakfast_db.query("breakfastTable",null,null,null,null,null,null,null);}
            else {
                double price_Max=Double.parseDouble(editText_breakfast_price_Max.getText().toString());
                c=breakfast_db.query("breakfastTable",null,"breakfast_price<="+"'"+price_Max+"'",null,null,null,null);
            }
        }
        else{
            if (editText_breakfast_price_Max.getText().toString().equals("")) {
                double price_min=Double.parseDouble(editText_breakfast_price_min.getText().toString());
                c=breakfast_db.query("breakfastTable",null,"breakfast_price>="+"'"+price_min+"'",null,null,null,null);

            }
            else{
                double price_min=Double.parseDouble(editText_breakfast_price_min.getText().toString());
                double price_Max=Double.parseDouble(editText_breakfast_price_Max.getText().toString());
                if (price_Max<price_min){
                    double translate=price_Max;
                    price_Max=price_min;
                    price_min=translate;
                    c=breakfast_db.query("breakfastTable",null,"breakfast_price>="+"'"+price_min+"'"+"AND breakfast_price<="+"'"+price_Max+"'",null,null,null,null);
                }
                else {
                    c = breakfast_db.query("breakfastTable", null, "breakfast_price>=" + "'" + price_min + "'" + "AND breakfast_price<=" + "'" + price_Max + "'", null, null, null, null);
                }
            }
        }


        if(c.getCount()>0){
            c.moveToFirst();
            final  BreakfastData[] breakfastData=new BreakfastData[1];
            final RandomBreakfastData[] randombreakfastData=new RandomBreakfastData[c.getCount()];

            for(int ii=0;ii<breakfastData.length;ii++){
                breakfastData[ii]=new BreakfastData();
            }
            for(int ii=0;ii<randombreakfastData.length;ii++){
                randombreakfastData[ii]=new RandomBreakfastData();
            }
            for (int i=0;i<randombreakfastData.length;i++){

                randombreakfastData[i].breakfast_name =c.getString(1);
                randombreakfastData[i].breakfast_description  =c.getString(2);
                randombreakfastData[i].breakfast_price=c.getString(3);

                c.moveToNext();
            }
            Random breakfast_num_random=new Random();
            int breakfast_num=breakfast_num_random.nextInt(randombreakfastData.length)+0;

                breakfastData[0].breakfast_name = randombreakfastData[breakfast_num].breakfast_name;
                breakfastData[0].breakfast_description = randombreakfastData[breakfast_num].breakfast_description;
                breakfastData[0].breakfast_price = randombreakfastData[breakfast_num].breakfast_price;
                breakfastprice= Double.parseDouble(breakfastData[0].breakfast_price) ;




            BreakfastAdapter breakfastAdapter=new BreakfastAdapter(breakfastData,R.layout.item_layout);
            ListView_breakfast_random=(ListView)findViewById(R.id.ListView_breakfast_random);

            ListView_breakfast_random.setAdapter(breakfastAdapter);


        }

        Cursor c2;
        if(editText_lunch_price_min.getText().toString().equals("")){
            if(editText_lunch_price_Max.getText().toString().equals("")){c2=lunch_db.query("lunchTable",null,null,null,null,null,null,null);}
            else {
                double price_Max=Double.parseDouble(editText_lunch_price_Max.getText().toString());
                c2=lunch_db.query("lunchTable",null,"lunch_price<="+"'"+price_Max+"'",null,null,null,null);
            }
        }
        else{
            if (editText_lunch_price_Max.getText().toString().equals("")) {
                double price_min=Double.parseDouble(editText_lunch_price_min.getText().toString());
                c2=lunch_db.query("lunchTable",null,"lunch_price>="+"'"+price_min+"'",null,null,null,null);

            }
            else{
                double price_min=Double.parseDouble(editText_lunch_price_min.getText().toString());
                double price_Max=Double.parseDouble(editText_lunch_price_Max.getText().toString());
                if (price_Max<price_min){
                    double translate=price_Max;
                    price_Max=price_min;
                    price_min=translate;
                    c2=lunch_db.query("lunchTable",null,"lunch_price>="+"'"+price_min+"'"+"AND lunch_price<="+"'"+price_Max+"'",null,null,null,null);
                }
                else {
                    c2 = lunch_db.query("lunchTable", null, "lunch_price>=" + "'" + price_min + "'" + "AND lunch_price<=" + "'" + price_Max + "'", null, null, null, null);
                }
            }
        }

        if(c2.getCount()>0){
            c2.moveToFirst();
            final LunchData[] lunchData=new LunchData[1];
            final RandomLunchData[] randomlunchData=new RandomLunchData[c2.getCount()];

            for(int ii=0;ii<lunchData.length;ii++){
                lunchData[ii]=new LunchData();
            }
            for(int ii=0;ii<randomlunchData.length;ii++){
                randomlunchData[ii]=new RandomLunchData();
            }
            for (int i=0;i<randomlunchData.length;i++){

                randomlunchData[i].lunch_name =c2.getString(1);
                randomlunchData[i].lunch_description  =c2.getString(2);
                randomlunchData[i].lunch_price=c2.getString(3);

                c2.moveToNext();
            }
            Random lunch_num_random=new Random();
            int lunch_num=lunch_num_random.nextInt(randomlunchData.length)+0;

                lunchData[0].lunch_name = randomlunchData[lunch_num].lunch_name;
                lunchData[0].lunch_description = randomlunchData[lunch_num].lunch_description;
                lunchData[0].lunch_price = randomlunchData[lunch_num].lunch_price;
                lunchprice=Double.parseDouble(lunchData[0].lunch_price);



            LunchAdapter lunchAdapter=new LunchAdapter(lunchData,R.layout.item_layout);
            ListView_lunch_random=(ListView)findViewById(R.id.ListView_lunch_random);

            ListView_lunch_random.setAdapter(lunchAdapter);


        }
        Cursor c3;
        if(editText_dinner_price_min.getText().toString().equals("")){
            if(editText_dinner_price_Max.getText().toString().equals("")){c3=dinner_db.query("dinnerTable",null,null,null,null,null,null,null);}
            else {
                double price_Max=Double.parseDouble(editText_dinner_price_Max.getText().toString());
                c3=dinner_db.query("dinnerTable",null,"dinner_price<="+"'"+price_Max+"'",null,null,null,null);
            }
        }
        else{
            if (editText_dinner_price_Max.getText().toString().equals("")) {
                double price_min=Double.parseDouble(editText_dinner_price_min.getText().toString());
                c3=dinner_db.query("dinnerTable",null,"dinner_price>="+"'"+price_min+"'",null,null,null,null);

            }
            else{
                double price_min=Double.parseDouble(editText_dinner_price_min.getText().toString());
                double price_Max=Double.parseDouble(editText_dinner_price_Max.getText().toString());
                if (price_Max<price_min){
                    double translate=price_Max;
                    price_Max=price_min;
                    price_min=translate;
                    c3=dinner_db.query("dinnerTable",null,"dinner_price>="+"'"+price_min+"'"+"AND dinner_price<="+"'"+price_Max+"'",null,null,null,null);
                }
                else {
                    c3 = dinner_db.query("dinnerTable", null, "dinner_price>=" + "'" + price_min + "'" + "AND dinner_price<=" + "'" + price_Max + "'", null, null, null, null);
                }
            }
        }

        if(c3.getCount()>0){
            c3.moveToFirst();
            final DinnerData[] dinnerData=new DinnerData[1];
            final RandomDinnerData[] randomdinnerData=new RandomDinnerData[c3.getCount()];

            for(int ii=0;ii<dinnerData.length;ii++){
                dinnerData[ii]=new DinnerData();
            }
            for(int ii=0;ii<randomdinnerData.length;ii++){
                randomdinnerData[ii]=new RandomDinnerData();
            }
            for (int i=0;i<randomdinnerData.length;i++){

                randomdinnerData[i].dinner_name =c3.getString(1);
                randomdinnerData[i].dinner_description  =c3.getString(2);
                randomdinnerData[i].dinner_price=c3.getString(3);

                c3.moveToNext();
            }
            Random dinner_num_random=new Random();
            int dinner_num=dinner_num_random.nextInt(randomdinnerData.length)+0;

                dinnerData[0].dinner_name = randomdinnerData[dinner_num].dinner_name;
                dinnerData[0].dinner_description = randomdinnerData[dinner_num].dinner_description;
                dinnerData[0].dinner_price = randomdinnerData[dinner_num].dinner_price;
                dinnerprice=Double.parseDouble(dinnerData[0].dinner_price);



            DinnerAdapter dinnerAdapter=new DinnerAdapter(dinnerData,R.layout.item_layout);
            ListView_dinner_random=(ListView)findViewById(R.id.ListView_dinner_random);

            ListView_dinner_random.setAdapter(dinnerAdapter);


        }
        pricesum=breakfastprice+lunchprice+dinnerprice;
        String sum=Double.toString(pricesum);
        textView_cost.setText(sum);
    }
}
