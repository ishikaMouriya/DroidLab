package com.example.chatapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MessageAdapter extends ArrayAdapter<Map<String,String>> {

    private ArrayList<Map<String,String>> messageList;
    private Context context;
   HashMap<String,String> replies;

    public MessageAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Map<String,String>> objects,HashMap<String,String> object2) {

        super(context, resource,objects);
        this.context=context;
        this.messageList=objects;
        this.replies= (HashMap<String, String>) object2;

    }



    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;



        if (view == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           LayoutInflater inflater=LayoutInflater.from(context);
            view = inflater.inflate(R.layout.chatitems, parent, false);
           holder=new ViewHolder();
            holder.textViewName = view.findViewById(R.id.textView6);
            holder.textViewText = view.findViewById(R.id.textViewleft);
             holder.imageViewLeft = view.findViewById(R.id.imageView5);
             holder.textViewTextRight = view.findViewById(R.id.textViewright);
             view.setTag(holder);
             }else{
            holder=(ViewHolder)view.getTag();
        }
        Map<String, String> message = messageList.get(position);

        String leftreply = "";

        String sentright="";

        if (message!=null) {

            if (message.containsKey("message") ) {

                sentright = message.get("message");
                leftreply = getReply(sentright);
            }




                holder.textViewName.setText("@miniflower");
            holder.imageViewLeft.setVisibility(View.VISIBLE);
            holder.textViewName.setVisibility(View.VISIBLE);
            if(!leftreply.isEmpty()){
                holder.textViewText.setText(leftreply);
                holder.textViewText.setVisibility(View.VISIBLE);
            }else{

                holder.textViewText.setVisibility(View.GONE);
                holder.imageViewLeft.setVisibility(View.GONE);
                holder.textViewName.setVisibility(View.GONE);
                //holder.textViewTextRight.setVisibility(View.GONE);

            }
            if(!sentright.isEmpty()) {
                holder.textViewTextRight.setText(sentright);
                holder.textViewTextRight.setVisibility(View.VISIBLE);
            }
            else{

                holder.textViewTextRight.setVisibility(View.GONE);

            }


            }


       return view ;
    }

    private String getReply(String message){
int f=0;
        for(String i:replies.keySet()){
            f=0;
            if(i.equalsIgnoreCase(message)){
                  f=1;
                return replies.get(i);
            }
            if(f==1)
                break;;
        }
        return "Sorry,I couldn't understand";
    }

       static class  ViewHolder{
           TextView textViewName;
           TextView textViewText;
           ImageView imageViewLeft;
           TextView textViewTextRight;
       }



}



