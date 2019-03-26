package com.example.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // create a List and define its generic type as ListItem class, as we want only ListItem class objects to be displayed...
    private List<ListItem> listItemList;
    // also create a Context object
    private Context context;

    public MyAdapter(List<ListItem> listItemList, Context context) {
        this.listItemList = listItemList;
        this.context = context;
    }

    /* This method is called when a ViewHolder is created i.e.
         when an instance of ViewHolder class is created */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // create a View using LayoutInflater to view our customized layout i.e. list_item.xml in RecyclerView
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    /* This method binds the actual data to our recycler view and
     it is executed after onCreateViewHolder method */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // using List listItemList object we are getting the specific position of listItems
        final ListItem listItem = listItemList.get(position);

        // now using ViewHolder object 'holder' we can get the data for both TextViews
        holder.textViewHead.setText(listItem.getHeading());
        holder.textViewDesc.setText(listItem.getDescription());

        // using Picasso library we can display the image in ImageView
        Picasso.get().load(listItem.getImageUrl()).into(holder.imageView);

        // set an onCLickListener on each listItem of the List
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "You clicked list item number " + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // define both the TextViews for ViewHolder
        private TextView textViewHead, textViewDesc;
        private ImageView imageView;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialize both the TextViews...
            textViewHead = itemView.findViewById(R.id.textViewHeading);
            textViewDesc = itemView.findViewById(R.id.textViewDescription);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
