package app.mrobot.cn.drawabletest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    private Message[] messages = new Message[]{
            new Message("Gas bill overdue", true),
            new Message("Congratulations, you've won!", true),
            new Message("I love you!", false),
            new Message("Please reply!", false),
            new Message("You ignoring me?", false),
            new Message("Not heard from you", false),
            new Message("Electricity bill", true),
            new Message("Gas bill", true),
            new Message("Holiday plans", false),
            new Message("Marketing stuff", false),};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setAdapter(new ArrayAdapter<Message>(this, -1, messages) {
            private LayoutInflater mInflater = LayoutInflater.from(getContext());

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_msg_list, parent, false);
                }
                MessageListItem messageListItem = (MessageListItem) convertView;
                TextView tv = (TextView) convertView.findViewById(R.id.id_msg_item_text);
                tv.setText(getItem(position).subject);
                messageListItem.setStateMessageReaded(getItem(position).readed);
                return convertView;
            }

        });

    }

    private static class Message {

        private String subject;
        private boolean readed;

        private Message(String subject, boolean readed) {
            this.subject = subject;
            this.readed = readed;
        }

    }
}
