package com.example.cse.mostafijur.bookshop;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.cse.mostafijur.bookshop.bookdb.BookDataSource;

import java.util.List;

public class BookListActivity extends AppCompatActivity implements BookAdapter.itemActionListener {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private BookDataSource dataSource;
    private LinearLayoutManager llm;
    private GridLayoutManager glm;
    private List<Book>bookList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        dataSource = new BookDataSource(this);
        fab = findViewById(R.id.fab);
        bookList = dataSource.getAllBooks();
        recyclerView = findViewById(R.id.bookListRV);
        glm = new GridLayoutManager(this, 2);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new BookAdapter(this, bookList);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookListActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void onItemDelete(int rowId) {
            boolean status = dataSource.deleteBook(rowId);
            if (status){
                Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
                adapter.updteList(dataSource.getAllBooks());
            }else {
                Toast.makeText(this,"Failed to Deleted",Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onItemEdit(int rowId) {
        startActivity(new Intent(this,MainActivity.class).putExtra("id",rowId));
    }
}
