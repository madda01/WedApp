package com.example.dreamwedmadd.DecorationAdmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamwedmadd.MainActivity;
import com.example.dreamwedmadd.R;
import com.example.dreamwedmadd.database.DBDecorator;
import com.example.dreamwedmadd.models.Decorator;

import java.util.List;

public class AdminDecoView extends AppCompatActivity {

    Button btn;
    TextView textView;
    Context context;
    DecoAdaptor decoAdaptor;
    List<Decorator> decorators;
    DBDecorator dbDecorator;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_deco_view);
        btn=findViewById(R.id.btnDecoAdd);
        textView=findViewById(R.id.tvDecCount);
        listView=findViewById(R.id.DecoAdminView);
        context=this;
        dbDecorator=new DBDecorator(context);
        decorators=dbDecorator.getAllDeco();
        decoAdaptor=new DecoAdaptor(context,R.layout.decosingleraw,decorators);
        listView.setAdapter(decoAdaptor);
        textView.setText("Decorators "+dbDecorator.DecoCount());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Decorator decorator=decorators.get(i);
                String name=decorator.getfName();
                String des=decorator.getDescription();

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(name);
                builder.setMessage(des);

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(context,DeleteDeco.class);
                        intent.putExtra("idDel",decorator.getId());
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(context,EditDeco.class);
                        intent.putExtra("id",decorator.getId());
                        startActivity(intent);

                    }
                });

                builder.show();

            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(context,AddDeco.class));
            }
        });


    }
}




//
// listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        ToDo selectTodo=toDos.get(i);
//        String title=selectTodo.getTitle();
//        String description=selectTodo.getDescription();
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        builder.setTitle(title);
//        builder.setMessage(description);
//        builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        selectTodo.setFinished(System.currentTimeMillis());
//        dBhandler.UpdateSngleToDo(selectTodo);
//        startActivity(new Intent(context,MainActivity.class));
//        }
//        });
//
//        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        dBhandler.deleteToDo(selectTodo.getId());
//        startActivity(new Intent(context,MainActivity.class));
//        }
//        });
//        builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        Intent intent=new Intent(context,EditTODO.class);
//        intent.putExtra("id",String.valueOf(selectTodo.getId()));
//        startActivity(intent);
//        }
//        });
//        builder.show();
//
//        }
//        });