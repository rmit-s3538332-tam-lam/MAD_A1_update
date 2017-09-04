package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Tam on 4/9/17.
 */

public class popActListener implements View.OnClickListener {
    Context context;
    Intent intent;
    public popActListener(Context context, Class activity){
        this.context = context;
        intent = new Intent(context,activity);
    }
    @Override
    public void onClick(View view) {
        context.startActivity(intent);
    }
}