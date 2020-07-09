package com.example.studyplanner;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    public void setConfig(RecyclerView rv, Context cn, List<ActualData> bk, List<String> s){
        mContext=cn;
        mBooksAdapter=new BooksAdapter(bk,s);
        rv.setLayoutManager(new LinearLayoutManager(cn));
        rv.setAdapter(mBooksAdapter);
    }

    class BookItemView extends RecyclerView.ViewHolder {
        private TextView sub;
        private TextView rat;
        private TextView al;

        private String key;

        public BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false));

            sub=(TextView) itemView.findViewById(R.id.subj_txtView);
            rat=(TextView) itemView.findViewById(R.id.rat_txtView);
            al=(TextView) itemView.findViewById(R.id.algo_txtView);
        }
        public void bind(ActualData book, String key){
            sub.setText(book.Subject);
            rat.setText(book.Rating);
            al.setText(book.Algo);
            this.key=key;
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<ActualData> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<ActualData> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mBookList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}

