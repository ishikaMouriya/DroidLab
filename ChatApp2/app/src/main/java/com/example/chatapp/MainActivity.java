
package com.example.chatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listvieww;
   EditText editText;
ImageButton sendbutton;


    private MessageAdapter messageAdapter;
  ArrayList<Map<String,String>> messageList=new ArrayList<>();
  private HashMap<String,String> predefinedMessages;
private ArrayList<Map<String,String>> predefinedarray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

     listvieww=findViewById(R.id.listView);
      editText=findViewById(R.id.editTextText);
      sendbutton=findViewById(R.id.imageButton);
        predefinedMessages = new HashMap<>();
        predefinedMessages.put("Hello", "Hi there!");
        //predefinedarray.add(predefinedMessages);

        predefinedMessages.put("What's your name?", "I am Ishika");
        predefinedMessages.put("where do you live?","I live in India");
        predefinedMessages.put("which language do you speak?","I speak Hindi and English");
        predefinedMessages.put("What do you do?", "I am a student");
        predefinedMessages.put("Which course are you persuing?", "I am doing btech in CSE");
        predefinedMessages.put("Goodbye", "Goodbye!");



        messageAdapter = new MessageAdapter(this, R.layout.chatitems,messageList,predefinedMessages);
        listvieww.setAdapter(messageAdapter);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage();
            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void sendMessage() {
        String messagetxt = editText.getText().toString().trim();
        if (!messagetxt.isEmpty()) {
            Map<String, String> messageMap = new HashMap<>();
            messageMap.put("message",messagetxt);
            messageList.add(messageMap);
            messageAdapter.notifyDataSetChanged();
            listvieww.setSelection(messageAdapter.getCount() - 1);
            editText.setText("");

            
            generateReply(messagetxt);
        }
    }


    private void generateReply(String messagetxt) {
        //Check if the message matches any predefined message
        String replyText=predefinedMessages.get(messagetxt);
        if(replyText!=null){
            Map<String,String> replyMap=new HashMap<>();
            if(predefinedMessages.containsKey(messagetxt)){
            replyMap.put(messagetxt,predefinedMessages.get((messagetxt)));
            }
            else
                replyMap.put(messagetxt,"Sorry ,I can't understand your question well");
            messageList.add(replyMap);


            //messageAdapter.notifyDataSetChanged();
            listvieww.setSelection(messageAdapter.getCount() - 1);
        }
    }}
