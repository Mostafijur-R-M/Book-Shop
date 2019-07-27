package com.example.cse.mostafijur.bookshop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private Context context;
    private List<Book> bookList;
    private itemActionListener listener;


    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        listener = (itemActionListener) context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.book_row, viewGroup, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, final int i) {
        bookViewHolder.nameTV.setText(bookList.get(i).getName());
        bookViewHolder.categoryTV.setText(bookList.get(i).getCategory());
        bookViewHolder.infoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.book_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        final int rowId = bookList.get(i).getBookId();
                        switch (item.getItemId()){

                            case R.id.book_edit:
                                listener.onItemEdit(rowId);
                                break;
                            case R.id.book_delete:
                                Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();

                                listener.onItemDelete(rowId);
                                break;
                            case R.id.book_details:
                                Toast.makeText(context,bookList.get(i).getName(),Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        bookViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,bookList.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookIV;
        TextView nameTV, categoryTV, infoTV;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIV = itemView.findViewById(R.id.row_book_img);
            nameTV = itemView.findViewById(R.id.row_book_name);
            categoryTV = itemView.findViewById(R.id.row_book_category);
            infoTV = itemView.findViewById(R.id.row_book_info);
        }
    }
    public void updteList(List<Book> books){
        this.bookList = books;
        notifyDataSetChanged();
    }
    interface itemActionListener {
        void onItemDelete(int rowId);
        void onItemEdit(int rowId);
    }
}
