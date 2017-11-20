package app.mrobot.cn.drawabletest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2017/11/20.
 */

public class MessageListItem extends RelativeLayout {

    private static final int[] STATE_MESSAGE_READED = {R.attr.state_message_readed};
    private boolean mMessageReaded = false;

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setStateMessageReaded(boolean readed) {
        if (this.mMessageReaded != readed) {
            mMessageReaded = readed;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (mMessageReaded) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);
            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }
}
