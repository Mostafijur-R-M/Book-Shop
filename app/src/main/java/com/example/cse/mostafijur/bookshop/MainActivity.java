package com.example.cse.mostafijur.bookshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cse.mostafijur.bookshop.bookdb.BookDataSource;

public class MainActivity extends AppCompatActivity {
    private EditText nameET, categroyET, priceET;
    private BookDataSource dataSource;
    private Button action_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.book_name_input);
        categroyET = findViewById(R.id.book_category_input);
        priceET = findViewById(R.id.book_price_input);
        action_btn = findViewById(R.id.action_btn);
        dataSource = new BookDataSource(this);


        final int rowId = getIntent().getIntExtra("id",0);
        action_btn.setTag(rowId);
        if (rowId > 0){
            Book book = dataSource.getBookById(rowId);

            if (book != null){
                nameET.setText(book.getName());
                categroyET.setText(book.getCategory());
                priceET.setText(String.valueOf(book.getPrice()));
                action_btn.setText(getString(R.string.update_btn));
            }
        }
    }

    public void saveBook(View view) {
        final int rowId = (int) action_btn.getTag();
        String name = nameET.getText().toString();
        String category = categroyET.getText().toString();
        String price = priceET.getText().toString();


        if (rowId > 0){
            Book book = new Book(rowId, name, category, Double.parseDouble(price));
            if (dataSource.updateBook(book)){
                Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,BookListActivity.class));

            }else {

                Toast.makeText(this,"Failed to update",Toast.LENGTH_SHORT).show();

            }

        }else {
            Book book = new Book(name, category, Double.parseDouble(price));
            boolean status = dataSource.insertNewBooks(book);
            if (status){
                Toast.makeText(this,"Saved Book",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, BookListActivity.class));
            }else {
                Toast.makeText(this,"Faild to Saved Book",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
